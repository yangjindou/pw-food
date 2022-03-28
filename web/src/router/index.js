import Vue from "vue";
import VueRouter from "vue-router";
import routes from './routes';

// 下方代码解决重复路由时，控制台报错的问题
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push (location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject);
  return originalPush.call(this, location).catch(err => err);
}
const originalReplace = VueRouter.prototype.replace;
VueRouter.prototype.replace = function replace (location, onResolve, onReject) {
  if (onResolve || onReject) return originalReplace.call(this, location, onResolve, onReject);
  return originalReplace.call(this, location).catch(err => err);
}
Vue.use(VueRouter);

const router = new VueRouter({
  mode: "hash",
  routes,
});

export default router;