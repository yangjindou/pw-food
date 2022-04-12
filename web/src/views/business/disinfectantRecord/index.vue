<template>
  <div>
    <breadcrumb :items="['环境消杀记录','环境消杀记录列表']" title="环境消杀记录列表" />
    <div v-if="false" class="search">
      <a-form class="search-form" :form="formSearch">
        <a-row :gutter="24">
          <a-col :span="6" >
            <a-form-item label="消毒液名称">
              <a-input v-decorator="[`name`]" placeholder="请输入" />
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
          <a-button type="primary" @click="formOpen('新增')">新增</a-button>
          <a-button type="danger" @click="del">删除</a-button>
          <a-button type="primary" @click="exp">导出</a-button>
        </div>
      </div>
      <div class="table-content">
        <a-table :columns="columns" size="small"
                 :row-selection="{ selectedRowKeys: selectedRowKeys, type: 'checkbox', onChange: onSelectChange}"
                 :row-key="row => row['id']" :data-source="tableData"
                 :pagination="pagination" :loading="loading" @change="handleTableChange">
          <template slot="operation" slot-scope="row">
            <div class="operation-btn">
              <a @click="formOpen('详情', row)">详情</a>
              <a @click="formOpen('修改', row)">修改</a>
            </div>
          </template>
        </a-table>
      </div>
    </div>
    <t-form ref="form" />
  </div>
</template>

<script>
import tForm from "./form";
import Breadcrumb from "@/components/breadcrumb";
import tableMixin from './table';
import apiUtils from "@/utils/apiUtils";
export default {
  components: {Breadcrumb, tForm},
  mixins:[tableMixin],
  data() {
    return {
      formSearch: this.$form.createForm(this, { name: 'search_user' }),
      searchParams: {},
    };
  },
  mounted() {
    this.fetch();
  },
  methods: {
    exp() {
      this.formSearch.validateFields((err, data) => {
        let url = apiUtils.createGetUrl(`${process.env.VUE_APP_API_BASE_URL}/disinfectionRecord/export`, data);
        window.open(url);
      });
    },
    formOpen(state, row) {
      this.$refs.form.open(state, row);
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
          _this.$axios.delete("/disinfectionRecord", {params}).then(res => {
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
  },
}
</script>
<style scoped lang="less">
.search {
  margin: 20px 20px 0 20px;
  .search-form {
    padding: 24px;
    background: white;
  }
}
.table {
  margin: 20px;
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