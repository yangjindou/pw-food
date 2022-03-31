export const data = {
  tableData: [],
  loading: false,
  pagination: {
    "show-size-changer": true,
    "show-quick-jumper": true,
    "page-size-options": ['10', '20', '30', '40', '50'],
    current: 1,
    total: 0,
    pageSize: 10,
  },
}

export const methods = {
  handleTableChange(pagination, filters, sorter) {
    this.pagination.current = pagination.current;
    this.pagination.pageSize = pagination.pageSize;
    let params = {};
    if (sorter.order) {
      params.col = sorter.columnKey;
      params.isAsc = sorter.order === "ascend";
    }
    this.fetch(params);
  },
}