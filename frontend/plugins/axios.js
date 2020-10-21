export default function ({ $axios, store, redirect, app }) {
  $axios.onRequest((request) => {
    request.headers.common.intercepted = 'true'
    return request
  })
  $axios.onResponse((response) => {
    const code = parseInt(response.status)
    if (code === 403 || code === 401) {
      console.log('patear')
      // return redirect('/logout')
    }
  })

  $axios.onError((error) => {
    const code = parseInt(error.response.status)
    if (code === 403 || code === 401) {
      console.log('patear')
      // return redirect('/logout')
    }
    return error
  })
  $axios.getOrFalse = (url, options) => {
    return $axios.get(url, options).then((response) => {
      if (response.status === 200) {
        return response.data
      } else {
        return false
      }
    }).catch(() => {
      return false
    })
  }

  $axios.postOrFalse = (url, options, config) => {
    return $axios.post(url, options, config).then((response) => {
      if (response.status === 200) {
        return response
      } else {
        return false
      }
    }).catch(() => {
      return false
    })
  }

  $axios.putOrFalse = (url, options) => {
    return $axios.put(url, options).then((response) => {
      if (response.status === 200) {
        return response
      } else {
        return false
      }
    }).catch(() => {
      return false
    })
  }

  $axios.deleteOrFalse = (url, options) => {
    return $axios.delete(url, options).then((response) => {
      if (response.status === 200) {
        return response
      } else {
        return false
      }
    }).catch(() => {
      return false
    })
  }
}
