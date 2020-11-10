<template>
  <v-text-field
    class="numeric-input"
    placeholder="0"
    type="number"
    v-bind="$attrs"
    v-on="$listeners"
  />
</template>
<style >
.numeric-input input{
  text-align: right !important;
}
</style>
<script>
export default {
  props: {

  }
}
</script>
