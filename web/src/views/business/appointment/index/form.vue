<template>
  <a-modal :width="800" v-model="formModal" :title="formState" :maskClosable="false">
    <a-form class="form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="上报的省">
            <AreaCascader :field-names="{
          label: 'name',
          value: 'name',
          children: 'children'
        }" v-decorator="['area',{rules}]" placeholder="上报的省（省市区）" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="预约监管仓">
            <a-select v-decorator="[`warehouse`,{rules}]" placeholder="预约监管仓" :disabled="disabled">
              <a-select-option v-for="item in selectList.warehouse" :key="item.id" :value="item.id">{{item.name}}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="货物类别">
            <a-select v-decorator="[`goodType`,{rules}]" placeholder="货物类别" :disabled="disabled">
              <a-select-option v-for="item in selectList.goodType" :key="item.id" :value="item.id">{{item.name}}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="货物名称">
            <a-input v-decorator="['goodName',{rules}]" placeholder="货物名称" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="货物来源">
            <a-select v-decorator="[`goodSource`,{rules}]" placeholder="货物来源" :disabled="disabled">
              <a-select-option v-for="item in selectList.goodSource" :key="item.id" :value="item.id">{{item.name}}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="来源名称">
            <a-input v-decorator="['sourceName',{rules}]" placeholder="来源名称" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="原产国/产地">
            <a-input v-decorator="['originPlace',{rules}]" placeholder="原产国/产地" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="件数">
            <a-input v-decorator="['amount',{rules: numberRules}]" placeholder="件数" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="重量（Kg）">
            <a-input v-decorator="['weight',{rules: numberRules}]" placeholder="重量（Kg）" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="承运司机">
            <a-input v-decorator="['driver',{rules}]" placeholder="承运司机" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="车牌号">
            <a-input v-decorator="['carNumber',{rules}]" placeholder="车牌号" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="司机电话">
            <a-input v-decorator="['driverPhone',{rules}]" placeholder="司机电话" :disabled="disabled" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-form-item label="出仓证明">
        <u-upload-list :file-list="fileList.warehousedProve" :allow-type="['jpg','jpeg','png']"
                       @change="uploadChange(fileList.warehousedProve, $event)"
                       :show-upload-list="true" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="检疫证明">
        <u-upload-list :file-list="fileList.quarantineCertificate" :allow-type="['jpg','jpeg','png']"
                       @change="uploadChange(fileList.quarantineCertificate, $event)"
                       :show-upload-list="true" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="报关单">
        <u-upload-list :file-list="fileList.customsBill" :allow-type="['jpg','jpeg','png']"
                       @change="uploadChange(fileList.customsBill, $event)"
                       :show-upload-list="true" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="港口核酸证明">
        <u-upload-list :file-list="fileList.portInspectionCertificate" :allow-type="['jpg','jpeg','png']"
                       @change="uploadChange(fileList.portInspectionCertificate, $event)"
                       :show-upload-list="true" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="港口消杀证明">
        <u-upload-list :file-list="fileList.portDisinfectionCertificate" :allow-type="['jpg','jpeg','png']"
                       @change="uploadChange(fileList.portDisinfectionCertificate, $event)"
                       :show-upload-list="true" :disabled="disabled" />
      </a-form-item>
    </a-form>
    <template slot="footer">
      <a-button @click="modalCancel">取消</a-button>
      <a-button v-if="!disabled" type="primary" @click="modalOk">保存</a-button>
      <a-button v-if="!disabled" type="primary" @click="modalOk('提交')">保存并提交</a-button>
    </template>
  </a-modal>
</template>

