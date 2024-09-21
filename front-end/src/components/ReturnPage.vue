<template>
  <h2 class="boxTest">반납하기 페이지</h2>
  <router-link to="/">메인페이지 이동</router-link>
  <hr><br><br><br>

  <!-- 2024.09.21 화면 접근 프로세스 변경 -->
<!--  <div>-->
<!--    &lt;!&ndash; userID로 verified 되지 않은 경우 CheckUserPage 만 노출&ndash;&gt;-->
<!--    <CheckUserPage v-if="!isUserVerified" @user-verified="showReturnPage" @error="handleError" />-->

<!--    &lt;!&ndash; userID로 verified 된 경우 LendPage 만 노출 &ndash;&gt;-->
<!--    <div v-else>-->
<!--      <h1>🙈반납하기 페이지🙈</h1>-->
<!--      <table v-if="loanList.length > 0">-->
<!--        <thead>-->
<!--          <tr>-->
<!--            <th>책 제목</th>-->
<!--            <th>ISBN 코드</th>-->
<!--            <th>대출일</th>-->
<!--            <th>반납 예정일</th>-->
<!--          </tr>-->
<!--        </thead>-->
<!--        <tbody>-->
<!--          <tr v-for="loan in loanList" :key="loan.id">-->
<!--            <td>{{ loan.title }}</td>-->
<!--            <td>{{ loan.isbnCode }}</td>-->
<!--            <td>{{ loan.loanDate }}</td>-->
<!--            <td>{{ loan.returnDate }}</td>-->
<!--          </tr>-->
<!--        </tbody>-->
<!--      </table>-->
<!--      <p v-else>대출 목록이 없습니다.</p>-->
<!--    </div>-->

<!--    &lt;!&ndash; error 발생 시 error message 노출 &ndash;&gt;-->
<!--    <p v-if="error">{{ error }}</p>-->
<!--  </div>-->

<div class="return-container">
  <div>
    <h1> 🎃 반납할 도서의 바코드를 읽거나<br>
      ISBN 코드를 입력해주세요~~ 🎃 </h1>
  </div>
  <input type="text"
         id="isbnCode"
         @keydown.enter="doReturnBook"
         placeholder="바코드를 읽거나 ISBN 코드를 입력해주세요."/>
</div>
  <div class="button-gohome">
    <button class="btn btn-primary" type="button" @click="goHome">첫화면 이동</button>
  </div>
</template>

<script>
// import CheckUserPage from "@/components/CheckUserPage.vue";
import axios from "axios";

export default {
  // components: {CheckUserPage},
  data() {
    return {
      returnDate: '',
      isUserVerified: false,
      loanList: [],   // 대출 목록
      error: ''
    };
  },
  methods: {
    async doReturnBook() {
      /* 반납할 도서의 ISBN 코드로 대충 테이블에서 도서 목록 조회 */
      console.log("===> doReturnBook() call!!!");
      this.setReturnDate();

      const isbnCode = document.getElementById('isbnCode').value.trim();
      console.log("isbnCode: ", isbnCode);
      if(!isbnCode) {
        alert("Please enter a valid ISBN code.");
        return;
      }

      await axios
          .post("/loan/doReturn", { isbnCode, bookStatus: "Y" })
          .then((response) => {
            if(response.status === 200) {
              alert("성공적으로 반납되었습니다.");
              document.getElementById('isbnCode').value = '';   // isbn 입력창 초기화
            } else {
              alert("반납 중 오류가 발생했습니다. 다시 시도해 주세요");
            }
          })
          .catch((error) => {
            console.error("Error returning book: ", error);
            alert("반납 중 오류가 발생했습니다.");
          })
    },
    setReturnDate() {
      console.log("===> setReturnDate() cal!!!!");

      const formatDateToLocalDateTime = (date) => {
        return date.toISOString().split('.')[0];
      };

      this.returnDate = formatDateToLocalDateTime(new Date());
    },
    goHome() {
      this.$router.push('/')
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

table {
  width: 100%;
  border-collapse: collapse;
}

table, th, td {
  border: 1px solid black;
}

th, td {
  padding: 8px;
  text-align: left;
}
</style>
