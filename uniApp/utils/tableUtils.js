export const data = {
	listData: [],
	selectIndex: [],
	pageSize: 5,
	total: 0,
	pageNum: 1
}

export const methods = {
	pageChange(index) {
		this.pageNum = index;
		this.getList();
	},
	selectIndexChange(e) {
		this.selectIndex = e;
	}
}
