import Vue from 'vue'
import Toasted from 'vue-toasted'
Vue.use(Toasted)
Vue.mixin({
  methods: {
    toastError (msg) {
      this.$toasted.show('Error: ' + msg, {
        theme: 'bubble',
        position: 'top-center',
        duration: 5000,
        icon: 'alert',
        iconPack: 'mdi'
      })
    },
    toastWarn (msg) {
      this.$toasted.show('Advertencia: ' + msg, {
        theme: 'bubble',
        position: 'top-center',
        duration: 5000,
        icon: 'information-outline',
        iconPack: 'mdi'
      })
    },
    toastNotification (msg) {
      this.$toasted.show('Info: ' + msg, {
        theme: 'toasted-primary',
        position: 'top-center',
        duration: 5000,
        icon: 'information-outline',
        iconPack: 'mdi'
      })
    },
    toastSuccess (msg) {
      this.$toasted.success(msg, {
        theme: 'bubble',
        position: 'top-center',
        duration: 5000,
        icon: 'check-circle-outline',
        iconPack: 'mdi'
      })
    }
  }
})
export default ({ app }) => {
  app.toastBase = new Vue()
}
