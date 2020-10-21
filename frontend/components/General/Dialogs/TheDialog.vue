<template>
  <v-dialog
    v-model="mostrar"
    persistent
    v-bind="$attrs"
    :loading="loading"
    max-width="700"
  >
    <template #activator="{on}">
      <slot name="activator" :on="on" />
    </template>
    <v-card>
      <v-progress-linear v-if="loading" :indeterminate="loading" />
      <slot />
    </v-card>
  </v-dialog>
</template>
<script>
export default {
  props: {
    value: {
      type: Boolean,
      default: true
    },
    loading: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    mostrar: {
      get () {
        return this.value
      },
      set (value) {
        this.$emit('input', value)
      }
    }
  }
}
</script>
