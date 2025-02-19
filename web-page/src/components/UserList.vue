<template>
  <div class="user-list">
    <h2>用户列表</h2>
    <div v-if="loading">加载中...</div>
    <ul v-else>
      <li v-for="user in users" :key="user.id">
        {{ user.name }}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '../utils/request'

const users = ref([])
const loading = ref(false)

const getUsers = async () => {
  try {
    loading.value = true
    const response = await request.get('/users')
    users.value = response.data
  } catch (error) {
    console.error('获取用户列表失败:', error)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  getUsers()
})
</script> 