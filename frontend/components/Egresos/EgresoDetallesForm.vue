<template>
  <v-card>
    <v-card-title>
      {{ $t('egresos.detalles') }}
    </v-card-title>
    <TheFilterTable
      class="px-4"
      :items="detalles"
      :headers="headers"
      :total="totalList"
    >
      <template #[`item.actions`]="{ }">
        VINCULAR BOTON
        <v-icon>
          mdi-link-variant
        </v-icon>
      </template>
    </TheFilterTable>
  </v-card>
</template>
<script>
import TheFilterTable from '~/components/General/Tables/TheFilterTable'
export default {
  components: {
    TheFilterTable
  },
  props: {
    detalles: {
      type: Array,
      required: true
    }
  },
  data () {
    return {
      totalList: 5
    }
  },
  computed: {
    headers () {
      return [
        {
          text: this.$t('items.item'),
          align: 'start',
          sortable: false,
          value: 'item'
        },
        {
          text: this.$t('detalles.cantidad'),
          align: 'center',
          sortable: false,
          value: 'cantidad'
        },
        {
          text: this.$t('detalles.precio'),
          align: 'end',
          sortable: false,
          value: 'precio'
        },
        {
          text: this.$t('detalles.total'),
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

  }
}
</script>
