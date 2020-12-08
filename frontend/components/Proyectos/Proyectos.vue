<template>
  <TheLayoutWithHeader title="proyectos.titulo" :loading="loading">
    <template #filter>
      <v-row no-gutters>
        <v-col class="text-right">
          <ThePrimaryButton
            class="my-4"
            :inner-text="$t('search')"
            icon="mdi-magnify"
            @click="getProyectos()"
          />
          <ProyectoForm
            @created="getProyectos()"
          />
        </v-col>
      </v-row>
    </template>
    <template #body>
      <TheFilterTable
        :page-info.sync="pageInfo"
        :items="proyectosFinanciamiento"
        :headers="headers"
        :total="totalList"
        @change="getProyectos()"
      >
        <template #[`item.actions`]="{ item }">
          <VinculacionForm
            :title-text="$t('vincular.proyectoIngresoTitle')"
            button-icon="mdi-cash-plus"
            :vincular-tooltip-text="$t('vincular.proyecto_ingreso')"
            :objeto-a-vincular="item"
            :vincular-con-egreso="false"
            :vinculacion-function="$proyectoFinanciamientoService.vincularProyecto"
            :mensaje-ingreso-de-id="$t('vincular.info_proyecto_ingreso_id')"
            @created="getProyectos()"
          />
          <VinculacionForm
            :title-text="$t('vincular.proyectoEgresoTitle')"
            button-icon="mdi-cash-minus"
            :vincular-tooltip-text="$t('vincular.proyecto_egreso')"
            :objeto-a-vincular="item"
            :vinculacion-function="$proyectoFinanciamientoService.vincularProyecto"
            :mensaje-ingreso-de-id="$t('vincular.info_proyecto_egreso_id')"
            @created="getProyectos()"
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
import ProyectoForm from '~/components/Proyectos/ProyectoForm'
import VinculacionForm from '~/components/Egresos/VinculacionForm' // Esto se va ir ALV
export default {
  components: {
    TheLayoutWithHeader,
    TheFilterTable,
    ThePrimaryButton,
    ProyectoForm,
    VinculacionForm
  },
  data: () => ({
    pageInfo: {
      page: 1,
      itemsPerPage: 20
    },
    totalList: 5,
    proyectosFinanciamiento: [],
    loading: false
  }),
  computed: {
    headers () {
      return [
        {
          text: this.$t('proyectos.id'),
          align: 'center',
          sortable: false,
          value: 'id'
        },
        {
          text: this.$t('proyectos.entidad'),
          align: 'start',
          sortable: false,
          value: 'entidadRealizadora.nombre'
        },
        {
          text: this.$t('proyectos.director'),
          align: 'start',
          sortable: false,
          value: 'director.nombre'
        },
        {
          text: this.$t('proyectos.presupuestos_exigibles'),
          align: 'start',
          sortable: false,
          value: 'presupuestosMinimos'
        },
        {
          text: this.$t('proyectos.monto'),
          align: 'end',
          sortable: false,
          value: 'montoMaximoSinPresupuestos'
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
    getProyectos () {
      this.loading = true
      this.$proyectoFinanciamientoService.getProyectos(this.pageInfo)
        .then((result) => {
          if (result) {
            this.totalList = result.total
            this.proyectosFinanciamiento = result.data
          }
        }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    }
  }
}
</script>
