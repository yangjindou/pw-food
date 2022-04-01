import { data, methods } from '@/utils/tableUtils';
export default {
  data: {
    ...data,
    selectedRowKeys: [],
    columns: [
      {title: '登录名', dataIndex: 'loginName', width: '25%'},
      {title: '用户名', dataIndex: 'userName'},
      {title: '角色', dataIndex: 'role'},
      {title: '创建时间', width: 230,dataIndex: 'createDate'},
      // {title: '操作', width: 110, scopedSlots: {customRender: 'operation'},fixed: 'right'}
    ],
  },
  methods: {
    ...methods,
    fetch(params = {}) {
      this.loading = true;
      Object.assign(params, this.searchParams);
      params.pageSize = this.pagination.pageSize;
      params.pageNum = this.pagination.current;
      this.$axios.get("user/list", {params}).then(res => {
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