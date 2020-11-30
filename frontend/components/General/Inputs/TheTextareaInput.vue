<template>
  <v-textarea
    v-bind="$attrs"
    :maxlength="maxlength"
    :counter="maxlength"
    clearable
    clear-icon="mdi-close-circle"
    v-on="$listeners"
  />
</template>
<script>
export default {
  props: {
    maxlength: {
      default: null,
      type: String
    }
  }
}
</script>
