<template name="graceDate">
	<view class="grace-date" v-if="realshow" 
	:style="{top:top, zIndex:zIndex}" 
	@tap.stop="stopfun" @touchmove.stop.prevent="stopfun">
		<view class="grace-date-header">
			<text class="grace-date-header-btn grace-icons" @click="prevYear">&#xe600;&#xe600;</text>
			<text class="grace-date-header-btn grace-icons" @click="prevMonth">&#xe600;</text>
			<text class="grace-date-header-date grace-icons">{{cYear}} 年 {{cMonth}} 月</text>
			<text class="grace-date-header-btn grace-icons" @click="nextMonth">&#xe601;</text>
			<text class="grace-date-header-btn grace-icons" @click="nextYear">&#xe601;&#xe601;</text>
		</view>
		<view class="grace-date-week">
			<text class="grace-date-weeks" v-for="(item, index) in weeks" :key="index">{{item}}</text>
		</view>
		<view class="grace-date-days">
			<view :class="['grace-date-ditems', currentDayIn == cYear+'-'+cMonthStr+'-'+ item.date ? 'grace-d-current' : '']" 
			:style="{background: currentDayIn == cYear+'-'+cMonthStr+'-'+ item.date ? activeBgColor : bgColor, borderRadius:borderRadius}" 
			@click="chooseDate(cYear+'-'+cMonthStr+'-'+item.date, item.date)" v-for="(item, index) in days" :key="index">
				<text class="grace-date-day" :class="[currentDayIn == (cYear+'-'+cMonthStr+'-'+item.date) ? 'grace-d-current-txt' : '']">{{item.date}}</text>
				<text class="grace-date-nl" v-if="isLunar" :class="[currentDayIn == (cYear+'-'+cMonthStr+'-'+item.date) ? 'grace-d-current-txt' : '']">{{item.nl}}</text>
			</view>
		</view>
		<view class="grace-nowrap grace-flex-center" style="margin-top:50rpx;" v-if="isTime">
			<picker class="grace-date-time" mode="time" @change="timechange" :value="currentTimeIn">
				<text>时间 : {{currentTimeIn}}</text>
			</picker>
		</view>
		<view class="grace-date-btns" v-if="isTime">
			<text class="grace-date-btns-text" @click="close" style="color:#888888;">关闭</text>
			<text class="grace-date-btns-text" @click="submit">确定</text>
		</view>
	</view>
