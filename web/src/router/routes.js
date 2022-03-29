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
  component: () => import('@/views/login/login')
}];