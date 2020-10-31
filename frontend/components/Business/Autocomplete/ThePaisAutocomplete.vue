<template>
  <v-autocomplete
    v-model="showValue"
    :label="$t('pais.pais')"
    :search-input.sync="nombrePais"
    :items="paises"
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
      nombrePais: '',
      loading: false,
      paises: []
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
    nombrePais (val) {
      if (val) {
        this.getPaisesDebounced()
      }
    }
  },
  methods: {
    getPaisesDebounced: debounce(function () { this.getPaises() }, 500),
    getPaises () {
      this.loading = true
      this.$paisService.getPaises(this.nombrePais).then((result) => {
        if (result) {
          this.paises = result.data
        }
      }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    }
  }
}
</script>
