<template>
  <TheLayoutWithHeader title="entidades.title" :loading="loading" @search="getEntidades()">
    <template #filter>
      <v-row>
        <v-col cols="10" sm="8" md="4" lg="3">
          <TheTextInput
            v-model="filter.nombre"
            clearable
            :label="$t('entidades.search')"
            maxlength="32"
          />
        </v-col>
        <v-col class="text-right">
          <ThePrimaryButton
            class="mt-4"
            :inner-text="$t('search')"
            icon="mdi-magnify"
            @click="getEntidades()"
          />
        </v-col>
      </v-row>
    </template>
    <template #body>
      <TheFilterTable
        :page-info.sync="pageInfo"
        :items="entidades"
        :headers="headers"
        :total="totalList"
        @change="getEntidades()"
      />
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
    entidades: [],
    loading: false,
    filter: {}
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
          text: this.$t('entidades.nombre'),
          align: 'start',
          sortable: false,
          value: 'nombre'
        }
      ]
    }
  },
  methods: {
    getEntidades () {
      this.loading = true
      this.$entidadService.getEntidades(this.filter.nombre, this.pageInfo).then((result) => {
        if (result) {
          this.totalList = result.total
          this.entidades = result.data
        }
      }).finally(this.stopLoading)
    },
    stopLoading () {
      this.loading = false
    }
  }
}
</script>
