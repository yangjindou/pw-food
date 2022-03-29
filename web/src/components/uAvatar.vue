<template>
  <a-avatar :size="size" :src="src ||img"/>
</template>

<script>
import img from "@/assets/images/avatar.jpg";
export default {
  name: "uAvatar",
  props: {
    size: {
      type: Number,
    },
    src: {
      type: String,
    }
  },
  data() {
    return {
      img,
    }
  }
}
</script>

<style scoped>

</style>