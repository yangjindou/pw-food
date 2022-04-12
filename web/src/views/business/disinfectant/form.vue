<template>
  <a-modal v-model="formModal" :title="formState" :maskClosable="false">
    <a-form id="disinfectant-form" class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="消毒液名称">
        <a-input v-decorator="['name',{rules}]" placeholder="消毒液名称" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="消毒液配比比例">
        <a-input v-decorator="['ratio',{rules}]" placeholder="消毒液配比比例" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="调配时间">
        <a-date-picker v-decorator="[`deploymentDate`,{rules}]" placeholder="调配时间" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="调配人">
        <a-input v-decorator="['deploymentUser',{rules}]" placeholder="调配人" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="消毒液浓度">
        <a-input v-decorator="['concentration',{rules}]" placeholder="消毒液浓度" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="消杀评估">
        <a-input v-decorator="['assessment',{rules}]" placeholder="消杀评估" :disabled="disabled" />
      </a-form-item>
    </a-form>
    <template slot="footer">
      <a-button @click="modalCancel">取消</a-button>
      <a-button v-if="!disabled" type="primary" @click="modalOk">确定</a-button>
    </template>
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
      disabled: false,
    }
  },
  mounted() {
  },
  methods: {
    open(state, row) {
      this.formState = state;
      this.setFormData(row);
      this.disabled = state === '详情';
    },
    setFormData(row) {
      this.formModal = true;
      this.form.resetFields();
      if (row) {
        this.$nextTick(() => {
          let data = objUtils.getObjectByKey(row, "id", "name", "ratio", "deploymentDate", "deploymentUser",
              "concentration", "assessment");
          if (data['deploymentDate']) {
            data['deploymentDate'] = this.$moment(data['deploymentDate']);
          }
          this.form.setFieldsValue(data);
        });
      }
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (data['deploymentDate']) {
          data['deploymentDate'] = data['deploymentDate'].format('YYYY-MM-DD');
        }
        if (this.formState === '新增') {
          this.$axios.post("/disinfectant", data).then(res => {
            if (res) {
              this.$message.success("添加成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        } else if (this.formState === '修改') {
          this.$axios.put("/disinfectant", data).then(res => {
            if (res) {
              this.$message.success("修改成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        }
      });
    },
    modalCancel() {
      this.formModal = false;
    }
  }
}
</script>

<style lang="less" scoped>

</style>
<style lang="less">
#disinfectant-form {
  .ant-form-item {
    .ant-form-item-label {
      width: 195px;
      text-align: right;
    }
  }
}
</style>