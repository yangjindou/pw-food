import Vue from "vue";
import VueRouter from "vue-router";
import routes from './routes';
import store from "../store";

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

router.beforeEach((to, from, next) => {
  // passAuth，跳过登录验证
  if (to.meta['passAuth']) return next();
  let user = localStorage.getItem('user');
  if (!user) return next({ name: "login" });
  store.commit('menu/setSelectedKeys', to.name);
  // user = JSON.parse(user);
  // const menuList = store.getters["menu/getMenu"](user['role']);
  // const menuKeyList = menuList.map(e => e.key);
  // if (menuKeyList.includes(to.name)) {
    return next();
  // } else {
  //   return next({ name: "404" });
  // }
});

export default router;