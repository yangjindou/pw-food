import { data, methods } from '@/utils/tableUtils';
let tableMixin = {
  data(){
    return {
      ...data,
      columns: [
        {title: '备案单号', dataIndex: 'filingOrder'},
        {title: '监管仓', dataIndex: 'warehouseName'},
        {title: '出仓时间', dataIndex: 'warehousedDate'},
        {title: '总重量（Kg）', scopedSlots: {customRender: 'weightAll'}},
        {title: '禽类（总Kg）', dataIndex: 'warehousedWeightPoultry'},
        {title: '畜类（Kg）', dataIndex: 'warehousedWeightLivestock'},
        {title: '水产品（Kg）', dataIndex: 'warehousedWeightAquatic'},
        {title: '其他（Kg）', dataIndex: 'warehousedWeightOther'},
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