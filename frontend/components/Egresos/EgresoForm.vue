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
        class="mt-4"
        :inner-text="titleText"
        :disabled="disabled"
        icon="mdi-plus"
        color="accent"
        v-on="on"
      />
    </template>
    <template>
      <template v-if="page === 1">
        <v-card
          outlined
          class="mb-2"
        >
          <v-card-title>
            {{ $t('egresos.titulo') }}
          </v-card-title>
          <v-row class="px-2">
            <v-col md="4" sm="6" cols="12" class="py-0">
              <TheTextInput
                v-model="egreso.codigoOperacion"
                :label="this.$t('egresos.codigo_operacion')"
                :rules="[$rl.required()]"
              />
            </v-col>
            <v-col md="4" sm="6" cols="12" class="py-0">
              <TheAsyncAutocompleteInput
                v-model="egreso.entidad"
                item-text="nombre"
                :get-items-function="$entidadService.getEntidades"
                :label="$t('entidad.entidad')"
                :rules="[$rl.required()]"
              />
            </v-col>
            <v-col md="4" sm="6" cols="12" class="py-0">
              <TheAsyncAutocompleteInput
                v-model="egreso.proveedor"
                item-text="nombreRazonSocial"
                :get-items-function="$proveedorService.getProveedores"
                :label="$t('egresos.proveedor')"
                :rules="[$rl.required()]"
              />
            </v-col>
            <v-col md="4" sm="6" cols="12" class="py-0">
              <TheAsyncAutocompleteInput
                v-model="egreso.medioPago"
                item-text="instrumentoPago"
                :get-items-function="$medioPagoService.getMediosDePago"
                :label="$t('medio_pago.medio_pago')"
                :rules="[$rl.required()]"
              />
            </v-col>
            <v-col md="4" sm="6" cols="12" class="py-0">
              <TheTextInput
                v-model="egreso.cantidadPresupuestosMinimos"
                :label="this.$t('egresos.presupuestos_minimos')"
                :rules="[$rl.required()]"
              />
            </v-col>
            <v-col md="4" sm="6" cols="12" class="py-0">
              <TheDateInput
                v-model="egreso.fechaOperacion"
                :label="this.$t('egresos.fecha_operacion')"
                :rules="[$rl.required()]"
              />
            </v-col>
          </v-row>
        </v-card>
        <DocumentoComercialForm
          :documento-comercial="egreso.documentoComercial"
          class="px-2"
        />
      </template>
      <EgresoDetallesForm
        v-if="page === 2"
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
export default {
  components: {
    TheFormDialog,
    TheCreateButton,
    TheTextInput,
    TheDateInput,
    DocumentoComercialForm,
    TheAsyncAutocompleteInput,
    EgresoDetallesForm
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
      this.loading = true
      this.$egresoService.crearEgreso(this.egreso)
        .then((response) => {
          if (response) {
            this.closeForm()
            this.toastSuccess(this.$t('saved-ok'))
            this.$emit('saved-ok', response)
          }
        })
        .finally(() => { this.loading = false })
    },
    changePage (page) {
      this.page = page
    },
    closeForm () {
      this.showForm = false
    }
  }
}
</script>
