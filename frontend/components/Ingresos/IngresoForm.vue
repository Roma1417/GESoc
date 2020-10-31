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
            <TheAsyncAutocompleteInput
              v-model="egreso.entidad"
              item-text="nombre"
              :get-items-function="$entidadService.getEntidades"
              :label="$t('entidad.entidad')"
              :rules="[$rl.required()]"
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
      <DocumentoComercialForm class="px-2" />
    </template>
  </TheFormDialog>
</template>
<script>
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheCreateButton from '~/components/General/Buttons/TheCreateButton'
import TheAsyncAutocompleteInput from '~/components/General/Inputs/TheAsyncAutocompleteInput'
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
    TheAsyncAutocompleteInput
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
      ingreso: {}
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
    }
  }
}
</script>
