<template>
  <img
    :class="'company-' + size"
    alt="Company"
    :src="logoSrc"
  >
</template>

<style sass>
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
import { get } from 'lodash'
export default {
  props: {
    size: {
      type: String,
      default: 'medium'
    }
  },
  computed: {
    logoSrc () {
      return get(this.$store.state.userInfo, 'current_location.company.logo') || '/vis-logo.png'
    }
  }
}
</script>
