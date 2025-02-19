import axios from 'axios';

const request = axios.create({
  baseURL: 'http://localhost:9090/generator',
  timeout: 10000
});

// 请求拦截器
request.interceptors.request.use(
  config => {
    // 如果请求头没有特别指定 Content-Type，则默认使用 application/json
    if (!config.headers['Content-Type']) {
      config.headers['Content-Type'] = 'application/json';
    }
    
    // 如果是 FormData 类型，让 axios 自动设置正确的 Content-Type
    if (config.data instanceof FormData) {
      delete config.headers['Content-Type'];
    }
    
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

// 响应拦截器
request.interceptors.response.use(
  response => {
    const res = response.data;
    if (res.code === 0) {
      return res;
    }
    // 处理其他状态码
    console.error('API Error:', res.msg || 'Error');
    return Promise.reject(new Error(res.msg || 'Error'));
  },
  error => {
    console.error('Request Error:', error);
    return Promise.reject(error);
  }
);

export default request; 