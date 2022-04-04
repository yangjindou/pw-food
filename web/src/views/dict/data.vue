<template>
  <a-modal :width="800" v-model="formModal" title="数据字典关联" @ok="modalOk">
    <a-form class="form" :form="form">
      <a-row :gutter="24" class="col-name">
        <a-col :span="7">名称</a-col>
        <a-col :span="7">值</a-col>
        <a-col :span="7">排序</a-col>
      </a-row>
      <a-row :gutter="24" v-for="k in keys" :key="k">
        <a-form-item hidden>
          <a-input v-decorator="[`id[${k}]`]" placeholder="id" />
        </a-form-item>
        <a-col :span="7">
          <a-form-item>
            <a-input v-decorator="[`name[${k}]`,{rules}]" placeholder="名称" />
          </a-form-item>
        </a-col>
        <a-col :span="7">
          <a-form-item>
            <a-input v-decorator="[`value[${k}]`,{rules}]" placeholder="值" />
          </a-form-item>
        </a-col>
        <a-col :span="7">
          <a-form-item>
            <a-input v-decorator="[`sort[${k}]`]" placeholder="排序" />
          </a-form-item>
        </a-col>
        <a-col :span="3">
          <a-form-item>
            <a-button type="danger" @click="del(k)">删除</a-button>
          </a-form-item>
        </a-col>
      </a-row>
      <a-button type="primary" @click="add">新增数据字典</a-button>
    </a-form>
  </a-modal>
</template>

<script>
export default {
  data() {
    return {
      keys: [],
      count: 0,
      form: this.$form.createForm(this, { name: 'form_data' }),
      formModal: false,
      rules: [{
        required: true,
        message: '必填项',
      }],
      pid: -1
    }
  },
  methods: {
    add() {
      this.keys.push(this.count++);
    },
    del(item) {
      this.keys.splice(item, 1);
    },
    open(row) {
      this.formModal = true;
      this.init();
      this.pid = row.id;
      let params = {
        pid: row.id,
        pageSize: 50
      }
      this.$axios.get("dict/data/list", {params}).then(res => {
        if (res && res.data) {
          const list = res.data;
          const formData = {};
          this.count = list.length;
          list.forEach((e,index) => {
            this.keys.push(index);
            formData[`id[${index}]`] = e.id;
            formData[`name[${index}]`] = e.name;
            formData[`value[${index}]`] = e.value;
            formData[`sort[${index}]`] = e.sort;
          });
          this.$nextTick(() => this.form.setFieldsValue(formData));
        }
      });
    },
    init() {
      this.count = 0;
      this.keys = [];
      this.form.resetFields();
    },
    modalOk() {
      this.form.validateFields((error, data) => {
        if (error) return;
        const formData = [];
        if (this.keys.length === 0) {
          this.$message.error('请新增数据字典');
          return;
        }
        data.name.forEach((e,index) => {
          formData.push({
            id: data.id[index],
            name: data.name[index],
            value: data.value[index],
            sort: data.sort[index],
            pid: this.pid,
          })
        })
        this.$axios.post("/dict/data", formData).then(res => {
          if (res) {
            this.$message.success("保存成功");
            this.formModal = false;
            this.$parent.fetch();
          }
        });
      });
    },
  }
}
</script>

<style lang="less" scoped>
.col-name {
  margin-bottom: 10px;
}
</style>