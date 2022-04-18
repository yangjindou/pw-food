export const data = {
	listData: [],
	selectIndex: [],
	total: 0,
	pageNum: 1,
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
