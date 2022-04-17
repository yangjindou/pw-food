<template>
  <div>
    <u-breadcrumb :items="['出仓上报','出仓列表']" title="出仓列表" />
    <div class="search">
      <a-form class="search-form" :form="formSearch">
        <a-row :gutter="24">
          <a-col :span="8" >
            <a-form-item label="出仓时间">
              <a-range-picker v-decorator="[`warehousedDate`]" />
            </a-form-item>
          </a-col>
          <a-col :offset="10" :span="6" :style="{ textAlign: 'right' }">
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
          <a-button type="primary" @click="exp">导出</a-button>
        </div>
      </div>
      <div class="table-content">
        <a-table :columns="columns" size="small"
                 :row-selection="{ selectedRowKeys: selectedRowKeys, type: 'checkbox', onChange: onSelectChange}"
                 :row-key="row => row['id']" :data-source="tableData"
                 :pagination="pagination" :loading="loading" @change="handleTableChange">
          <template slot="weightAll" slot-scope="row">
            {{row['warehousedWeightPoultry'] + row['warehousedWeightLivestock'] + row['warehousedWeightAquatic'] + row['warehousedWeightOther']}}
          </template>
          <template slot="operation" slot-scope="row">
            <div class="operation-btn">
              <a @click="warehoused(row)">详情</a>
            </div>
          </template>
        </a-table>
      </div>
    </div>
    <warehoused ref="warehoused" />
  </div>
</template>

<script>
import warehoused from "./warehoused";
import tableMixin from './table';
import apiUtils from "@/utils/apiUtils";
export default {
  components: {warehoused},
  mixins:[tableMixin],
  data() {
    return {
      formSearch: this.$form.createForm(this, { name: 'search_user' }),
      searchParams: {},
      basicParams: {
        showAuditfilingState: true,
        filterType: '出仓'
      },
    };
  },
  mounted() {
    this.basicParams['warehouseCreateUser'] = this.$store.state.user.userData['id'];
    this.fetch();
  },
  methods: {
    warehoused(row) {
      this.$refs.warehoused.open("详情", row);
    },
    exp() {
      this.formSearch.validateFields((err, data) => {
        if (data['warehousedDate']) {
          data["warehousedDateStart"] = this.$moment(data['warehousedDate'][0]).format("YYYY-MM-DD") + ' 00:00:00';
          data["warehousedDateEnd"] = this.$moment(data['warehousedDate'][1]).format("YYYY-MM-DD") + ' 23:59:59';
          delete data["warehousedDate"];
        }
        Object.assign(data, this.basicParams);
        let url = apiUtils.createGetUrl(`${process.env.VUE_APP_API_BASE_URL}/appointment/warehouse/export`, data);
        window.open(url);
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
        if (data['warehousedDate']) {
          data["warehousedDateStart"] = this.$moment(data['warehousedDate'][0]).format("YYYY-MM-DD") + ' 00:00:00';
          data["warehousedDateEnd"] = this.$moment(data['warehousedDate'][1]).format("YYYY-MM-DD") + ' 23:59:59';
          delete data["warehousedDate"];
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