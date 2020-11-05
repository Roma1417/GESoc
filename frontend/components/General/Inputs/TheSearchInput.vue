<template>
  <v-form @submit.prevent="getItemsFunction(toSearch)">
    <input type="submit" value="" class="hideInput">
    <v-row class="mx-2">
      <TheTextInput
        v-model="toSearch"
        maxlength="32"
        required
        prepend-icon="mdi-magnify"
        :append-icon="toSearch ? 'mdi-close' : ''"
        v-bind="$attrs"
        v-on="$listeners"
        @click:append="toSearch = ''; getItemsFunction()"
      />
    </v-row>
  </v-form>
</template>
<script>
// import debounce from 'lodash'
export default {
  props: {
    getItemsFunction: {
      type: Function,
      required: true
    }
  },
  data () {
    return {
      toSearch: ''
    }
  },
  /* watch: {
    toSearch () {
      this.debouncedFunction(this.toSearch)
    }
  }, */
  methods: {
    // No anda
    // debouncedFunction: debounce(function (val) { this.getItemsFunction(val) }, 500)
  }
}
</script>
