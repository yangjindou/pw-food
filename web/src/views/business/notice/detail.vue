<template>
  <a-modal v-model="formModal" title="公告详情" :footer="null"  :maskClosable="false">
    <div class="notice-title">{{formData['title']}}</div>
    <div class="notice-time">{{formData['createDate']}}</div>
    <pre class="notice-content">{{formData['content']}}</pre>
  </a-modal>
</template>

<script>
export default {
  data() {
    return {
      formModal: false,
      formData: {},
    }
  },
  methods: {
    open(row) {
      this.formModal = true;
      this.formData = row;
    },
  }
}
</script>

<style lang="less" scoped>

.notice-title {
  margin: 0 20px;
  text-align: center;
  font-size: 17px;
}

.notice-time {
  margin-top: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #7d7d7d;
}

.notice-content {
  // margin-top: 60rpx;
  // margin-bottom: 60rpx;
  margin: 30px 20px;
}
</style>