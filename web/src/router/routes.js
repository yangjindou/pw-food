const meta = {
  passAuth: true
}

export default [{
  path: '*',
  name: '404',
  component: () => import('@/views/404'),
  meta
}, {
  path: "/",
  name: "index",
  component: () => import('@/views/index/index'),
  redirect: '/home',
  children: [{
    path: "home",
    name: "home",
    component: () => import('@/views/home/home')
  }]
}, {
  path: "/test",
  name: "test",
  component: () => import('@/views/test')
}, {
  path: "/login",
  name: "login",
  component: () => import('@/views/login/login')
}];