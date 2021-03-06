import { data, methods } from '@/utils/tableUtils';
export default {
  data: {
    ...data,
    columns: [
      {title: '名称', dataIndex: 'name'},
      {title: '标识', dataIndex: 'sign'},
      {title: '备注', dataIndex: 'remark'},
      {title: '操作', width: 200, scopedSlots: {customRender: 'operation'}, fixed: 'right'}
    ],
  },
  methods: {
    ...methods,
    fetch(params = {}) {
      this.loading = true;
      Object.assign(params, this.searchParams);
      params.pageSize = this.pagination.pageSize;
      params.pageNum = this.pagination.current;
      this.$axios.get("dict/list", {params}).then(res => {
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