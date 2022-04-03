<template>
  <div>
    <breadcrumb :items="['数据字典','字典列表']" title="字典列表" />
    <div class="search">
      <a-form class="search-form" :form="formSearch">
        <a-row :gutter="24">
          <a-col :span="6" >
            <a-form-item label="名称">
              <a-input v-decorator="[`name`]" placeholder="请输入" />
            </a-form-item>
          </a-col>
          <a-col :span="6" >
            <a-form-item label="标识">
              <a-input v-decorator="[`sign`]" placeholder="请输入" />
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
            <a @click="join(row)">关联</a>
            <span>&nbsp;&nbsp;&nbsp;</span>
            <a @click="update(row)">修改</a>
          </template>
        </a-table>
      </div>
    </div>
    <t-form ref="form" />
    <data-form  ref="dataForm"/>
  </div>
</template>

<script>
import dataForm from "./data";
import tForm from "./form";
import Breadcrumb from "@/components/breadcrumb";
import table from './table';
export default {
  components: {Breadcrumb, tForm, dataForm},
  data() {
    return {
      formSearch: this.$form.createForm(this, { name: 'search_user' }),
      searchParams: {},
      ...table.data,
    };
  },
  mounted() {
    this.fetch();
  },
  methods: {
    join(row) {
      this.$refs.dataForm.open(row);
    },
    add() {
      this.$refs.form.add();
    },
    update(row) {
      this.$refs.form.update(row);
    },
    del() {
      if (this.selectedRowKeys.length === 0) {
        this.$message.error("请选择数据!");
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
          _this.$axios.delete("/dict", {params}).then(res => {
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
}
</style>