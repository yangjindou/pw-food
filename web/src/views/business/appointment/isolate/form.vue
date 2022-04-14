<template>
  <a-modal v-model="formModal" :title="formState" :maskClosable="false">
    <a-form id="warehouseUser-form" class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" />
      </a-form-item>
      <a-form-item label="pid" hidden>
        <a-input v-decorator="['pid']" />
      </a-form-item>
      <a-form-item label="隔离时间">
        <a-date-picker v-decorator="[`date`,{rules}]" placeholder="隔离时间" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="隔离地点">
        <a-input v-decorator="['place',{rules}]" placeholder="隔离地点" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="备注">
        <a-input v-decorator="['remark',{rules}]" placeholder="备注" :disabled="disabled" />
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
          let data = objUtils.getObjectByKey(row, "id", "pid", "date", "place", "remark");
          if (data['date']) {
            data['date'] = this.$moment(data['date']);
          }
          this.form.setFieldsValue(data);
        });
      }
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (data['date']) {
          data['date'] = data['date'].format('YYYY-MM-DD');
        }
        if (this.formState === '新增') {
          this.$axios.post("/warehouseUser/isolate", data).then(res => {
            if (res) {
              this.$message.success("添加成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        } else if (this.formState === '修改') {
          this.$axios.put("/warehouseUser/isolate", data).then(res => {
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