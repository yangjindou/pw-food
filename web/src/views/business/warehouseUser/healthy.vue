
<template>
  <a-modal v-model="formModal" :title="formState" :maskClosable="false">
    <a-form id="warehouseUserHealthy-form" class="" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否是否">
        <a-radio-group v-decorator="['option1',{rules}]" :options="['是','否']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="是否是否是否是否是否是否是否是否是否是否">
        <a-radio-group v-decorator="['option2',{rules}]" :options="['是','否']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="是否是否是否是否是否是否是否是否是否是否">
        <a-radio-group v-decorator="['option3',{rules}]" :options="['是','否']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="是否是否是否是否是否是否是否是否是否是否">
        <a-radio-group v-decorator="['option4',{rules}]" :options="['是','否']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="是否是否是否是否是否是否是否是否是否是否">
        <a-radio-group v-decorator="['option5',{rules}]" :options="['是','否']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="是否是否是否是否是否是否是否是否是否是否">
        <a-radio-group v-decorator="['option6',{rules}]" :options="['是','否']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="是否是否是否是否是否是否是否是否是否是否">
        <a-radio-group v-decorator="['option7',{rules}]" :options="['是','否']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="是否是否是否是否是否是否是否是否是否是否">
        <a-radio-group v-decorator="['option8',{rules}]" :options="['是','否']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="是否是否是否是否是否是否是否是否是否是否">
        <a-radio-group v-decorator="['option9',{rules}]" :options="['是','否']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="是否是否是否是否是否是否是否是否是否是否">
        <a-radio-group v-decorator="['option10',{rules}]" :options="['是','否']" :disabled="disabled" />
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
      selectList: {
        warehouse: [],
      }
    }
  },
  mounted() {
    this.getSelectList();
  },
  methods: {
    getSelectList() {
      this.$axios.get(`/warehouse/list`).then(res => {
        if (res) {
          res.data.forEach(item => this.selectList.warehouse.push(item));
        }
      });
    },
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
          let data = objUtils.getObjectByKey(row, "id", "option1", "option2", "option3",
              "option4", "option5", "option6", "option7", "option8", "option9", "option10");
          this.form.setFieldsValue(data);
        });
      }
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (this.formState === '新增') {
          this.$axios.post("/warehouseUser", data).then(res => {
            if (res) {
              this.$message.success("添加成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        } else if (this.formState === '修改') {
          this.$axios.put("/warehouseUser", data).then(res => {
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
#warehouseUserHealthy-form {
  .ant-form-item-label {
    line-height: 20px;
    white-space: break-spaces;
    text-align: left;
  }
  .ant-form-item-control {
    text-align: right;
  }
}
</style>