<template>
  <a-modal v-model="formModal" :title="formState" :maskClosable="false">
    <a-form id="warehouseUser-form" class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" />
      </a-form-item>
      <a-form-item label="pid" hidden>
        <a-input v-decorator="['pid']" />
      </a-form-item>
      <a-form-item label="检测结果">
        <a-radio-group v-decorator="['result',{rules}]" :options="['阴性','阳性']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="检测时间">
        <a-date-picker v-decorator="[`date`,{rules}]" placeholder="检测时间" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="检测人">
        <a-select v-decorator="['user',{rules}]" placeholder="检测人" :disabled="disabled">
          <a-select-option v-for="item in selectList.user" :key="item.id" :value="item.name">{{item.name}}</a-select-option>
        </a-select>
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
        user: [],
      },
    }
  },
  mounted() {
    this.getSelectList();
  },
  methods: {
    getSelectList() {
      let params = {
        warehouseCreateUser: this.$store.state.user.userData['id']
      };
      this.$axios.get(`/warehouseUser/list`, {params}).then(res => {
        if (res) {
          res.data.forEach(item => this.selectList.user.push(item));
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
          let data = objUtils.getObjectByKey(row, "id", "pid", "result", "date", "user");
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
          this.$axios.post("/warehouseUser/check", data).then(res => {
            if (res) {
              this.$message.success("添加成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        } else if (this.formState === '修改') {
          this.$axios.put("/warehouseUser/check", data).then(res => {
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