<template>
  <TheLayoutWithHeader title="transacciones.egresos" :loading="loading">
    <template #filter>
      <v-row no-gutters>
        <v-col>
          <TheCategoriasAutocomplete
            v-model="filter.categorias"
            multiple
            :chips="false"
          />
        </v-col>
        <v-col class="text-right">
          <ThePrimaryButton
            class="mt-4"
            :inner-text="$t('search')"
            icon="mdi-magnify"
            @click="getEgresos()"
          />
          <EgresoForm
            @created="getEgresos()"
          />
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
        <template #[`item.actions`]="{ item }">
          <VinculacionForm
            v-if="!item.idIngresoAsociado"
            :title-text="$t('vincular.egresoTitle')"
            :vincular-tooltip-text="$t('vincular.egreso')"
            :egreso-a-vincular="item"
          />
        </template>
      </TheFilterTable>
    </template>
  </TheLayoutWithHeader>
</template>
<script>
import TheLayoutWithHeader from '~/components/General/Layouts/TheLayoutWithHeader'
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
import TheCategoriasAutocomplete from '~/components/Business/Autocomplete/TheCategoriasAutocomplete'
import EgresoForm from '~/components/Egresos/EgresoForm'
import VinculacionForm from '~/components/Egresos/VinculacionForm'
export default {
  components: {
    TheLayoutWithHeader,
    TheFilterTable,
    ThePrimaryButton,
    TheCategoriasAutocomplete,
    EgresoForm,
    VinculacionForm
  },
  data: () => ({
    pageInfo: {
      page: 1,
      itemsPerPage: 20
    },
    totalList: 5,
    egresos: [],
    filter: {},
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
      this.$egresoService.getEgresos(this.pageInfo, this.filter.categorias)
        .then((result) => {
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
