<template>
  <TheLayoutWithHeader title="items" :loading="loading">
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
        @change="getItems()"
      >
        <template #[`item.categorias`]="{ }">
          <Categorias />
        </template>
      </TheFilterTable>
    </template>
  </TheLayoutWithHeader>
</template>
<script>
import TheLayoutWithHeader from '~/components/General/Layouts/TheLayoutWithHeader'
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
import Categorias from '~/components/Items/Categorias'
export default {
  components: {
    TheLayoutWithHeader,
    TheFilterTable,
    Categorias
  },
  data: () => ({
    pageInfo: {
      page: 1,
      itemsPerPage: 20
    },
    totalList: 5,
    items: [],
    loading: false
  }),
  computed: {
    headers () {
      return [
        {
          text: this.$t('ID'),
          align: 'center',
          sortable: false,
          value: 'id'
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
          width: '40%',
          sortable: false
        }
      ]
    }
  },
  methods: {
    getItems () {
      this.loading = true
      this.$itemService.getItems(this.pageInfo).then((result) => {
        if (result) {
          this.totalList = result.total
          this.items = result.data
        }
      }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    }
  }
}
</script>
