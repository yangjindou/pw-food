import { data, methods } from '@/utils/tableUtils';
let tableMixin = {
  data(){
    return {
      ...data,
      columns: [
        {title: '备案单号', dataIndex: 'filingOrder'},
        {title: '备案状态', dataIndex: 'filingState'},
        {title: '预约监管仓', dataIndex: 'warehouseName'},
        {title: '入仓时间', dataIndex: 'warehousingDate'},
        {title: '货物类别', dataIndex: 'goodTypeName'},
        {title: '货物名称', dataIndex: 'goodName'},
        {title: '货物来源', dataIndex: 'goodSourceName'},
        {title: '车牌号', dataIndex: 'carNumber'},
        {title: '操作', width: 300, scopedSlots: {customRender: 'operation'}, fixed: 'right'}
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
      this.$axios.get("/appointment/list", {params}).then(res => {
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