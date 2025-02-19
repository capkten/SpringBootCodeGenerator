<template>
  <div class="code-generator">
    <!-- Header Section -->
    <header class="header">
      <div class="logo">
        <img src="../assets/logo.svg" alt="Logo" class="logo-img" />
        <h1>JAVA代码生成平台</h1>
      </div>
      <p class="subtitle">Release your hands from tedious and repetitive CRUD tasks.</p>
      <div class="header-actions">
        <a href="#" class="action-link">Raise Issue</a>
        <a href="#" class="action-link">Github</a>
      </div>
    </header>

    <!-- Main Content -->
    <main class="main-content">
      <!-- SQL Input Section -->
      <section class="sql-input-section">
        <div class="section-header">
          <h2>输入SQL</h2>
          <button class="clear-btn" @click="clearForm">清空</button>
        </div>
        <div class="code-editor">
          <SqlEditor v-model="sqlInput" />
        </div>
      </section>

      <!-- Configuration Section -->
      <section class="config-section">
        <h2>生成设置</h2>
        <div class="config-grid">
          <div class="config-item">
            <label>作者</label>
            <input type="text" v-model="config.author" placeholder="Zhengkai.blog.csdn.net" />
          </div>
          <div class="config-item">
            <label>包名</label>
            <input type="text" v-model="config.packageName" placeholder="com.software.system" />
          </div>
          <div class="config-item">
            <label>返回(成功)</label>
            <input type="text" v-model="config.successReturn" placeholder="ReturnT.success" />
          </div>
          <div class="config-item">
            <label>返回(失败)</label>
            <input type="text" v-model="config.errorReturn" placeholder="ReturnT.error" />
          </div>
          <div class="config-item">
            <label>命名前缀</label>
            <input type="text" v-model="config.prefix" placeholder="sys_" />
          </div>
          <div class="config-item">
            <label>输入类型</label>
            <select v-model="config.inputType">
              <option value="ddl">DDL SQL</option>
              <option value="select">Select SQL</option>
            </select>
          </div>
          <div class="config-item">
            <label>TinyInt转换</label>
            <select v-model="config.tinyIntConversion">
              <option value="int">int</option>
              <option value="boolean">boolean</option>
            </select>
          </div>
          <div class="config-item">
            <label>时间类型</label>
            <select v-model="config.timeType">
              <option value="date">Date</option>
              <option value="localDateTime">LocalDateTime</option>
            </select>
          </div>
          <div class="config-item">
            <label>命名类型</label>
            <select v-model="config.namingType">
              <option value="camel">驼峰</option>
              <option value="underscore">下划线</option>
            </select>
          </div>
        </div>

        <!-- Switch Options -->
        <div class="switch-options">
          <div class="switch-item">
            <span>包装类型</span>
            <label class="switch">
              <input type="checkbox" v-model="switches.swaggerUI">
              <span class="slider">swaggerUI</span>
            </label>
          </div>
          <div class="switch-item">
            <span>字段注释</span>
            <label class="switch">
              <input type="checkbox" v-model="switches.stringTrim">
              <span class="slider">字符串trim</span>
            </label>
          </div>
          <div class="switch-item">
            <span>自动引包</span>
            <label class="switch">
              <input type="checkbox" v-model="switches.autoImport">
              <span class="slider">自动引包</span>
            </label>
          </div>
          <div class="switch-item">
            <span>带包路径</span>
            <label class="switch">
              <input type="checkbox" v-model="switches.withComments">
              <span class="slider">带包路径</span>
            </label>
          </div>
          <div class="switch-item">
            <span>Lombok</span>
            <label class="switch">
              <input type="checkbox" v-model="switches.lombok">
              <span class="slider">Lombok</span>
            </label>
          </div>
        </div>

        <!-- Move Action Buttons here -->
        <div class="action-buttons">
          <button class="primary-btn" @click="handleGenerateCode" :disabled="isGenerating">
            <i class="icon">▶</i> {{ isGenerating ? '生成中...' : '生成' }}
          </button>
          <button class="secondary-btn" @click="clearForm">
            <i class="icon">⟳</i> 重置
          </button>
        </div>
      </section>

      <!-- History Section -->
      <section class="history-section" v-if="historyRecords.length > 0">
        <div class="history-header">
          <h3>历史记录</h3>
        </div>
        <div class="history-list">
          <button v-for="record in historyRecords" :key="record.timestamp"
            :class="['history-btn', { active: currentHistory?.timestamp === record.timestamp }]"
            @click="switchTable(record)">
            <span class="table-name">{{ record.tableName }}</span>
            <span class="timestamp">{{ formatTime(record.timestamp) }}</span>
          </button>
        </div>
      </section>

      <!-- Template Selection -->
      <section class="template-section">
        <h2>模板选择</h2>
        <div class="template-categories">
          <div v-for="group in templates" :key="group.group" class="template-category">
            <h3>{{ group.group }}</h3>
            <div class="template-items">
              <el-button v-for="template in group.templates" :key="template.id" @click="toggleTemplate(template.name)"
                :type="currentTemplate.includes(template.name) ? 'primary' : 'default'">
                {{ template.name }}
              </el-button>
            </div>
          </div>
        </div>
      </section>

      <!-- Code Output Section -->
      <section class="code-output-section">
        <h2>输出代码</h2>
        <div class="code-output">
          <CodePreview v-if="currentTemplateCode" :code="currentTemplateCode"
            :defaultLanguage="getLanguageForTemplate(currentTemplate.value)" />
          <div v-else class="empty-state">
            请选择要查看的模板
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted, nextTick } from 'vue';
import SqlEditor from '../components/SqlEditor.vue';
import CodePreview from '../components/CodePreview.vue';
import { generateCode, getAllTemplates } from '../api/generator';

