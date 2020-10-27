<template>
  <v-autocomplete
    v-model="showValue"
    :label="$t('categorias.categorias')"
    :search-input.sync="nombreCategoria"
    :items="categorias"
    :loading="loading"
    hide-no-data
    cache-items
    item-text="descripcion"
    return-object
    :chips="chips"
    multiple
    v-bind="$attrs"
    v-on="$listeners"
  >
    <template v-if="chips" #selection="data">
      <v-chip
        v-bind="data.attrs"
        :input-value="data.selected"
        close
        @click="data.select"
        @click:close="remove(data.item)"
      >
        {{ data.item.descripcion }}
      </v-chip>
    </template>
  </v-autocomplete>
</template>
<script>
import { debounce } from 'lodash'
export default {
  props: {
    value: {
      type: Array,
      default: () => ([])
    },
    chips: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      nombreCategoria: '',
      loading: false,
      categorias: []
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
    nombreCategoria (val) {
      if (val) {
        this.getCategoriasDebounced()
      }
    }
  },
  methods: {
    getCategoriasDebounced: debounce(function () { this.getCategorias() }, 500),
    getCategorias () {
      this.loading = true
      this.$categoriaService.getCategorias(this.nombreCategoria).then((result) => {
        if (result) {
          this.categorias = result.data
        }
      }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    },
    remove (categoria) {
      this.showValue = this.showValue.filter(unaCategoria => unaCategoria.id !== categoria.id)
    }
  }
}
</script>
