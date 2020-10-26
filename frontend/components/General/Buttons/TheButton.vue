<template>
  <v-btn
    v-bind="$attrs"
    v-on="$listeners"
  >
    <span v-if="innerText" class="pad-wide">
      {{ innerText }}
    </span>
    <v-icon v-if="icon" color="iconColor">
      {{ icon }}
    </v-icon>
  </v-btn>
</template>
<script>
export default {
  props: {
    innerText: {
      type: String,
      default: ''
    },
    icon: {
      type: String,
      default: ''
    },
    iconColor: {
      type: String,
      default: ''
    }
  }
}
</script>
