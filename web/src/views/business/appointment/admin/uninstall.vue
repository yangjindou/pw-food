<template>
  <a-modal v-model="formModal" title="卸货" :maskClosable="false">
    <a-form class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="卸货人员">
        <a-select v-decorator="['uninstallUser',{rules}]" :disabled="disabled" mode="tags" placeholder="卸货人员">
          <a-select-option v-for="item in selectList.uninstallUser" :key="item.id" :value="item.name">{{item.name}}</a-select-option>
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
      selectList: {
        uninstallUser: [],
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
          res.data.forEach(item => this.selectList.uninstallUser.push(item));
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
      this.reasonVisible = false;
      this.form.resetFields();
      if (row) {
        this.$nextTick(() => {
          let data = objUtils.getObjectByKey(row, "id", "uninstallUser");
          if (data['uninstallUser']) {
            data['uninstallUser'] = data['uninstallUser'].split('、');
          }
          this.form.setFieldsValue(data);
        });
      }
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (data['uninstallUser']) {
          data['uninstallUser'] = data['uninstallUser'].join('、');
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