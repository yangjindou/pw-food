import { data, methods } from '@/utils/tableUtils';
export default {
  data: {
    ...data,
    columns: [
      {title: '公告标题', dataIndex: 'title', width: '25%'},
      {title: '公告人', dataIndex: 'createUserName'},
      {title: '公告时间', dataIndex: 'createDate'},
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
      this.$axios.get("/notice/list", {params}).then(res => {
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