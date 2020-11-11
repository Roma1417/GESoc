<template>
  <v-col
    md="4"
    sm="6"
    cols="12"
    v-bind="$attrs"
    v-on="$listeners"
  >
    <slot />
  </v-col>
</template>
<script>
export default {
}
</script>
