<template>
  <a-modal v-model="formModal" :title="formState" :maskClosable="false">
    <a-form id="warehouseUser-form" class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="姓名">
        <a-input v-decorator="['name',{rules}]" placeholder="姓名" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="性别">
        <a-radio-group v-decorator="['gender',{rules}]" :options="['男','女']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="年龄">
        <a-input v-decorator="['age',{rules}]" placeholder="年龄" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="手机号">
        <a-input v-decorator="['phone',{rules}]" placeholder="手机号" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="工作种类">
        <a-input v-decorator="['workType',{rules}]" placeholder="工作种类" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="监管仓">
        <a-select placeholder="监管仓" v-decorator="[`warehouse`,{rules}]" :disabled="disabled">
          <a-select-option v-for="item in selectList.warehouse" :key="item.id" :value="item.id">{{item.name}}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="是否接种疫苗">
        <a-radio-group v-decorator="['isInoculate',{rules}]" :options="['是','否']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="接种次数">
        <a-input v-decorator="['inoculateCount',{rules}]" placeholder="接种次数" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="接种时间">
        <a-date-picker v-decorator="[`inoculateDate`,{rules}]" placeholder="接种时间" :disabled="disabled" />
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
          let data = objUtils.getObjectByKey(row, "id", "name", "gender", "age", "phone",
              "workType", "warehouse", "isInoculate", "inoculateCount", "inoculateDate");
          if (data['inoculateDate']) {
            data['inoculateDate'] = this.$moment(data['inoculateDate']);
          }
          this.form.setFieldsValue(data);
        });
      }
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (data['inoculateDate']) {
          data['inoculateDate'] = data['inoculateDate'].format('YYYY-MM-DD');
        }
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