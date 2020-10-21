export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
    this.$store = ctx.$store
  }

  authenticate (username, password) {
    return this.$axios.post('/api/user/login', {
      username,
      password
    })
  }

  changePassword (password, newPassword) {
    return this.$axios.post('/api/user/password', {
      password,
      newPassword
    })
  }
}
