import { get } from 'lodash'
export const responsiveMixin = {
  computed: {
    isXs () {
      return get(this.$vuetify, 'breakpoint.name') === 'xs'
    },
    isSm () {
      return get(this.$vuetify, 'breakpoint.name') === 'sm'
    }
  }
}
