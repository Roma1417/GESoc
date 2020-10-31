<template>
  <TheLayoutWithHeader title="transacciones.presupuestos" :loading="loading">
    <template #filter>
      <v-row>
        <v-col class="text-right">
          CREAR PRESUPUESTO BOTON
        </v-col>
      </v-row>
    </template>
    <template #body>
      <TheFilterTable
        :page-info.sync="pageInfo"
        :items="presupuestos"
        :headers="headers"
        :total="totalList"
        @change="getPresupuestos()"
      >
        <template #[`item.actions`]="{ }">
          VER DETALLE
        </template>
      </TheFilterTable>
    </template>
  </TheLayoutWithHeader>
</template>
<script>
import TheLayoutWithHeader from '~/components/General/Layouts/TheLayoutWithHeader'
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
export default {
  components: {
    TheLayoutWithHeader,
    TheFilterTable
  },
  data: () => ({
    pageInfo: {
      page: 1,
      itemsPerPage: 20
    },
    totalList: 5,
    ingresos: [],
    loading: false
  }),
  computed: {
    headers () {
      return [
        {
          text: this.$t('ingresos.id'),
          align: 'center',
          sortable: false,
          value: 'idIngreso'
        },
        {
          text: this.$t('ingresos.entidad_realizadora'),
          align: 'start',
          sortable: false,
          value: 'entidadRealizadora.nombre'
        },
        {
          text: this.$t('ingresos.documento_comercial'),
          align: 'center',
          sortable: false,
          value: 'documentoComercial.numero'
        },
        {
          text: this.$t('ingresos.descripcion'),
          align: 'start',
          sortable: false,
          value: 'descripcion'
        },
        {
          text: this.$t('ingresos.total'),
          align: 'end',
          sortable: false,
          value: 'total'
        },
        {
          text: this.$t('actions'),
          value: 'actions',
          sortable: false
        }
      ]
    }
  },
  methods: {
    getPresupuestos () {
      this.loading = true
      this.$presupuestoService.getPresupuestos(this.pageInfo).then((result) => {
        if (result) {
          this.totalList = result.total
          this.presupuestos = result.data
        }
      }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    }
  }
}
</script>
