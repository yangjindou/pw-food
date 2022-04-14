<template>
  <a-upload name="file" :action="uploadUrl" :list-type="listType" :file-list="list"
            @preview="handlePreview" @change="uploadChange" :disabled="disabled"
            :showUploadList="showUploadList" :before-upload="beforeUpload">
    <slot></slot>
  </a-upload>
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
      fileType: {
        // 'doc': 'application/msword',
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
    handlePreview(file) {
      this.$emit('preview', file);
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
        this.$emit('change', {type: file.status, file: file.response});
      } else {
        this.list = fileList;
      }
    },
  }
}
</script>

<style scoped>

</style>