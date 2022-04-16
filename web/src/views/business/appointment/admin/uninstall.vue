<template>
  <a-modal v-model="formModal" title="卸货" :maskClosable="false">
    <a-form class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="卸货时间">
        <a-date-picker v-decorator="[`uninstallDate`,{rules}]" placeholder="卸货时间" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="卸货人员">
        <a-input v-decorator="['uninstallUser',{rules}]" placeholder="卸货人员" :disabled="disabled" />
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
          let data = objUtils.getObjectByKey(row, "id", "uninstallDate", "uninstallUser");
          if (data['uninstallDate']) {
            data['uninstallDate'] = this.$moment(data['uninstallDate']);
          }
          this.form.setFieldsValue(data);
        });
      }
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (data['uninstallDate']) {
          data['uninstallDate'] = data['uninstallDate'].format('YYYY-MM-DD');
        }
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