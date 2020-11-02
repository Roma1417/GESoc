<template>
  <v-autocomplete
    v-model="showValue"
    :label="$t('items.categorias.autocomplete')"
    :search-input.sync="nombreCategoria"
    :items="categorias"
    :loading="loading"
    :disabled="disabled"
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
    },
    disabled: {
      type: Boolean,
      default: false
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
  mounted () {
    if (this.value) {
      this.categorias = this.value
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
