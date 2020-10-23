<template>
  <v-card
    class="mx-auto mhx-60"
    color="transparent"
    shaped
    elevation="0"
  >
    <v-list-item dense no-gutters>
      <v-list-item-content class="header-data-user text-right">
        <span class="text-right">
          {{ userInfo.nombre }}
        </span>
        <v-list-item-subtitle>
          <v-row no-gutters>
            <v-col class="text-right">
              <div>
                {{ userInfo.username }}
              </div>
            </v-col>
          </v-row>
        </v-list-item-subtitle>
      </v-list-item-content>
      <v-list-item-icon>
        <v-icon class="mt-4" x-large>
          mdi-account-circle
        </v-icon>
      </v-list-item-icon>
    </v-list-item>
  </v-card>
</template>
<script>
import { get } from 'lodash'
export default {
  computed: {
    userInfo: {
      get () {
        return get(this, '$store.state.userInfo') || {}
      },
      set (val) {
        this.$store.commit('setUserInfo', val)
      }
    }
  },
  mounted () {
    this.setUserInfo()
  },
  methods: {
    setUserInfo () {
      this.loadingUser = true
      this.$userService.getUsuario()
        .then((result) => {
          if (result) {
            this.userInfo = result
          }
        }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loadingUser = false
    }
  }
}
</script>
