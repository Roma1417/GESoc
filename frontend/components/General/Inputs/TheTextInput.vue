<template>
  <v-text-field
    v-bind="$attrs"
    :maxlength="maxlength"
    :counter="maxlength"
    v-on="$listeners"
  />
</template>
<script>
export default {
  props: {
    maxlength: {
      default: '250',
      type: String
    }
  }
}
</script>
