<template>
  <v-btn height="120" class="px-0" @click="$emit('click')">
    <v-card dark>
      <v-row>
        <v-col v-if="actionIcon" class="d-flex align-content-center justify-center">
          <v-icon x-large>
            {{ actionIcon }}
          </v-icon>
        </v-col>
        <v-col>
          <v-card-title>
            {{ actionName }}
          </v-card-title>

          <v-card-subtitle v-if="actionDescription">
            {{ actionDescription }}
          </v-card-subtitle>
        </v-col>
      </v-row>
    </v-card>
  </v-btn>
</template>
<style scoped>
.v-card__title {
  word-break: inherit;
}
.v-card{
    cursor: pointer;
}
</style>
<script>
export default {
  props: {
    actionName: {
      type: String,
      default: 'The action'
    },
    actionDescription: {
      type: String,
      default: ''
    },
    actionIcon: {
      type: String,
      default: ''
    }
  }
}
</script>
