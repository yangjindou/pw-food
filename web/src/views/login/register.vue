<template>
  <a-modal id="register" v-model="formModal" title="注册" @ok="modalOk" :maskClosable="false">
    <a-form class="modal-form" :form="form">
      <a-form-item label="登录名">
        <a-input v-decorator="['loginName',{rules}]" placeholder="登录名" />
      </a-form-item>
      <a-form-item label="用户名">
        <a-input v-decorator="['userName',{rules}]" placeholder="用户名" />
      </a-form-item>
      <a-form-item label="属地">
        <a-select placeholder="属地" v-decorator="[`area`,{rules}]">
          <a-select-option v-for="item in selectList.area" :key="item.id" :value="item.id">{{item.name}}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="联系电话">
        <a-input v-decorator="['phone',{ rules: [{ required: true,pattern: /^1[3|4|5|7|8][0-9]\d{8}$/, message: '请输入正确的联系电话' }]}]" placeholder="联系电话" />
      </a-form-item>
      <a-form-item label="企业名称">
        <a-input v-decorator="['enterpriseName',{rules}]" placeholder="企业名称" />
      </a-form-item>
      <a-form-item label="社会统一信用代码">
        <a-input v-decorator="['enterpriseCode',{rules}]" placeholder="社会统一信用代码" />
      </a-form-item>
      <a-form-item label="法人">
        <a-input v-decorator="['enterpriseOwner',{rules}]" placeholder="法人" />
      </a-form-item>
      <a-form-item label="营业执照注册地址">
        <a-input v-decorator="['enterpriseLicenseAddress',{rules}]" placeholder="营业执照注册地址" />
      </a-form-item>
      <a-form-item label="营业执照照片">
        <div class="img-upload">
          <div v-if="enterpriseLicenseImg" class="img-box">
            <img :src="enterpriseLicenseImg" alt=""/>
          </div>
          <u-upload :allow-type="['jpg','jpeg','png']" @change="uploadChange">
            <a-button>{{enterpriseLicenseImg ? '更换': '上传'}}</a-button>
          </u-upload>
        </div>
      </a-form-item>
      <a-form-item label="营业执照照片">
        <a-input v-decorator="['enterpriseLicenseImg',{rules}]" placeholder="营业执照照片" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
import apiUtils from "@/utils/apiUtils";
export default {
  data() {
    return {
      enterpriseLicenseImg: '',
      form: this.$form.createForm(this, { name: 'form' }),
      formModal: false,
      rules: [{
        required: true,
        message: '必填项',
      }],
      selectList: {
        area: []
      }
    }
  },
  mounted() {
    this.getSelectList();
  },
  methods: {
    uploadChange({path}) {
      this.enterpriseLicenseImg = path;
    },
    getSelectList() {
      apiUtils.getDictData(this.selectList.area, 'register_area');
    },
    open() {
      this.formModal = true;
      this.enterpriseLicenseImg = '';
      this.form.resetFields();
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        this.$axios.post("/auth/register", data).then(res => {
          if (res) {
            this.$message.success("注册成功");
            this.formModal = false;
          }
        });
      });
    },
  }
}
</script>

<style lang="less">
#register {
  .ant-form-item {
    .ant-form-item-label {
      width: 135px;
      text-align: right;
    }
  }
}
</style>