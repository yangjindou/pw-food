<template>
  <div id="login">
    <div class="bg" :class="bgBlur ? 'bg-blur':''"></div>
    <div class="content">
      <div class="content-middle">
        <a-form :form="form" class="form">
          <div class="form-title">欢迎使用</div>
          <a-form-item>
            <a-input v-decorator="['loginName',{ rules: [{ required: true, message: '请输入账号' }] }]" placeholder="账号" >
              <a-icon slot="prefix" type="user" style="color: rgba(0,0,0,.25)" />
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-input v-decorator="['password',{ rules: [{ required: true, message: '请输入密码' }] }]"
                     type="password" placeholder="密码" >
              <a-icon slot="prefix" type="lock" style="color: rgba(0,0,0,.25)" />
            </a-input>
          </a-form-item>
          <div class="register-text"><span @click="registerClick">没有账号？点击注册</span></div>
          <a-button type="primary" block @click="loginClick">登录</a-button>
          <register ref="register" />
        </a-form>
      </div>
      <div class="content-footer">
        <div>技术支持由XXX提供</div>
        <div>联系电话：12345678900</div>
      </div>
    </div>
  </div>
</template>
<script>
import Register from "@/views/login/register";
export default {
  components: {Register},
  data(){
    return {
      bgBlur: true,
      form: this.$form.createForm(this, { name: 'teacher-login' }),
    }
  },
  methods: {
    registerClick() {
      this.$refs.register.open();
    },
    loginClick() {
      this.form.validateFields((err, data) => {
        if (err) {
          return;
        }
        this.$axios.post("auth/login", data).then(res => {
          if (res) {
            this.$store.commit("user/setUser", res.data);
            this.$message.success('登录成功');
            this.$router.push({ name: 'home' });
          }
        });
      });
    },
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
    box-shadow: 0 0 7px 0 #4c4c4c;
    .form-title {
      margin-bottom: 25px;
      font-size: 24px;
      font-weight: bold;
    }
    .register-text {
      width: 100%;
      .text-right();
      margin-bottom: 20px;
      span {
        cursor: pointer;
      }
      span:hover {
        color: #1890ff;
      }
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
<style lang="less">
#login {
  .ant-form-item {
    margin: 10px;
  }
}
</style>