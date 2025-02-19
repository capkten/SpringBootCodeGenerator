<template>
  <div class="code-preview-wrapper">
    <div class="code-preview-header">
      <div class="language-selector">
        <select v-model="currentLanguage" @change="updateLanguage">
          <option value="java">Java</option>
          <option value="javascript">JavaScript/Vue</option>
          <option value="html">HTML</option>
          <option value="css">CSS</option>
        </select>
      </div>
      <button class="copy-btn" @click="copyCode">
        <span class="icon">⟳</span> 复制
      </button>
    </div>
    <div ref="editorContainer" class="code-preview-content"></div>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, watch } from 'vue';
import { EditorView, basicSetup } from 'codemirror';
import { EditorState } from '@codemirror/state';
import { java } from '@codemirror/lang-java';
import { javascript } from '@codemirror/lang-javascript';
import { html } from '@codemirror/lang-html';
import { css } from '@codemirror/lang-css';
import { oneDark } from '@codemirror/theme-one-dark';

const props = defineProps({
  code: {
    type: String,
    default: ''
  },
  defaultLanguage: {
    type: String,
    default: 'java'
  }
});

const editorContainer = ref(null);
const editor = ref(null);
const currentLanguage = ref(props.defaultLanguage);

const getLanguageExtension = (lang) => {
  switch (lang) {
    case 'java':
      return java();
    case 'javascript':
      return javascript();
    case 'html':
      return html();
    case 'css':
      return css();
    default:
      return java();
  }
};

const initEditor = () => {
  const startState = EditorState.create({
    doc: props.code,
    extensions: [
      basicSetup,
      getLanguageExtension(currentLanguage.value),
      oneDark,
      EditorView.editable.of(false), // Read-only mode
      EditorView.theme({
        "&": {
          height: "100%",
          minHeight: "300px"
        }
      })
    ]
  });

  editor.value = new EditorView({
    state: startState,
    parent: editorContainer.value
  });
};

const updateLanguage = () => {
  if (editor.value) {
    editor.value.destroy();
    initEditor();
  }
};

const copyCode = async () => {
  try {
    await navigator.clipboard.writeText(props.code);
    alert('代码已复制到剪贴板');
  } catch (err) {
    console.error('复制失败:', err);
    alert('复制失败，请手动复制');
  }
};

onMounted(() => {
  initEditor();
});

onBeforeUnmount(() => {
  if (editor.value) {
    editor.value.destroy();
  }
});

watch(() => props.code, (newValue) => {
  if (editor.value) {
    const transaction = editor.value.state.update({
      changes: {
        from: 0,
        to: editor.value.state.doc.length,
        insert: newValue
      }
    });
    editor.value.dispatch(transaction);
  }
});
</script>

<style>
.code-preview-wrapper {
  width: 100%;
  border: 1px solid #d9d9d9;
  border-radius: 4px;
  overflow: hidden;
  background: #282c34;
}

.code-preview-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 16px;
  background: #21252b;
  border-bottom: 1px solid #181a1f;
}

.language-selector select {
  padding: 4px 8px;
  border: 1px solid #181a1f;
  border-radius: 4px;
  background: #282c34;
  color: #abb2bf;
  font-size: 14px;
}

.copy-btn {
  display: flex;
  align-items: center;
  gap: 4px;
  padding: 4px 12px;
  border: 1px solid #181a1f;
  border-radius: 4px;
  background: #282c34;
  color: #abb2bf;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s;
}

.copy-btn:hover {
  background: #2c313a;
  border-color: #2c313a;
}

.code-preview-content {
  min-height: 300px;
  max-height: 600px;
  overflow: auto;
}

.code-preview-content .cm-editor {
  height: 100%;
  min-height: 300px;
}

.code-preview-content .cm-scroller {
  font-family: 'Monaco', 'Menlo', 'Ubuntu Mono', 'Consolas', monospace;
  font-size: 14px;
  line-height: 1.5;
  padding: 16px;
}

.code-preview-content .cm-line {
  padding: 0 8px;
}
</style> 