<template>
  <TheLayoutWithHeader title="items.menu" :loading="loading">
    <template #filter>
      <v-row>
        <v-col>
          ACA IRIA EL FILTRO POR NOMBRE DE ITEM
        </v-col>
        <v-col class="text-right">
          <ThePrimaryButton
            :inner-text="$t('search')"
            icon="mdi-magnify"
            @click="getItems()"
          />
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
        <template #[`item.categorias`]="{ item }">
          <TheCategoriasDialog
            v-model="item.categorias"
          />
        </template>
      </TheFilterTable>
    </template>
  </TheLayoutWithHeader>
</template>
<script>
import TheLayoutWithHeader from '~/components/General/Layouts/TheLayoutWithHeader'
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
import TheCategoriasDialog from '~/components/Business/Dialogs/TheCategoriasDialog'
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
export default {
  components: {
    TheLayoutWithHeader,
    TheFilterTable,
    TheCategoriasDialog,
    ThePrimaryButton
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
