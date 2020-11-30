<template>
  <TheTextInput
    v-model="password"
    :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
    :rules="[$rl.required('password')]"
    :type="showPassword ? 'text' : 'password'"
    :label="$t('login.password')"
    maxlength="32"
    prepend-icon="mdi-lock"
    v-on="$listeners"
    @click:append="showPassword = !showPassword"
  />
</template>

<script>
import TheTextInput from '~/components/General/Inputs/TheTextInput'

export default {
  components: {
    TheTextInput
  },
  props: {
    value: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      showPassword: false
    }
  },
  computed: {
    password: {
      get () {
        return this.value
      },
      set (val) {
        this.$emit('input', val)
      }
    }
  }
}
</script>
