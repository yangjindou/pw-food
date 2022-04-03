<template>
  <a-modal v-model="formModal" title="注册" @ok="modalOk">
    <a-form class="modal-form" :form="form">
      <a-form-item label="名称">
        <a-input v-decorator="['name',{rules}]" placeholder="名称" />
      </a-form-item>
      <a-form-item label="标识">
        <a-input v-decorator="['sign',{rules}]" placeholder="标识" />
      </a-form-item>
      <a-form-item label="备注">
        <a-input v-decorator="['remark']" placeholder="备注" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
export default {
  data() {
    return {
      form: this.$form.createForm(this, { name: 'form' }),
      formModal: false,
      rules: [{
        required: true,
        message: '必填项',
      }],
    }
  },
  methods: {
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

<style scoped>

</style>