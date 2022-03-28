import Vue from "vue";
import axios from "axios";
import config from './env.config';
axios.defaults.baseURL = config.baseUrl;
axios.interceptors.request.use((config) => {
  return config;
},(error) => {
  return Promise.reject(error);
});
axios.interceptors.response.use((config) => {
  return config;
},(error) => {
  return Promise.reject(error);
});
Vue.prototype.$axios = axios;