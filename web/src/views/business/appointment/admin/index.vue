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
          <a-button type="primary" @click="exp">导出</a-button>
        </div>
      </div>
      <div class="table-content">
        <a-table :columns="columns" size="small"
                 :row-selection="{ selectedRowKeys: selectedRowKeys, type: 'checkbox', onChange: onSelectChange}"
                 :row-key="row => row['id']" :data-source="tableData"
                 :rowClassName="tableRowClass"
                 :pagination="pagination" :loading="loading" @change="handleTableChange">
          <template slot="operation" slot-scope="row">
            <div class="operation-btn">
              <a @click="detail(row)">详情</a>
              <template v-if="!row['emergency']">
                <a @click="emergency(row)">应急通知</a>
                <a v-if="row['filingState'] === '待审核'" @click="audit(row)">审核</a>
                <a @click="sampling(row)">采样</a>
                <a @click="uninstall(row)">卸货</a>
                <a @click="warehousing(row)">入仓</a>
                <a @click="warehoused(row)">出仓</a>
                <a v-if="row['filingState'] === '申请修改'" @click="applyUpdate(row)">处理修改</a>
              </template>
            </div>
          </template>
        </a-table>
      </div>
    </div>
    <audit ref="audit" />
    <detail ref="detail" />
    <sampling ref="sampling" />
    <uninstall ref="uninstall" />
    <warehousing ref="warehousing" />
    <warehoused ref="warehoused" />
    <applyUpdate ref="applyUpdate" />
  </div>
</template>

<script>
import applyUpdate from "./applyUpdate";
import audit from "./audit";
import uninstall from "./uninstall";
import sampling from "./sampling";
import warehousing from "./warehousing";
import warehoused from "./warehoused";
import tableMixin from './table';
import apiUtils from "@/utils/apiUtils";
import Detail from "@/views/business/appointment/detail";
export default {
  components: {Detail, audit, sampling, uninstall, warehousing, warehoused, applyUpdate},
  mixins:[tableMixin],
  data() {
    return {
      formSearch: this.$form.createForm(this, { name: 'search_user' }),
      searchParams: {},
      basicParams: {},
      selectList: {
        goodType: [],
        goodSource: [],
      }
    };
  },
  mounted() {
    this.basicParams['showAuditfilingState'] = true;
    this.basicParams['warehouseCreateUser'] = this.$store.state.user.userData['id'];
    this.fetch();
    this.getSelectList();
  },
  methods: {
    applyUpdate(row) {
      this.$refs.applyUpdate.open("修改", row);
    },
    tableRowClass(row) {
      return row['emergency'] ? 'text-red' : '';
    },
    warehoused(row) {
      this.$refs.warehoused.open("修改", row);
    },
    warehousing(row) {
      this.$refs.warehousing.open("修改", row);
    },
    uninstall(row) {
      this.$refs.uninstall.open("修改", row);
    },
    sampling(row) {
      this.$refs.sampling.open("修改", row);
    },
    detail(row) {
      this.$refs.detail.open("详情", row);
    },
    audit(row) {
      this.$refs.audit.open("修改", row);
    },
    getSelectList() {
      apiUtils.getDictData(this.selectList.goodType, 'goodType');
      apiUtils.getDictData(this.selectList.goodSource, 'goodSource');
    },
    emergency(row) {
      const _this = this;
      this.$confirm({
        title: '确定应急通知?',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          let params = {
            emergency: true,
            id: row.id
          };
          _this.$axios.put("/appointment", params).then(res => {
            if (res) {
              _this.$message.success("操作成功");
              _this.fetch();
            }
          });
        },
      });
    },
    exp() {
      this.formSearch.validateFields((err, data) => {
        if (data['warehousingDate']) {
          data["warehousingDateStart"] = this.$moment(data['warehousingDate'][0]).format("YYYY-MM-DD") + ' 00:00:00';
          data["warehousingDateEnd"] = this.$moment(data['warehousingDate'][1]).format("YYYY-MM-DD") + ' 23:59:59';
          delete data["warehousingDate"];
        }
        let url = apiUtils.createGetUrl(`${process.env.VUE_APP_API_BASE_URL}/appointment/export`, data);
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
        if (data['warehousingDate']) {
          data["warehousingDateStart"] = this.$moment(data['warehousingDate'][0]).format("YYYY-MM-DD") + ' 00:00:00';
          data["warehousingDateEnd"] = this.$moment(data['warehousingDate'][1]).format("YYYY-MM-DD") + ' 23:59:59';
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