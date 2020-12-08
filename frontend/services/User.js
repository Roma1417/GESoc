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
    return this.$axios.post('/api/auth', qs.stringify(requestBody), config).then((res) => {
      if (res.status === 200) {
        return res
      } else {
        return false
      }
    }).catch(() => false)
  }

  getMensajes (userId, page, itemsPerPage) {
    return this.$axios.getOrFalse(`/api/user/${userId}/mensajes`, {
      params: {
        page,
        itemsPerPage
      }
    })
  }

  getUsers ({ page, itemsPerPage }, user) {
    const params = {
      page,
      itemsPerPage
    }
    if (user && user.length) {
      params.users = user.map(user => user.userId).join(',')
    }
    return this.$axios.getOrFalse('/api/users', { params })
  }

  getUsuario () {
    return this.$axios.getOrFalse('/api/user')
  }
}
