<template>
  <v-flex class="d-flex flex-column">
    <v-row justify="center" align="center">
      <v-col md="5">
        <v-card color="transparent" flat>
          <v-row>
            <CompanyLogo src="/gesoc.png" :size="isMdOrLess ? 'big' : 'xbig'" />
          </v-row>
          <v-card-title class="headline pt-10">
            {{ $t('login.title') }}
          </v-card-title>
          <v-card-subtitle class="subheadline">
            {{ $t('login.message') }}
            <strong class="primary-color">
              {{ $t('login.message_highlight') }}
            </strong>
            <v-icon x-large color="primary">
              mdi-handshake
            </v-icon>
          </v-card-subtitle>
        </v-card>
      </v-col>
      <v-col v-if="isSmOrLess" xs="12" sm="12">
        <v-divider />
      </v-col>
      <v-col v-else cols="1" class="divider-height">
        <v-divider
          vertical
        />
      </v-col>
      <v-col md="6">
        <v-row justify="center">
          <v-col cols="12" sm="11" md="10" lg="8">
            <v-row justify="center" class="mb-6">
              <CompanyLogo src="/logo_utn.png" size="big" />
            </v-row>
            <Login />
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-flex>
</template>
<style>
</style>
<script>
import Login from '~/components/Login.vue'
import CompanyLogo from '~/components/General/CompanyLogo'
import { responsiveMixin } from '~/services/mixins/responsiveMixin'
export default {
  layout: 'login',
  components: {
    Login,
    CompanyLogo
  },
  mixins: [responsiveMixin]
}
</script>
