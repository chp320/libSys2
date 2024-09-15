<script>
export default {
  name: 'ConfirmPage',
  computed: {
    userID() {
      console.log('userID: ', this.$route.query.userID);
      return this.$route.query.userID || 'No User ID';
    },
    userName() {
      console.log('userName: ', this.$route.query.userName);
      return this.$route.query.userName || 'No User Name';
    },
    userBirth() {
      console.log('userBirth: ', this.$route.query.userBirth);
      // return this.$route.query.userBirth || 'No User Birth';

      // 생년월일을 yyyy-mm-dd 형식으로 변경
      const birth = this.$route.query.userBirth;
      if(!birth) return 'No User Birth';

      if(/^\d{8}$/.test(birth)) {
        const year = birth.substring(0,4);
        const month = birth.substring(4,6);
        const day = birth.substring(6,8);
        return `${year}-${month}-${day}`;
      }

      const date = new Date(birth);
      if(isNaN(date.getTime())) {
        console.error('Invalid date format: ', birth);
        return 'Invalid Date';
      }

      const formattedYear = date.getFullYear();
      const formattedMonth = String(date.getMonth()+1).padStart(2,'0');
      const formattedDay = String(date.getDate()).padStart(2,'0');
      console.log('year:'+formattedYear+', month:'+formattedMonth+', day:'+formattedDay);

      return `${formattedYear}-${formattedMonth}-${formattedDay}`;
    }
  },
  methods: {
    goHome() {
      this.$router.push('/')
    }
  }
}
</script>

<template>
  <div class="container">
    <div>
      <h1>🥰 축하합니다~ 🥰</h1>
      <h1>가입이 완료되었습니다!</h1>
    </div>
    <div class="grid">
      <div class="grid-header">
        가입 정보 확인
      </div>
<!--      <div class="grid-item">회원 번호: {{ userID }}</div>-->
<!--      <div class="grid-item">회원 이름: {{ userName }}</div>-->
<!--      <div class="grid-item">회원 생년월일: {{ userBirth }}</div>-->
      <div class="grid-item">
        <div class="column-name">회원 번호</div>
        <div class="column-value">{{ userID }}</div>
      </div>
      <div class="grid-item">
        <div class="column-name">회원 이름</div>
        <div class="column-value">{{ userName }}</div>
      </div>
      <div class="grid-item">
        <div class="column-name">회원 생년월일</div>
        <div class="column-value">{{ userBirth }}</div>
      </div>
    </div>
    <div class="button-container">
      <button class="btn btn-primary" type="button" @click="goHome">확인</button>
    </div>
  </div>
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
  min-height: 100vh;
  text-align: center;
}

.grid {
  //display: grid;
  ////grid-template-columns: repeat(1, auto);
  ////grid-template-rows: repeat(3, auto);
  //grid-template-rows: auto;
  ////gap: 10px;
  //gap: 15px;
  //border: 1px solid #ddd;
  //padding: 10px;
  //background-color: #f9f9f9;
  ////max-width: 100%;
  //max-width: 600px;
  //width: 100%;
  //text-align: left;
  display: grid;
  grid-template-columns: 1fr;
  gap: 10px;
  max-width: 600px;
  width: 100%;
  margin-top: 20px;
  border: 1px solid #ddd;
  background-color: #f9f9f9;
}

.grid-header {
  //padding: 10px;
  //border-bottom: 1px solid #ddd;
  //background-color: cyan;
  //font-weight: bold;
  //grid-column: span 2;
  font-weight: bold;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  background-color: cyan;
}

.grid-item {
  //padding: 10px;
  //border: 1px solid #ddd;
  //background-color: #fff;
  display: grid;
  grid-template-columns: 1fr 2fr;
  gap: 10px;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ddd;
  background-color: #f9f9f9;
}

.column-name {
  font-weight: bold;
  text-align: right;
}

.column-value {
  text-align: left;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.btn {
  //margin-top: 20px;
}

</style>