// State
const sqlInput = ref('');
const config = reactive({
  author: 'Zhengkai.blog.csdn.net',
  packageName: 'com.software.system',
  successReturn: 'ReturnT.success',
  errorReturn: 'ReturnT.error',
  prefix: '',
  inputType: 'ddl',
  tinyIntConversion: 'int',
  timeType: 'date',
  namingType: 'camel'
});

const switches = reactive({
  swaggerUI: false,
  stringTrim: false,
  autoImport: false,
  withComments: false,
  lombok: false
});

const templates = ref([]);
const selectedTemplateKeys = ref(new Set());
const isGenerating = ref(false);
const currentHistory = ref(null);
const currentTemplate = ref([]);
const currentTemplateCode = ref('');
const generatedResults = ref(null);
const historyRecords = ref([]);
const templateMap = ref({});
const codes = ref([]);
const currentCode = ref(null);

// Computed
const selectedTemplates = computed(() => Array.from(selectedTemplateKeys.value));

// Methods
const fetchTemplates = async () => {
  try {
    const res = await getAllTemplates();
    templates.value = res.templates;
    // Create template mapping
    templateMap.value = templates.value.reduce((acc, group) => {
      group.templates.forEach(template => {
        acc[template.key] = template;
      });
      return acc;
    }, {});
  } catch (error) {
    console.error('Failed to fetch templates:', error);
  }
};

const handleGenerateCode = async () => {
  isGenerating.value = true;
  try {
    const res = await generateCode({
      sql: sqlInput.value,
      config,
      switches
    });
    const now = new Date();
    
    // 格式化为 YYYYMMDD_HHMMSS
    const formattedTime = `${now.getFullYear()}${(now.getMonth() + 1).toString().padStart(2, '0')}${now.getDate().toString().padStart(2, '0')}_${now.getHours().toString().padStart(2, '0')}${now.getMinutes().toString().padStart(2, '0')}${now.getSeconds().toString().padStart(2, '0')}`;

    // 使用格式化后的时间作为名字的一部分
    const nameWithTime = `code_${formattedTime}`;
    console.log(nameWithTime);
    codes.value.push({
      "name": nameWithTime,
      "code": res.results
    })
    switchTable(nameWithTime);
    window.open(res.url, '_blank');
  } catch (error) {
    console.error('生成代码失败:', error);
    alert(error.message || '生成代码失败');
  } finally {
    isGenerating.value = false;
  }
};

const clearForm = () => {
  sqlInput.value = '';
  Object.assign(config, {
    author: 'Zhengkai.blog.csdn.net',
    packageName: 'com.software.system',
    successReturn: 'ReturnT.success',
    errorReturn: 'ReturnT.error',
    prefix: '',
    inputType: 'ddl',
    tinyIntConversion: 'int',
    timeType: 'date',
    namingType: 'camel'
  });
};

const reflashShowCode = () => {
  if (!currentCode.value || !currentCode.value.code || !currentCode.value.code[0]) {
    currentTemplateCode.value = "";
    return;
  }
  
  const newCode = currentTemplate.value.reduce((acc, item) => {
    if (currentCode.value.code[0][item]) {
      return acc + currentCode.value.code[0][item];
    }
    return acc;
  }, "");
  
  currentTemplateCode.value = newCode;
}

