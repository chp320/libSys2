<template>
<!--  <h2 class="boxTest">대출하기 페이지</h2>-->
<!--  <router-link to="/">메인페이지 이동</router-link>-->
<!--  <hr><br><br><br>-->

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

        <div class="search-bar">
          <!-- 검색창에서 입력 후 엔터키 입력하거나 '도서 검색' 버튼 클릭 시 검색 수행 -->
          <input type="text"
                 v-model="bookKeyword"
                 placeholder="바코드를 읽거나 입력해 주세요."
                 @keydown.enter="searchBook"/>
          <button @click="searchBook">도서 검색</button>
        </div>

        <!-- 검색 결과 없는 경우 alertMessage 출력 -->
        <div v-if="alertMessage" class="alert">
          {{ alertMessage }}
        </div>

        <!-- 검색 된 책 목록에 추가 -->
        <div class="grid" v-if="bookList.length > 0">
          <h3>🦄 추가된 도서 목록 🦄</h3>
          <div class="grid-header">
            <span>제목</span>
            <span>ISBN코드</span>
            <span>저자</span>
            <span>출판사</span>
          </div>
          <div class="grid-item"
               v-for="(book, index) in bookList" :key="index">
            <span> {{ book.titleInfo }}</span>
            <span> {{ book.isbnCode }}</span>
            <span> {{ book.authorInfo }}</span>
            <span> {{ book.pubInfo }}</span>
          </div>
        </div>

        <!-- 저장 -->
        <button v-if="bookList.length > 0" @click="saveBooks">저장</button>

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
import axios from 'axios';
import CheckUserPage from "@/components/CheckUserPage.vue";

