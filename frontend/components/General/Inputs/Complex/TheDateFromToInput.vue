<template>
  <v-row no-gutters>
    <v-col class="mr-8">
      <TheDateInput
        v-model="filter.dateFrom"
        :label="$t('fechas.datefrom')"
        :max-date="filter.dateTo"
      />
    </v-col>
    <v-col>
      <TheDateInput
        v-model="filter.dateTo"
        :label="$t('fechas.dateTo')"
        :min-date="filter.dateFrom"
      />
    </v-col>
  </v-row>
</template>
<script>
import TheDateInput from '~/components/General/Inputs/TheDateInput'
export default {
  components: {
    TheDateInput
  },
  props: {
    filter: {
      type: Object,
      required: true
    }
  }
}
</script>
