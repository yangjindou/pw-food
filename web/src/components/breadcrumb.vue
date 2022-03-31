<template>
  <div id="breadcrumb" :style="{background: bgColor}">
    <a-breadcrumb v-if="items" class="breadcrumb">
      <a-breadcrumb-item>首页</a-breadcrumb-item>
      <a-breadcrumb-item v-for="(item,index) in items" :key="index">{{ item }}</a-breadcrumb-item>
    </a-breadcrumb>
    <div class="banner" v-if="title">
      <div class="banner-title">{{ title }}</div>
      <div v-if="backBtn" class="banner-btn">
        <a-button type="primary" @click="backClick">返回</a-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "breadcrumb",
  props: {
    items: {
      type: Array
    },
    bgColor: {
      type: String,
      default: 'white'
    },
    title: {
      type: String
    },
    backBtn: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    backClick() {
      this.$router.go(-1);
    }
  }
}
</script>

<style scoped lang="less">
.breadcrumb {
  height: 40px;
  display: flex;
  align-items: center;
  padding: 0 30px;
}
.banner {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 30px;
  background: white;
  .banner-title {
    font-size: 20px;
    height: 50px;
    display: flex;
    align-items: center;
  }
  .banner-btn {

  }
}

</style>