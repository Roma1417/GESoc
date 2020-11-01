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
          {{ $t('ingresos.titulo') }}
        </v-card-title>
        <v-row class="px-2">
          <v-col md="4" cols="12">
            <TheTextInput
              v-model="ingreso.codigoOperacion"
              :label="this.$t('ingresos.codigo_operacion')"
              :rules="[$rl.required()]"
              maxlength="10"
            />
          </v-col>
          <v-col md="4" cols="12">
            <TheAsyncAutocompleteInput
              v-model="ingreso.entidadRealizadora"
              item-text="nombre"
              :get-items-function="$entidadService.getEntidades"
              :label="$t('entidad.entidad')"
              :rules="[$rl.required()]"
            />
          </v-col>
          <v-col md="4" cols="12">
            <TheTextInput
              v-model="ingreso.total"
              :label="this.$t('ingresos.total')"
              :rules="[$rl.required(),$rl.positive()]"
              type="number"
              maxlength="10"
            />
          </v-col>
          <v-col cols="12">
            <TheTextAreaInput
              v-model="ingreso.descripcion"
              :label="this.$t('ingresos.descripcion')"
              :rules="[$rl.required()]"
              maxlength="100"
            />
          </v-col>
        </v-row>
      </v-card>
      <DocumentoComercialForm
        :documento-comercial="ingreso.documentoComercial"
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
import TheTextAreaInput from '~/components/General/Inputs/TheTextareaInput'
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
      ingreso: {
        documentoComercial: {},
        entidadRealizadora: {}
      }
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
      this.ingreso = {
        documentoComercial: {},
        entidadRealizadora: {}
      }
      this.$refs.form.resetValidation()
      this.showForm = false
    }
  }
}
</script>
