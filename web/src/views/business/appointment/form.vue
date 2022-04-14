<template>
  <a-modal v-model="formModal" :title="formState" :maskClosable="false">
    <a-form id="warehouseUser-form" class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="上报的省">
        <AreaCascader :field-names="{
          label: 'name',
          value: 'name',
          children: 'children'
        }" v-decorator="['area',{rules}]" placeholder="上报的省（省市区）" :disabled="disabled"/>
      </a-form-item>
      <a-form-item label="预约监管仓">
        <a-select v-decorator="[`warehouse`,{rules}]" placeholder="预约监管仓" :disabled="disabled">
          <a-select-option v-for="item in selectList.warehouse" :key="item.id" :value="item.id">{{item.name}}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="入仓时间">
        <a-date-picker v-decorator="[`warehousingDate`,{rules}]" placeholder="入仓时间" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="货物类别">
        <a-select v-decorator="[`goodType`,{rules}]" placeholder="货物类别" :disabled="disabled">
          <a-select-option v-for="item in selectList.goodType" :key="item.id" :value="item.id">{{item.name}}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="货物名称">
        <a-input v-decorator="['goodName',{rules}]" placeholder="货物名称" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="货物来源">
        <a-select v-decorator="[`goodSource`,{rules}]" placeholder="货物来源" :disabled="disabled">
          <a-select-option v-for="item in selectList.goodSource" :key="item.id" :value="item.id">{{item.name}}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="来源名称">
        <a-input v-decorator="['sourceName',{rules}]" placeholder="来源名称" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="出仓证明">
        <a-input v-decorator="['warehousedProve',{rules}]" placeholder="出仓证明" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="原产国/产地">
        <a-input v-decorator="['originPlace',{rules}]" placeholder="原产国/产地" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="件数">
        <a-input v-decorator="['amount',{rules: integerRules}]" placeholder="件数" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="重量（Kg）">
        <a-input v-decorator="['weight',{rules: integerRules}]" placeholder="重量（Kg）" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="检疫证明">
        <u-upload-list :file-list="fileList.quarantineCertificate" :allow-type="['jpg','jpeg','png']"
                       @preview="handlePreview" @change="uploadChange(fileList.quarantineCertificate, $event)"
                       :show-upload-list="true" :disabled="disabled">
          <a-icon type="plus" style="font-size: 20px;" />
          <div class="ant-upload-text">
            上传
          </div>
        </u-upload-list>
      </a-form-item>
      <a-form-item label="报关单">
        <u-upload-list :file-list="fileList.customsBill" :allow-type="['jpg','jpeg','png']"
                       @preview="handlePreview" @change="uploadChange(fileList.customsBill, $event)"
                       :show-upload-list="true" :disabled="disabled">
          <a-icon type="plus" style="font-size: 20px;" />
          <div class="ant-upload-text">
            上传
          </div>
        </u-upload-list>
      </a-form-item>
      <a-form-item label="港口核酸证明">
        <u-upload-list :file-list="fileList.portInspectionCertificate" :allow-type="['jpg','jpeg','png']"
                       @preview="handlePreview" @change="uploadChange(fileList.portInspectionCertificate, $event)"
                       :show-upload-list="true" :disabled="disabled">
          <a-icon type="plus" style="font-size: 20px;" />
          <div class="ant-upload-text">
            上传
          </div>
        </u-upload-list>
      </a-form-item>
      <a-form-item label="港口消杀证明">
        <u-upload-list :file-list="fileList.portDisinfectionCertificate" :allow-type="['jpg','jpeg','png']"
                       @preview="handlePreview" @change="uploadChange(fileList.portDisinfectionCertificate, $event)"
                       :show-upload-list="true" :disabled="disabled">
          <a-icon type="plus" style="font-size: 20px;" />
          <div class="ant-upload-text">
            上传
          </div>
        </u-upload-list>
      </a-form-item>
      <a-form-item label="承运司机">
        <a-input v-decorator="['driver',{rules}]" placeholder="承运司机" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="车牌号">
        <a-input v-decorator="['carNumber',{rules}]" placeholder="车牌号" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="司机电话">
        <a-input v-decorator="['driverPhone',{rules}]" placeholder="司机电话" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="备案时间">
        <a-date-picker v-decorator="[`filingDate`,{rules}]" placeholder="备案时间" :disabled="disabled" />
      </a-form-item>
    </a-form>
    <template slot="footer">
      <a-button @click="modalCancel">取消</a-button>
      <a-button v-if="!disabled" type="primary" @click="modalOk">确定</a-button>
    </template>
  </a-modal>