<script>
import {rules, numberRules} from "@/utils/formRules";
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
      numberRules,
      disabled: false,
      selectList: {
        warehouse: [],
        goodType: [],
        goodSource: [],
      },
      fileList: {
        warehousedProve: [],
        quarantineCertificate: [],
        customsBill: [],
        portInspectionCertificate: [],
        portDisinfectionCertificate: [],
      },
      fileUrl: process.env.VUE_APP_FILE_URL
    }
  },
  mounted() {
    this.getSelectList();
  },
  methods: {
    uploadChange(item, {fileList}) {
      item.splice(0, item.length);
      fileList.forEach(file => item.push(file));
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
      this.fileList.warehousedProve.splice(0, this.fileList.warehousedProve.length);
      this.fileList.quarantineCertificate.splice(0, this.fileList.quarantineCertificate.length);
      this.fileList.customsBill.splice(0, this.fileList.customsBill.length);
      this.fileList.portInspectionCertificate.splice(0, this.fileList.portInspectionCertificate.length);
      this.fileList.portDisinfectionCertificate.splice(0, this.fileList.portDisinfectionCertificate.length);
      if (row) {
        this.$nextTick(() => {
          let data = objUtils.getObjectByKey(row, "id", "area", "warehouse",
              "goodType", "goodName", "goodSource", "sourceName",
              "originPlace", "amount", "weight", "driver", "carNumber", "driverPhone");
          if (data['area']) {
            data['area'] = data['area'].split('/');
          }
          this.form.setFieldsValue(data);
          let imgData = objUtils.getObjectByKey(row,"warehousedProve", "quarantineCertificate", "customsBill",
              "portInspectionCertificate", "portDisinfectionCertificate");
          if (imgData['warehousedProve']) {
            imgData['warehousedProve'].split(',').forEach((e,index) => {
              this.fileList.warehousedProve.push({
                uid: `-${index}`,
                name: e,
                status: 'done',
                url: this.fileUrl + e,
                response: {
                  name: e
                }
              });
            })
          }
          if (imgData['quarantineCertificate']) {
            imgData['quarantineCertificate'].split(',').forEach((e,index) => {
              this.fileList.quarantineCertificate.push({
                uid: `-${index}`,
                name: e,
                status: 'done',
                url: this.fileUrl + e,
                response: {
                  name: e
                }
              });
            })
          }
          if (imgData['customsBill']) {
            imgData['customsBill'].split(',').forEach((e,index) => {
              this.fileList.customsBill.push({
                uid: `-${index}`,
                name: e,
                status: 'done',
                url: this.fileUrl + e,
                response: {
                  name: e
                }
              });
            })
          }
          if (imgData['portInspectionCertificate']) {
            imgData['portInspectionCertificate'].split(',').forEach((e,index) => {
              this.fileList.portInspectionCertificate.push({
                uid: `-${index}`,
                name: e,
                status: 'done',
                url: this.fileUrl + e,
                response: {
                  name: e
                }
              });
            })
          }
          if (imgData['portDisinfectionCertificate']) {
            imgData['portDisinfectionCertificate'].split(',').forEach((e,index) => {
              this.fileList.portDisinfectionCertificate.push({
                uid: `-${index}`,
                name: e,
                status: 'done',
                url: this.fileUrl + e,
                response: {
                  name: e
                }
              });
            })
          }
        });
      }
    },
    modalOk(type) {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (data['area']) {
          data['area'] = data['area'].join("/");
        }
        if (this.fileList.warehousedProve.length) {
          data['warehousedProve'] = this.fileList.warehousedProve.map(e => e.response.name).join(',');
        }
        if (!this.fileList.quarantineCertificate.length) {
          this.$message.error('检疫证明未上传');
          return;
        }
        if (!this.fileList.customsBill.length) {
          this.$message.error('报关单未上传');
          return;
        }
        if (!this.fileList.portInspectionCertificate.length) {
          this.$message.error('港口核酸证明未上传');
          return;
        }
        if (!this.fileList.portDisinfectionCertificate.length) {
          this.$message.error('港口消杀证明未上传');
          return;
        }
        data['quarantineCertificate'] = this.fileList.quarantineCertificate.map(e => e.response.name).join(',');
        data['customsBill'] = this.fileList.customsBill.map(e => e.response.name).join(',');
        data['portInspectionCertificate'] = this.fileList.portInspectionCertificate.map(e => e.response.name).join(',');
        data['portDisinfectionCertificate'] = this.fileList.portDisinfectionCertificate.map(e => e.response.name).join(',');
        data['filingState'] = type === '提交' ? '待审核' : '保存';
        data['refuseReason'] = '';
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