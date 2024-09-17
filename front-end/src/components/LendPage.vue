<template>
  <h2 class="boxTest">대출하기 페이지</h2>
  <router-link to="/">메인페이지 이동</router-link>
  <hr><br><br><br>

  <div>
<!--    1st (no) -->
<!--    <CheckUserPage v-if="!isUserVerified" @user-verified="userVerifiedHandler" />-->
<!--    <div v-else>-->
<!--      <h1>대출하기 페이지</h1>-->
<!--    </div>-->

<!--    2nd (no) -->
<!--    <CheckUserPage @user-verified="showLendPage" @error="handleError"/>-->
<!--    <div v-if="isUserVerified">-->
<!--      <h1>대출하기 페이지</h1>-->
<!--    </div>-->
<!--    <p v-else-if="error">{{ error }}</p>-->
<!--  </div>-->

<!--    3rd (yes) -->
    <!-- userID로 verified 되지 않은 경우 CheckUserPage 만 노출-->
    <CheckUserPage v-if="!isUserVerified" @user-verified="showLendPage" @error="handleError"/>

    <!-- userID로 verified 된 경우 LendPage 만 노출 -->
    <div v-else>
      <div class="lend-page">
        <h1>📚 대출하기 페이지 📚</h1>
<!--        <p>회원번호: {{ userID }}</p>-->
        <p>안녕하세요, {{ userName }}님~</p>

        <div class="search-section">
          <input type="text" v-model="searchQeury" placeholder="바코드를 읽거나 입력해 주세요." />
          <button @click="serachBook">도서 검색</button>
        </div>

        <div v-if="alertMessage" class="alert">{{ alertMessage }}</div>
      </div>

    </div>

    <!-- error 발생 시 error message 노출 -->
    <p v-if="error">{{ error }}</p>

<!--    4th (no) -->
<!--    <CheckUserPage @user-verified="showLendPage" @error="handleError" />-->
<!--    <div v-if="isUserVerified">-->
<!--      <h1>대출하 페이지</h1>-->
<!--      <p>회원번호: {{ userID }}</p>-->
<!--      <p>회원이름: {{ userName }}</p>-->
<!--    </div>-->
<!--    <p v-else-if="error">{{ error }}</p>-->

  </div>
</template>

<script>
import CheckUserPage from "@/components/CheckUserPage.vue";

export default {
  components: {CheckUserPage},
  data() {
    return {
      isUserVerified: false,
      error: '',
      userID: '',
      userName: ''
    };
  },
  methods: {
    // userVerifiedHandler() {
    //   this.isUserVerified = true;
    // }
    showLendPage( { userID, userName } ) {
      this.isUserVerified = true;
      this.error = '';
      this.userID = userID;
      this.userName = userName;
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
