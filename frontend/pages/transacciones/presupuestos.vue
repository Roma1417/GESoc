<template>
  <TheDefaultLayout>
    <template>
      <Presupuestos />
    </template>
  </TheDefaultLayout>
</template>

<script>
import TheDefaultLayout from '~/components/General/Layouts/TheDefaultLayout'
import Presupuestos from '~/components/Presupuestos/Presupuestos'
export default {
  components: {
    TheDefaultLayout,
    Presupuestos
  }
}
</script>
