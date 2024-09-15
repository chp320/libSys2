import { createApp } from 'vue'
import App from './App.vue'

// router를 전역으로 사용하기 위함
import routes from './routes'

import 'bootstrap/dist/css/bootstrap.min.css'

const app = createApp(App)
app.use(routes)
app.mount('#app')
