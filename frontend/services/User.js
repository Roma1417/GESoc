const qs = require('querystring')
export default class User {
  constructor (ctx) {
    this.$axios = ctx.$axios
    this.$store = ctx.$store
  }

  test () {
    return this.$axios.getOrFalse('/api/hi')
  }

  authenticate (user, password) {
    const requestBody = {
      user,
      password
    }
    const config = {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }
    return this.$axios.post('/api/auth', qs.stringify(requestBody), config)
  }

  getMensajes (userId, page, itemsPerPage) {
    return this.$axios.getOrFalse(`/api/user/${userId}/mensajes`, {
      params: {
        page,
        itemsPerPage
      }
    })
  }

  getUsuario () {
    return this.$axios.getOrFalse('/api/user')
  }
}
