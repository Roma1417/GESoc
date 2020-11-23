<template>
  <TheDefaultLayout>
    <template>
      <Mensajes />
    </template>
  </TheDefaultLayout>
</template>

<script>
import TheDefaultLayout from '~/components/General/Layouts/TheDefaultLayout'
import Mensajes from '~/components/Mensajes/Mensajes'
export default {
  components: {
    TheDefaultLayout,
    Mensajes
  }
}
</script>
