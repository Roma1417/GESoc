<template>
  <v-flex class="d-flex flex-column">
    <v-row justify="center" class="mb-6">
      <CompanyLogo src="/logo_utn.png" size="big" />
    </v-row>
    <v-row justify="center">
      <v-col cols="10" sm="8" md="6" lg="4">
        <Login />
      </v-col>
    </v-row>
  </v-flex>
</template>
<style>
</style>
<script>
import Login from '~/components/Login.vue'
import CompanyLogo from '~/components/General/CompanyLogo'
export default {
  layout: 'login',
  components: {
    Login,
    CompanyLogo
  }
}
</script>
