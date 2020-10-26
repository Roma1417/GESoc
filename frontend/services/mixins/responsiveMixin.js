import { get } from 'lodash'
export const responsiveMixin = {
  computed: {
    isXs () {
      return get(this.$vuetify, 'breakpoint.name') === 'xs'
    },
    isSm () {
      return get(this.$vuetify, 'breakpoint.name') === 'sm'
    },
    isMd () {
      return get(this.$vuetify, 'breakpoint.name') === 'md'
    },
    isSmOrLess () {
      return this.isXs || this.isSm
    },
    isMdOrLess () {
      return this.isMd || this.isSmOrLess
    }
  }
}
