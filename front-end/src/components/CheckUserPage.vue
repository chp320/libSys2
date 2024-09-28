<script>
import axios from 'axios'

export default {
  name: "CheckUserPage",
  mounted() {
    this.$refs.inputUserID.focus();
  },
  data() {
    return {
      userID: '',
      userName: '',
      data: []
    }
  },
  methods: {
    searchUserID() {
      console.log(this.userID);
      axios
          .get(`http://localhost:8080/user/search/${this.userID}`)
          .then((result) => {
            console.log(result.data);

            if(Array.isArray(result.data) && result.data.length === 0) {
              this.error = 'no users';
              this.$emit('error', this.error);
              return;
            }

            // this.data.push(result.data);
            this.data = result.data[0];
            // this.$emit('user-verified');
            this.$emit('user-verified', { userID: this.data.userID, userName: this.data.userName });
          })
          .catch((error) => {
            console.log(error);
            this.error = 'no users';
            this.$emit('error', this.error);
          })
          .finally(() => {
            this.clearUserID();
            console.log("finally");
          });
    },
    clearUserID() {
      this.userID = '' ;
      this.data = [] ;

      // 입력창 초기화
      this.$refs.inputUserID.focus();
    },
    goHome() {
      this.$router.push('/')
    }
  }
}
</script>

<template>
<div class="container">
  <div>
    <h1>👻 회원증의 바코드를 읽거나, 입력해주세요~ 👻</h1>
  </div>
  <div class="input-group">
    <div class="input-group-prepend">
      <span class="input-group-text">회원 번호</span>
    </div>
    <input type="text"
           id="userID"
           v-model="userID"
           @keydown.enter="searchUserID"
           ref="inputUserID"
           placeholder="바코드를 읽거나 고객번호를 입력해주세요"
           class="form-control" />
  </div>
  <div class="bottom-0">
    <div class="btn-outline-primary">
      <button class="btn btn-primary" type="button" @click="goHome">첫 화면으로 이동</button>
    </div>
  </div>
</div>



<!--<div class="search-user">-->
<!--  <div>-->
<!--    <h1>👻 회원증의 바코드를 읽거나, 입력해주세요~ 👻</h1>-->
<!--  </div>-->
<!--  <input type="text"-->
<!--         id="userID"-->
<!--         v-model="userID"-->
<!--         @keydown.enter="searchUserID"-->
<!--         ref="inputUserID"-->
<!--         placeholder="바코드를 읽거나 고객번호를 입력해주세요"-->
<!--         class="form-control" />-->
<!--  <button @click="clearUserID">다시 입력하기</button>-->
<!--</div>-->
<!--<div class="button-gohome">-->
<!--  <button class="btn btn-primary" type="button" @click="goHome">확인</button>-->
<!--</div>-->
</template>

<style scoped>
html, body {
  height: 100%;
  margin: 0;
}

.container {
  //max-width: 100%;
  //margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 50vh;
  min-width: 50vh;
  text-align: center;
}

.input-group {
  gap: 10px;
  align-items: center;
}

.btn-outline-primary {
  gap: 10px;
  align-items: center;
}

.button-gohome {
  float: right;
}
</style>
