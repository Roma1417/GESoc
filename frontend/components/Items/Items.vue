<template>
  <TheLayoutWithHeader title="items.menu" :loading="loading" @search="getItems()">
    <template #filter>
      <v-row>
        <v-col cols="10" sm="8" md="4" lg="3">
          <TheTextInput
            v-model="filter.nombre"
            clearable
            :label="$t('items.search')"
            maxlength="32"
          />
        </v-col>
        <v-col class="text-right">
          <ThePrimaryButton
            class="mt-4"
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
          {{ categorias(item) }}
          <ItemCategoriaForm
            :original-item="item"
            @change="getItems()"
          />
        </template>
      </TheFilterTable>
    </template>
  </TheLayoutWithHeader>
</template>
<script>
import TheLayoutWithHeader from '~/components/General/Layouts/TheLayoutWithHeader'
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
import ItemCategoriaForm from '~/components/Business/Dialogs/ItemCategoriaForm'
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
export default {
  components: {
    TheLayoutWithHeader,
    TheFilterTable,
    ItemCategoriaForm,
    TheTextInput,
    ThePrimaryButton
  },
  data: () => ({
    pageInfo: {
      page: 1,
      itemsPerPage: 20
    },
    totalList: 5,
    items: [],
    loading: false,
    filter: {},
    aBuscar: ''
  }),
  computed: {
    headers () {
      return [
        {
          text: this.$t('items.id'),
          align: 'center',
          sortable: false,
          value: 'id'
        },
        {
          text: this.$t('items.descripcion'),
          align: 'start',
          sortable: false,
          value: 'descripcion'
        },
        {
          text: this.$t('items.categorias.table'),
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
      this.$itemService.getItems(this.filter.nombre, this.pageInfo).then((result) => {
        if (result) {
          this.totalList = result.total
          this.items = result.data
        }
      }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    },
    categorias (item) {
      return item.categorias.map(categoria => categoria.descripcion).join()
    }
  }
}
</script>
