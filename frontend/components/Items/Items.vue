<template>
  <TheLayoutWithHeader title="Items" :loading="loading">
    <template #filter>
      <v-row>
        <v-col>
          ACA IRIA EL FILTRO POR CATEGORIA
        </v-col>
      </v-row>
    </template>
    <template #body>
      <TheFilterTable
        :page-info.sync="pageInfo"
        :items="items"
        :headers="headers"
        :total="totalList"
        @change="getEgresos()"
      >
        <template #[`item.categorias`]="categorias">
          {{ categorias.value.join() }}
          <TheEditButton />
        </template>
      </TheFilterTable>
    </template>
  </TheLayoutWithHeader>
</template>
<script>
import TheLayoutWithHeader from '~/components/General/Layouts/TheLayoutWithHeader'
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
import TheEditButton from '~/components/Items/TheEditButton'
export default {
  components: {
    TheLayoutWithHeader,
    TheFilterTable,
    TheEditButton
  },
  data: () => ({
    pageInfo: {
      page: 1,
      itemsPerPage: 20
    },
    totalList: 5,
    items: [{
      idItem: '1',
      descripcion: 'Batata',
      categorias: ['Cat1', 'cat2']
    },
    {
      idItem: '2',
      descripcion: 'Papa',
      categorias: ['Cat1', 'cat2']
    }],
    loading: false
  }),
  computed: {
    headers () {
      return [
        {
          text: this.$t('ID'),
          align: 'center',
          sortable: false,
          value: 'idItem'
        },
        {
          text: this.$t('Descripcion'),
          align: 'start',
          sortable: false,
          value: 'descripcion'
        },
        {
          text: this.$t('Categorias'),
          value: 'categorias',
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
