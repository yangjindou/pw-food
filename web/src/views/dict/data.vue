<template>
  <div>
    <a-row :gutter="24">
      <a-col :span="6">
      </a-col>
    </a-row>
    <a-form-item hidden>
      <a-input v-decorator="['id']" placeholder="id" />
      <a-input v-decorator="['pid']" placeholder="pid" />
    </a-form-item>
    <a-form-item label="名称">
      <a-input v-decorator="['name',{rules}]" placeholder="名称" />
    </a-form-item>
    <a-form-item label="值">
      <a-input v-decorator="['sign',{rules}]" placeholder="值" />
    </a-form-item>
    <a-form-item label="排序">
      <a-input v-decorator="['remark']" placeholder="排序" />
    </a-form-item>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rules: [{
        required: true,
        message: '必填项',
      }],
    }
  }
}
</script>

<style scoped>

</style>