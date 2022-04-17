<template>
  <a-modal v-model="formModal" title="入仓" :maskClosable="false">
    <a-form class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="禽类（Kg）">
        <a-input v-decorator="['warehousingWeightPoultry',{rules: integerRules}]" placeholder="禽类（Kg）" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="畜类（Kg）">
        <a-input v-decorator="['warehousingWeightLivestock',{rules: integerRules}]" placeholder="畜类（Kg）" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="水产品（Kg）">
        <a-input v-decorator="['warehousingWeightAquatic',{rules: integerRules}]" placeholder="水产品（Kg）" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="其他（Kg）">
        <a-input v-decorator="['warehousingWeightOther',{rules: integerRules}]" placeholder="其他（Kg）" :disabled="disabled" />
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
import {integerRules} from "@/utils/formRules";
export default {
  data() {
    return {
      form: this.$form.createForm(this, { name: 'form' }),
      formState: '',
      formModal: false,
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
          let data = objUtils.getObjectByKey(row, "id", "warehousingWeightPoultry",
              "warehousingWeightLivestock", "warehousingWeightAquatic", "warehousingWeightOther");
          if (data['samplingDate']) {
            data['samplingDate'] = this.$moment(data['samplingDate']);
          }
          this.form.setFieldsValue(data);
        });
      }
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (data['samplingDate']) {
          data['samplingDate'] = data['samplingDate'].format('YYYY-MM-DD');
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