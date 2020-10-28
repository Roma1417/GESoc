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
      <ThePrimaryButton
        v-if="!editMode"
        :inner-text="titleText"
        :disabled="disabled"
        icon="mdi-plus"
        v-on="on"
      />
    </template>
    <template>
      <slot />
    </template>
  </TheFormDialog>
</template>
<script>
import TheFormDialog from '~/components/General/Dialogs/TheFormDialog'
import TheButtonWithTooltip from '~/components/General/Buttons/TheButtonWithTooltip'
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
export default {
  components: {
    TheFormDialog,
    TheButtonWithTooltip,
    ThePrimaryButton
  },
  props: {
    item: {
      type: Object,
      default: () => ({})
    },
    isEditing: {
      type: Function,
      default: item => item.id
    },
    createFunction: {
      type: Function,
      required: true
    },
    updateFunction: {
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
      showForm: false
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
      if (this.editMode) {
        this.updateFunction(this.item).then(this.handleResult).finally(() => { this.loading = false })
      } else {
        this.createFunction(this.item).then(this.handleResult).finally(() => { this.loading = false })
      }
    },
    handleResult (response) {
      if (response) {
        this.showForm = false
        this.toastSuccess(this.$t('saved-ok'))
        this.$emit('saved-ok', response)
      }
    },
    closeForm () {
      this.showForm = false
    }
  }
}
</script>
