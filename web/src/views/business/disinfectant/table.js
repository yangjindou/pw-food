import { data, methods } from '@/utils/tableUtils';
let tableMixin = {
  data(){
    return {
      ...data,
      columns: [
        {title: '消毒液名称', dataIndex: 'name'},
        {title: '消毒液配比比例', dataIndex: 'ratio'},
        {title: '调配时间', dataIndex: 'deploymentDate'},
        {title: '调配人', dataIndex: 'deploymentUser'},
        {title: '消毒液浓度', dataIndex: 'concentration'},
        {title: '消杀评估', dataIndex: 'assessment'},
        {title: '录入人', dataIndex: 'createUserName'},
        {title: '操作', width: 120, scopedSlots: {customRender: 'operation'}, fixed: 'right'}
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
      this.$axios.get("/disinfectant/list", {params}).then(res => {
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