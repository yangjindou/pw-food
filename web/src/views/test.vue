<template>
  <div>123</div>
</template>
<script>
export default {
  data() {
    return {

    };
  },
  mounted() {
    console.log(this.$axios.get('test'));
  }
};
</script>
<style>
</style>