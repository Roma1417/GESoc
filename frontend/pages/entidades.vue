<template>
  <TheDefaultLayout>
    <template>
      <Entidades />
    </template>
  </TheDefaultLayout>
</template>

<script>
import TheDefaultLayout from '~/components/General/Layouts/TheDefaultLayout'
import Entidades from '~/components/Entidades/Entidades'
export default {
  components: {
    TheDefaultLayout,
    Entidades
  }
}
</script>
