<template>
  <TheLayoutWithHeader title="transacciones.egresos" :loading="loading">
    <template #filter>
      <v-row>
        <v-col>
          ACA IRIA EL FILTRO POR CATEGORIA
        </v-col>
        <v-col class="text-right">
          <ThePrimaryButton
            :inner-text="$t('search')"
            icon="mdi-magnify"
            @click="getEgresos()"
          />
          CREAR EGRESO BOTON
        </v-col>
      </v-row>
    </template>
    <template #body>
      <TheFilterTable
        :page-info.sync="pageInfo"
        :items="egresos"
        :headers="headers"
        :total="totalList"
        @change="getEgresos()"
      >
        <template #[`item.actions`]="{ }">
          VINCULAR BOTON
          <v-icon>
            mdi-link-variant
          </v-icon>
        </template>
      </TheFilterTable>
    </template>
  </TheLayoutWithHeader>
</template>
<script>
import TheLayoutWithHeader from '~/components/General/Layouts/TheLayoutWithHeader'
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
export default {
  components: {
    TheLayoutWithHeader,
    TheFilterTable,
    ThePrimaryButton
  },
  data: () => ({
    pageInfo: {
      page: 1,
      itemsPerPage: 20
    },
    totalList: 5,
    egresos: [{
      idEgreso: '1',
      proveedor: { nombreRazonSocial: 'Pepe' },
      entidadRealizadora: { nombre: 'Pepelandia' },
      fechaOperacion: '10/07/99',
      total: '345'
    },
    {
      idEgreso: '2',
      proveedor: { nombreRazonSocial: 'Pepe' },
      entidadRealizadora: { nombre: 'Pepelandia' },
      fechaOperacion: '10/07/99',
      total: '345'
    }],
    loading: false
  }),
  computed: {
    headers () {
      return [
        {
          text: this.$t('egresos.id'),
          align: 'center',
          sortable: false,
          value: 'idEgreso'
        },
        {
          text: this.$t('egresos.proveedor'),
          align: 'start',
          sortable: false,
          value: 'proveedor.nombreRazonSocial'
        },
        {
          text: this.$t('egresos.entidad_realizadora'),
          align: 'start',
          sortable: false,
          value: 'entidadRealizadora.nombre'
        },
        {
          text: this.$t('egresos.fecha_operacion'),
          align: 'end',
          sortable: false,
          value: 'fechaOperacion'
        },
        {
          text: this.$t('egresos.total'),
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
    getEgresos () {
      this.loading = true
      this.$egresoService.getEgresos(this.pageInfo).then((result) => {
        if (result) {
          this.totalList = result.total
          this.egresos = result.data
        }
      }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    }
  }
}
</script>
