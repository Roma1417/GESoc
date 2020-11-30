<template>
  <v-layout
    column
    align-center
  >
    <v-flex
      xs12
      sm8
      md6
    >
      <v-container class="pageContainer">
        <slot />
      </v-container>
    </v-flex>
  </v-layout>
</template>