export default {
  components: {CheckUserPage},
  // mounted() {
  //   // automatically focus the input
  //   this.$refs.bookKeywordInput.focus();
  // },
  data() {
    return {
      loanData: {
        userID: '',
        userName: '',
        isbnCode: '',
        title: '',
        loanDate: '',
        returnDate: ''
      },
      isUserVerified: false,
      error: '',
      userID: '',
      userName: '',
      // grid 추가 위한 검색어 및 검색된 데이터
      bookKeyword: '',
      title: '',
      bookList: [],
      errorMessage: ''
    };
  },
  methods: {
    // userVerifiedHandler() {
    //   this.isUserVerified = true;
    // }
    setLoanDates() {
      // 1st
/*
      const currentDate = new Date();
      this.loanData.loanDate = currentDate.toISOString();
      console.log("loanDate: ", this.loanData.loanDate);

      const returnDate = new Date();
      returnDate.setDate(currentDate.getDate() + 14);   // add 14 days
      this.loanData.returnDate = returnDate.toISOString();
      console.log("returnDate: ", this.loanData.returnDate);
*/
      // 2nd
      const formatDateToLocalDateTime = (date) => {
        return date.toISOString().split('.')[0];
      };

      this.loanData.loanDate = formatDateToLocalDateTime(new Date());
      const returnDate = new Date();
      returnDate.setDate(new Date().getDate()+14);
      this.loanData.returnDate = formatDateToLocalDateTime(returnDate);
    },
    showLendPage( { userID, userName } ) {
      this.isUserVerified = true;
      this.error = '';
      this.userID = userID;
      this.userName = userName;
    },
    handleError(message) {
      this.isUserVerified = false;
      this.error = message || '사용자 없음';
    },
    isBookInList(isbnCode) {
      console.log("input: ", isbnCode);
      return this.bookList.some(book => book.isbnCode === isbnCode);
    },
    async searchBook() {
      console.log('입력된 코드: ', this.bookKeyword);
      axios
          .get('https://www.nl.go.kr/NL/search/openApi/search.do', {
            params: {
              key: '025d72e99c97d98d7b028fd26cf768ec4af09fefe6d5eec3d97a91a5bee86a7d',
              apiType: 'json',
              detailSearch: 'true',
              isbnOp: 'isbn',
              isbnCode: this.bookKeyword,
              systemType: '오프라인자료',
              govYn: 'N',
              category: '도서',
              pageNum: 1,
              pageSize: 1,
              sort: 'ipub_year',
              order: 'desc'
            }
          })
          .then((result) => {
            const response = result.data;

            // 검색 도서 없는 경우 처리
            if(response.total === 0 || !response.result || response.result.length === 0) {
              this.errorMessage = '데이터가 없습니다.'
            } else {
              this.errorMessage = '';

              // 도서 정보 추출 후 grid 에 추가
              const book = response.result[0];  // 첫 번째 항목 추출

              // 이미 목록에 있는지 확인 (존재한다면 true 반환)
              if (!this.isBookInList(this.bookKeyword)) {
                console.log("userID: " + book.userID
                    + ", userName: " + book.userName
                    + ", isbnCode: " + book.isbnCode
                    + ", title: " + book.title
                    + ", loanDate: " + book.loanDate
                    + ", returnDate: " + book.returnDate
                );

                this.bookList.push({
                  userID: this.userID,
                  userName: this.userName,
                  isbnCode: this.bookKeyword,
                  titleInfo: book.titleInfo,
                  authorInfo: book.authorInfo,
                  pubInfo: book.pubInfo
                });
              } else {
                this.errorMessage = "이미 추가된 도서입니다.";
                alert("이미 추가된 도서입니다.");
              }
            }
          })
          .catch((error) => {
            console.log("Error fetching data: ", error);
            this.errorMessage = "데이터를 가져오는 도중 오류가 발생했습니다.";
          })
          .finally(() => {
            console.log("finally search");
          });
    },
    updateBookList() {
      this.bookList.map(book => ({
        userID: book.userID,
        userName: book.userName,
        isbnCode: book.isbnCode,
        title: book.titleInfo,
        loanDate: this.loanData.loanDate,
        returnDate: this.loanData.returnDate
      }));
    },
    async saveBooks() {
      console.log("==> saveBooks()");
      this.setLoanDates();    // to set the dates
      // this.updateBookList();  // ensure bookList has proper data

      // add to bookList to include loanDate and returnDate
      const updateBookList = this.bookList.map(book => ({
        ...book,
        title: book.titleInfo,
        loanDate: this.loanData.loanDate,
        returnDate: this.loanData.returnDate
      }));

      console.log("update booklist: ", updateBookList);

      axios
          .post('http://localhost:8080/loan/register', updateBookList)
          .then((response) => {
            console.log("Books saved: ", response.data);
            alert("저장되었습니다.");
            this.bookList = [];   // clear the booklist after saving
          })
          .catch((error) => {
            // console.log("Error saving books: ", error);
            // alert("저장 중 오류가 발생했습니다: " + (error.response ? error.response.data : error.message));

            if (error.response && error.response.status === 409) {
              // Handle duplicate record error
              alert("이미 대출 중인 중복된 도서가 존재합니다: " + error.response.data);
            } else if (error.response && error.response.status === 400) {
              // Handle unique constraint violation error
              alert("저장 중 오류가 발생했습니다: " + error.response.data);
            } else {
              // Handle any other errors
              alert("저장 중 오류가 발생했습니다.");
            }
            console.error("Error saving books: ", error);
          })
    }
  }
}
</script>

<style scoped>
.boxTest {
  width: 200px;
  height: 50px;
  text-align: center;
  border: 1px solid #000;
}

.search-bar {
  margin: 20px 0;
}

.alert {
  color: red;
  font-weight: bold;
}

.grid {
  margin-top: 20px;
  border: 1px solid #ccc;
  padding: 10px;
}

.grid-header {
  font-weight: bold;
  display: flex;
  justify-content: space-between;
}

.grid-item {
  display: flex;
  justify-content: space-between;
  padding: 5px 0;
  border-bottom: 1px solid #eee;
}
</style>
