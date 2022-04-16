import { data, methods } from '@/utils/tableUtils';
export default {
  data: {
    ...data,
    columns: [
      {title: '物品名称', dataIndex: 'itemName'},
      {title: '物品数量', dataIndex: 'itemAmount'},
      {title: '存放地方', dataIndex: 'savePlace'},
      {title: '是否存放48小时', dataIndex: 'isSave48hour'},
      {title: '备注', dataIndex: 'remark'},
      {title: '录入人', dataIndex: 'createUserName'},
      {title: '录入时间', dataIndex: 'createDate'},
      {title: '操作', width: 120, scopedSlots: {customRender: 'operation'}, fixed: 'right'}
    ],
  },
  methods: {
    ...methods,
    fetch(params = {}) {
      this.loading = true;
      Object.assign(params, this.basicParams);
      Object.assign(params, this.searchParams);
      params.pageSize = this.pagination.pageSize;
      params.pageNum = this.pagination.current;
      this.$axios.get("/wasteDisposal/list", {params}).then(res => {
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