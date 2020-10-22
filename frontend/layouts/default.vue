<template>
  <v-app>
    <v-app-bar
      :clipped-left="clipped"
      fixed
      app
    >
      <v-app-bar-nav-icon @click.stop="miniVariant = !miniVariant" />
      <CompanyLogo src="/gesoc.png" />
    </v-app-bar>
    <MenuDrawer :clipped="clipped" :mini-variant="miniVariant" />
    <v-main class="base-background">
      <v-container
        align="start"
        class="pt-12 ma-lg-auto"
      >
        <v-row
          align="start"
          justify="center"
        >
          <v-col cols="12">
            <nuxt class="nuxt-component" />
          </v-col>
        </v-row>
      </v-container>
      <v-footer
        fixed="fixed"
        app
        align="center"
        justify="center"
      >
        <v-spacer />
        <CompanyLogo src="/logo_utn.png" />
        <v-spacer />
      </v-footer>
    </v-main>
  </v-app>
</template>

<script>
import MenuDrawer from '~/components/General/Menus/MenuDrawer'
import CompanyLogo from '~/components/General/CompanyLogo'
export default {
  components: {
    MenuDrawer,
    CompanyLogo
  },
  data () {
    return {
      miniVariant: false,
      clipped: true,
      datosContacto: '+ 54 11 5263-9757 | info@verifarma.com |',
      iconos: [
        {
          name: 'BDEV',
          src: '/logo-verifarma.jpg'
        }
      ]
    }
  },
  computed: {
    title () {
      return this.$t(this.$route.name + '.menu')
    },
    userInfo: {
      get () {
        return this.$store.state.userInfo
      },
      set (val) {
        this.$store.commit('setUserInfo', val)
      }
    }
  },
  mounted () {
    this.setUserInfo()
  },
  methods: {
    setUserInfo () {
      this.loadingUser = true
      this.$userService.getUsuario()
        .then((result) => {
          if (result) {
            this.userInfo = result.data
          }
        }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loadingUser = false
    }
  }
}
</script>
