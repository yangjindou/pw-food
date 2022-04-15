<template>
  <div>
    <a-upload name="file" :action="uploadUrl" :list-type="listType" :file-list="list"
              @preview="handlePreview" @change="uploadChange" :disabled="disabled"
              :showUploadList="showUploadList" :before-upload="beforeUpload">
      <template v-if="!disabled">
        <template v-if="$slots.default">
          <slot></slot>
        </template>
        <div v-else>
          <a-icon type="plus" style="font-size: 20px;" />
          <div class="ant-upload-text">
            上传
          </div>
        </div>
      </template>
    </a-upload>
    <a-modal v-model="previewVisible" footer="" @cancel="handleCancel">
      <img alt="example" style="width: 100%" :src="previewImage" />
    </a-modal>
  </div>
</template>

<script>
export default {
  name: "uUploadList",
  props: {
    showUploadList: {
      type: Boolean,
      default: false
    },
    allowType: {
      type: Array ,
      default: () => ['docx','pdf','jpg','jpeg']
    },
    maximumSize: {
      type: Number,
      default: 200
    },
    listType: {
      type: String,
      default: 'picture-card'
    },
    fileList: {
      type: Array ,
      default: () => []
    },
    disabled: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      uploadUrl: process.env.VUE_APP_API_BASE_URL + '/file/upload',
      list: [],
      previewVisible: false,
      previewImage: '',
      fileType: {
        'doc': 'application/msword',
        'docx': 'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
        'pdf': 'application/pdf',
        'jpg': 'image/jpeg',
        'jpeg': 'image/jpeg',
        'png': 'image/png',
        'xlsx': 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
        'xls': 'application/vnd.ms-excel',
      },
    }
  },
  watch: {
    fileList: {
      handler(newValue) {
        if (newValue.length) {
          console.log('list修改');
          this.list = newValue;
        }
      },
    }
  },
  mounted() {
    if (this.fileList.length) {
      this.list = this.fileList;
    }
  },
  methods: {
    handleCancel() {
      this.previewVisible = false;
    },
    async handlePreview(file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj);
      }
      this.previewImage = file.url || file.preview;
      this.previewVisible = true;
    },
    beforeUpload(file) {
      return new Promise((resolve, reject) => {
        const allowType = this.allowType.map(e => this.fileType[e]);
        if (allowType.includes(file.type)) {
          if (file.size / 1024 / 1024 < this.maximumSize) {
            return resolve(true);
          } else {
            this.$message.error(`文件大小超过${this.maximumSize}M！`)
            return reject(false);
          }
        } else {
          this.$message.error('文件格式错误，仅支持'+ this.allowType);
          return reject(false);
        }
      });
    },
    uploadChange({file, fileList}) {
      if (file.status === 'error') {
        this.$message.error(file.response.message);
        fileList.pop();
        this.list = fileList;
      } else if (['done','removed'].includes(file.status)) {
        this.list = fileList;
        this.$emit('change', {type: file.status, file, fileList});
      } else {
        this.list = fileList;
      }
    },
  }
}
function getBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}
</script>

<style scoped>

</style>