<template>
  <a-modal v-model="formModal" title="采样" :maskClosable="false">
    <a-form class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="采样货物名称">
        <a-input v-decorator="['samplingGoodName',{rules}]" placeholder="采样货物名称" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="采样数量">
        <a-input v-decorator="['samplingAmount',{rules: integerRules}]" placeholder="采样数量" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="采样检测人">
        <a-input v-decorator="['samplingUser',{rules}]" placeholder="采样检测人" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="备注">
        <a-input v-decorator="['samplingRemark']" placeholder="备注" :disabled="disabled" />
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
import {rules, integerRules} from "@/utils/formRules";
export default {
  data() {
    return {
      form: this.$form.createForm(this, { name: 'form' }),
      formState: '',
      formModal: false,
      rules,
      integerRules,
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
      this.reasonVisible = false;
      this.form.resetFields();
      if (row) {
        this.$nextTick(() => {
          let data = objUtils.getObjectByKey(row, "id", "samplingGoodName",
              "samplingAmount", "samplingRemark", "samplingUser");
          this.form.setFieldsValue(data);
        });
      }
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        this.$axios.put("/appointment", data).then(res => {
          if (res) {
            this.$message.success("操作成功");
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