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
      <TheButtonWithToolTip
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
          <TheResponsiveColumn v-if="egresoAVincular">
            <TheTextInput
              v-model="egreso.codigoOperacion"
              :label="$t('egresos.codigo_operacion')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
          <TheResponsiveColumn v-if="ingresoAVincular">
            <TheAsyncAutocompleteInput
              v-model="egreso.entidadRealizadora"
              item-text="nombre"
              :get-items-function="$entidadService.getEntidades"
              :label="$t('entidad.entidad')"
              :rules="[$rl.required()]"
            />
          </TheResponsiveColumn>
        </v-row>
      </v-card>
    </template>
  </TheFormDialog>
</template>
<script>
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import TheAsyncAutocompleteInput from '~/components/General/Inputs/TheAsyncAutocompleteInput'
import TheResponsiveColumn from '~/components/General/Columns/TheResponsiveColumn'
import TheButtonWithToolTip from '~/components/General/Buttons/TheButtonWithTooltip'
export default {
  components: {
    TheFormDialog,
    TheTextInput,
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
      vinculacion: { }
    }
  },
  methods: {
    saveOrUpdate () {
      if (!this.vinculacion.egresoId && !this.vinculacion.ingresoId) {
        this.toastError(this.$t('vincular.error_sin_ids'))
      } else {
        this.realizarVinculacion()
      }
    },
    realizarVinculacion () {
      this.loading = true
      this.$egresoService.vincularEgresoIngreso(this.vinculacion)
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
      this.vinculacion = { }
      this.showForm = false
    }
  }
}
</script>
