<template>
  <v-autocomplete
    v-model="showValue"
    :label="$t('entidad.entidad')"
    :search-input.sync="nombreEntidad"
    :items="paises"
    :loading="loading"
    hide-no-data
    cache-items
    item-text="nombre"
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
      nombreEntidad: '',
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
    nombreEntidad (val) {
      if (val) {
        this.getEntidadesDebounced()
      }
    }
  },
  methods: {
    getEntidadesDebounced: debounce(function () { this.getEntidades() }, 500),
    getEntidades () {
      this.loading = true
      this.$entidadService.getEntidades(this.nombreEntidad).then((result) => {
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
