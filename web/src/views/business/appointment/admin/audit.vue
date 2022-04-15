<template>
  <a-modal v-model="formModal" title="审批" :maskClosable="false">
    <a-form id="warehouseUserHealthy-form" class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="审核">
        <a-radio-group v-decorator="['filingState',{rules}]" @change="auditChange">
          <a-radio-button value="审核通过">审核通过</a-radio-button>
          <a-radio-button value="驳回">驳回</a-radio-button>
        </a-radio-group>
      </a-form-item>
      <a-form-item v-if="reasonVisible" label="理由">
        <a-textarea class="textarea" v-decorator="['reason',{rules}]" placeholder="理由" />
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
import {rules} from "@/utils/formRules";
export default {
  data() {
    return {
      form: this.$form.createForm(this, { name: 'form' }),
      formState: '',
      formModal: false,
      rules,
      disabled: false,
      reasonVisible: false,
    }
  },
  mounted() {
  },
  methods: {
    auditChange(e) {
      this.reasonVisible = e.target.value === "驳回";
    },
    open(state, row) {
      this.formState = state;
      this.setFormData(row);
      this.disabled = state === '详情';
    },
    setFormData(row) {
      this.formModal = true;
      this.reasonVisible = false;
      this.form.resetFields();
      if (row) {
        this.$nextTick(() => {
          let data = objUtils.getObjectByKey(row, "id");
          this.form.setFieldsValue(data);
        });
      }
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
          this.$axios.put("/appointment", data).then(res => {
            if (res) {
              this.$message.success("审核成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
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