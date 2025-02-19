<template>
  <div class="monaco-editor-wrapper">
    <div ref="editorContainer" class="monaco-editor-container"></div>
  </div>
</template>

<script>
import loader from '@monaco-editor/loader';

export default {
  name: 'MonacoEditor',
  props: {
    modelValue: {
      type: String,
      default: ''
    },
    language: {
      type: String,
      default: 'sql'
    },
    theme: {
      type: String,
      default: 'vs'
    }
  },
  data() {
    return {
      editor: null,
      monaco: null,
      initTimeout: null
    }
  },
  watch: {
    modelValue(newValue) {
      if (this.editor && newValue !== this.editor.getValue()) {
        this.editor.setValue(newValue);
      }
    }
  },
  async mounted() {
    // 延迟初始化以确保DOM已经完全渲染
    this.initTimeout = setTimeout(async () => {
      await this.initMonaco();
    }, 100);
  },
  beforeUnmount() {
    if (this.editor) {
      this.editor.dispose();
    }
    if (this.initTimeout) {
      clearTimeout(this.initTimeout);
    }
  },
  methods: {
    async initMonaco() {
      try {
        this.monaco = await loader.init();
        
        // 配置 SQL 语言特性
        this.monaco.languages.registerCompletionItemProvider('sql', {
          provideCompletionItems: () => {
            const suggestions = [
              {
                label: 'CREATE TABLE',
                kind: this.monaco.languages.CompletionItemKind.Keyword,
                insertText: 'CREATE TABLE \${1:table_name} (\n\t\${2:column_name} \${3:data_type} \${4:constraints}\n);',
                insertTextRules: this.monaco.languages.CompletionItemRules.InsertAsSnippet,
                detail: 'Create a new table',
                documentation: 'Creates a new table with the specified columns and constraints'
              },
              {
                label: 'SELECT',
                kind: this.monaco.languages.CompletionItemKind.Keyword,
                insertText: 'SELECT',
                detail: 'SQL SELECT statement'
              },
              {
                label: 'INSERT INTO',
                kind: this.monaco.languages.CompletionItemKind.Keyword,
                insertText: 'INSERT INTO',
                detail: 'SQL INSERT statement'
              },
              {
                label: 'UPDATE',
                kind: this.monaco.languages.CompletionItemKind.Keyword,
                insertText: 'UPDATE',
                detail: 'SQL UPDATE statement'
              },
              {
                label: 'DELETE FROM',
                kind: this.monaco.languages.CompletionItemKind.Keyword,
                insertText: 'DELETE FROM',
                detail: 'SQL DELETE statement'
              },
              {
                label: 'WHERE',
                kind: this.monaco.languages.CompletionItemKind.Keyword,
                insertText: 'WHERE',
                detail: 'SQL WHERE clause'
              },
              {
                label: 'GROUP BY',
                kind: this.monaco.languages.CompletionItemKind.Keyword,
                insertText: 'GROUP BY',
                detail: 'SQL GROUP BY clause'
              },
              {
                label: 'ORDER BY',
                kind: this.monaco.languages.CompletionItemKind.Keyword,
                insertText: 'ORDER BY',
                detail: 'SQL ORDER BY clause'
              },
              // 数据类型提示
              {
                label: 'int',
                kind: this.monaco.languages.CompletionItemKind.TypeParameter,
                insertText: 'int',
                detail: 'Integer type'
              },
              {
                label: 'varchar',
                kind: this.monaco.languages.CompletionItemKind.TypeParameter,
                insertText: 'varchar',
                detail: 'Variable-length string'
              },
              {
                label: 'datetime',
                kind: this.monaco.languages.CompletionItemKind.TypeParameter,
                insertText: 'datetime',
                detail: 'Date and time'
              },
              {
                label: 'boolean',
                kind: this.monaco.languages.CompletionItemKind.TypeParameter,
                insertText: 'boolean',
                detail: 'Boolean type'
              },
              // 约束提示
              {
                label: 'PRIMARY KEY',
                kind: this.monaco.languages.CompletionItemKind.Keyword,
                insertText: 'PRIMARY KEY',
                detail: 'Primary key constraint'
              },
              {
                label: 'NOT NULL',
                kind: this.monaco.languages.CompletionItemKind.Keyword,
                insertText: 'NOT NULL',
                detail: 'Not null constraint'
              },
              {
                label: 'AUTO_INCREMENT',
                kind: this.monaco.languages.CompletionItemKind.Keyword,
                insertText: 'AUTO_INCREMENT',
                detail: 'Auto increment'
              }
            ];
            return { suggestions };
          }
        });

        // SQL 语法验证规则
        this.monaco.languages.registerHoverProvider('sql', {
          provideHover: (model, position) => {
            const word = model.getWordAtPosition(position);
            if (!word) return;

            const keywords = {
              'SELECT': 'Used to select data from a database',
              'CREATE': 'Used to create new database objects',
              'TABLE': 'Defines a new table structure',
              'INSERT': 'Used to insert new records in a table',
              'UPDATE': 'Used to modify existing records',
              'DELETE': 'Used to delete records from a table',
              'WHERE': 'Used to filter records',
              'PRIMARY': 'Used to define a primary key',
              'KEY': 'Part of primary key or foreign key definition',
              'NOT': 'Used in constraints (e.g., NOT NULL)',
              'NULL': 'Represents a null value',
              'AUTO_INCREMENT': 'Automatically increment the value',
              'int': 'Integer data type',
              'varchar': 'Variable-length string data type',
              'datetime': 'Date and time data type',
              'boolean': 'Boolean data type'
            };

            const content = keywords[word.word.toUpperCase()];
            if (content) {
              return {
                contents: [{ value: '**' + word.word + '**\n\n' + content }]
              };
            }
          }
        });

        // 创建编辑器实例
        this.editor = this.monaco.editor.create(this.$refs.editorContainer, {
          value: this.modelValue,
          language: this.language,
          theme: this.theme,
          automaticLayout: true,
          minimap: { enabled: false },
          scrollBeyondLastLine: false,
          lineNumbers: 'on',
          roundedSelection: false,
          scrollbar: {
            useShadows: false,
            vertical: 'visible',
            horizontal: 'visible',
            horizontalScrollbarSize: 12,
            verticalScrollbarSize: 12
          },
          lineHeight: 24,
          fontSize: 14,
          tabSize: 2,
          renderLineHighlight: 'all',
          contextmenu: true,
          quickSuggestions: {
            other: true,
            comments: true,
            strings: true
          },
          suggestOnTriggerCharacters: true,
          wordWrap: 'on',
          fixedOverflowWidgets: true,
          overviewRulerBorder: false,
          renderWhitespace: 'none',
          scrollBeyondLastColumn: 0,
          links: true,
          padding: { top: 8, bottom: 8 },
          domReadOnly: false,
          readOnly: false,
          mouseWheelZoom: false,
          cursorStyle: 'line',
          cursorBlinking: 'blink',
          cursorSmoothCaretAnimation: true,
          smoothScrolling: true,
          mouseStyle: 'text',
          ariaLabel: 'SQL Editor',
          accessibilitySupport: 'on',
          renderValidationDecorations: 'on',
          folding: false,
          glyphMargin: false,
          fixedOverflowWidgets: true,
          parameterHints: {
            enabled: true
          }
        });

        // 监听内容变化
        this.editor.onDidChangeModelContent(() => {
          const value = this.editor.getValue();
          this.$emit('update:modelValue', value);
          this.validateSQL(value);
        });

        // 设置初始值
        if (this.modelValue) {
          this.editor.setValue(this.modelValue);
        }

        // 添加默认的 SQL 示例作为占位符
        const defaultSQL = `CREATE TABLE 'sys_user_info' (
  'user_id' int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  'user_name' varchar(255) NOT NULL COMMENT '用户名',
  'status' tinyint(1) NOT NULL COMMENT '状态',
  'create_time' datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY ('user_id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';`;

        if (!this.modelValue) {
          this.editor.setValue(defaultSQL);
        }

        // 强制重新布局
        window.addEventListener('resize', () => {
          if (this.editor) {
            this.editor.layout();
          }
        });

      } catch (error) {
        console.error('Failed to initialize Monaco Editor:', error);
      }
    },
    validateSQL(sql) {
      if (!sql.trim()) return;

      // 简单的 SQL 语法验证
      const errors = [];
      const warnings = [];

      // 检查基本语法
      if (sql.includes('CREATE TABLE')) {
        if (!sql.includes('(')) {
          errors.push({
            message: 'Missing opening parenthesis in CREATE TABLE statement',
            severity: this.monaco.MarkerSeverity.Error
          });
        }
        if (!sql.includes(')')) {
          errors.push({
            message: 'Missing closing parenthesis in CREATE TABLE statement',
            severity: this.monaco.MarkerSeverity.Error
          });
        }
        if (!sql.toLowerCase().includes('primary key')) {
          warnings.push({
            message: 'Table should have a PRIMARY KEY defined',
            severity: this.monaco.MarkerSeverity.Warning
          });
        }
      }

      // 设置验证标记
      if (this.editor && this.editor.getModel()) {
        this.monaco.editor.setModelMarkers(
          this.editor.getModel(),
          'sql',
          [...errors, ...warnings].map(({ message, severity }) => ({
            message,
            severity,
            startLineNumber: 1,
            startColumn: 1,
            endLineNumber: 1,
            endColumn: 1
          }))
        );
      }
    }
  }
}
</script>

<style>
.monaco-editor-wrapper {
  position: relative;
  width: 100%;
  height: 300px;
  border: 1px solid #d9d9d9;
  border-radius: 4px;
  overflow: hidden;
}

.monaco-editor-container {
  width: 100%;
  height: 100%;
}

/* 移除所有可能影响点击事件的z-index */
.monaco-editor,
.monaco-editor .overflow-guard,
.monaco-editor .monaco-scrollable-element,
.monaco-editor .cursor {
  position: static !important;
  z-index: auto !important;
}

/* 修复编辑器内部样式 */
.monaco-editor .overflow-guard {
  border-radius: 4px;
  position: relative;
  z-index: 12;
}

.monaco-editor .monaco-scrollable-element {
  border-radius: 4px;
  position: relative;
  z-index: 13;
}

/* 确保编辑器内容可以正常交互 */
.monaco-editor {
  position: relative;
  z-index: 14;
}

.monaco-editor .cursor {
  z-index: 15;
}
</style> 