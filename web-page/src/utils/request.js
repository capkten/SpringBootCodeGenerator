import axios from 'axios'

const request = axios.create({
  baseURL: 'https://api.example.com', // 替换成您的API基础URL
  timeout: 5000
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    // 在发送请求之前做些什么
    return config
  },
  error => {
    // 处理请求错误
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    // 对响应数据做点什么
    return response.data
  },
  error => {
    // 处理响应错误
    return Promise.reject(error)
  }
)

export default request 