<template>
  <v-data-table
    :options.sync="options"
    :server-items-length="total"
    :loading-text="$t('loading')"
    :no-data-text="$t('no_data_text')"
    :expanded.sync="expanded"
    v-bind="$attrs"
    :footer-props="{
      itemsPerPageText: $t('items-per-page'),
      'items-per-page-options': [20,50,100]
    }"
    v-on="listeners"
  >
    <template v-for="slotName in Object.keys($scopedSlots)" #[slotName]="slotScope">
      <slot :name="slotName" v-bind="slotScope" />
    </template>
  </v-data-table>
</template>
<script>
export default {
  props: {
    pageInfo: {
      type: Object,
      default: () => {
        return {
          page: 1,
          itemsPerPage: 20
        }
      }
    },
    total: {
      type: Number,
      default: 0
    }
  },
  data () {
    return {
      mounted: false,
      expanded: [],
      options: {
        page: 1,
        itemsPerPage: 20
      }
    }
  },
  computed: {
    listeners () {
      return {
        ...this.$listeners
      }
    }
  },
  watch: {
    options: {
      handler: 'searchList',
      deep: true
    }
  },
  mounted () {
    this.mounted = true
  },
  methods: {
    searchList () {
      if (this.mounted) {
        this.$emit('update:pageInfo', this.options)
        this.$emit('change', this.options)
      }
    }
  }
}
</script>
