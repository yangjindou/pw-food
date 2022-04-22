import { data, methods } from '@/utils/tableUtils';
let tableMixin = {
  data(){
    return {
      ...data,
      columns: [
        {title: '检测时间', dataIndex: 'date'},
        {title: '检测结果', dataIndex: 'result'},
        {title: '检测人', dataIndex: 'userName'},
        {title: '操作人', dataIndex: 'createUserName'},
        {title: '操作', width: 120, scopedSlots: {customRender: 'operation'}, fixed: 'right'}
      ],
    }
  },
  methods: {
    ...methods,
    fetch(params = {}) {
      this.loading = true;
      Object.assign(params, this.basicParams);
      Object.assign(params, this.searchParams);
      params.pageSize = this.pagination.pageSize;
      params.pageNum = this.pagination.current;
      this.$axios.get("/warehouseUser/check/list", {params}).then(res => {
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
  },
};

export default tableMixin;