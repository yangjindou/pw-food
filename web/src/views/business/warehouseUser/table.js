import { data, methods } from '@/utils/tableUtils';
let tableMixin = {
  data(){
    return {
      ...data,
      columns: [
        {title: '姓名', dataIndex: 'name'},
        {title: '手机号', dataIndex: 'phone'},
        {title: '工作种类', dataIndex: 'workType'},
        {title: '所属监管仓', dataIndex: 'warehouseName'},
        {title: '是否接种疫苗', dataIndex: 'isInoculate'},
        {title: '接种次数', dataIndex: 'inoculateCount'},
        {title: '操作', width: 330, scopedSlots: {customRender: 'operation'}, fixed: 'right'},
        {title: '数据操作', width: 120, scopedSlots: {customRender: 'basicOperation'}, fixed: 'right'}
      ],
    }
  },
  methods: {
    ...methods,
    fetch(params = {}) {
      this.loading = true;
      Object.assign(params, this.searchParams);
      params.pageSize = this.pagination.pageSize;
      params.pageNum = this.pagination.current;
      this.$axios.get("/warehouseUser/list", {params}).then(res => {
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