</template>
<script>
import guiCalendar from './graceCalendar.js';
export default {
	name: "graceCountd",
	props: {
		show        : {type:Boolean, default:false },
		currentDate : {type:String, default:"" },
		isTime      : {type:Boolean, default:true },
		top : {
			type : String,
			// #ifdef H5
			default : '44px'
			// #endif
			// #ifndef H5
			default : '0'
			// #endif
		},
		zIndex        : {type:String, default:"1"},
		bgColor       : {type:String, default:"#F6F7F8"},
		activeBgColor : {type:String, default:"#3688FF"},
		borderRadius  : {type:String, default:"6rpx"},
		isLunar       : {type:Boolean, default:true }
	},
	created:function(){
		this.initTime();
		this.realshow = this.show;
	},
	watch:{
		currentDate  : function(){this.initTime();},
		show         : function(val){
			if(val){this.open();}else{this.hide();}
		}
	},
	data(){
		return {
			weeks         : ['一', '二', '三', '四', '五', '六', '日'],
			cYear         : 2016,
			cMonth        : 6,
			cMonthStr     : "06",
			cDay          : "01",
			days          : '',
			currentDayIn  : '',
			currentTimeIn : '',
			realshow      : false
		}
	},
	methods: {
		stopfun : function(){},
		timechange : function(e){
			this.currentTimeIn = e.detail.value;
		},
		getDaysInOneMonth : function (){
			var d = new Date(this.cYear, this.cMonth, 0);
			return d.getDate();
		},
		getDay : function (){
			var d = new Date(this.cYear, this.cMonth - 1, 0);
			return d.getDay();
		},
		prevYear : function(){
			this.cYear = this.cYear - 1;
			this.changeMonth();
		},
		prevMonth : function(){
			this.cMonth =  this.cMonth - 1;
			if (this.cMonth < 1) { this.cMonth = 12; this.cYear = this.cYear - 1; }
			this.cMonthStr = this.cMonth < 10 ? '0' + this.cMonth : this.cMonth;
			this.changeMonth();
		},
		nextMonth : function(){
			this.cMonth = this.cMonth + 1;
			if (this.cMonth > 12){this.cMonth = 1; this.cYear = this.cYear + 1;}
			this.cMonthStr = this.cMonth < 10 ? '0' + this.cMonth : this.cMonth;
			this.changeMonth();
		},
		nextYear : function(){
			this.cYear = this.cYear + 1;
			this.changeMonth();
		},
		changeMonth:function(){
			var daysList  = [];
			var days      = this.getDaysInOneMonth();
			var startWeek = this.getDay();
			var forSteps  = 0;
			for (var i = (0 - startWeek); i < days; i++){
				if(i >= 0){
					daysList[forSteps] = {date : i >= 9 ? i + 1 : '0' + (i+1), nl : ''};
					daysList[forSteps].nl = guiCalendar.getLunarInfo(this.cYear, this.cMonth, i + 1);
				}else{
					daysList[forSteps] = '';
				}
				forSteps++;
			}
			this.days    = daysList;
		},
		chooseDate: function (sedDate, isday) {
			if(!isday){return ;}
			this.currentDayIn = sedDate;
			if(this.isTime){return ;}
			this.$emit('changeDate', sedDate);
		},
		submit : function(){
			if(this.isTime){
				this.$emit('changeDate', this.currentDayIn+' '+this.currentTimeIn);
			}else{
				this.$emit('changeDate', this.currentDayIn);
			}
		},
		close : function(){
			this.$emit("closeDate");
		},
		//初始化时间
		initTime : function(){
			if(this.currentDate == ''){
				var dateObj        = new Date();
				this.cYear         = dateObj.getFullYear();
				this.cMonth        = dateObj.getMonth() + 1;
				this.cMonthStr     = this.cMonth < 10 ? '0' + this.cMonth : this.cMonth;
				this.cDay          = dateObj.getDate();
				this.cDay          = this.cDay < 10 ? '0' + this.cDay : this.cDay;
				this.currentDayIn  = this.cYear + '-' + this.cMonthStr + '-' + this.cDay;
				this.currentTimeIn = '00:00';
				this.changeMonth();
			}else{
				var dates          = this.currentDate.split(' ');
				if (!dates[1]) { dates[1] = '';}
				var dayArr         = dates[0].split('-');
				this.cYear         = Number(dayArr[0]);
				this.cMonth        = dayArr[1];
				this.cDay          = dayArr[2];
				var reg            = new RegExp('^0[0-9]+$');
				if(reg.test(this.cMonth)){this.cMonth = this.cMonth.substr(1,1);}
				this.cMonth        = Number(this.cMonth);
				this.cMonthStr     = this.cMonth < 10 ? '0'+this.cMonth : this.cMonth;
				this.currentDayIn  = dates[0];
				this.currentTimeIn = dates[1];
				this.changeMonth();
			}
		},
		open:function(){this.realshow = true;},
		hide:function(){this.realshow = false;}
	}
}
</script>
<style scoped>
.grace-date{position:fixed; z-index:1; left:0; top:0; bottom:0px; width:730rpx; padding:0 10rpx; display:flex; flex-direction:column; align-items:center; background:#FFFFFF; overflow:hidden;}
.grace-date-header{display:flex; justify-content:center; flex-direction:row; text-align:center; margin-top:20rpx;}
.grace-date-header-btn{font-size:36rpx; line-height:88rpx; padding:0 10rpx; color:#888888;}
.grace-date-header-date{line-height:88rpx; font-size:36rpx; margin:0 20rpx;}
.grace-date-week{text-align:center; width:702rpx; display:flex; flex-wrap:nowrap; flex-direction:row;}
.grace-date-weeks{display:block; width:100rpx; height:80rpx; text-align:center; font-size:32rpx; line-height:80rpx; color:#666666;}
.grace-date-days{width:702rpx; display:flex; flex-direction:row; flex-wrap:wrap;}
.grace-date-ditems{width:96rpx; height:96rpx; display:flex; flex-direction:column; align-items:center; justify-content:center; margin:2rpx; background-color:#F6F7F8; border-radius:5rpx;}
.grace-d-current{background-color:#3688FF;}
.grace-d-current-txt{color:#FFFFFF !important;}
.grace-date-day{display:block; width:100%; height:38rpx; line-height:38rpx; text-align:center; font-size:32rpx;}
.grace-date-nl{display:block; width:100%; height:26rpx; line-height:26rpx; color:#888888; font-size:20rpx; text-align:center;}
.grace-date-btns{display:flex; flex-wrap:nowrap; flex-direction:row; justify-content:space-between; position:absolute; z-index:1; left:0; bottom:20px; width:100%;}
.grace-date-btns-text{display:block; color:#3688FF; line-height:100rpx; font-size:30rpx; text-align:center; width:300rpx;}
.grace-date-time{font-size:30rpx; line-height:100rpx; color:#666666; border-top:1px solid #F6F6F6; border-bottom:1px solid #F6F6F6; padding:0 20rpx;}
</style>