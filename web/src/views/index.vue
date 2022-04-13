<template>
  <div id="index">
    <div class="menu">
      <div class="menu-logo">
        <!--        <img src="@/assets/images/logo_white.png" alt="" />-->
        <div class="menu-logo-text">管理系统</div>
      </div>
      <a-menu mode="inline" theme="dark" @click="menuClick"
              :selectedKeys="$store.state.menu.selectedKeys">
        <a-menu-item v-for="item in menuList" :key="item.key">
          <a-icon :type="item.icon" />
          <span>{{item.name}}</span>
        </a-menu-item>
      </a-menu>
    </div>
    <div class="container">
      <div class="container-header">
        <div class="avatar">
          <div class="user-name">{{$store.state.user.userData["userName"]}}</div>
          <a-dropdown>
            <a-menu slot="overlay">
              <a-menu-item key="修改密码" @click="modifyPasswordClick">修改密码</a-menu-item>
              <a-menu-item key="退出" @click="logoutClick">退出</a-menu-item>
            </a-menu>
            <a-icon type="down" class="down" />
          </a-dropdown>
        </div>
      </div>
      <div class="container-content">
        <router-view />
      </div>
    </div>
    <a-modal v-model="passwordModal" title="修改密码" @ok="handleOk" :maskClosable="false">
      <a-form :form="form" class="modal-form">
        <a-form-item label="账号">
          <a-input disabled v-decorator="['loginName',{ rules: [{ required: true, message: '请输入账号' }] }]" placeholder="账号" />
        </a-form-item>
        <a-form-item label="旧密码">
          <a-input v-decorator="['passwordOld',{ rules: [{ required: true, message: '请输入旧密码' }] }]" type="password" placeholder="旧密码" />
        </a-form-item>
        <a-form-item label="新密码">
          <a-input v-decorator="['passwordNew',{ rules: [{ required: true, message: '请输入新密码' }] }]" type="password" placeholder="新密码" />
        </a-form-item>
        <a-form-item label="确认密码">
          <a-input v-decorator="['passwordNew2',{ rules: [{ required: true, message: '请输入确认密码' }] }]" type="password" placeholder="确认密码" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userName: '',
      passwordModal: false,
      form: this.$form.createForm(this, { name: 'password-form' }),
      menuList: []
    }
  },
  mounted() {
    this.setMenu();
  },
  methods: {
    setMenu() {
      this.$axios.post("/auth/verifyLogin").then(res => {
        res.data['token'] = this.$store.state.user.userData.token;
        this.$store.commit('user/setUser', res.data);
        if (res && res.data) {
          const menuList = [{
            key: 'home',
            icon: 'line-chart',
            name: '首页'
          }];
          if (res.data['role'] === '管理员') {
            menuList.push({
              key: 'user',
              icon: 'pie-chart',
              name: '用户管理'
            });
            menuList.push({
              key: 'dict',
              icon: 'pie-chart',
              name: '数据字典'
            });
            menuList.push({
              key: 'notice',
              icon: 'pie-chart',
              name: '公告管理'
            });
            menuList.push({
              key: 'device',
              icon: 'pie-chart',
              name: '设备管理'
            });
            menuList.push({
              key: 'wasteDisposal',
              icon: 'pie-chart',
              name: '废物处理'
            });
            menuList.push({
              key: 'warehouse',
              icon: 'pie-chart',
              name: '监管仓管理'
            });
            menuList.push({
              key: 'disinfectant',
              icon: 'pie-chart',
              name: '消毒液管理'
            });
            menuList.push({
              key: 'disinfectionRecord',
              icon: 'pie-chart',
              name: '环境消杀记录'
            });
            menuList.push({
              key: 'warehouseUser',
              icon: 'pie-chart',
              name: '监管仓人员管理'
            });
          }
          this.menuList = menuList;
        }
      });
    },
    handleOk() {
      this.form.validateFields((err, data) => {
        if (err) {
          return;
        }
        if (data["passwordNew"] !== data["passwordNew2"]) {
          this.$message.error("两次密码不一致！");
          return;
        }
        let params = {
          oldPwd: data["passwordOld"],
          newPwd: data["passwordNew"]
        }
        this.$axios.put("auth/passwordModify", params).then(res => {
          if (res) {
            this.$message.success("修改成功，请重新登录。");
            this.logout();
          }
        });
      });
    },
    modifyPasswordClick() {
      this.passwordModal = true;
      this.$nextTick(()=> {
        this.form.setFieldsValue({ loginName: this.$store.state.user.userData["loginName"]});
      })
    },
    logoutClick() {
      this.$axios.delete("auth/logout").then(res => {
        if (res) {
          this.logout();
        }
      });
    },
    logout() {
      this.$store.commit("user/removeUser");
      this.$router.push({ name: 'login' });
    },
    menuClick({key}) {
      if (key) {
        this.$router.push({
          name: key
        });
      }
    }
  },
};
</script>
<style lang="less" scoped>
@menu-width: 220px;
#index {
  width: 100%;
  height: 100%;
  min-width: 1400px;
  position: absolute;
  display: flex;
}
.menu {
  width: @menu-width;
  height: 100%;
  background: #001529;
  color: white;

  .menu-logo {
    //height: 64px;
    background: #001529;
    display: flex;
    flex-direction: column;
    padding: 40px 0;
    .all-center();
    img {
      width: 170px;
      margin-bottom: 10px;
    }
    .menu-logo-text {
      color: #808080;
    }
  }
}
.container {
  height: 100%;
  width: calc(100% - @menu-width);
  overflow: hidden;

  .container-header {
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    padding-right: 50px;
    border-bottom: 2px solid #f0f2f5;
    .avatar {
      display: flex;
      align-items: center;
      .user-name {
        margin-left: 10px;
      }
      .down {
        margin-left: 10px;
        font-size: 13px;
        cursor: pointer;
      }
    }
  }

  .container-content {
    height: calc(100% - 60px - 2px);
    background: #f0f2f5;
    overflow-y: auto;
  }

}
</style>