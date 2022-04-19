<template>
  <a-modal v-model="formModal" :title="formState" @ok="modalOk" :maskClosable="false">
    <a-form class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="公告标题">
        <a-input v-decorator="['title',{rules}]" placeholder="公告标题" />
      </a-form-item>
      <a-form-item label="公告内容">
        <a-textarea class="textarea" v-decorator="['content',{rules}]" placeholder="公告内容" />
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
        this.form.setFieldsValue(objUtils.getObjectByKey(row, "id", "title", "content"));
      });
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (this.formState === '新增') {
          this.$axios.post("/notice", data).then(res => {
            if (res) {
              this.$message.success("添加成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        } else if (this.formState === '修改') {
          this.$axios.put("/notice", data).then(res => {
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

<style lang="less" scoped>

</style>