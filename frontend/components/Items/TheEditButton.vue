<template>
  <TheButtonWithToolTip
    icon="mdi-pencil"
    v-bind="$attrs"
    title="Pepe"
    v-on="$listeners"
  />
</template>
<script>
import TheButtonWithToolTip from '~/components/General/Buttons/TheButtonWithToolTip'
export default {
  components: {
    TheButtonWithToolTip
  }
}
</script>
