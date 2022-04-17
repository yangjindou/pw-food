<template>
	<view 
	class="gui-page-refresh grace-flex grace-rows grace-flex-center grace-flex-vcenter" 
	:style="{
	height:refreshHeight+'px', 
	backgroundColor:refreshBgColor[refreshStatus]}" 
	:class="[refreshStatus == 3 ? 'gload-hide' : '']">
		<text class="gui-page-refresh-icon grace-icons gui-block-text" 
		v-if="refreshStatus == 0 || refreshStatus == 1" 
		:style="{
			fontSize:refreshFontSize,
			color:refreshColor[refreshStatus]
		}">&#xe66c;</text>
		<view class="gui-page-refresh-icon" ref="loadingIcon" v-if="refreshStatus == 2" >
			<text class="grace-icons gui-rotate360 gui-block-text"
			:style="{
				fontSize:refreshFontSize,
				color:refreshColor[refreshStatus]
			}">&#xe9db;</text>
		</view>
		<text class="gui-page-refresh-icon grace-icons"
		v-if="refreshStatus == 3" 
		:style="{
			fontSize:refreshFontSize,
			color:refreshColor[refreshStatus]
		}">&#xe7f8;</text>
		<text class="gui-page-refresh-text gui-block-text" 
		:style="{
			fontSize:refreshFontSize,
			color:refreshColor[refreshStatus]
		}">{{refreshText[refreshStatus]}}</text>
	</view>
</template>
<script>
var animation = weex.requireModule('animation');
const dom = weex.requireModule('dom');
export default{
	props:{
		refreshText    : {type:Array,   default:function () {
			return ['继续下拉刷新','松开手指开始刷新','数据刷新中','数据已刷新'];
		}},
		refreshBgColor : {type:Array,   default:function () {
			return ['#FFFFFF','#FFFFFF','#FFFFFF','#63D2BC'];
		}},
		refreshColor : {type:Array,   default:function () {
			return ['rgba(69, 90, 100, 0.6)','rgba(69, 90, 100, 0.6)','#63D2BC','#FFFFFF'];
		}},
		refreshFontSize : {type:String, default:'26rpx'}
	},
	data() {
		return {
			reScrollTop         : 0,
			refreshHeight       : 0,
			refreshY            : 0,
			refreshStatus       : 0,
			refreshTimer        : 0
		}
	},
	methods:{
		touchstart : function (e){
			this.reScrollTop = e.scrollTop;
			this.refreshY = e.moveY;
		},
		touchmove : function(e){
			this.reScrollTop = e.scrollTop;
			if(this.refreshStatus >= 1){ return null;}
			if(this.reScrollTop > 10){return ;}
			if(this.refreshStatus != 0){return ;}
			var moveY = e.moveY - this.refreshY;
			if(moveY >= 50){
				moveY = 50;
				this.refreshStatus = 1;
			}
			if(moveY > 15){this.refreshHeight = moveY;}
		},
		touchend : function (e) {
			this.reScrollTop = e.scrollTop;
			if(this.refreshStatus < 1){
				return this.resetFresh();
			}else if(this.refreshStatus == 1){
				this.refreshStatus = 2;
				setTimeout(()=>{
					this.rotate360();
				}, 200);
				this.$emit('reload');
			}
		},
		scroll:function(e){
			this.reScrollTop = e.detail.scrollTop;
		},
		endReload : function(){
			this.refreshStatus = 3;
			setTimeout(()=>{this.resetFresh()}, 500);
		},
		resetFresh : function () {
			this.refreshHeight = 0;
			this.refreshStatus = 0;
			return null;
		},
		rotate360 : function(){
			var el = this.$refs.loadingIcon;
			animation.transition(el, {
				styles     : {transform: 'rotate(7200deg)'},
				duration   : 20000,
				timingFunction: 'linear',
				needLayout :false,
				delay: 0
			});
		},
	}
}
</script>
<style scoped>
.gui-page-refresh{overflow:hidden}
.gui-page-refresh-text{line-height:32rpx;}
.gui-page-refresh-icon{padding:0 12rpx; line-height:40rpx; font-size:28rpx;}
</style>