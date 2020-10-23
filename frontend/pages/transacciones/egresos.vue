<template>
  <TheDefaultLayout>
    <template>
      <Egresos />
    </template>
  </TheDefaultLayout>
</template>

<script>
import TheDefaultLayout from '~/components/General/Layouts/TheDefaultLayout'
import Egresos from '~/components/Egresos/Egresos'
export default {
  components: {
    TheDefaultLayout,
    Egresos
  }
}
</script>
