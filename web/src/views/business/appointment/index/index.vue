<template>
  <div>
    <u-breadcrumb :items="['预约管理','预约管理列表']" title="预约管理列表" />
    <div class="search">
      <a-form class="search-form" :form="formSearch">
        <a-row :gutter="24">
          <a-col :span="6" >
            <a-form-item label="货物类别">
              <a-select v-decorator="[`goodType`]" placeholder="请选择">
                <a-select-option v-for="item in selectList.goodType" :key="item.id" :value="item.id">{{item.name}}</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :span="6" >
            <a-form-item label="货物来源">
              <a-select v-decorator="[`goodSource`]" placeholder="请选择">
                <a-select-option v-for="item in selectList.goodSource" :key="item.id" :value="item.id">{{item.name}}</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :span="6" >
            <a-form-item label="车牌号">
              <a-input v-decorator="[`carNumber`]" placeholder="请输入" />
            </a-form-item>
          </a-col>
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
        <a-row :gutter="24">
          <a-col :span="8" >
            <a-form-item label="入仓时间">
              <a-range-picker v-decorator="[`warehousingDate`]" />
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
              <a @click="formOpen('修改', row)">修改</a>
            </div>
          </template>
        </a-table>
      </div>
    </div>
    <t-form ref="form" />
    <detail ref="detail" />
  </div>
</template>

<script>
import tForm from "./form";
import tableMixin from './table';
import apiUtils from "@/utils/apiUtils";
import Detail from "@/views/business/appointment/detail";
export default {
  components: {Detail, tForm},
  mixins:[tableMixin],
  data() {
    return {
      formSearch: this.$form.createForm(this, { name: 'search_user' }),
      searchParams: {},
      selectList: {
        goodType: [],
        goodSource: [],
      }
    };
  },
  mounted() {
    this.fetch();
    this.getSelectList();
  },
  methods: {
    getSelectList() {
      apiUtils.getDictData(this.selectList.goodType, 'goodType');
      apiUtils.getDictData(this.selectList.goodSource, 'goodSource');
    },
    detail(row) {
      this.$refs.detail.open("详情", row);
    },
    formOpen(state, row) {
      if (state === '修改' && !['修改','驳回'].includes(row['filingState'])) {
        this.$message.error(`${row['filingState']}，无法修改`);
        return;
      }
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
          _this.$axios.delete("/appointment", {params}).then(res => {
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
        if (data['warehousingDate']) {
          data["warehousingDateStart"] = this.$moment(data['warehousingDate'][0]).format("YYYY-MM-DD");
          data["warehousingDateEnd"] = this.$moment(data['warehousingDate'][1]).format("YYYY-MM-DD");
          delete data["warehousingDate"];
        }
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
        margin-right: 8px;
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