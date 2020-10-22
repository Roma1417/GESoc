<template>
  <v-container fill-height fluid>
    <v-row justify="center">
      <v-col>
        <h1>Oops...</h1>
        <p>{{ $t('errors.session') }}</p>
        <ThePrimaryButton
          :inner-text="$t('errors.session_login')"
          @click="goToLogin"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
export default {
  layout: 'error',
  components: {
    ThePrimaryButton
  },
  methods: {
    goToLogin () {
      this.$router.push('/login')
    }
  }
}
</script>
