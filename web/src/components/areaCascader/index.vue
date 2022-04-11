<template>
  <!-- 包装一个 cascader，让它支持数据双向绑定 v-model，同时也支持 form 表单的效验 -->
  <a-cascader
      :expand-trigger="expandTrigger"
      :change-on-select="changeOnSelect"
      :placeholder="placeholder"
      :field-names="fieldNames"
      :options="options"
      :show-search="{ filter }"
      :disabled="disabled"
      v-on="$listeners"
      v-model="valuePro"
      @change="onChange"/>
</template>

<script>
import jsonData from "@/components/areaCascader/data.json";

export default {
  // a-form 表单会监听下面事件与值，到时候需要通过 change 事件传出新的值即可，v-decorator 会通过 change 事件接受新值
  model: {
    prop: 'value',
    event: 'change'
  },
  props: {
    // 当前组件值
    // 默认是给 v-model 绑定值使用的
    value: {
      type: Array
      // 当通过 a-from 标签包裹时：
      // 这个参数是 v-decorator 给子组件传值用的
      // 这里不要给默认值， 在 form 下使用会爆警告 Warning: SquareUpload `default value` can not collect,  please use `option.initialValue` to set default value.
    },
    // 滑上展示下一级
    expandTrigger: {
      type: String,
      default: 'hover'
    },
    // 点击选中 省市区，不用选择三级
    changeOnSelect: {
      type: Boolean,
      default: true
    },
    // 提示信息
    placeholder: {
      type: String,
      default: ''
    },
    // 外部传入的部分省  只显示部分省市区
    filterProvince: {
      type: Array,
      default() {
        return []
      }
    },
    fieldNames: {
      type: Object,
      default() {
        return {
          label: 'name',
          value: 'id',
          children: 'children'
        }
      }
    },
    disabled: {
      type: Boolean,
      default: false
    },
  },
  computed: {
    // 支持 v-model 双向数据绑定，如果有新数据会通过 $emit 一个 input 事件进行修改 v-model 的值，也就是当前 value 值。
    // 写法是固定的，vue 自动处理，只管通过 $emit('input', newValue) 抛出去新的值即可。
    currentValue: {
      get () {
        return this.value
      },
      set (newValue) {
        this.$emit('change', newValue)
      }
    }
  },
  watch: {
    // 监听当前值变化，及时提交给父组件
    currentValue: {
      deep: true,
      immediate: true,
      handler: function (newValue) {
        // 记录最新的值
        this.valuePro = newValue
        // 判断当前的 value 值是否为 undefined， 如果是的话不用抛出去，要不然 form 标签就会走效验，并提示对应的错误了
        if (newValue !== undefined) {
          // v-decorator 会通过 change 事件接受新值
          this.$emit('change', newValue)
        }
      }
    }
  },
  data () {
    return {
      // 当前页面值，用于做桥梁使用的
      // 当前页面操作修改的就是这个值，外部传进来也会更新这个值，它变化了在通过 input change 去更新外部的值
      valuePro: undefined,
      options: [],
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    // input 输入
    onChange (e) {
      // 传出去最新值，这里只需要通过 change 事件传出去即可，内部的 value 也会发生变化
      this.$emit('change', e)
    },
    async init() {
      const data  = jsonData;
      // 只显示部分省份
      if (this.filterProvince.length >= 1) {
        const brr = []
        this.filterProvince.forEach(v => {
          brr.push(data.find(options => options.id === v))
        })
        this.options = brr
      } else {
        this.options = data
      }
    },
    filter(inputValue, path) {
      return path.some(
          option =>
              option.name.toLowerCase().indexOf(inputValue.toLowerCase()) > -1
      )
    }
  }
}
</script>