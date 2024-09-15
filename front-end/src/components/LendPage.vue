<template>
  <h2 class="boxTest">대출하기 페이지</h2>
  <router-link to="/">메인페이지 이동</router-link>
  <hr><br><br><br>

  <div>
<!--    1st-->
<!--    <CheckUserPage v-if="!isUserVerified" @user-verified="userVerifiedHandler" />-->
<!--    <div v-else>-->
<!--      <h1>대출하기 페이지</h1>-->
<!--    </div>-->

<!--    2nd-->
<!--    <CheckUserPage @user-verified="showLendPage" @error="handleError"/>-->
<!--    <div v-if="isUserVerified">-->
<!--      <h1>대출하기 페이지</h1>-->
<!--    </div>-->
<!--    <p v-else-if="error">{{ error }}</p>-->
<!--  </div>-->

<!--    3rd-->
    <!-- userID로 verified 되지 않은 경우 CheckUserPage 만 노출-->
    <CheckUserPage v-if="!isUserVerified" @user-verified="showLendPage" @error="handleError"/>

    <!-- userID로 verified 된 경우 LendPage 만 노출 -->
    <div v-else>
      <h1>대출하기 페이지</h1>
    </div>

    <!-- error 발생 시 error message 노출 -->
    <p v-if="error">{{ error }}</p>
  </div>
</template>

<script>
import CheckUserPage from "@/components/CheckUserPage.vue";

export default {
  components: {CheckUserPage},
  data() {
    return {
      isUserVerified: false,
      error: ''
    };
  },
  methods: {
    // userVerifiedHandler() {
    //   this.isUserVerified = true;
    // }
    showLendPage() {
      this.isUserVerified = true;
      this.error = '';
    },
    handleError(message) {
      this.isUserVerified = false;
      this.error = message || '사용자 없음';
    }
  }
}
</script>

<style>
.boxTest {
  width: 200px;
  height: 50px;
  text-align: center;
  border: 1px solid #000;
}
</style>
