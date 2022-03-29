<template>
  <div id="teacher-login">
    <div class="bg" :class="bgBlur ? 'bg-blur':''"></div>
    <div class="content">
      <div class="content-middle">
        <div class="form">
          <div class="form-text">管理系统</div>
          <a-form :form="form" class="login-form">
            <a-form-item>
              <a-input v-decorator="['userName',{ rules: [{ required: true, message: '请输入账号' }] }]" placeholder="账号" >
                <a-icon slot="prefix" type="user" style="color: rgba(0,0,0,.25)" />
              </a-input>
            </a-form-item>
            <a-form-item>
              <a-input v-decorator="['password',{ rules: [{ required: true, message: '请输入密码' }] }]"
                       type="password" placeholder="密码" >
                <a-icon slot="prefix" type="lock" style="color: rgba(0,0,0,.25)" />
              </a-input>
            </a-form-item>
            <a-button type="primary" block @click="loginClick">登录</a-button>
          </a-form>
        </div>
      </div>
      <div class="content-footer">
        <div>技术支持由XXX提供</div>
        <div>联系电话：12345678900</div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  data(){
    return {
      bgBlur: true,
      form: this.$form.createForm(this, { name: 'teacher-login' }),
    }
  },
  methods: {
    loginClick() {
      this.form.validateFields((err, values) => {
        if (err) {
          return;
        }
        this.$axios.post("auth/login",{
          username: values.userName,
          password: values.password,
        }).then(res => {
          if (res) {
            res.data.user["type"] = 1;
            res.data.user["token"] = res.data.token;
            this.$store.commit("user/setUser", res.data.user);
            this.$message.success('登录成功');
            this.$router.push({ name: 'home-index' });
          }
        });
      });
    },
    test() {
      this.bgBlur = !this.bgBlur;
    }
  },
}
</script>

<style scoped lang="less">
.bg {
  width: 100%;
  height:100%;
  background: rgba(24, 144, 255, 0.8);
  //background: url("../../../assets/images/bg_login_white_blur.jpg") no-repeat center;
  //background-size: cover;
  position: absolute;
}
.bg-blur {
  -webkit-filter: blur(15px);
  -moz-filter: blur(15px);
  -o-filter: blur(15px);
  -ms-filter: blur(15px);
  filter: blur(15px);
}
.content{
  width: 100%;
  height: 100%;
  position: absolute;
}
.content-middle{
  width: 100%;
  height: 100%;
  display: flex;
  .all-center();
  .form {
    padding: 60px 100px;
    background: white;
    display: flex;
    flex-direction: column;
    align-items: center;
    border-radius: 3px;
    box-shadow: 0 0 7px 0px #4c4c4c;
    .form-logo {
      width: 180px;
    }
    .form-text {
      margin-bottom: 15px;
      font-size: 24px;
      font-weight: bold;
    }
  }
}
.content-footer {
  height: 70px;
  color: white;
  position: fixed;
  bottom: 0;
  text-align: center;
  width: 100%
}
</style>