const toggleTemplate = (templateKey) => {
  const newTemplates = currentTemplate.value.includes(templateKey)
    ? currentTemplate.value.filter(item => item !== templateKey)
    : [...currentTemplate.value, templateKey];
    
  currentTemplate.value = newTemplates;
  nextTick(() => {
    reflashShowCode();
  });
};

const selectTemplate = (templateKey) => {
  if (!generatedResults.value) {
    alert('请先生成代码');
    return;
  }

  currentTemplate.value = templateKey;
  currentTemplateCode.value = generatedResults.value[templateKey] || '';
};

const getTemplateName = (templateKey) => {
  return templateMap.value[templateKey]?.name || templateKey;
};

const getLanguageForTemplate = (templateKey) => {
  const languageMap = {
    'plusentity': 'java',
    'controller': 'java',
    'service': 'java',
    'mapper': 'java',
    'xml': 'xml',
    'vue': 'javascript',
    'api': 'javascript',
    'sql': 'sql'
  };
  return languageMap[templateKey] || 'java';
};

const switchTable = (recordName) => {
  const foundCode = codes.value.find(item => item.name === recordName);
  if (!foundCode) return;
  
  currentCode.value = foundCode;
  nextTick(() => {
    reflashShowCode();
  });
};

const formatTime = (timestamp) => {
  const date = new Date(timestamp);
  const hours = date.getHours().toString().padStart(2, '0');
  const minutes = date.getMinutes().toString().padStart(2, '0');
  const seconds = date.getSeconds().toString().padStart(2, '0');
  return `${hours}:${minutes}:${seconds}`;
};

const handleTemplateChange = (templateKey) => {
  if (selectedTemplateKeys.value.has(templateKey)) {
    selectedTemplateKeys.value.delete(templateKey);
    if (currentTemplate.value.includes(templateKey)) {
      currentTemplate.value = currentTemplate.value.filter(item => item !== templateKey);
      currentTemplateCode.value = '';
    }
  } else {
    selectedTemplateKeys.value.add(templateKey);
    showTemplateCode(templateKey);
  }
};

const showTemplateCode = (key) => {
  if (!currentHistory.value?.results?.generatedCode) {
    return;
  }

  if (!currentTemplate.value.includes(key)) {
    currentTemplate.value.push(key);
  }
  const code = currentHistory.value.results.generatedCode[key];
  if (code) {
    currentTemplateCode.value = code;
  } else {
    currentTemplateCode.value = '';
    console.warn(`No code found for template: ${key}`);
  }
};

// Lifecycle hooks
onMounted(async () => {
  await fetchTemplates();
});
</script>

<style>
.code-generator {
  width: 100%;
  height: 100vh;
  margin: 0;
  padding: 0;
  background-color: #f5f5f5;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.header {
  text-align: center;
  background: white;
  padding: 16px 0;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  width: 100%;
  flex-shrink: 0;
}

.logo {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
}

.logo-img {
  width: 40px;
  height: 40px;
}

.header h1 {
  margin: 0;
  color: #333;
  font-size: 24px;
}

.subtitle {
  color: #666;
  margin: 10px 0;
}

.header-actions {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-top: 15px;
}

.action-link {
  color: #1890ff;
  text-decoration: none;
  font-size: 14px;
}

.action-link:hover {
  text-decoration: underline;
}

.main-content {
  flex: 1;
  width: 100%;
  height: calc(100vh - 120px);
  overflow-y: auto;
  padding: 24px;
  display: grid;
  grid-template-rows: auto auto auto auto auto auto;
  gap: 24px;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.clear-btn {
  padding: 6px 12px;
  background: #f5f5f5;
  border: 1px solid #d9d9d9;
  border-radius: 4px;
  cursor: pointer;
}

.clear-btn:hover {
  background: #e6e6e6;
}

.sql-input-section,
.config-section,
.action-section,
.code-output-section {
  background: white;
  border-radius: 8px;
  padding: 24px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  width: 100%;
}

.code-editor {
  width: 100%;
  border-radius: 4px;
  overflow: visible;
}

.config-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 24px;
  width: 100%;
}

