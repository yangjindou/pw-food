<template>
  <div>
    <u-breadcrumb :items="['公告管理','公告列表']" title="公告列表" />
    <div class="search">
      <a-form class="search-form" :form="formSearch">
        <a-row :gutter="24">
          <a-col :span="6" >
            <a-form-item label="公告标题">
              <a-input v-decorator="[`title`]" placeholder="请输入" />
            </a-form-item>
          </a-col>
          <a-col :span="12"></a-col>
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
      <div class="table-banner">
        <div class="table-btn">
          <a-button type="primary" @click="add">新增</a-button>
          <a-button type="danger" @click="del">删除</a-button>
        </div>
      </div>
      <div class="table-content">
        <a-table :columns="columns" size="small"
                 :row-selection="{ selectedRowKeys: selectedRowKeys, type: 'checkbox', onChange: onSelectChange}"
                 :row-key="row => row['id']" :data-source="tableData"
                 :pagination="pagination" :loading="loading" @change="handleTableChange">
          <template slot="operation" slot-scope="row">
            <div class="operation-btn">
              <a @click="detail(row)">详情</a>
              <a @click="update(row)">修改</a>
            </div>
          </template>
        </a-table>
      </div>
    </div>
    <t-form ref="form" />
    <t-detail ref="detail" />
  </div>
</template>

<script>
import tForm from "./form";
import table from './table';
import tDetail from "./detail";
export default {
  components: {tForm, tDetail},
  data() {
    return {
      formSearch: this.$form.createForm(this, { name: 'search_user' }),
      searchParams: {},
      basicParams: {},
      ...table.data,
    };
  },
  mounted() {
    this.basicParams['createUser'] = this.$store.state.user.userData['id'];
    this.fetch();
  },
  methods: {
    detail(item) {
      this.$refs.detail.open(item);
    },
    add() {
      this.$refs.form.add();
    },
    update(row) {
      this.$refs.form.update(row);
    },
    del() {
      if (this.selectedRowKeys.length === 0) {
        this.$message.error("请选择数据！");
        return;
      }
      const _this = this;
      this.$confirm({
        title: '确定删除这些数据?',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          let params = {
            ids: _this.selectedRowKeys.join(',')
          };
          _this.$axios.delete("/notice", {params}).then(res => {
            if (res) {
              _this.$message.success("删除成功");
              _this.fetch();
              _this.selectedRowKeys = [];
            }
          });
        },
      });
    },
    searchReset() {
      this.formSearch.resetFields();
      this.searchParams = {};
      this.fetch();
    },
    searchClick() {
      this.pagination.current = 1; // 搜索后跳转到第一页
      this.formSearch.validateFields((err, data) => {
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
  .table-banner {
    margin-bottom: 20px;
    display: flex;
    justify-content: space-between;
    .table-btn {
      button {
        margin: 0 5px;
      }
    }
  }

  .operation-btn {
    & > * {
      margin-right: 15px;
    }
  }
}
</style>