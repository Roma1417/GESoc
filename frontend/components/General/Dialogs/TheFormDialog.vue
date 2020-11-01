<template>
  <TheDialog
    v-bind="$attrs"
    max-width="800"
    :loading="loading"
    v-on="$listeners"
  >
    <template #activator="{on}">
      <slot name="activator" :on="on" />
    </template>
    <v-card-title v-if="headerMessage" class="headline">
      {{ headerMessage }}
    </v-card-title>
    <v-card-text>
      <v-form
        ref="form"
        v-model="valid"
        @submit.prevent="confirm"
      >
        <slot />
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-spacer />
      <TheSecondaryButton
        v-if="!loading"
        :inner-text="$t(cancelButton)"
        @click="cancel"
      />
      <ThePrimaryButton
        :inner-text="$t(confirmButton)"
        :loading="loading"
        @click="confirm"
      />
    </v-card-actions>
  </TheDialog>
</template>
<script>
import TheDialog from '~/components/General/Dialogs/TheDialog'
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
import TheSecondaryButton from '~/components/General/Buttons/TheSecondaryButton'
export default {
  components: {
    TheDialog,
    ThePrimaryButton,
    TheSecondaryButton
  },
  props: {
    headerMessage: {
      type: String,
      default: ''
    },
    bodyMessage: {
      type: String,
      default: 'bodyMessage'
    },
    confirmButton: {
      type: String,
      default: 'save'
    },
    cancelButton: {
      type: String,
      default: 'cancel'
    },
    loading: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      valid: true
    }
  },
  methods: {
    confirm () {
      this.$refs.form.validate()
      if (this.valid) {
        this.$emit('onConfirm')
      } else {
        this.toastError('Revise los campos para proceder')
      }
    },
    cancel () {
      this.$emit('onCancel')
      this.resetValidation()
    },
    resetValidation () {
      this.$refs.form.resetValidation()
    }
  }
}
</script>
