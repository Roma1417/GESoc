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
      <TheButtonWithToolTip
        :disabled="disabledForEgreso"
        icon="mdi-link-variant"
        :title="vincularTooltipText"
        v-on="on"
      />
    </template>
    <template>
      <v-card
        outlined
        class="mb-2"
      >
        <v-card-title>
          {{ $t('vincular.solicitud') }}
        </v-card-title>
        <v-row class="px-4">
          <TheResponsiveColumn v-if="ingresoAVincular">
            <TheAsyncAutocompleteInput
              v-model="vinculacion.egreso"
              item-text="idEgreso"
              :get-items-function="$egresoService.getEgresosById"
              :label="$t('egresos.titulo')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn v-if="egresoAVincular">
            <TheAsyncAutocompleteInput
              v-model="vinculacion.ingreso"
              item-text="idIngreso"
              :get-items-function="$ingresoService.getIngresosById"
              :label="$t('ingresos.titulo')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <v-col>
            <v-alert
              color="primary"
              outlined
              dense
              type="info"
            >
              {{ mensajeIngresoDeID() }}
            </v-alert>
          </v-col>
        </v-row>
      </v-card>
    </template>
  </TheFormDialog>
</template>
<script>
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheAsyncAutocompleteInput from '~/components/General/Inputs/TheAsyncAutocompleteInput'
import TheResponsiveColumn from '~/components/General/Columns/TheResponsiveColumn'
import TheButtonWithToolTip from '~/components/General/Buttons/TheButtonWithTooltip'
export default {
  components: {
    TheFormDialog,
    TheAsyncAutocompleteInput,
    TheResponsiveColumn,
    TheButtonWithToolTip
  },
  props: {
    egresoAVincular: {
      type: Object,
      default: null
    },
    ingresoAVincular: {
      type: Object,
      default: null
    },
    titleText: {
      type: String,
      required: true
    },
    vincularTooltipText: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      loading: false,
      showForm: false,
      disabledForEgreso: false,
      vinculacion: {}
    }
  },
  methods: {
    mensajeIngresoDeID () {
      return this.egresoAVincular ? this.$t('vincular.info_egreso_id')
        : this.$t('vincular.info_ingreso_id')
    },
    saveOrUpdate () {
      this.loading = true
      const params = this.getIdsVinculacion()
      this.$egresoService.vincularEgresoIngreso(params)
        .then((response) => {
          if (response) {
            this.closeForm()
            this.toastSuccess(this.$t('saved-ok'))
            this.$emit('created', response)
            this.updateIdIngresoAsociado()
          }
        }).finally(() => { this.loading = false })
    },
    closeForm () {
      this.vinculacion = {}
      this.showForm = false
      this.$refs.form.resetValidation()
    },
    getIdsVinculacion () {
      const ids = { }
      if (this.egresoAVincular) {
        ids.egresoId = this.egresoAVincular.idEgreso
        ids.ingresoId = this.vinculacion.ingreso.idIngreso
      } else {
        ids.egresoId = this.vinculacion.egreso.idEgreso
        ids.ingresoId = this.ingresoAVincular.idIngreso
      }
      return ids
    },
    updateIdIngresoAsociado () {
      if (this.egresoAVincular) {
        this.egresoAVincular.idIngresoAsociado = this.vinculacion.idIngreso
        this.disabledForEgreso = true
      }
    }
  }
}
</script>
