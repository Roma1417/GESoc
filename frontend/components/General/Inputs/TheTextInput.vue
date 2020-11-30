<template>
  <v-text-field
    v-bind="$attrs"
    :maxlength="maxlength"
    :counter="showCounter ? maxlength : null"
    v-on="$listeners"
  />
</template>
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
