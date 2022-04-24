<template>
  <div>
    <div id="notice">
      <div class="notice-title">
        <div class="title-left">公告</div>
        <div class="title-right" @click="notice">更多</div>
      </div>
      <div class="notice-list">
        <div class="notice-list-item" v-for="(item, index) in noticeList" :key="index">
          <template v-if="index < 10">
            <div class="item-title" @click="notice(item)">{{item['title'].length > 10 ? item['title'].substr(0,20) + "..." : item['title']}}</div>
            <div class="item-time">{{item['createDate']}}</div>
          </template>
        </div>
      </div>
      <t-form ref="form" />
    </div>
  </div>
</template>
<script>
import tForm from "../business/notice/detail";
export default {
  components: {tForm},
  data() {
    return {
      noticeList: [],
    }
  },
  mounted() {
    this.getNotice();
  },
  methods: {
    notice(item) {
      if(item.title) {
        this.$refs.form.open(item);
      } else {
        this.$router.push({name: 'notice-user'})
      }
    },
    getNotice() {
      this.$axios.get("/notice/list").then(res => {
        if (res && res.data && res.data.length) {
          this.noticeList = res.data;
        }
      });
    },
  },
};
</script>
<style lang="less" scoped>
#notice {
  margin: 15px;
  background: white;
  width: 500px;
  padding: 20px;

  .notice-title {
    margin-bottom: 10px;
    padding-bottom: 10px;
    border-bottom: 1px solid #e8e8e8;
    display: flex;
    justify-content: space-between;
    align-items: flex-end;

    .title-left {
      font-size: 17px;
    }

    .title-right {
      cursor: pointer;
      color: #1890ff;
    }
  }

  .notice-list-item {
    display: flex;
    justify-content: space-between;
    padding: 10px 0;

    .item-title {
      cursor: pointer;
    }

    .item-title:hover {
      color: #1890ff;
    }
  }
}
</style>