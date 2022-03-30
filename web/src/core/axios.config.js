import Vue from "vue";
import axios from "axios";
import { message } from 'ant-design-vue';
import router from "../router";

axios.defaults.baseURL = process.env.VUE_APP_API_BASE_URL;

axios.interceptors.request.use((config) => {
  let user = localStorage.getItem('user');
  if (user) {
    user = JSON.parse(user);
    if (user.token) {
      config.headers['token'] = user.token;
    }
  }
  return config;
},(error) => {
  return Promise.reject(error);
});

axios.interceptors.response.use((response) => {
  return response;
},(error) => {
  if (error.response.status === 400) {
    message.error(error.response.data.message || '出错了');
    return Promise.resolve();
  } else if (error.response.status === 401) {
    message.error('登录已过期，请重新登录');
    router.push({ name: 'login' });
    return Promise.reject('登录已过期，请重新登录');
  } else {
    message.error('出错了');
    return Promise.reject(error);
  }
});
Vue.prototype.$axios = axios;