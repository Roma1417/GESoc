<template>
  <v-tooltip top>
    <template #activator="{ on }">
      <TheActionButton
        v-bind="$attrs"
        medium
        v-on="on"
        @click="clicked($event)"
      />
    </template>
    <span>{{ title }}</span>
  </v-tooltip>
</template>
<script>
import TheActionButton from '~/components/General/Buttons/TheActionButton'
export default {
  components: {
    TheActionButton
  },
  props: {
    title: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      dialog: false
    }
  },
  methods: {
    clicked ($event) {
      this.$emit('click', $event)
    }
  }
}
</script>
