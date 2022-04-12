import { data, methods } from '@/utils/tableUtils';
export default {
  data: {
    ...data,
    columns: [
      {title: '检验计量器', dataIndex: 'inspectionMeter'},
      {title: '是否定期校验', dataIndex: 'isPeriodicInspection'},
      {title: '校验日期', dataIndex: 'inspectionDate'},
      {title: '检测废弃物是否按规定存放', dataIndex: 'isRequiredSave'},
      {title: '录入人', dataIndex: 'createUserName'},
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
      this.$axios.get("/device/list", {params}).then(res => {
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