<template>
  <TheDefaultLayout>
    <template>
      <Ingresos />
    </template>
  </TheDefaultLayout>
</template>

<script>
import TheDefaultLayout from '~/components/General/Layouts/TheDefaultLayout'
import Ingresos from '~/components/Ingresos/Ingresos'
export default {
  components: {
    TheDefaultLayout,
    Ingresos
  }
}
</script>
