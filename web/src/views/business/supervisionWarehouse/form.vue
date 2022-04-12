<template>
  <a-modal v-model="formModal" :title="formState" :maskClosable="false">
    <a-form id="supervisionWarehouse-form" class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="国家平台备案号">
        <a-input v-decorator="['recordNumber',{rules}]" placeholder="国家平台备案号" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="监管仓编码">
        <a-input v-decorator="['code',{rules}]" placeholder="监管仓编码" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="监管仓名称">
        <a-input v-decorator="['name',{rules}]" placeholder="监管仓名称" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="集中监管仓分类">
        <a-input v-decorator="['type',{rules}]" placeholder="集中监管仓分类" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="建筑面积（平方米）">
        <a-input v-decorator="['s',{rules}]" placeholder="建筑面积（平方米）" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="容量（千克）">
        <a-input v-decorator="['v',{rules}]" placeholder="容量（千克）" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="监管仓所在省（市、区）" >
        <AreaCascader :field-names="{
          label: 'name',
          value: 'name',
          children: 'children'
        }" v-decorator="['area',{rules}]" placeholder="监管仓所在省（市、区）" :disabled="disabled"/>
      </a-form-item>
      <a-form-item label="详细地址">
        <a-input v-decorator="['address',{rules}]" placeholder="详细地址" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="启用日期">
        <a-date-picker v-decorator="[`enableDate`,{rules}]" placeholder="启用日期" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="运营企业名称">
        <a-input v-decorator="['enterpriseName',{rules}]" placeholder="运营企业名称" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="运营企业统一社会信用代码">
        <a-input v-decorator="['enterpriseCode',{rules}]" placeholder="运营企业统一社会信用代码" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="联系电话">
        <a-input v-decorator="['phone',{rules}]" placeholder="联系电话" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="集中监管仓经度">
        <a-input v-decorator="['longitude',{rules}]" placeholder="集中监管仓经度" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="集中监管仓纬度">
        <a-input v-decorator="['latitude',{rules}]" placeholder="集中监管仓纬度" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="监管仓状态">
        <a-select placeholder="监管仓状态" v-decorator="[`state`,{rules}]" :disabled="disabled">
          <a-select-option v-for="item in selectList.state" :key="item.id" :value="item.id">{{item.name}}</a-select-option>
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
import AreaCascader from '@/components/areaCascader/index'
import objUtils from "@/utils/objUtils";
import apiUtils from "@/utils/apiUtils";
export default {
  components: {AreaCascader},
  data() {
    return {
      form: this.$form.createForm(this, { name: 'form' }),
      formState: '',
      formModal: false,
      rules: [{
        required: true,
        message: '必填项',
      }],
      selectList: {
        state: []
      },
      disabled: false,
    }
  },
  mounted() {
    this.getSelectList();
  },
  methods: {
    getSelectList() {
      apiUtils.getDictData(this.selectList.state, 'waste_disposal_state');
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
          let data = objUtils.getObjectByKey(row, "id", "recordNumber", "code", "name", "type",
              "s", "v", "area", "address", "enableDate", "enterpriseName", "enterpriseCode", "phone",
              "longitude", "latitude", "state");
          if (data['area']) {
            data['area'] = data['area'].split('/');
          }
          if (data['enableDate']) {
            data['enableDate'] = this.$moment(data['enableDate']);
          }
          this.form.setFieldsValue(data);
        });
      }
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (data['area']) {
          data['area'] = data['area'].join("/");
        }
        if (data['enableDate']) {
          data['enableDate'] = data['enableDate'].format('YYYY-MM-DD');
        }
        if (this.formState === '新增') {
          this.$axios.post("/supervisionWarehouse", data).then(res => {
            if (res) {
              this.$message.success("添加成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        } else if (this.formState === '修改') {
          this.$axios.put("/supervisionWarehouse", data).then(res => {
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
#supervisionWarehouse-form {
  .ant-form-item {
    .ant-form-item-label {
      width: 195px;
      text-align: right;
    }
  }
}
</style>