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
      return [
        {
          title: 'home',
          icon: 'mdi-home',
          to: '/home'
        },
        {
          title: 'transacciones.menu',
          icon: 'mdi-cash-multiple',
          to: null,
          children: [
            {
              title: 'transacciones.egresos',
              to: '/transacciones/egresos'
            },
            {
              title: 'transacciones.ingresos',
              to: '/transacciones/ingresos'
            }
          ]
        },
        {
          title: 'entidades.menu',
          icon: 'mdi-domain',
          to: '/entidades'
        },
        {
          title: 'items.menu',
          icon: 'mdi-package-variant',
          to: '/items'
        },
        {
          title: 'usuario.menu',
          icon: 'mdi-account-circle',
          to: null,
          children: [
            {
              title: 'mensajes.menu',
              to: '/usuario/mensajes'
            }
          ]
        },
        {

          title: 'sesion.salir',
          icon: 'mdi-power',
          to: '/logout'
        }

      ]
    }
  }
}
</script>
