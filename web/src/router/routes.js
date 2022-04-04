const meta = {
  passAuth: true
}

export default [{
  path: '*',
  name: '404',
  component: () => import('@/views/404'),
  meta
}, {
  path: "/test",
  name: "test",
  component: () => import('@/views/test')
}, {
  path: "/login",
  name: "login",
  component: () => import('@/views/login/login'),
  meta
}, {
  path: "/",
  name: "index",
  component: () => import('@/views/index'),
  redirect: '/home',
  children: [{
    path: "home",
    name: "home",
    component: () => import('@/views/home/home')
  }, {
    path: "user",
    name: "user",
    component: () => import('@/views/user/index')
  }, {
    path: "dict",
    name: "dict",
    component: () => import('@/views/dict/index')
  }, {
    path: "notice",
    name: "notice",
    component: () => import('@/views/notice/index')
  }]
}];