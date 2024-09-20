<template>
  <h2 class="boxTest">반납하기 페이지</h2>
  <router-link to="/">메인페이지 이동</router-link>
  <hr><br><br><br>

  <div>
    <!-- userID로 verified 되지 않은 경우 CheckUserPage 만 노출-->
    <CheckUserPage v-if="!isUserVerified" @user-verified="showReturnPage" @error="handleError" />

    <!-- userID로 verified 된 경우 LendPage 만 노출 -->
    <div v-else>
      <h1>🙈반납하기 페이지🙈</h1>
      <table v-if="loanList.length > 0">
        <thead>
          <tr>
            <th>책 제목</th>
            <th>ISBN 코드</th>
            <th>대출일</th>
            <th>반납 예정일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="loan in loanList" :key="loan.id">
            <td>{{ loan.title }}</td>
            <td>{{ loan.isbnCode }}</td>
            <td>{{ loan.loanDate }}</td>
            <td>{{ loan.returnDate }}</td>
          </tr>
        </tbody>
      </table>
      <p v-else>대출 목록이 없습니다.</p>
    </div>

    <!-- error 발생 시 error message 노출 -->
    <p v-if="error">{{ error }}</p>
  </div>
</template>

<script>
import CheckUserPage from "@/components/CheckUserPage.vue";
import axios from "axios";

export default {
  components: {CheckUserPage},
  data() {
    return {
      isUserVerified: false,
      loanList: [],   // 대출 목록
      error: ''
    };
  },
  methods: {
    showReturnPage() {
      this.isUserVerified = true;
      this.fetchLoanList();     // call api to fetch data
      this.error = '';
    },
    handleError(message) {
      this.isUserVerified = false;
      this.error = message || '사용자 없음';
    },
    fetchLoanList() {
      axios
          .get('http://localhost:8080/loan/getLists')
          .then((response) => {
            if(response.data && response.data.length > 0) {
              this.loanList = response.data;
            } else {
              this.loanList = [];
              alert("There is no data.");
            }
          })
          .catch((error) => {
            this.error = "데이터를 불러오는 중 오류가 발생했습니다.";
            console.error(error);
          });
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
