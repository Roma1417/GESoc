<template>
  <TheAutocompleteInput
    :search-input.sync="inputUsuario"
    :items="showableItems"
    :loading="loading"
    hide-no-data
    :cache-items="cacheItems"
    return-object
    v-bind="$attrs"
    v-on="$listeners"
  />
</template>
<script>
import { debounce } from 'lodash'
import TheAutocompleteInput from '~/components/General/Inputs/TheAutocompleteInput'
export default {
  components: {
    TheAutocompleteInput
  },
  props: {
    getItemsFunction: {
      type: Function,
      required: true
    },
    cacheItems: {
      type: Boolean,
      default: true
    },
    ignoreItems: {
      type: Array,
      default: () => []
    },
    matchIgnoreFunction: {
      type: Function,
      default: () => false
    }
  },
  data () {
    return {
      inputUsuario: '',
      loading: false,
      items: []
    }
  },
  computed: {
    showableItems () {
      return this.items
        .filter(item => !this.ignoreItems
          .some(ignorableItem => this.matchIgnoreFunction(ignorableItem, item)))
    }
  },
  watch: {
    inputUsuario (val) {
      if (val) {
        this.getItemsDebounced()
      }
    }
  },
  methods: {
    getItemsDebounced: debounce(function () { this.getItems() }, 500),
    getItems () {
      this.loading = true
      this.getItemsFunction(this.inputUsuario).then((result) => {
        if (result) {
          this.items = result.data
        }
      }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    }
  }
}
</script>
