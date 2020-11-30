<template>
  <v-container fill-height fluid>
    Redireccionando...
  </v-container>
</template>

<script>
export default {
  layout: 'login',
  mounted () {
    this.$router.push('/')
  }
}
</script>
