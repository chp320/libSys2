<template>
  <div class="container">
    <div class="row">
      <div class="register-form">
        <div class="logo-wrapper">
<!--          <img class="logo" src="@/assets/image1.jpeg" alt="로고">-->
          <div class="tagline"><h2>안녕하세요~<br>박시은 도서관입니다~</h2></div>
        </div>
<!--        <form @submit.prevent="submitForm"></form>-->
        <div class="button-container">
          <div class="join">
            <button class="btn btn-primary" type="button" @click="onClickMoveJoin">회원가입</button>
          </div>
          <div class="lend">
            <button class="btn btn-primary" type="button" @click="onClickMoveLend">대출하기</button>
          </div>
          <div class="return">
            <button class="btn btn-primary" type="button" @click="onClickMoveReturn">반납하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    onClickMoveJoin() {
      this.$router.push('/join')
    },
    onClickMoveLend() {
      this.$router.push('/lend')
    },
    onClickMoveReturn() {
      this.$router.push('/return')
    }
  }
}
</script>

<style scoped>
.container {
  max-width: 900px;
  //max-height: 500px;
  position: relative;
  top: 150px;
  left: 3%;
}
.container::before {
  //width: 100%;
  //height: 100%;
  width: 900px;
  height: 350px;
  position: absolute;
  content: "";
  background-image: url("../assets/image1.jpeg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  z-index: -1;
  opacity: 0.45;
  top: 0;
  left: 0;
}

.register-form {
  margin-top: 50px;
  max-width: 320px;
  margin-left: 0;
}

.logo-wrapper {
  margin-left: 0;
  padding-left: 0;
  margin-bottom: 40px;
}

.tagline {
  text-align: left;
  line-height: 80%;
  color: hotpink;
}

.logo {
  max-width: 300px;
  margin: 0 auto;
}

.button-container {
  display: flex;
  justify-content: space-between;
  gap: 10px;
}

.join {
  width: 200px;
  height: 50px;
  text-align: center;
  //border: 1px solid #000;
  border: 0;
}
.lend {
  width: 200px;
  height: 50px;
  text-align: center;
  //border: 1px solid #000;
  border: 0;
}
.return {
  width: 200px;
  height: 50px;
  text-align: center;
  //border: 1px solid #000;
  border: 0;
}
</style>
