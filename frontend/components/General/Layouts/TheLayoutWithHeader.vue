<template>
  <div>
    <v-row v-if="title != ''">
      <v-col>
        <h2 color="primary" class="pl-4">
          {{ $t(title) }}
        </h2>
      </v-col>
    </v-row>
    <template v-show="slotInUse('header')">
      <slot name="header" />
    </template>
    <v-card raised class="mx-auto" min-width="75vw">
      <v-card-subtitle v-show="slotInUse('filter')" class="py-2">
        <v-card outlined class="mt-2 px-2">
          <slot name="filter" />
        </v-card>
      </v-card-subtitle>
      <v-progress-linear v-if="loading" :indeterminate="loading" />
      <v-card-text v-show="slotInUse('body')" class="py-0">
        <v-row v-show="slotInUse('body')">
          <v-col>
            <v-card outlined>
              <slot name="body" />
            </v-card>
          </v-col>
        </v-row>
      </v-card-text>
      <v-row v-show="slotInUse('footer')">
        <v-col>
          <slot name="footer" />
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>
<script>
export default {
  props: {
    loading: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: ''
    }
  },
  methods: {
    slotInUse (slotName) {
      return !!this.$slots[slotName]
    }
  }
}
</script>
