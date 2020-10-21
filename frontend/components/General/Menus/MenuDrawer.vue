<template>
  <v-navigation-drawer
    v-model="drawer"
    permanent
    :mini-variant="miniVariant"
    :expand-on-hover="miniVariant"
    class=""
    style="top:65px;"
    fixed
    app
  >
    <v-progress-linear v-if="loading" :indeterminate="loading" />
    <v-list expand>
      <template v-for="(item,i) in menus">
        <MenuDrawerContent :key="i + 'A'" :item="item" />
      </template>
    </v-list>
  </v-navigation-drawer>
</template>
<script>
import MenuDrawerContent from '~/components/General/Menus/MenuDrawerContent'
export default {
  components: {
    MenuDrawerContent
  },
  props: {
    miniVariant: {
      type: Boolean,
      default: false
    },
    loading: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      drawer: true
    }
  },
  computed: {
    menus () {
      return this.$store.state.userInfo ? this.$store.state.userInfo.menu : [
        {
          permissions: [],
          title: 'home',
          icon: 'mdi-home',
          to: '/'
        },
        {
          permissions: [],
          title: 'calculations.calculations',
          icon: 'mdi-calculator',
          to: null,
          children: [
            {
              permissions: [],
              title: 'calculations.list',
              to: '/calculus/calculus'
            },
            {
              permissions: [],
              title: 'results.results',
              to: '/calculus/results',
              parent: null
            }
          ]
        },
        {
          permissions: [],
          title: 'devices.devices',
          icon: 'mdi-devices',
          to: '/devices',
          children: [
            {
              permissions: [],
              title: 'devices.devices',
              to: '/devices/'
            },
            {
              permissions: [],
              title: 'devices.device_models',
              to: '/devices/models'
            },
            {
              permissions: [],
              title: 'devices.device_brands',
              to: '/devices/brands'
            },
            {
              permissions: [],
              title: 'devices.device_types',
              to: '/devices/types'
            }
          ],
          parent: null
        },
        {
          permissions: [],
          title: 'measurement_units.measurement_units',
          icon: 'mdi-weight-kilogram',
          to: '/measure-units',
          children: [
            {
              permissions: [],
              title: 'measurement_units.measurement_units',
              to: '/measure-units/units'
            },
            {
              permissions: [],
              title: 'measurement_units.measurement_unit_types',
              to: '/measure-units/types'
            },
            {
              permissions: [],
              title: 'measurement_units.measurement_unit_systems',
              to: '/measure-units/systems'
            }
          ]
        }
      ]
    }
  }
}
</script>
