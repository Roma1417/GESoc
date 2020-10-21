<template>
  <v-app id="login">
    <v-app-bar
      height="100"
      class="elevation-1"
      app
    >
      <div>
        GESOC LOGO
      </div>
    </v-app-bar>
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
    </v-main>
    <v-footer
      fixed="fixed"
      app
      align="center"
      justify="center"
    >
      <v-spacer />
      <div>
        GESOC &copy; {{ new Date().getFullYear() }}
      </div>
      <v-spacer />
    </v-footer>
  </v-app>
</template>
<script>
export default {
}
</script>
