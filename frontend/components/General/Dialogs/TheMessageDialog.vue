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
      {{ bodyMessage }}
      <slot />
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-spacer />
      <ThePrimaryButton
        :inner-text="$t(acceptButton)"
        :loading="loading"
        @click="accept"
      />
    </v-card-actions>
  </TheDialog>
</template>
<script>
import TheDialog from '~/components/General/Dialogs/TheDialog'
import ThePrimaryButton from '~/components/General/Buttons/ThePrimaryButton'
export default {
  components: {
    TheDialog,
    ThePrimaryButton
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
    acceptButton: {
      type: String,
      default: 'accept'
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
    accept () {
      this.$emit('onAccept')
    },
    resetValidation () {
      this.$refs.form.resetValidation()
    }
  }
}
</script>
