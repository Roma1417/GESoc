<template>
  <TheLayoutWithHeader title="proyectos.titulo" :loading="loading">
    <template #filter>
      <v-row no-gutters>
        <v-col>
          <TheTextInput
            v-model="filter.nombre"
            clearable
            :label="$t('proyectos.search')"
            maxlength="32"
          />
        </v-col>
        <v-col class="text-right">
          <ThePrimaryButton
            class="my-4"
            :inner-text="$t('search')"
            icon="mdi-magnify"
            @click="getEgresos()"
          />
          <ProyectoForm
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
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import ProyectoForm from '~/components/Proyectos/ProyectoForm'
import VinculacionForm from '~/components/Egresos/VinculacionForm' // Esto se va ir ALV
export default {
  components: {
    TheLayoutWithHeader,
    TheFilterTable,
    ThePrimaryButton,
    TheTextInput,
    ProyectoForm,
    VinculacionForm
  },
  data: () => ({
    pageInfo: {
      page: 1,
      itemsPerPage: 20
    },
    totalList: 5,
    // Adaptar esto para que queden bien los campos que se van a mostrar
    egresos: [],
    filter: {},
    loading: false
  }),
  computed: {
    headers () {
      return [
        {
          text: this.$t('proyectos.id'),
          align: 'center',
          sortable: false,
          value: 'idEgreso'
        },
        {
          text: this.$t('proyectos.director'),
          align: 'start',
          sortable: false,
          value: 'proveedor.nombreRazonSocial'
        },
        {
          text: this.$t('proyectos.presupuestos_exigibles'),
          align: 'start',
          sortable: false,
          value: 'entidadRealizadora.nombre'
        },
        {
          text: this.$t('proyectos.ingresos'),
          align: 'start',
          sortable: false,
          value: 'fechaOperacion'
        },
        {
          text: this.$t('proyectos.total'),
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
