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
      <TheButtonWithTooltip
        v-if="editMode"
        :title="titleText"
        :disabled="disabled"
        icon="mdi-pencil"
        v-on="on"
      />
      <TheCreateButton
        v-else
        class="mt-4"
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
          {{ $t('egresos.titulo') }}
        </v-card-title>
        <v-row class="px-2">
          <v-col md="4" sm="6">
            <TheTextInput
              :label="this.$t('egresos.codigo_operacion')"
              :rules="[$rl.required()]"
            />
          </v-col>
          <v-col md="4" sm="6">
            <TheAutocompleteInput
              :label="this.$t('egresos.entidad_realizadora')"
              :rules="[$rl.required()]"
            />
          </v-col>
          <v-col md="4" sm="6">
            <TheAutocompleteInput
              :label="this.$t('egresos.proveedor')"
              :rules="[$rl.required()]"
            />
          </v-col>
          <v-col md="4" sm="6">
            <TheAutocompleteInput
              :label="this.$t('egresos.medio_pago')"
              :rules="[$rl.required()]"
            />
          </v-col>
          <v-col md="4" sm="6">
            <TheTextInput
              :label="this.$t('egresos.presupuestos_minimos')"
              :rules="[$rl.required()]"
            />
          </v-col>
          <v-col md="4" sm="6">
            <TheDateInput
              :label="this.$t('egresos.fecha_operacion')"
              :rules="[$rl.required()]"
              @input="loadFilterWithDate"
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
import TheButtonWithTooltip from '~/components/General/Buttons/TheButtonWithTooltip'
import TheCreateButton from '~/components/General/Buttons/TheCreateButton'
import TheAutocompleteInput from '~/components/General/Inputs/TheAutocompleteInput'
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import TheDateInput from '~/components/General/Inputs/TheDateInput'
import DocumentoComercialForm from '~/components/Business/Forms/DocumentoComercialForm'
export default {
  components: {
    TheFormDialog,
    TheButtonWithTooltip,
    TheCreateButton,
    TheTextInput,
    TheDateInput,
    DocumentoComercialForm,
    TheAutocompleteInput
  },
  props: {
    item: {
      type: Object,
      default: () => ({})
    },
    createFunction: {
      type: Function,
      required: true
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
      filter: {}
    }
  },
  computed: {
    titleText () {
      return this.editMode ? this.$t('egresos.editar') : this.$t('egresos.crear')
    },
    editMode () {
      return this.isEditing(this.item)
    }
  },
  methods: {
    saveOrUpdate () {
      this.loading = true
      this.createFunction(this.item)
        .then((response) => {
          if (response) {
            this.closeForm()
            this.toastSuccess(this.$t('saved-ok'))
            this.$emit('saved-ok', response)
          }
        })
        .finally(() => { this.loading = false })
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
