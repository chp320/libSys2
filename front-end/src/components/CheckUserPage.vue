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
            console.log("finally");
          });
    },
    clearUserID() {
      this.userID = '' ;
      this.data = [] ;

      // 입력창 초기화
      this.$refs.inputUserID.focus();
    }
  }
}
</script>

<template>
<div class="search-user">
  <div>
    <h1>👻 회원증의 바코드를 읽거나, 입력해주세요~ 👻</h1>
  </div>
  <input type="text"
         id="userID"
         v-model="userID"
         @keydown.enter="searchUserID"
         ref="inputUserID"
         placeholder="바코드를 읽거나 고객번호를 입력해주세요">
  <button @click="clearUserID">다시 입력하기</button>
</div>
</template>

<style scoped>

</style>
