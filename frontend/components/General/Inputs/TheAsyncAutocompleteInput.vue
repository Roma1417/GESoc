<template>
  <TheAutocompleteInput
    :search-input.sync="inputUsuario"
    :items="items"
    :loading="loading"
    hide-no-data
    cache-items
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
    }
  },
  data () {
    return {
      inputUsuario: '',
      loading: false,
      items: []
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
