<template>
  <div>
    <h1>main Page</h1>
  </div>
  <button type="button" @click="onClickMoveTestPage">페이지 이동</button>
</template>

<script>
export default {
  methods: {
    onClickMoveTestPage() {
      this.$router.push('/test')
    }
  }
}
</script>

<style>

</style>
