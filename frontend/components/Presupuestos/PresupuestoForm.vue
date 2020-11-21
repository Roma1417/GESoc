<template>
  <TheFormDialog
    v-model="showForm"
    :header-message="titleText"
    :loading="loading"
    v-bind="$attrs"
    paged
    :pages-length="2"
    @onConfirm="saveOrUpdate"
    @onCancel="closeForm"
    @onPageChanged="changePage"
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
      <v-card
        v-show="page === 1"
        outlined
        class="mb-2"
      >
        <v-card-title>
          {{ $t('presupuestos.titulo') }}
        </v-card-title>
        <v-row class="px-4">
          <TheResponsiveColumn>
            <TheTextInput
              v-model="presupuesto.codigoOperacion"
              :label="this.$t('presupuestos.codigo_operacion')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheAsyncAutocompleteInput
              v-model="presupuesto.egresoID"
              item-text="idEgreso"
              :get-items-function="$egresoService.getEgresosById"
              :label="$t('presupuestos.egreso_id')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheAsyncAutocompleteInput
              v-model="presupuesto.egresoID"
              item-text="idEgreso"
              :get-items-function="$egresoService.getEgresoById"
              :label="$t('presupuestos.egreso_id')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheAsyncAutocompleteInput
              v-model="presupuesto.egresoID"
              item-text="idEgreso"
              :get-items-function="$egresoService.getEgresoById"
              :label="$t('presupuestos.egreso_id')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheAsyncAutocompleteInput
              v-model="presupuesto.entidadRealizadora"
              item-text="nombre"
              :get-items-function="$entidadService.getEntidades"
              :label="$t('entidad.entidad')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
        </v-row>
      </v-card>
      <DocumentoComercialForm
        v-show="page === 1"
        :documento-comercial="presupuesto.documentoComercial"
      />
      <PresupuestoDetallesForm
        v-show="page === 2"
        :detalles="presupuesto.detalles"
      />
    </template>
  </TheFormDialog>
</template>
<script>
import { cloneDeep } from 'lodash'
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheCreateButton from '~/components/General/Buttons/TheCreateButton'
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import DocumentoComercialForm from '~/components/Business/Forms/DocumentoComercialForm'
import TheAsyncAutocompleteInput from '~/components/General/Inputs/TheAsyncAutocompleteInput'
import PresupuestoDetallesForm from '~/components/Presupuestos/PresupuestoDetallesForm'
import TheResponsiveColumn from '~/components/General/Columns/TheResponsiveColumn'
export default {
  components: {
    TheFormDialog,
    TheCreateButton,
    TheTextInput,
    DocumentoComercialForm,
    TheAsyncAutocompleteInput,
    PresupuestoDetallesForm,
    TheResponsiveColumn
  },
  props: {
    disabled: {
      type: Boolean,
      default: false
    },
    item: {
      type: Object,
      default: null
    }
  },
  data () {
    return {
      loading: false,
      showForm: false,
      page: 1,
      presupuesto: {
        documentoComercial: {},
        detalles: []
      }
    }
  },
  computed: {
    titleText () {
      return this.$t('presupuestos.crear')
    }
  },
  watch: {
    showForm (val) {
      if (val && this.item) {
        this.presupuesto = cloneDeep(this.item)
      }
    }
  },
  methods: {
    saveOrUpdate () {
      if (this.presupuesto.detalles.length === 0) {
        this.toastError(this.$t('presupuestos.error_sin_detalles'))
      } else {
        this.savePresupuesto()
      }
    },
    savePresupuesto () {
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
    changePage (page) {
      this.page = page
    },
    closeForm () {
      this.presupuesto = {
        documentoComercial: {},
        detalles: []
      }
      this.showForm = false
    }
  }
}
</script>
