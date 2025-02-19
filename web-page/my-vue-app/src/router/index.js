import { createRouter, createWebHistory } from 'vue-router';
import CodeGenerator from '../views/CodeGenerator.vue';

const routes = [
  {
    path: '/',
    redirect: '/generator'
  },
  {
    path: '/generator',
    name: 'CodeGenerator',
    component: CodeGenerator,
    meta: {
      title: 'Java代码生成平台'
    }
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to, from, next) => {
  document.title = to.meta.title || 'Java代码生成平台';
  next();
});

export default router; 