<template>
  <a-modal v-model="formModal" :title="formState" :maskClosable="false">
    <a-form id="warehouseUser-form" class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="备案状态">
        <a-input v-decorator="['filingState']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="上报的省">
        <a-input v-decorator="['area']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="预约监管仓">
        <a-input v-decorator="['warehouseName']" placeholder="预约监管仓" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="入仓时间">
        <a-date-picker v-decorator="[`warehousingDate`]" placeholder="入仓时间" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="货物类别">
        <a-input v-decorator="['goodTypeName']" placeholder="货物类别" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="货物名称">
        <a-input v-decorator="['goodName']" placeholder="货物名称" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="货物来源">
        <a-input v-decorator="['goodSourceName']" placeholder="货物来源" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="来源名称">
        <a-input v-decorator="['sourceName']" placeholder="来源名称" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="出仓证明">
        <a-input v-decorator="['warehousedProve']" placeholder="出仓证明" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="原产国/产地">
        <a-input v-decorator="['originPlace']" placeholder="原产国/产地" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="件数">
        <a-input v-decorator="['amount']" placeholder="件数" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="重量（Kg）">
        <a-input v-decorator="['weight']" placeholder="重量（Kg）" :disabled="disabled" />
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
      <a-form-item label="承运司机">
        <a-input v-decorator="['driver']" placeholder="承运司机" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="车牌号">
        <a-input v-decorator="['carNumber']" placeholder="车牌号" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="司机电话">
        <a-input v-decorator="['driverPhone']" placeholder="司机电话" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="备案时间">
        <a-date-picker v-decorator="[`filingDate`]" placeholder="备案时间" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="驳回理由">
        <a-textarea class="textarea" v-decorator="['refuseReason']" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="修改理由">
        <a-textarea class="textarea" v-decorator="['updateReason']" :disabled="disabled" />
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
      this.fileList.quarantineCertificate.splice(0, this.fileList.quarantineCertificate.length);
      this.fileList.customsBill.splice(0, this.fileList.customsBill.length);
      this.fileList.portInspectionCertificate.splice(0, this.fileList.portInspectionCertificate.length);
      this.fileList.portDisinfectionCertificate.splice(0, this.fileList.portDisinfectionCertificate.length);
      if (row) {
        this.$nextTick(() => {
          let data = objUtils.getObjectByKey(row, "id", "area", "warehouseName", "warehousingDate",
              "goodTypeName", "goodName", "goodSourceName", "sourceName", "warehousedProve",
              "originPlace", "amount", "weight", "driver", "carNumber", "driverPhone", "filingDate",
              "filingState", "refuseReason", "updateReason");
          if (data['warehousingDate']) {
            data['warehousingDate'] = this.$moment(data['warehousingDate']);
          }
          if (data['filingDate']) {
            data['filingDate'] = this.$moment(data['filingDate']);
          }
          this.form.setFieldsValue(data);
          let imgData = objUtils.getObjectByKey(row,"quarantineCertificate", "customsBill", "portInspectionCertificate", "portDisinfectionCertificate");
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
        if (data['warehousingDate']) {
          data['warehousingDate'] = data['warehousingDate'].format('YYYY-MM-DD');
        }
        if (data['filingDate']) {
          data['filingDate'] = data['filingDate'].format('YYYY-MM-DD');
        }
        if (data['area']) {
          data['area'] = data['area'].join("/");
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
        data['filingState'] = type ? '待审核' : '保存';
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