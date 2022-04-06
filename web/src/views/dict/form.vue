<template>
  <a-modal v-model="formModal" :title="formState" @ok="modalOk" :maskClosable="false">
    <a-form class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
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
import objUtils from "@/utils/objUtils";
export default {
  data() {
    return {
      form: this.$form.createForm(this, { name: 'form' }),
      formState: '',
      formModal: false,
      rules: [{
        required: true,
        message: '必填项',
      }],
    }
  },
  methods: {
    add() {
      this.formState = '新增';
      this.formModal = true;
      this.form.resetFields();
    },
    update(row) {
      this.formState = '修改';
      this.formModal = true;
      this.$nextTick(() => {
        this.form.setFieldsValue(objUtils.getObjectByKey(row, "id", "name", "sign", "remark"));
      });
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (this.formState === '新增') {
          this.$axios.post("/dict", data).then(res => {
            if (res) {
              this.$message.success("添加成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        } else if (this.formState === '修改') {
          this.$axios.put("/dict", data).then(res => {
            if (res) {
              this.$message.success("修改成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        }
      });
    },
  }
}
</script>

<style scoped>

</style>