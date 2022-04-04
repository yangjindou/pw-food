<template>
  <a-modal v-model="formModal" :title="formState" @ok="modalOk">
    <a-form id="device-form" class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="检验计量器">
        <a-input v-decorator="['inspectionMeter',{rules}]" placeholder="检验计量器" />
      </a-form-item>
      <a-form-item label="是否定期校验">
        <a-radio-group v-decorator="['isPeriodicInspection',{rules}]">
          <a-radio value="是">是</a-radio>
          <a-radio value="否">否</a-radio>
        </a-radio-group>
      </a-form-item>
      <a-form-item label="校验日期">
        <a-date-picker v-decorator="[`inspectionDate`,{rules}]" placeholder="校验日期" />
      </a-form-item>
      <a-form-item label="检测废弃物是否按规定存放">
        <a-radio-group v-decorator="['isRequiredSave',{rules}]">
          <a-radio value="是">是</a-radio>
          <a-radio value="否">否</a-radio>
        </a-radio-group>
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
        let data = objUtils.getObjectByKey(row, "id", "inspectionMeter",
            "isPeriodicInspection", "inspectionDate", "isRequiredSave");
        if (data['inspectionDate']) {
          data['inspectionDate'] = this.$moment(data['inspectionDate'])
        }
        this.form.setFieldsValue(data);
      });
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (data['inspectionDate']) {
          data['inspectionDate'] = data['inspectionDate'].format('YYYY-MM-DD');
        }
        if (this.formState === '新增') {
          this.$axios.post("/device", data).then(res => {
            if (res) {
              this.$message.success("添加成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        } else if (this.formState === '修改') {
          this.$axios.put("/device", data).then(res => {
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
#device-form {
  .ant-form-item {
    .ant-form-item-label {
      width: 190px;
      text-align: right;
    }
  }
}
</style>