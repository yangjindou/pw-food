<template>
  <div>
    <breadcrumb :items="['用户管理','用户列表']" title="订单列表" />
    <div class="search">
      <a-form class="search-form" :form="form">
        <a-row :gutter="24">
          <a-col :span="6" >
            <a-form-item label="账号">
              <a-input v-decorator="[`loginName`]" placeholder="请输入" />
            </a-form-item>
          </a-col>
          <a-col :span="6" >
            <a-form-item label="用户名">
              <a-input v-decorator="[`userName`]" placeholder="请输入" />
            </a-form-item>
          </a-col>
          <a-col :span="6" ></a-col>
          <a-col :span="6" :style="{ textAlign: 'right' }">
            <a-form-item>
              <a-button :style="{ marginRight: '10px' }" @click="searchReset">
                重置
              </a-button>
              <a-button type="primary" @click="searchClick">
                查询
              </a-button>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <div class="table">
      <div class="table-title">订单列表</div>
      <div class="table-content">
        <a-table :columns="columns" size="small"
                 :row-key="row => row['id']" :data-source="tableData"
                 :pagination="pagination" :loading="loading" @change="handleTableChange">
          <template slot="operation" slot-scope="row"><a @click="info(row)">详情</a> </template>
        </a-table>
      </div>
    </div>
  </div>
</template>

<script>
import Breadcrumb from "@/components/breadcrumb";
import table from './table';
export default {
  components: {Breadcrumb},
  data() {
    return {
      form: this.$form.createForm(this, { name: 'search_user' }),
      searchParams: {},
      ...table.data
    };
  },
  mounted() {
    this.fetch();
  },
  methods: {
    info(item) {
      this.$router.push({name: 'home-order-form',params: {item}});
    },
    searchReset() {
      this.form.resetFields();
      this.searchParams = {};
      this.fetch();
    },
    searchClick() {
      this.pagination.current = 1; // 搜索后跳转到第一页
      this.form.validateFields((err, data) => {
        this.searchParams = {...data};
        this.fetch();
      });
    },
    ...table.methods
  },
}
</script>
<style scoped lang="less">
.search {
  padding: 20px;
  .search-form {
    padding: 24px;
    background: white;
  }
}
.table {
  margin: 0 20px 20px 20px;
  background: white;
  padding: 30px 20px;
  .table-title {
    margin-bottom: 30px;
    font-size: 16px;
  }

}
</style>