.config-item {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.config-item label {
  font-weight: 500;
  color: #333;
  font-size: 14px;
}

.config-item input,
.config-item select {
  padding: 8px 12px;
  border: 1px solid #d9d9d9;
  border-radius: 4px;
  font-size: 14px;
  transition: all 0.3s;
}

.config-item input:focus,
.config-item select:focus {
  outline: none;
  border-color: #1890ff;
  box-shadow: 0 0 0 2px rgba(24, 144, 255, 0.2);
}

.config-item input:hover,
.config-item select:hover {
  border-color: #40a9ff;
}

.switch-options {
  margin-top: 24px;
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
}

.switch-item {
  display: flex;
  align-items: center;
  gap: 8px;
}

.switch {
  position: relative;
  display: inline-block;
  width: 100px;
  height: 32px;
}

.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #f5f5f5;
  border: 1px solid #d9d9d9;
  border-radius: 16px;
  transition: .3s;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
  font-size: 14px;
}

.switch input:checked+.slider {
  background-color: #1890ff;
  color: white;
  border-color: #1890ff;
}

.action-buttons {
  display: flex;
  gap: 16px;
  justify-content: center;
  margin-top: 24px;
  padding-top: 24px;
  border-top: 1px solid #f0f0f0;
}

.primary-btn,
.secondary-btn {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 8px 24px;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s;
}

.primary-btn {
  background: #1890ff;
  color: white;
  border: none;
}

.primary-btn:hover {
  background: #40a9ff;
}

.secondary-btn {
  background: white;
  color: #666;
  border: 1px solid #d9d9d9;
}

.secondary-btn:hover {
  border-color: #40a9ff;
  color: #40a9ff;
}

.code-output {
  margin-top: 16px;
  min-height: 300px;
  background: #282c34;
  border-radius: 4px;
  overflow: hidden;
}

.empty-state {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 300px;
  color: #abb2bf;
  font-size: 14px;
  background: #282c34;
}

.icon {
  font-style: normal;
}

h2 {
  margin-top: 0;
  margin-bottom: 20px;
  color: #333;
  font-size: 18px;
  font-weight: 500;
}

.template-categories {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.template-category {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 16px;
}

.template-category h3 {
  color: #666;
  font-size: 14px;
  font-weight: 500;
  margin: 0;
  padding: 4px 8px;
  background: #f5f5f5;
  border-radius: 4px;
  display: inline-block;
}

.template-items {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  margin-bottom: 16px;
}

.template-items .el-checkbox-group {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.template-items .el-checkbox {
  margin-right: 0;
  margin-bottom: 8px;
}

.template-items .el-checkbox__label {
  font-size: 14px;
}

/* 自定义复选框样式 */
.template-items .el-checkbox.is-bordered {
  padding: 6px 12px;
  height: auto;
  border-radius: 4px;
}

.template-items .el-checkbox.is-bordered.is-checked {
  border-color: #1890ff;
  background-color: #e6f7ff;
}

.template-items .el-checkbox.is-checked .el-checkbox__label {
  color: #1890ff;
}

/* 移除旧的模板按钮样式 */
.template-btn {
  display: none;
}

.history-section {
  background: white;
  border-radius: 8px;
  padding: 16px 24px;
  margin-bottom: 24px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.history-header {
  margin-bottom: 16px;
}

.history-header h3 {
  margin: 0;
  font-size: 16px;
  color: #333;
}

.history-list {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
}

.history-btn {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 12px 20px;
  border: 1px solid #d9d9d9;
  border-radius: 6px;
  background: white;
  cursor: pointer;
  transition: all 0.3s;
}

.history-btn:hover {
  border-color: #40a9ff;
  color: #40a9ff;
}

.history-btn.active {
  background: #e6f7ff;
  border-color: #1890ff;
}

.history-btn .table-name {
  color: #1890ff;
  font-weight: 500;
  font-size: 14px;
  margin-bottom: 4px;
}

.history-btn .timestamp {
  color: #999;
  font-size: 12px;
}

.history-btn.active .table-name {
  color: #1890ff;
}

.history-btn.active .timestamp {
  color: #666;
}

.code-tabs {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin-bottom: 16px;
  padding: 8px;
  background: #f5f5f5;
  border-radius: 4px;
}

.tab-btn {
  padding: 6px 12px;
  border: 1px solid #d9d9d9;
  border-radius: 4px;
  background: white;
  color: #666;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s;
}

.tab-btn:hover {
  border-color: #40a9ff;
  color: #40a9ff;
}

.tab-btn.active {
  background: #1890ff;
  border-color: #1890ff;
  color: white;
}
</style>