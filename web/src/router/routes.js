const meta = {
  passAuth: true
}

export default [{
  path: '*',
  name: '404',
  component: () => import('@/views/error/404'),
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
    component: () => import('@/views/business/notice/index')
  }, {
    path: "device",
    name: "device",
    component: () => import('@/views/business/device/index')
  }, {
    path: "wasteDisposal",
    name: "wasteDisposal",
    component: () => import('@/views/business/wasteDisposal/index')
  }, {
    path: "warehouse",
    name: "warehouse",
    component: () => import('@/views/business/warehouse/index')
  }, {
    path: "disinfectant",
    name: "disinfectant",
    component: () => import('@/views/business/disinfectant/index')
  }, {
    path: "disinfectionRecord",
    name: "disinfectionRecord",
    component: () => import('@/views/business/disinfectionRecord/index')
  }, {
    path: "warehouseUser",
    name: "warehouseUser",
    component: () => import('@/views/business/warehouseUser/index')
  }, {
    path: "warehouseUser-check",
    name: "warehouseUser-check",
    component: () => import('@/views/business/warehouseUser/check/index')
  }, {
    path: "warehouseUser-isolate",
    name: "warehouseUser-isolate",
    component: () => import('@/views/business/warehouseUser/isolate/index')
  }, {
    path: "warehouseUser-emergency",
    name: "warehouseUser-emergency",
    component: () => import('@/views/business/warehouseUser/emergency/index')
  }, {
    path: "appointment",
    name: "appointment",
    component: () => import('@/views/business/appointment/index')
  }]
}];