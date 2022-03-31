<template>
  <div>
    <breadcrumb :items="['用户管理','用户列表']" title="订单列表" />
    <div class="search">
      <a-form class="search-form" :form="formSearch">
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
      <div class="table-banner">
        <div class="table-title">管理员列表</div>
        <div class="table-btn">
          <a-button type="primary" @click="action('add')">新增</a-button>
          <a-button type="danger" @click="action('del')">删除</a-button>
        </div>
      </div>
      <div class="table-content">
        <a-table :columns="columns" size="small"
                 :row-selection="{ selectedRowKeys: selectedRowKeys, type: 'checkbox', onChange: onSelectChange}"
                 :row-key="row => row['id']" :data-source="tableData"
                 :pagination="pagination" :loading="loading" @change="handleTableChange">
          <template slot="operation" slot-scope="row">
            <a @click="action('info', row)">详情</a>
            <span>&nbsp;|&nbsp;</span>
            <a @click="action('update', row)">修改</a>
          </template>
        </a-table>
      </div>
    </div>
    <a-modal :width="800" v-model="formModal" title="添加更多材料" @ok="modalOk">
      <a-form class="modal-form" :form="form">
        123
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import Breadcrumb from "@/components/breadcrumb";
import table from './table';
export default {
  components: {Breadcrumb},
  data() {
    return {
      formState: '',
      formModal: false,
      formSearch: this.$form.createForm(this, { name: 'search_user' }),
      form: this.$form.createForm(this, { name: 'form_user' }),
      searchParams: {},
      ...table.data
    };
  },
  mounted() {
    this.fetch();
  },
  methods: {
    modalOk() {

    },
    action(state, row) {
      this.formState = state;
      if (state === 'add') {
        this.formModal = true;
      } else if (state === 'add') {
        this.formModal = true;
      } else if (state === 'del') {
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
            _this.$axios.delete("user", {params}).then(res => {
              if (res) {
                _this.$message.success("删除成功");
                _this.fetch();
                _this.selectedRowKeys = [];
              }
            });
          },
        });
      }
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
  .table-banner {
    margin-bottom: 30px;
    display: flex;
    justify-content: space-between;
    .table-title {
      font-size: 16px;
    }
    .table-btn {
      button {
        margin: 0 5px;
      }
    }
  }
}
</style>