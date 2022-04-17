<template>
	<view class="grace-schedule-wrap">
		<view class="grace-schedule-header grace-space-between grace-flex-vcenter">
			<picker class="grace-flex-center grace-flex-vcenter" 
			mode="date" :value="currentDayIn" :start="startDate" :end="endDate" @change="selectDate">
				<text class="grace-schedule-header-date grace-icons">{{cYear}} 年 {{cMonthStr}} 月 &#xe603;</text>
			</picker>
			<view class="grace-schedule-today" @tap="gotoToday">返回今天</view>
		</view>
		<view class="grace-date-week">
			<text class="grace-date-weeks" v-for="(item, index) in weeks" :key="index">{{item}}</text>
		</view>
		<view class="grace-date-days">
			<block v-for="(item, index) in days" :key="index">
				<view class="grace-date-ditems" v-if="item != ''" 
				:style="{background:currentDayIn == cYear+'-'+cMonthStr+'-'+ item.date ? activeBgColor : bgColor}" 
				@click="chooseDate(cYear+'-'+cMonthStr+'-'+item.date, item.date)">
					<text class="grace-date-day" 
					:class="[currentDayIn == (cYear+'-'+cMonthStr+'-'+item.date) ? 'grace-d-current-txt' : '']">{{item.date}}</text>
					<text class="grace-date-nl" v-if="isLunar" 
					:class="[currentDayIn == (cYear+'-'+cMonthStr+'-'+item.date) ? 'grace-d-current-txt' : '']">{{item.nl}}</text>
					<view class="grace-schedule-point" v-if="item.haveSe" :style="{backgroundColor:pointColor}"></view>
				</view>
				<view class="grace-date-ditems" v-else style="background-color:none;"></view>
			</block>
		</view>
		<view class="grace-schedule-line"></view>
		<view class="grace-schedule-time-list-wrap">
			<view class="grace-schedule-time-list" v-for="(item, index) in hours" :key="index">
				<text class="grace-schedule-timer">{{item}}:00</text>
				<view class="grace-schedule-body">
					<view class="grace-schedules" v-for="(schedule, idx) in schedulesIn[index]" :key="idx" 
					@tap="scheduleTap" :data-id="schedule.id" 
					:style="{background:schedule.bgColor, color:schedule.color}">{{schedule.content}}</view>
				</view>
			</view>
		</view>
	</view>
