<template>
  <div class="wrapper">
    <div style="margin:150px auto;background-color: #ffffff;width: 600px;height: 400px;padding:20px;border-radius: 10px">
      <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  name: "ForgetPass"
}
</script>

<style scoped>
.wrapper{
  height: 100vh;
  background-image: linear-gradient(to bottom right, #228B22,#FFFF00);
  overflow: hidden;
}
</style>