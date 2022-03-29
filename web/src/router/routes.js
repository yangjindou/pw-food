const routes = [{
  path: "/test",
  name: "test",
  component: () => import('@/views/test')
}, {
  path: "/login",
  name: "login",
  component: () => import('@/views/login/login')
}];

export default routes;