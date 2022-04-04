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
        <a-input v-decorator="['phone',{rules}]" placeholder="联系电话" />
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
    getSelectList() {
      apiUtils.getDictData(this.selectList.area, 'register_area');
    },
    open() {
      this.formModal = true;
      this.form.resetFields();
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        this.$axios.post("/dict", data).then(res => {
          if (res) {
            this.$message.success("添加成功");
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