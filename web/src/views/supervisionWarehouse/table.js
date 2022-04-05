import { data, methods } from '@/utils/tableUtils';
export default {
  data: {
    ...data,
    selectedRowKeys: [],
    columns: [
      {title: '监管仓编码', dataIndex: 'code'},
      {title: '监管仓名称', dataIndex: 'name'},
      {title: '集中监管仓分类', dataIndex: 'type'},
      {title: '启用日期', dataIndex: 'enableDate'},
      {title: '运营企业名称', dataIndex: 'enterpriseName'},
      {title: '状态', dataIndex: 'state'},
      {title: '操作', width: 120, scopedSlots: {customRender: 'operation'}, fixed: 'right'}
    ],
  },
  methods: {
    ...methods,
    fetch(params = {}) {
      this.loading = true;
      Object.assign(params, this.searchParams);
      params.pageSize = this.pagination.pageSize;
      params.pageNum = this.pagination.current;
      this.$axios.get("/supervisionWarehouse/list", {params}).then(res => {
        if (res) {
          this.tableData = res.data.list;
          this.pagination.total = res.data.total;
          this.loading = false;
        }
      });
    },
    onSelectChange(selectedRowKeys) {
      this.selectedRowKeys = selectedRowKeys;
    },
  }
}