package com.softdev.system.generator.config;


// import com.alibaba.fastjson.support.config.FastJsonConfig;
// import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
*  2019-2-11 liutf WebMvcConfig 整合 cors 和 SpringMvc MessageConverter
*/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${code.generator.file.location}")
    private String fileLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        registry.addResourceHandler("/download/**").addResourceLocations("file:" + fileLocation);

        // 添加新的静态资源路径
//        registry.addResourceHandler("/code/generator/**").addResourceLocations("classpath:/code/generator/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }

//    @Bean
//    public FilterRegistrationBean xssFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setDispatcherTypes(DispatcherType.REQUEST);
//        registration.setFilter(new XssFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("xssFilter");
//        registration.setOrder(Integer.MAX_VALUE);
//        return registration;
//    }

    // @Override
    // public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //     converters.clear();
    //     //FastJsonHttpMessageConverter
    //     FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

    //     List<MediaType> fastMediaTypes = new ArrayList<>();
    //     fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
    //     fastConverter.setSupportedMediaTypes(fastMediaTypes);

    //     FastJsonConfig fastJsonConfig = new FastJsonConfig();
    //     fastJsonConfig.setCharset(StandardCharsets.UTF_8);
    //     fastConverter.setFastJsonConfig(fastJsonConfig);

    //     //StringHttpMessageConverter
    //     StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
    //     stringConverter.setDefaultCharset(StandardCharsets.UTF_8);
    //     stringConverter.setSupportedMediaTypes(fastMediaTypes);
    //     converters.add(stringConverter);
    //     converters.add(fastConverter);
    // }
    /**
     * FASTJSON2升级 by https://zhengkai.blog.csdn.net/
     * https://blog.csdn.net/moshowgame/article/details/138013669
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //自定义配置...
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
        config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.PrettyFormat);
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(0, converter);
    }

}
