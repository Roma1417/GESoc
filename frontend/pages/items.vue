<template>
  <TheDefaultLayout>
    <template>
      <Items />
    </template>
  </TheDefaultLayout>
</template>

<script>
import TheDefaultLayout from '~/components/General/Layouts/TheDefaultLayout'
import Items from '~/components/Items/Items'
export default {
  components: {
    TheDefaultLayout,
    Items
  }
}
</script>
