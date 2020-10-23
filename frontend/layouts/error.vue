<template>
  <v-app dark>
    <v-row justify="center">
      <v-col>
        <CompanyLogo src="/chrome.png" size="big" />
        <h1>Oops...</h1>
        <p>{{ $t('errors.session') }}</p>
        <ThePrimaryButton
          :inner-text="$t('errors.session_login')"
          @click="goToLogin"
        />
      </v-col>
    </v-row>
  </v-app>
</template>

<script>
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
import CompanyLogo from '~/components/General/CompanyLogo'
export default {
  layout: 'error',
  components: {
    ThePrimaryButton,
    CompanyLogo
  },
  methods: {
    goToLogin () {
      this.$router.push('/')
    }
  }
}
</script>
