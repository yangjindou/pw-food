<template>
  <a-modal v-model="formModal" :title="formState" @ok="modalOk" :maskClosable="false">
    <a-form id="wasteDisposal-form" class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="物品名称">
        <a-input v-decorator="['itemName',{rules}]" placeholder="物品名称" />
      </a-form-item>
      <a-form-item label="物品数量">
        <a-input v-decorator="['itemAmount',{rules}]" placeholder="物品数量" />
      </a-form-item>
      <a-form-item label="存放地方">
        <a-input v-decorator="['savePlace',{rules}]" placeholder="存放地方" />
      </a-form-item>
      <a-form-item label="是否存放48小时">
        <a-radio-group v-decorator="['isSave48hour',{rules}]">
          <a-radio value="是">是</a-radio>
          <a-radio value="否">否</a-radio>
        </a-radio-group>
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
        let data = objUtils.getObjectByKey(row, "id", "itemName", "itemAmount",
            "savePlace", "isSave48hour", "remark");
        this.form.setFieldsValue(data);
      });
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (this.formState === '新增') {
          this.$axios.post("/wasteDisposal", data).then(res => {
            if (res) {
              this.$message.success("添加成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        } else if (this.formState === '修改') {
          this.$axios.put("/wasteDisposal", data).then(res => {
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
<style lang="less">
#wasteDisposal-form {
  .ant-form-item {
    .ant-form-item-label {
      width: 125px;
      text-align: right;
    }
  }
}
</style>