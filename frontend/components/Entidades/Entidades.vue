<template>
  <TheLayoutWithHeader title="entidades" :loading="loading">
    <template #filter>
      <v-row>
        <v-col>
          ACA IRIA EL FILTRO POR CATEGORIA
        </v-col>
        <v-col class="text-right">
          CREAR EGRESO BOTON
        </v-col>
      </v-row>
    </template>
    <template #body>
      <TheFilterTable
        :page-info.sync="pageInfo"
        :items="entidades"
        :headers="headers"
        :total="totalList"
        @change="getEgresos()"
      >
        <template #[`item.entidadesBase`]="entidadesBase">
          <div v-for="entidad in entidadesBase.value" :key="entidad.nombre">
            {{ entidad.nombre }}
            <div />
          </div>
        </template>
        <template #[`item.actions`]="{ }">
          Ver egresos
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
    entidades: [{
      idEntidad: '1',
      entidadesBase: [{ nombre: 'Pepa' }, { nombre: 'Pepe' }]
    },
    {
      idEntidad: '2',
      entidadesBase: [{ nombre: 'Pepa' }]
    }],
    loading: false
  }),
  computed: {
    headers () {
      return [
        {
          text: this.$t('entidades.id'),
          align: 'center',
          sortable: false,
          value: 'idEntidad'
        },
        {
          text: this.$t('entidades.entidadesBase'),
          align: 'start',
          sortable: false,
          value: 'entidadesBase'
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
