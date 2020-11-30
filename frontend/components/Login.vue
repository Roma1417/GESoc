<template>
  <v-card class="elevation-1">
    <v-progress-linear v-if="loading" :indeterminate="loading" />
    <v-toolbar
      color="primary"
      dark
      flat
    >
      <v-spacer />
      <v-toolbar-title class="small-toolbar-title">
        {{ $t('login.enter_credentials') }}
      </v-toolbar-title>
      <v-spacer />
    </v-toolbar>
    <v-card-text>
      <v-form @submit.prevent="loginIfPossible">
        <input type="submit" value="" class="hideInput">
        <v-row class="mx-2">
          <TheTextInput
            v-model="username"
            prepend-icon="mdi-account"
            :rules="[$rl.required('user')]"
            maxlength="32"
            :label="$t('login.username')"
            autofocus
            required
          />
        </v-row>
        <v-row class="mx-2">
          <ThePasswordInput v-model="password" />
        </v-row>
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-row justify="center">
        <v-col cols="7">
          <ThePrimaryButton
            width="100%"
            inner-text="Acceder"
            :loading="loading"
            :disabled="loading || !canLogin"
            @click="loginIfPossible()"
          />
        </v-col>
      </v-row>
    </v-card-actions>
  </v-card>
</template>
<style>
.small-toolbar-title{
  font-size: 1rem !important;
}
</style>
<script>
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
import ThePasswordInput from '~/components/General/Inputs/ThePasswordInput'
import TheTextInput from '~/components/General/Inputs/TheTextInput'
export default {
  components: {
    ThePrimaryButton,
    ThePasswordInput,
    TheTextInput
  },
  data () {
    return {
      username: '',
      password: '',
      loading: false
    }
  },
  computed: {
    canLogin () {
      return this.username && this.password
    }
  },
  methods: {
    stopLoading () {
      this.loading = false
    },
    loginIfPossible () {
      if (this.canLogin) {
        this.login()
      }
    },
    login () {
      this.loading = true
      this.$userService.authenticate(
        this.username,
        this.password
      ).then((result) => {
        if (result) {
          this.$router.push('/home')
        } else {
          this.toastError(this.$t('errors.login'))
        }
      }).finally(this.stopLoading)
    }
  }
}
</script>
