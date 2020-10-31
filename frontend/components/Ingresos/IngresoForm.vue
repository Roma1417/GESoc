<template>
  <TheFormDialog
    v-model="showForm"
    :header-message="titleText"
    :loading="loading"
    v-bind="$attrs"
    @onConfirm="saveOrUpdate"
    @onCancel="closeForm"
    v-on="$listeners"
  >
    <template #activator="{on}">
      <TheCreateButton
        class="my-4"
        :inner-text="titleText"
        :disabled="disabled"
        icon="mdi-plus"
        color="accent"
        v-on="on"
      />
    </template>
    <template>
      <v-card outlined class="mb-2">
        <v-card-title>
          {{ $t('ingresos.titulo') }}
        </v-card-title>
        <v-row class="px-2">
          <v-col md="4" cols="12">
            <TheTextInput
              :label="this.$t('ingresos.codigo_operacion')"
              :rules="[$rl.required()]"
              :v-model="ingreso.codigoOperacion"
            />
          </v-col>
          <v-col md="4" cols="12">
            <TheEntidadAutocomplete
              :label="this.$t('ingresos.entidad_realizadora')"
              :rules="[$rl.required()]"
              :v-model="ingreso.entidadRealizadora"
            />
          </v-col>
          <v-col md="4" cols="12">
            <TheTextInput
              :label="this.$t('ingresos.total')"
              :rules="[$rl.required(),$rl.positive()]"
              :v-model="ingreso.total"
            />
          </v-col>
          <v-col cols="12">
            <TheTextAreaInput
              :label="this.$t('ingresos.descripcion')"
              :rules="[$rl.required()]"
              :v-model="ingreso.descripcion"
            />
          </v-col>
        </v-row>
      </v-card>
      <v-card>
        <v-card-title>
          {{ $t('documento_comercial.titulo') }}
        </v-card-title>
        <DocumentoComercialForm class="px-2" />
      </v-card>
    </template>
  </TheFormDialog>
</template>
<script>
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheCreateButton from '~/components/General/Buttons/TheCreateButton'
import TheEntidadAutocomplete from '~/components/Business/Autocomplete/TheEntidadAutocomplete'
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import TheTextAreaInput from '~/components/General/Inputs/TheTextAreaInput'
import DocumentoComercialForm from '~/components/Business/Forms/DocumentoComercialForm'
export default {
  components: {
    TheFormDialog,
    TheCreateButton,
    TheTextInput,
    TheTextAreaInput,
    DocumentoComercialForm,
    TheEntidadAutocomplete
  },
  props: {
    item: {
      type: Object,
      default: () => ({})
    },
    disabled: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      loading: false,
      showForm: false,
      ingreso: {},
      filter: {}
    }
  },
  computed: {
    titleText () {
      return this.$t('ingresos.crear')
    }
  },
  methods: {
    saveOrUpdate () {
      this.loading = true
      this.$ingresoService.crearIngreso(this.ingreso)
    },
    closeForm () {
      this.showForm = false
    },
    loadFilterWithDate (val) {
      this.filter.operationDate = val
    }
  }
}
</script>
