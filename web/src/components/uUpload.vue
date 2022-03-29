<template>
  <a-upload :action="uploadUrl" :showUploadList="showUploadList" :before-upload="beforeUpload"
            name="file" @change="uploadChange">
    <slot></slot>
  </a-upload>
</template>

<script>
export default {
  name: "uUpload",
  props: {
    showUploadList: {
      type: Boolean,
      default: false
    },
    allowType: {
      type: Array ,
      default: () => ['docx','pdf','jpg','jpeg','png']
    },
    maximumSize: {
      type: Number,
      default: 200
    }
  },
  data() {
    return {
      uploadUrl: process.env.VUE_APP_API_BASE_URL + '/file/upload',
      fileList: [],
      fileType: {
        // 'doc': 'application/msword',
        'docx': 'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
        'pdf': 'application/pdf',
        'jpg': 'image/jpeg',
        'jpeg': 'image/jpeg',
        'png': 'image/png',
        'xlsx': 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
        'xls': 'application/vnd.ms-excel',
      }
    }
  },
  methods: {
    beforeUpload(file) {
      const allowType = this.allowType.map(e => this.fileType[e]);
      if (allowType.includes(file.type)) {
        if (file.size / 1024 / 1024 < this.maximumSize) {
          return true;
        } else {
          this.$message.error(`文件大小超过${this.maximumSize}M！`)
          return false;
        }
      } else {
        this.$message.error('文件格式错误，仅支持'+ this.allowType);
        return false;
      }
    },
    uploadChange({file}) {
      if (!file.status) return;
      if (file.status === 'error') {
        this.$message.error(file.response.message)
      } else if (file.status === 'done') {
        this.fileList.push(file);
        this.$emit('change', file.response);
      }
    },
  }
}
</script>

<style scoped>

</style>