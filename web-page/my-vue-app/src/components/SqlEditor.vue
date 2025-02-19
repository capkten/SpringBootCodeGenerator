<template>
  <div class="sql-editor-wrapper" ref="editorContainer"></div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, watch } from 'vue';
import { EditorView, basicSetup } from 'codemirror';
import { EditorState } from '@codemirror/state';
import { sql } from '@codemirror/lang-sql';
import { oneDark } from '@codemirror/theme-one-dark';

const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  }
});

const emit = defineEmits(['update:modelValue']);
const editorContainer = ref(null);
const editor = ref(null);

const getDefaultSQL = () => {
  return `CREATE TABLE 'sys_user_info' (
  'user_id' int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  'user_name' varchar(255) NOT NULL COMMENT '用户名',
  'status' tinyint(1) NOT NULL COMMENT '状态',
  'create_time' datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY ('user_id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';`;
};

const initEditor = () => {
  const startState = EditorState.create({
    doc: props.modelValue || getDefaultSQL(),
    extensions: [
      basicSetup,
      sql(),
      oneDark,
      EditorView.updateListener.of(update => {
        if (update.docChanged) {
          emit('update:modelValue', update.state.doc.toString());
        }
      })
    ]
  });

  editor.value = new EditorView({
    state: startState,
    parent: editorContainer.value
  });
};

onMounted(() => {
  initEditor();
});

onBeforeUnmount(() => {
  if (editor.value) {
    editor.value.destroy();
  }
});

watch(() => props.modelValue, (newValue) => {
  const currentValue = editor.value?.state.doc.toString();
  if (newValue !== currentValue) {
    editor.value?.dispatch({
      changes: {
        from: 0,
        to: currentValue?.length || 0,
        insert: newValue
      }
    });
  }
});
</script>

<style>
.sql-editor-wrapper {
  width: 100%;
  height: 300px;
  border: 1px solid #d9d9d9;
  border-radius: 4px;
  overflow: hidden;
  background: white;
}

.sql-editor-wrapper .cm-editor {
  height: 100%;
}

.sql-editor-wrapper .cm-editor .cm-scroller {
  font-family: 'Monaco', 'Menlo', 'Ubuntu Mono', 'Consolas', monospace;
  font-size: 14px;
  line-height: 1.5;
}

.sql-editor-wrapper .cm-editor .cm-line {
  padding: 0 8px;
}

.sql-editor-wrapper .cm-editor.cm-focused {
  outline: none;
}

.sql-editor-wrapper .cm-editor .cm-activeLine {
  background-color: rgba(24, 144, 255, 0.05);
}
</style> 