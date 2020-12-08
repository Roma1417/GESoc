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
        :icon="buttonIcon"
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
          <TheResponsiveColumn v-if="vincularConEgreso">
            <TheAsyncAutocompleteInput
              v-model="vinculacion.egreso"
              item-text="idEgreso"
              :get-items-function="$egresoService.getEgresosById"
              :label="$t('egresos.titulo')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn v-if="!vincularConEgreso">
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
              {{ mensajeIngresoDeId }}
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
    objetoAVincular: {
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
    },
    mensajeIngresoDeId: {
      type: String,
      required: true
    },
    buttonIcon: {
      type: String,
      default: 'mdi-link-variant'
    },
    vincularConEgreso: {
      type: Boolean,
      default: true
    },
    vinculacionFunction: {
      type: Function,
      required: true
    }
  },
  data () {
    return {
      loading: false,
      showForm: false,
      vinculacion: {}
    }
  },
  methods: {
    saveOrUpdate () {
      this.loading = true
      this.vinculacionFunction(this.vincularConEgreso, this.objetoAVincular, this.getIdVinculado())
        .then((response) => {
          if (response) {
            this.closeForm()
            this.toastSuccess(this.$t('saved-ok'))
            this.$emit('created', response)
          }
        }).finally(() => { this.loading = false })
    },
    closeForm () {
      this.vinculacion = {}
      this.showForm = false
      this.$refs.form.resetValidation()
    },
    getIdVinculado () {
      return this.vincularConEgreso ? this.vinculacion.egreso.idEgreso : this.vinculacion.ingreso.idIngreso
    }
  }
}
</script>
