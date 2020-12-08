<template>
  <TheDefaultLayout>
    <template>
      <Proyectos />
    </template>
  </TheDefaultLayout>
</template>

<script>
import TheDefaultLayout from '~/components/General/Layouts/TheDefaultLayout'
import Proyectos from '~/components/Proyectos/Proyectos'
export default {
  components: {
    TheDefaultLayout,
    Proyectos
  }
}
</script>
