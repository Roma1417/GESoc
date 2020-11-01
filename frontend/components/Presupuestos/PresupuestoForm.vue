<template>
  <TheFormDialog
    ref="form"
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
          {{ $t('presupuestos.titulo') }}
        </v-card-title>
        <v-row class="px-2">
          <v-col md="4" cols="12">
            <TheTextInput
              v-model="presupuesto.codigoOperacion"
              :label="this.$t('presupuestos.codigo_operacion')"
              :rules="[$rl.required()]"
              maxlength="10"
            />
          </v-col>
          <v-col md="4" cols="12">
            <TheTextInput
              v-model="presupuesto.egresoID"
              :label="this.$t('presupuestos.egreso_id')"
              :rules="[$rl.required()]"
              maxlength="10"
            />
          </v-col>
          <v-col md="4" cols="12">
            <TheAsyncAutocompleteInput
              v-model="presupuesto.entidadRealizadora"
              item-text="nombre"
              :get-items-function="$entidadService.getEntidades"
              :label="$t('entidad.entidad')"
              :rules="[$rl.required()]"
            />
          </v-col>
        </v-row>
      </v-card>
      <DocumentoComercialForm
        :documento-comercial="presupuesto.documentoComercial"
        class="px-2"
      />
    </template>
  </TheFormDialog>
</template>
<script>
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheCreateButton from '~/components/General/Buttons/TheCreateButton'
import TheAsyncAutocompleteInput from '~/components/General/Inputs/TheAsyncAutocompleteInput'
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import DocumentoComercialForm from '~/components/Business/Forms/DocumentoComercialForm'
export default {
  components: {
    TheFormDialog,
    TheCreateButton,
    TheTextInput,
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
      presupuesto: {
        documentoComercial: {},
        entidadRealizadora: {}
      }
    }
  },
  computed: {
    titleText () {
      return this.$t('presupuestos.crear')
    }
  },
  methods: {
    saveOrUpdate () {
      this.loading = true
      this.$presupuestoService.crearPresupuesto(this.presupuesto)
        .then((response) => {
          if (response) {
            this.closeForm()
            this.toastSuccess(this.$t('saved-ok'))
            this.$emit('created', response)
          }
        })
        .finally(() => { this.loading = false })
    },
    closeForm () {
      this.presupuesto = {
        documentoComercial: {},
        entidadRealizadora: {}
      }
      this.$refs.form.resetValidation()
      this.showForm = false
    }
  }
}
</script>