</template>
<script>
import guiCalendar from './graceCalendar.js';
export default{
	data() {
		return {
			cYear      : 2020,
			cMonth     : 1,
			cDay       : 10,
			cMonthStr  : '01',
			weeks      : ['一', '二', '三', '四', '五', '六', '日'],
			days       : [],
			currentDayIn : '',
			hours      : ['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23'],
			schedulesIn: [] 
		}
	},
	props:{
		// 当前默认日期
		currentDate   : {type:String, default:""},
		bgColor       : {type:String, default:"#F8F8F8"},
		activeBgColor : {type:String, default:"#3688FF"},
		isLunar       : {type:Boolean, default:true },
		startDate     : {type:String, default:'1950-01-01'},
		endDate       : {type:String, default:'2050-01-01'},
		schedules     : {type:Array, default:function () {return []}},
		pointColor    : {type:String, default:"#FF0036"}
	},
	created:function(){
		this.currentDayIn = this.currentDate;
		this.initTime();
		this.getSchedulesIn();
	},
	methods:{
		initTime : function(){
			if(this.currentDayIn == ''){
				var dateObj        = new Date();
				this.cYear         = Number(dateObj.getFullYear());
				this.cMonth        = Number(dateObj.getMonth() + 1);
				this.cMonthStr     = this.cMonth < 10 ? '0' + this.cMonth : this.cMonth;
				this.cDay          = dateObj.getDate();
				this.cDay          = this.cDay < 10 ? '0' + this.cDay : this.cDay;
				this.currentDayIn  = this.cYear + '-' + this.cMonthStr + '-' + this.cDay;
				this.changeMonth();
			}else{
				var dates          = this.currentDayIn.split(' ');
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
		changeMonth:function(){
			var daysList  = [];
			var days      = this.getDaysInOneMonth();
			var startWeek = this.getDay();
			var forSteps  = 0;
			for (var i = (0 - startWeek); i < days; i++){
				if(i >= 0){
					daysList[forSteps]        = {date : i >= 9 ? i + 1 : '0' + (i+1), nl : ''};
					daysList[forSteps].nl     = guiCalendar.getLunarInfo(this.cYear, this.cMonth, i + 1);
					daysList[forSteps].haveSe = this.haveSchedule(daysList[forSteps].date);
				}else{
					daysList[forSteps] = '';
				}
				forSteps++;
			}
			this.days    = daysList;
		},
		haveSchedule : function (day) {
			var cDay = this.cYear+'-'+this.cMonthStr+'-'+day;
			for(let i = 0; i < this.schedules.length; i++){
				if(this.schedules[i].datetime.indexOf(cDay) != -1){
					return true;
				}
			}
			return false;
		},
		getDaysInOneMonth : function (){
			var d = new Date(this.cYear, this.cMonth, 0);
			return d.getDate();
		},
		getDay : function (){
			var d = new Date(this.cYear, this.cMonth - 1, 0);
			return d.getDay();
		},
		selectDate : function(e){
			this.currentDayIn = e.detail.value;
			this.initTime();
			this.getSchedulesIn();
			this.$emit('selectDate', e.detail.value);
		},
		chooseDate: function (sedDate) {
			this.currentDayIn = sedDate;
			this.getSchedulesIn();
			this.$emit('chooseDate', sedDate);
		},
		getSchedulesIn : function (){
			var res = [];
			for(let i = 0; i < this.hours.length; i++){
				var ctime = this.currentDayIn + ' ' + this.hours[i] + ':00';
				res.push([]);
				for(let ii = 0; ii < this.schedules.length; ii++){
					if(this.schedules[ii].datetime == ctime){
						res[i].push(this.schedules[ii]);
					}
				}
			}
			this.schedulesIn = res;
		},
		scheduleTap : function (e) {
			var id = e.currentTarget.dataset.id;
			this.$emit('scheduleTap', id);
		},
		gotoToday : function(){
			var dateObj        = new Date();
			this.cYear         = Number(dateObj.getFullYear());
			this.cMonth        = Number(dateObj.getMonth() + 1);
			this.cMonthStr     = this.cMonth < 10 ? '0' + this.cMonth : this.cMonth;
			this.cDay          = dateObj.getDate();
			this.cDay          = this.cDay < 10 ? '0' + this.cDay : this.cDay;
			this.currentDayIn  = this.cYear + '-' + this.cMonthStr + '-' + this.cDay;
			this.changeMonth();
			this.getSchedulesIn();
			this.$emit('gotoToday', this.currentDayIn);
		}
	}
}
</script>
<style scoped>
.grace-schedule-wrap{width:702rpx; margin:0 auto;}
.grace-schedule-header-date{display:block; height:100rpx; line-height:100rpx; color:#323232; font-size:32rpx; margin:0 20rpx;}
.grace-date-week{width:702rpx; display:flex; flex-wrap:nowrap; flex-direction:row; justify-content:center;}
.grace-date-weeks{display:block; width:100rpx; height:60rpx; font-size:26rpx; line-height:60rpx; color:#666666; text-align:center;}
.grace-date-days{width:702rpx; display:flex; flex-direction:row; flex-wrap:wrap;}
.grace-date-ditems{width:80rpx; height:80rpx; display:flex; flex-direction:column; align-items:center; justify-content:center; margin:3px 10rpx; border-radius:80rpx; position:relative;}
.grace-d-current-txt{color:#FFFFFF !important;}
.grace-date-day{display:block; width:100%; height:38rpx; line-height:38rpx; text-align:center; font-size:28rpx;}
.grace-date-nl{display:block; width:100%; height:26rpx; line-height:26rpx; color:#888888; font-size:20rpx; text-align:center;}
.grace-schedule-line{height:20rpx; border-bottom:1px solid #F8F8F8;}
.grace-schedule-time-list{margin-top:28rpx; display:flex; flex-direction:row; flex-wrap:nowrap;}
.grace-schedule-timer{display:block; width:100rpx; font-size:22rpx; color:#999999; line-height:36rpx; flex-shrink:0;}
.grace-schedule-body{width:700rpx; border-top:1px solid #F8F8F8; padding:5rpx 0; margin-top:15rpx;}
.grace-schedules{padding:10rpx; line-height:30rpx; font-size:22rpx; margin-top:15rpx; border-radius:8rpx; width:auto;}
.grace-schedule-time-list-wrap{padding:20rpx;}
.grace-schedule-today{line-height:30rpx; height:30rpx; font-size:22rpx; color:#828282; padding:10rpx 20rpx; margin-left:30rpx; border-radius:8rpx; border:1px solid #F1F2F3;}
.grace-schedule-point{width:18rpx; height:18rpx; border-radius:10rpx; background-color:#FF0036; position:absolute; right:5rpx; top:5rpx; z-index:1;}
</style>
