<template>
  <v-text-field
    class="centered-input"
    placeholder="0"
    type="number"
    :counter="showCounter ? maxlength : null"
    v-bind="$attrs"
    v-on="$listeners"
  />
</template>
<style scoped>
    .centered-input >>> input {
      text-align: center
    }
</style>
<script>
export default {
  props: {
    maxlength: {
      default: '250',
      type: String
    },
    showCounter: {
      type: Boolean,
      default: false
    }
  }
}
</script>
