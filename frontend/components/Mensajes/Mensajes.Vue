<template>
  <TheLayoutWithHeader title="mensajes.menu" :loading="loading">
    <template #body>
      <TheMessageDialog
        v-model="mensaje.mostrar"
        :header-message="mensaje.asunto"
        :body-message="mensaje.cuerpo"
        @onAccept="closeDialog"
      />
      <TheFilterTable
        :page-info.sync="pageInfo"
        :items="mensajes"
        :headers="headers"
        :total="totalList"
        @change="getMensajes()"
        @click:row="mostrarItem"
      >
        <template #[`item.asunto`]="{ item }">
          {{ ajustarTexto(item.asunto, 22) }}
        </template>
        <template #[`item.cuerpo`]="{ item }">
          {{ ajustarTexto(item.cuerpo, 100) }}
        </template>
      </TheFilterTable>
    </template>
  </TheLayoutWithHeader>
</template>
<script>
import TheLayoutWithHeader from '~/components/General/Layouts/TheLayoutWithHeader'
import TheMessageDialog from '~/components/General/Dialogs/TheMessageDialog'
export default {
  components: {
    TheLayoutWithHeader,
    TheMessageDialog
  },
  data: () => ({
    pageInfo: {
      page: 1,
      itemsPerPage: 20
    },
    totalList: 5,
    mensajes: [],
    loading: false,
    mensaje: {
      mostrar: false,
      asunto: '',
      cuerpo: ''
    }
  }),
  computed: {
    headers () {
      return [
        {
          text: this.$t('mensajes.asunto'),
          align: 'start',
          sortable: false,
          value: 'asunto',
          width: '33%'
        },
        {
          text: this.$t('mensajes.cuerpo'),
          align: 'start',
          sortable: false,
          value: 'cuerpo'
        }
      ]
    }
  },
  methods: {
    getMensajes () {
      this.loading = true
      this.$mensajeService.getMensajes(this.pageInfo).then((result) => {
        if (result) {
          this.totalList = result.total
          this.mensajes = result.data
        }
      }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    },
    ajustarTexto (texto, largo) {
      return texto.substring(0, largo) + ((texto.length > largo) ? '...' : '')
    },
    mostrarItem (Item) {
      this.mensaje.asunto = Item.asunto
      this.mensaje.cuerpo = Item.cuerpo
      this.mensaje.mostrar = true
    },
    closeDialog () {
      this.mensaje.mostrar = false
    }
  }
}
</script>
