import { data, methods } from '@/utils/tableUtils';
export default {
  data: {
    ...data,
    columns: [
      {title: '订单编号', dataIndex: 'orderNo'},
      {title: '微信昵称', dataIndex: 'nickName'},
      {title: '手机号码', dataIndex: 'phone'},
      {title: '订单类型', dataIndex: 'orderTypeLabel'},
      {title: '创建时间', width: 230,dataIndex: 'createTime',sorter: true},
      {title: '支付时间', width: 230,dataIndex: 'payTime',sorter: true},
      {title: '操作', width: 100, scopedSlots: {customRender: 'operation'},fixed: 'right'}
    ],
  },
  methods: {
    ...methods,
    fetch(params = {}) {
      this.loading = true;
      Object.assign(params, this.searchParams);
      params.pageSize = this.pagination.pageSize;
      params.pageNum = this.pagination.current;
      this.$axios.get("order/list", {params}).then(res => {
        if (res) {
          this.tableData = res.data.records;
          this.pagination.total = res.data.total;
          this.loading = false;
        }
      });
    },
  }
}