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
          {{ $t('proyectos.titulo_creacion') }}
        </v-card-title>
        <v-row class="px-2">
          <v-col md="6" cols="12">
            <TheAsyncAutocompleteInput
              v-model="proyecto.entidadRealizadora"
              item-text="nombre"
              :get-items-function="$entidadService.getEntidades"
              :label="$t('proyectos.entidad')"
              :rules="[$rl.required()]"
            />
          </v-col>
          <v-col md="6" cols="12">
            <TheAsyncAutocompleteInput
              v-model="proyecto.director"
              item-text="nombre"
              :get-items-function="$userService.getUsuarios"
              :label="$t('proyectos.director')"
              :rules="[$rl.required()]"
            />
          </v-col>
          <v-col md="6" cols="12">
            <TheTextInput
              v-model="proyecto.presupuestosMinimos"
              :label="this.$t('proyectos.presupuestos_exigibles')"
              :rules="[$rl.required(),$rl.positive()]"
            />
          </v-col>
          <v-col md="6" cols="12">
            <TheTextInput
              v-model="proyecto.montoMaximoSinPresupuestos"
              :label="this.$t('proyectos.monto')"
              :rules="[$rl.required(),$rl.positive()]"
            />
          </v-col>
        </v-row>
      </v-card>
    </template>
  </TheFormDialog>
</template>
<script>
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheCreateButton from '~/components/General/Buttons/TheCreateButton'
import TheTextInput from '~/components/General/Inputs/TheTextInput'
import TheAsyncAutocompleteInput from '~/components/General/Inputs/TheAsyncAutocompleteInput'
export default {
  components: {
    TheFormDialog,
    TheCreateButton,
    TheTextInput,
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
      proyecto: {
        entidadRealizadora: {},
        director: {}
      }
    }
  },
  computed: {
    titleText () {
      return this.$t('proyectos.crear')
    }
  },
  methods: {
    saveOrUpdate () {
      this.loading = true
      this.$proyectoService.crearProyecto(this.crearProyecto)
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
      this.proyecto = {
        entidadRealizadora: {},
        director: {}
      }
      this.$refs.form.resetValidation()
      this.showForm = false
    }
  }
}
</script>
