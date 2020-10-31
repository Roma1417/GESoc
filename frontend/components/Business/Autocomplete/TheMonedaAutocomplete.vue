<template>
  <v-autocomplete
    v-model="showValue"
    :label="$t('moneda.moneda')"
    :search-input.sync="nombreMoneda"
    :items="monedas"
    :loading="loading"
    hide-no-data
    cache-items
    item-text="descripcion"
    return-object
    v-bind="$attrs"
    v-on="$listeners"
  />
</template>
<script>
import { debounce } from 'lodash'
export default {
  props: {
    value: {
      type: Object,
      default: () => ({})
    }
  },
  data () {
    return {
      nombreMoneda: '',
      loading: false,
      monedas: []
    }
  },
  computed: {
    showValue: {
      get () {
        return this.value
      },
      set (val) {
        this.$emit('input', val)
      }
    }
  },
  watch: {
    nombreMoneda (val) {
      if (val) {
        this.getMonedasDebounced()
      }
    }
  },
  methods: {
    getMonedasDebounced: debounce(function () { this.getMonedas() }, 500),
    getMonedas () {
      this.loading = true
      this.$monedaService.getMonedas(this.nombreMoneda).then((result) => {
        if (result) {
          this.monedas = result.data
        }
      }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    }
  }
}
</script>
