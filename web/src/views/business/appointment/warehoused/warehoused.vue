<template>
  <a-modal v-model="formModal" title="出仓" :maskClosable="false">
    <a-form class="modal-form" :form="form">
      <a-form-item label="id" hidden>
        <a-input v-decorator="['id']" placeholder="id" />
      </a-form-item>
      <a-form-item label="禽类（Kg）">
        <a-input v-decorator="['warehousedWeightPoultry',{rules: integerRules}]" placeholder="禽类（Kg）" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="畜类（Kg）">
        <a-input v-decorator="['warehousedWeightLivestock',{rules: integerRules}]" placeholder="畜类（Kg）" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="水产品（Kg）">
        <a-input v-decorator="['warehousedWeightAquatic',{rules: integerRules}]" placeholder="水产品（Kg）" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="其他（Kg）">
        <a-input v-decorator="['warehousedWeightOther',{rules: integerRules}]" placeholder="其他（Kg）" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="消杀证明">
        <u-upload-list :file-list="fileList.warehousedDisinfectionCertificate" :allow-type="['jpg','jpeg','png']"
                       @change="uploadChange(fileList.warehousedDisinfectionCertificate, $event)"
                       :show-upload-list="true" :disabled="disabled" />
      </a-form-item>
      <a-form-item label="核酸检测">
        <u-upload-list :file-list="fileList.warehousedInspectionCertificate" :allow-type="['jpg','jpeg','png']"
                       @change="uploadChange(fileList.warehousedInspectionCertificate, $event)"
                       :show-upload-list="true" :disabled="disabled" />
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
import {integerRules} from "@/utils/formRules";
export default {
  data() {
    return {
      form: this.$form.createForm(this, { name: 'form' }),
      formState: '',
      formModal: false,
      integerRules,
      disabled: false,
      fileList: {
        warehousedDisinfectionCertificate: [],
        warehousedInspectionCertificate: [],
      },
      fileUrl: process.env.VUE_APP_FILE_URL
    }
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
      this.reasonVisible = false;
      this.form.resetFields();
      this.fileList.warehousedDisinfectionCertificate.splice(0, this.fileList.warehousedDisinfectionCertificate.length);
      this.fileList.warehousedInspectionCertificate.splice(0, this.fileList.warehousedInspectionCertificate.length);
      if (row) {
        this.$nextTick(() => {
          let data = objUtils.getObjectByKey(row, "id", "warehousedWeightPoultry",
              "warehousedWeightLivestock", "warehousedWeightAquatic", "warehousedWeightOther");
          if (data['samplingDate']) {
            data['samplingDate'] = this.$moment(data['samplingDate']);
          }
          this.form.setFieldsValue(data);
          let imgData = objUtils.getObjectByKey(row,"warehousedDisinfectionCertificate", "warehousedInspectionCertificate");
          if (imgData['warehousedDisinfectionCertificate']) {
            imgData['warehousedDisinfectionCertificate'].split(',').forEach((e,index) => {
              this.fileList.warehousedDisinfectionCertificate.push({
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
          if (imgData['warehousedInspectionCertificate']) {
            imgData['warehousedInspectionCertificate'].split(',').forEach((e,index) => {
              this.fileList.warehousedInspectionCertificate.push({
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
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        if (data['samplingDate']) {
          data['samplingDate'] = data['samplingDate'].format('YYYY-MM-DD');
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