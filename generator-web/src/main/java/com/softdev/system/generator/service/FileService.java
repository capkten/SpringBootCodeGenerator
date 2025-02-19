package com.softdev.system.generator.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson2.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileService {

    @Value("${code.generator.file.location}")
    private String fileLocation;

    @Autowired
    private GeneratorService generatorService;

    public String saveFile(List<Map<String, String>> results) throws IOException {
        String projectName = System.currentTimeMillis() + "";
        String projectPath = FileUtil.file(fileLocation, projectName).getAbsolutePath();
        FileUtil.mkdir(projectPath);
        String templates = generatorService.getTemplateConfig();
        JSONArray templateList = JSONArray.parseArray(templates);
        for (Object o : templateList) {
            Map<String, Object> type = (Map<String, Object>) o;
            String group = (String) type.get("group");
            String groupPath = FileUtil.file(projectPath, group).getAbsolutePath();
            if (!FileUtil.exist(groupPath)) {
                FileUtil.mkdir(groupPath);
            }
            List<Map<String, String>> groupTemplates = (List<Map<String, String>>) type.get("templates");
            for (Map<String, String> groupTemplate : groupTemplates) {
                String name = groupTemplate.get("name");
                String filePath = FileUtil.file(groupPath, name).getAbsolutePath();
                String fileExt = null;
                if (name.contains("bootstrap") || name.contains("bootstrap-ui")) {
                    fileExt = ".html";
                } else if (name.contains("controller") || name.contains("jpacontroller") || name.contains("pluscontroller") || name.contains("beetlcontroller") || name.contains("rr-controller") || name.contains("starp-jpa-controller") || name.contains("tk-controller")) {
                    fileExt = ".java";
                } else if (name.contains("dao") || name.contains("mapper") || name.contains("mapper2") || name.contains("tkmapper") || name.contains("plusmapper") || name.contains("rr-dao") || name.contains("rr-daoxml") || name.contains("tk-mapper")) {
                    fileExt = ".java";
                } else if (name.contains("js")) {
                    fileExt = ".js";
                } else if (name.contains("mybatis") || name.contains("mybatis2") || name.contains("rr-daoxml") || name.contains("tkmapper")) {
                    fileExt = ".xml";
                } else if (name.contains("model") || name.contains("entity") || name.contains("beetlentity") || name.contains("tkentity") || name.contains("plusentity")) {
                    fileExt = ".java";
                } else if (name.contains("service") || name.contains("plusservice") || name.contains("rr-service")) {
                    fileExt = ".java";
                } else if (name.contains("repository") || name.contains("starp-repository")) {
                    fileExt = ".java";
                } else if (name.contains("beanutil") || name.contains("json") || name.contains("xml") || name.contains("sql") || name.contains("swagger-yml")) {
                    fileExt = ".java"; // 假设这些是Java工具类
                } else if (name.contains("menu-sql")) {
                    fileExt = ".sql";
                } else if (name.contains("qliksense")) {
                    fileExt = ".json"; // 假设Qlik Sense配置文件是JSON格式
                } else if (name.contains("bigquery") || name.contains("dataflowjjs")) {
                    fileExt = ".sql"; // 假设BigQuery和Dataflow JJS使用SQL脚本
                } else if (name.contains("swagger-ui") || name.contains("layui-edit")|| name.contains("layui-list")) {
                    fileExt = ".html"; // 假设这些UI模板是HTML文件
                } else if (name.contains("element-ui")  || name.contains("vue-list") || name.contains("vue-edit")) {
                    fileExt = ".vue";
                } else {
                    fileExt = ".java"; // 默认情况下，假设是Java文件
                }
                for (Map<String, String> result : results) {
                    String content = result.get(name);
                    String fileName = null;
                    if (fileExt.equals(".java")) {
                        fileName = extractClassName(content) + fileExt;
                    }
                    if (fileName == null) {
                        fileName = toCamelCase(result.get("tableName")) + fileExt;
                    }
                    String actFilePath = FileUtil.file(filePath, fileName).getAbsolutePath();
                    if (FileUtil.exist(actFilePath)) {
                        FileUtil.del(actFilePath);
                    }
                    FileUtil.appendString(content, new File(actFilePath), "utf-8");
                }
            }
        }
        ZipUtil.zip(projectPath, projectPath + ".zip");
        return "http://localhost:9090/generator/download/" + projectName + ".zip";
    }

    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder camelCaseString = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c) || c == '-' || c == '_') {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                camelCaseString.append(Character.toTitleCase(c));
                nextTitleCase = false;
            } else {
                camelCaseString.append(Character.toLowerCase(c));
            }
        }

        return camelCaseString.toString();
    }

    public static String extractClassName(String javaCode) {
        // 正则表达式匹配类声明
        String classPattern = "public\\s+class\\s+(\\w+)";
        // 正则表达式匹配接口声明
        String interfacePattern = "public\\s+interface\\s+(\\w+)";

        Pattern classRegex = Pattern.compile(classPattern);
        Pattern interfaceRegex = Pattern.compile(interfacePattern);

        Matcher classMatcher = classRegex.matcher(javaCode);
        Matcher interfaceMatcher = interfaceRegex.matcher(javaCode);

        if (classMatcher.find()) {
            return classMatcher.group(1);
        } else if (interfaceMatcher.find()) {
            return interfaceMatcher.group(1);
        } else {
            return null; // 如果没有找到类或接口声明，返回null
        }
    }

}

