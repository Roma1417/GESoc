<template>
  <v-app>
    <v-app-bar
      :clipped-left="clipped"
      fixed
      app
    >
      <v-app-bar-nav-icon @click.stop="miniVariant = !miniVariant" />
      <CompanyLogo src="/gesoc.png" />
      <v-spacer />
      <UserInfoCard />
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
import UserInfoCard from '~/components/Business/Cards/UserInfoCard'
export default {
  components: {
    MenuDrawer,
    CompanyLogo,
    UserInfoCard
  },
  data () {
    return {
      miniVariant: false,
      clipped: true
    }
  },
  computed: {
    title () {
      return this.$t(this.$route.name + '.menu')
    }
  }
}
</script>
