<template>
  <v-menu
    ref="menu"
    v-model="showMenu"
    transition="scale-transition"
    offset-y
    min-width="290px"
  >
    <template v-slot:activator="{ on }">
      <v-text-field
        v-model="modifiableDate"
        :label="label"
        :placeholder="placeholder"
        prepend-icon="mdi-calendar"
        readonly
        clearable
        @input="change"
        @click:clear="clear"
        v-on="on"
      />
    </template>
    <v-date-picker
      v-model="modifiableDate"
      :min="minDate || ''"
      :max="maxDate || ''"
      no-title
      scrollable
      @input="change"
      @change="change"
    />
  </v-menu>
</template>

<script>
export default {
  props: {
    maxDate: {
      type: String,
      default: null
    },
    minDate: {
      type: String,
      default: null
    },
    label: {
      type: String,
      default: null
    },
    placeholder: {
      type: String,
      default: null
    },
    value: {
      type: String,
      default: () => ''
    }
  },
  data: () => ({
    showMenu: false
  }),
  computed: {
    modifiableDate: {
      get () {
        return this.value
      },
      set (val) {
        this.change(val)
      }
    }
  },
  methods: {
    clear () {
      this.$emit('input', null)
    },
    change (val) {
      this.$emit('input', val)
    }
  }
}
</script>
