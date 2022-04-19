import objUtils from "@/utils/objUtils";
export default {
  namespaced: true,
  mutations: {
    setSelectedKeys(state, value) {
      state.selectedKeys = [value];
    },
  },
  state: {
    selectedKeys: [],
    menuData: {
      all: [{
        key: 'home',
        icon: 'line-chart',
        name: '首页'
      }],
      user: [{
        key: 'appointment',
        icon: 'pie-chart',
        name: '预约管理'
      }],
      admin: [{
        key: 'notice',
        icon: 'pie-chart',
        name: '公告管理'
      },{
        key: 'warehouse',
        icon: 'pie-chart',
        name: '监管仓管理'
      },{
        key: 'warehouseUser',
        icon: 'pie-chart',
        name: '监管仓人员管理'
      },{
        key: 'device',
        icon: 'pie-chart',
        name: '设备管理'
      },{
        key: 'wasteDisposal',
        icon: 'pie-chart',
        name: '废物处理'
      },{
        key: 'disinfectant',
        icon: 'pie-chart',
        name: '消毒液管理'
      },{
        key: 'disinfectionRecord',
        icon: 'pie-chart',
        name: '环境消杀记录'
      },{
        key: 'appointment-admin',
        icon: 'pie-chart',
        name: '预约管理'
      },{
        key: 'appointment-warehousing',
        icon: 'pie-chart',
        name: '入仓管理'
      },{
        key: 'appointment-warehoused',
        icon: 'pie-chart',
        name: '出仓管理'
      }],
      system: [{
        key: 'user',
        icon: 'pie-chart',
        name: '用户管理'
      },{
        key: 'dict',
        icon: 'pie-chart',
        name: '数据字典'
      }]
    }
  },
  getters: {
    getMenu(state) {
      return role => {
        const menuData = objUtils.cloneObject(state.menuData);
        let menuRoleData = menuData.all;
        if (role === '企业用户') {
          menuRoleData = menuRoleData.concat(menuData.user);
        } else if (role === '监管仓管理员') {
          menuRoleData = menuRoleData.concat(menuData.admin);
        } else if (role === '系统管理员') {
          menuRoleData = menuRoleData.concat(menuData.system);
        }
        return menuRoleData;
      }
    }
  }
}