<template>
  <img
    :class="'company-' + size"
    alt="Company"
    :src="src"
  >
</template>

<style sass>
.company-xbig {
  width: 400px;
  margin: 5px;
}
.company-big {
  width: 280px;
  margin: 5px;
}
.company-medium {
  width: 150px;
  margin: 5px;
}
.company-small {
  width: 100px;
  margin: 5px;
}
</style>
<script>
export default {
  props: {
    size: {
      type: String,
      default: 'medium'
    },
    src: {
      type: String,
      required: true
    }
  }
}
</script>
