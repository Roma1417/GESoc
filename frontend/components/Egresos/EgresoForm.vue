<template>
  <TheFormDialog
    v-model="showForm"
    :header-message="titleText"
    :loading="loading"
    v-bind="$attrs"
    paged
    :pages-length="2"
    :page.sync="page"
    @onConfirm="saveOrUpdate"
    @onCancel="closeForm"
    v-on="$listeners"
  >
    <template #activator="{on}">
      <TheCreateButton
        class="mt-4"
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
          {{ $t('egresos.titulo') }}
        </v-card-title>
        <v-row class="px-4">
          <TheResponsiveColumn>
            <TheTextInput
              v-model="egreso.codigoOperacion"
              :label="$t('egresos.codigo_operacion')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheAsyncAutocompleteInput
              v-model="egreso.entidadRealizadora"
              item-text="nombre"
              :get-items-function="$entidadService.getEntidades"
              :label="$t('entidad.entidad')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheAsyncAutocompleteInput
              v-model="egreso.proveedor"
              item-text="nombreRazonSocial"
              :get-items-function="$proveedorService.getProveedores"
              :label="$t('egresos.proveedor')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheAsyncAutocompleteInput
              v-model="egreso.medioPago"
              item-text="instrumentoPago"
              :get-items-function="$medioPagoService.getMediosDePago"
              :label="$t('medio_pago.medio_pago')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheTextInput
              v-model="egreso.cantidadPresupuestosMinimos"
              :label="$t('egresos.presupuestos_minimos')"
              :rules="[$rl.required(),$rl.positive()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn>
            <TheDateInput
              v-model="egreso.fechaOperacion"
              :label="$t('egresos.fecha_operacion')"
              :clearable="false"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
        </v-row>
      </v-card>
      <DocumentoComercialForm
        v-show="page === 1"
        :documento-comercial="egreso.documentoComercial"
      />
      <EgresoDetallesForm
        v-show="page === 2"
        :detalles="egreso.detalles"
      />
    </template>
  </TheFormDialog>
</template>
<script>
import { cloneDeep } from 'lodash'
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheCreateButton from '~/components/General/Buttons/TheCreateButton'
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import TheDateInput from '~/components/General/Inputs/TheDateInput'
import DocumentoComercialForm from '~/components/Business/Forms/DocumentoComercialForm'
import TheAsyncAutocompleteInput from '~/components/General/Inputs/TheAsyncAutocompleteInput'
import EgresoDetallesForm from '~/components/Egresos/EgresoDetallesForm'
import TheResponsiveColumn from '~/components/General/Columns/TheResponsiveColumn'
export default {
  components: {
    TheFormDialog,
    TheCreateButton,
    TheTextInput,
    TheDateInput,
    DocumentoComercialForm,
    TheAsyncAutocompleteInput,
    EgresoDetallesForm,
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
      egreso: {
        documentoComercial: {},
        detalles: []
      }
    }
  },
  computed: {
    titleText () {
      return this.$t('egresos.crear')
    }
  },
  watch: {
    showForm (val) {
      if (val && this.item) {
        this.egreso = cloneDeep(this.item)
      }
    }
  },
  methods: {
    saveOrUpdate () {
      if (this.egreso.detalles.length === 0) {
        this.toastError(this.$t('egresos.error_sin_detalles'))
      } else {
        this.saveEgreso()
      }
    },
    saveEgreso () {
      this.loading = true
      this.$egresoService.crearEgreso(this.egreso)
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
      this.egreso = {
        documentoComercial: {},
        detalles: []
      }
      this.showForm = false
    }
  }
}
</script>
