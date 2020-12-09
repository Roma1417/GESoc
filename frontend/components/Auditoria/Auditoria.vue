<template>
  <TheLayoutWithHeader title="auditoria.titulo" :loading="loading">
    <template #filter>
      <v-row no-gutters>
        <TheResponsiveColumn class="px-4">
          <AuditoriaNombreClase
            v-model="filter.nombreClase"
          />
        </TheResponsiveColumn>
        <TheResponsiveColumn class="px-4">
          <AuditoriaTipoOperacion
            v-model="filter.tipoOperacion"
          />
        </TheResponsiveColumn>
        <v-col class="text-right">
          <ThePrimaryButton
            class="my-4"
            :inner-text="$t('search')"
            icon="mdi-magnify"
            @click="getOperaciones()"
          />
        </v-col>
      </v-row>
    </template>
    <template #body>
      <TheMessageDialog
        v-model="operacion.mostrar"
        :header-message="operacion.nombreClase"
        :body-message="operacion.objetoModificado"
        @onAccept="closeDialog"
      />
      <TheFilterTable
        :page-info.sync="pageInfo"
        :items="operaciones"
        :headers="headers"
        :total="totalList"
        @change="getOperaciones()"
        @click:row="mostrarItem"
      >
        <template #[`item.objetoModificado`]="{ item }">
          {{ ajustarTexto(JSON.stringify(item.objetoModificado), 100) }}
        </template>
      </TheFilterTable>
    </template>
  </TheLayoutWithHeader>
</template>
<script>
import TheLayoutWithHeader from '~/components/General/Layouts/TheLayoutWithHeader'
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
import TheMessageDialog from '~/components/General/Dialogs/TheMessageDialog'
import AuditoriaNombreClase from '~/components/Business/Auditoria/AuditoriaNombreClase'
import AuditoriaTipoOperacion from '~/components/Business/Auditoria/AuditoriaTipoOperacion'
export default {
  components: {
    TheLayoutWithHeader,
    TheFilterTable,
    ThePrimaryButton,
    TheMessageDialog,
    AuditoriaNombreClase,
    AuditoriaTipoOperacion
  },
  data: () => ({
    pageInfo: {
      page: 1,
      itemsPerPage: 20
    },
    totalList: 5,
    operaciones: [],
    loading: false,
    filter: {},
    operacion: {
      mostrar: false,
      nombreClase: '',
      objetoModificado: ''
    }
  }),
  computed: {
    headers () {
      return [
        {
          text: this.$t('auditoria.fecha_hora'),
          align: 'center',
          sortable: false,
          value: 'fechaOperacion'
        },
        {
          text: this.$t('auditoria.entidad'),
          align: 'start',
          sortable: false,
          value: 'nombreClase'
        },
        {
          text: this.$t('auditoria.tipo_operacion'),
          align: 'start',
          sortable: false,
          value: 'tipoOperacion'
        },
        {
          text: this.$t('auditoria.detalle_entidad'),
          align: 'start',
          sortable: false,
          value: 'objetoModificado'
        }
      ]
    }
  },
  methods: {
    getOperaciones () {
      this.loading = true
      this.$registroOperacionService
        .getOperaciones(this.filter.nombreClase, this.filter.tipoOperacion, this.pageInfo)
        .then((result) => {
          if (result) {
            this.totalList = result.total
            this.operaciones = result.data
          }
        }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    },
    ajustarTexto (texto, largo) {
      return texto.substring(0, largo) + ((texto.length > largo) ? '...' : '')
    },
    mostrarItem (item) {
      this.operacion.nombreClase = item.nombreClase
      this.operacion.objetoModificado = JSON.stringify(item.objetoModificado)
      this.operacion.mostrar = true
    },
    closeDialog () {
      this.operacion.mostrar = false
    }
  }
}
</script>
