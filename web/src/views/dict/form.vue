<template>
  <div>
    <a-form-item label="id" hidden>
      <a-input v-decorator="['id']" placeholder="id" />
    </a-form-item>
    <a-form-item label="名称">
      <a-input v-decorator="['name',{rules}]" placeholder="名称" />
    </a-form-item>
    <a-form-item label="标识">
      <a-input v-decorator="['sign',{rules}]" placeholder="标识" />
    </a-form-item>
    <a-form-item label="备注">
      <a-input v-decorator="['remark']" placeholder="备注" />
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