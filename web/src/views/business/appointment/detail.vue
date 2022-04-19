<template>
  <a-modal :width="800" v-model="formModal" :title="formState" :maskClosable="false">
    <a-form class="form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="备案单号">
            <a-input v-decorator="['filingOrder']" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="备案状态">
            <a-input v-decorator="['filingState']" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="备案时间">
            <a-input v-decorator="['createDate']" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="上报的省">
            <a-input v-decorator="['area']" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="预约监管仓">
            <a-input v-decorator="['warehouseName']" placeholder="预约监管仓" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="货物类别">
            <a-input v-decorator="['goodTypeName']" placeholder="货物类别" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="货物名称">
            <a-input v-decorator="['goodName']" placeholder="货物名称" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="货物来源">
            <a-input v-decorator="['goodSourceName']" placeholder="货物来源" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="来源名称">
            <a-input v-decorator="['sourceName']" placeholder="来源名称" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="原产国/产地">
            <a-input v-decorator="['originPlace']" placeholder="原产国/产地" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="件数">
            <a-input v-decorator="['amount']" placeholder="件数" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="重量（Kg）">
            <a-input v-decorator="['weight']" placeholder="重量（Kg）" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="承运司机">
            <a-input v-decorator="['driver']" placeholder="承运司机" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="车牌号">
            <a-input v-decorator="['carNumber']" placeholder="车牌号" :disabled="disabled" />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="司机电话">
            <a-input v-decorator="['driverPhone']" placeholder="司机电话" :disabled="disabled" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-form-item label="驳回理由">
        <a-textarea class="textarea" v-decorator="['refuseReason']" :disabled="disabled" />
      </a-form-item>
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
      disabled: false,
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
  },
  methods: {
    uploadChange(item, {fileList}) {
      item.splice(0, item.length);
      fileList.forEach(file => item.push(file));
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
          let data = objUtils.getObjectByKey(row, "id", "area", "warehouseName", "filingOrder",
              "goodTypeName", "goodName", "goodSourceName", "sourceName",
              "originPlace", "amount", "weight", "driver", "carNumber", "driverPhone", "createDate",
              "filingState", "refuseReason");
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
    modalCancel() {
      this.formModal = false;
    }
  }
}
</script>

<style lang="less" scoped>

</style>