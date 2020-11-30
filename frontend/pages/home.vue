<template>
  <TheDefaultLayout>
    <v-row>
      <v-col class="headline text-center">
        {{ $t('dashboard.welcome') }}
      </v-col>
    </v-row>
    <v-row>
      <v-col v-for="(mainPath, idx) in mainPaths" :key="idx">
        <ActionCard
          :action-name="mainPath.title"
          :action-icon="mainPath.icon"
          :action-description="mainPath.description"
          @click="goTo(mainPath.path)"
        />
      </v-col>
    </v-row>
  </TheDefaultLayout>
</template>

<script>
import TheDefaultLayout from '~/components/General/Layouts/TheDefaultLayout'
import ActionCard from '~/components/General/Cards/ActionCard'
export default {
  components: {
    TheDefaultLayout,
    ActionCard
  },
  computed: {
    mainPaths () {
      return [
        {
          path: '/transacciones/egresos',
          icon: 'mdi-file-document',
          title: this.$t('egresos.menu'),
          description: this.$t('egresos.welcome_description')
        },
        {
          path: '/transacciones/ingresos',
          icon: 'mdi-file-document-outline',
          title: this.$t('ingresos.menu'),
          description: this.$t('ingresos.welcome_description')
        },
        {
          path: '/usuario/mensajes',
          icon: 'mdi-message-bulleted',
          title: this.$t('mensajes.menu'),
          description: this.$t('mensajes.welcome_description')
        }
      ]
    }
  },
  methods: {
    goTo (path) {
      this.$router.push(path)
    }
  }
}
</script>
