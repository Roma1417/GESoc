<template>
  <v-app id="login">
    <v-main class="base-background">
      <v-container
        align="start"
        class="ma-lg-auto"
      >
        <v-row
          align="start"
          justify="center"
          no-gutters
        >
          <v-col cols="12">
            <nuxt />
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
