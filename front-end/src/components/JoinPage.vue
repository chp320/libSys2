<template>
<!--  <div class="container">-->
<!--    <div class="row justify-content-center">-->
<!--      <div class="register-form">-->
<!--        <div class="logo-wrapper">-->
<!--          <div class="tagline">Anna's Library Management System</div>-->
<!--        </div>-->
<!--        <form @submit.prevent="submitForm">-->
<!--          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>-->
<!--          <div class="form-group">-->
<!--            <label for="username">Username</label>-->
<!--            <input type="text" class="form-control" id="username" v-model="form.username">-->
<!--            <div class="field-error" v-if="$v.form.username.$dirty">-->
<!--              <div class="error" v-if="!$v.form.username.required">Username is required</div>-->
<!--              <div class="error" v-if="!$v.form.username.alphaNum">Username can only contain letters and numbers</div>-->
<!--              <div class="error" v-if="!$v.form.username.minLength">Username must have at least {{$v.form.username.$params.minLength.min}} letters.</div>-->
<!--              <div class="error" v-if="!$v.form.username.maxLength">Username is too long. It can contains maximium {{$v.form.username.$params.maxLength.max}} letters.</div>-->
<!--            </div>-->
<!--          </div>-->
<!--          <button type="submit" class="btn btn-primary btn-block">Create account</button>-->
<!--        </form>-->
<!--      </div>-->
<!--    </div>-->
<!--    <footer class="footer">-->
<!--      <span class="copyright">&copy; 2018 TaskAgile.com</span>-->
<!--      <ul class="footer-links list-inline float-right">-->
<!--        <li class="list-inline-item"><a href="#">About</a></li>-->
<!--        <li class="list-inline-item"><a href="#">Terms of Service</a></li>-->
<!--        <li class="list-inline-item"><a href="#">Privacy Policy</a></li>-->
<!--        <li class="list-inline-item"><a href="https://github.com/taskagile/vuejs.spring-boot.mysql" target="_blank">GitHub</a></li>-->
<!--      </ul>-->
<!--    </footer>-->
<!--  </div>-->

<!--  <div class="container">-->
<!--    <div class="tagline">Anna's Library Management System</div>-->
<!--    <form @submit.prevent="submitForm">-->
<!--      <div>-->
<!--        <label for="userName">이름</label>-->
<!--        <input type="text" id="userName" v-model="userName" />-->
<!--      </div>-->
<!--      <div>-->
<!--        <label for="userBirth">생년월일</label>-->
<!--        <input type="text" id="userBirth" v-model="userBirth" />-->
<!--      </div>-->
<!--      <button type="submit" class="btn btn-primary btn-block">Create account</button>-->
<!--    </form>-->
<!--  </div>-->
<!--  <hr><br><br><br>-->

<!--  <h2 class="boxTest">회원가입 페이지</h2>-->
<!--  <router-link to="/">메인페이지 이동</router-link>-->
<!--  <hr><br><br><br>-->

  <div class="container">
    <div>
      <h1>🤠 회원 정보 등록 🤠</h1>
    </div>
    <form @submit.prevent="submitForm">
      <div class="grid">
        <div class="grid-item">
          <label for="userName">회원 이름</label>
          <input type="text" id="userName" ref="userNameInput" v-model="userName">
        </div>
        <div class="grid-item">
          <label for="userBirth">회원 생년월일</label>
          <input type="text" id="userBirth" v-model="userBirth">
        </div>
      </div>
      <div class="button-container">
        <button type="submit" class="btn btn-primary btn-block">회원 정보 생성</button>
      </div>
    </form>
  </div>
</template>

<script>
import {registerUser} from "@/services/registration";

export default {
  name: 'JoinPage',
  mounted() {
    this.$refs.userNameInput.focus();
  },
  data() {
    return {
      id: '',
      userName: '',
      userBirth: ''
    };
  },
  methods: {
    generateID() {
      // 1. get current date
      const now = new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth()+1).padStart(2,'0');
      const day = String(now.getDate()).padStart(2,'0');
      console.log('now:'+now+', year:'+year+', month:'+month+', day:'+day);

      // yyyymmdd 형식으로 변환
      const formattedDate = `${year}${month}${day}`;

      // 2. generate random 6-digit number
      const randomNum = String(Math.floor(100000 + Math.random() * 900000));

      // 3. combine
      const userID = `${formattedDate}${randomNum}`;

      return userID;
    },
    async submitForm() {
      console.log('submitForm1');
      // this.generateID();
      console.log('submitForm2');

      // validation 생략
      const userData = {
        userID: this.generateID(),
        userName: this.userName,
        userBirth: this.userBirth
      };
      console.log('submitForm3');
      console.log(userData);

      const response = await registerUser(userData);
      console.log('submitForm4');
      console.log(response);
      if(response.status === 200) {
        // alert('환영합니다.')
        // this.$router.push('/')

        // redirect to ConfirmPage.vue with userID, userName, userBirth
        this.$router.push({
          path: '/confirm',
          query: {
            userID: userData.userID,
            userName: userData.userName,
            userBirth: userData.userBirth
          }
        })
      } else {
        alert(response.data)
      }
      console.log('submitForm5');
      console.log(response);
    }
  }
}
</script>

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
  display: grid;
  //grid-template-columns: repeat(1, auto);
  grid-template-rows: repeat(3, auto);
  gap: 10px;
  border: 1px solid #ddd;
  padding: 10px;
  background-color: #f9f9f9;
  max-width: 100%;
}

.grid-item {
  padding: 10px;
  border: 1px solid #ddd;
  background-color: #fff;
}

.grid-item label {
  flex: 0 0 30%;
  text-align: right;
}

.grid-item input {
  flex: 1;
  width: 100%;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.btn-block {
  width: 100%;
}


.boxTest {
  width: 200px;
  height: 50px;
  text-align: center;
  border: 1px solid #000;
}
</style>