</template>

<script>
import {rules, integerRules} from "@/utils/formRules";
import objUtils from "@/utils/objUtils";
import AreaCascader from "@/components/areaCascader";
import apiUtils from "@/utils/apiUtils";
export default {
  components: {AreaCascader},
  data() {
    return {
      form: this.$form.createForm(this, { name: 'form' }),
      formState: '',
      formModal: false,
      rules,
      integerRules,
      disabled: false,
      selectList: {
        warehouse: [],
        goodType: [],
        goodSource: [],
      },
      fileList: {
        quarantineCertificate: [],
        customsBill: [],
        portInspectionCertificate: [],
        portDisinfectionCertificate: [],
      }
    }
  },
  mounted() {
    this.getSelectList();
  },
  methods: {
    uploadChange(item, {type, file}) {
      // if (type === 'removed') {
      //   this.$axios.delete("/memberMaterial", {params: {ids: file.memberMaterialId}}).then(res => {
      //     if (res) {
      //       this.$message.success('删除成功');
      //       // this.getData();
      //       this.refreshWebStep();
      //     }
      //   });
      // } else {
      //   let params = {
      //     "fileId": file.fileId,
      //     "materialId": item.materialId,
      //     "memberExtendId": this.memberExtendId,
      //   }
      //   this.$axios.post("/memberMaterial/createSubmitAndAudit", params).then(res => {
      //     if (res) {
      //       // this.getData();
      //       this.refreshWebStep();
      //     }
      //   });
      // }
    },
    async handlePreview(file) {
      // if (!file.url && !file.preview) {
      //   file.preview = await getBase64(file.originFileObj);
      // }
      // this.previewImage = file.url || file.preview;
      // this.previewVisible = true;
    },
    getSelectList() {
      this.$axios.get(`/warehouse/list?stateName=正常`).then(res => {
        if (res) {
          res.data.forEach(item => this.selectList.warehouse.push(item));
        }
      });
      apiUtils.getDictData(this.selectList.goodType, 'goodType');
      apiUtils.getDictData(this.selectList.goodSource, 'goodSource');
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
          let data = objUtils.getObjectByKey(row, "id", "area", "warehouse", "warehousingDate",
              "goodType", "goodName", "goodSource", "sourceName", "warehousedProve",
              "originPlace", "amount", "weight", "quarantineCertificate", "customsBill", "portInspectionCertificate",
              "portDisinfectionCertificate", "driver", "carNumber", "driverPhone", "filingDate");
          if (data['warehousingDate']) {
            data['warehousingDate'] = this.$moment(data['warehousingDate']);
          }
          if (data['filingDate']) {
            data['filingDate'] = this.$moment(data['filingDate']);
          }
          if (data['area']) {
            data['area'] = data['area'].split('/');
          }
          this.form.setFieldsValue(data);
        });
      }
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (data['warehousingDate']) {
          data['warehousingDate'] = data['warehousingDate'].format('YYYY-MM-DD');
        }
        if (data['filingDate']) {
          data['filingDate'] = data['filingDate'].format('YYYY-MM-DD');
        }
        if (data['area']) {
          data['area'] = data['area'].join("/");
        }
        if (this.formState === '新增') {
          this.$axios.post("/appointment", data).then(res => {
            if (res) {
              this.$message.success("添加成功");
              this.formModal = false;
              this.$parent.fetch();
            }
          });
        } else if (this.formState === '修改') {
          this.$axios.put("/appointment", data).then(res => {
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