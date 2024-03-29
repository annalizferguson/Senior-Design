import 'vuetify/styles'
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/dist/vuetify.min.css'
import {createApp} from 'vue'
import {createVuetify} from "vuetify"
import router from './router/index.js'
import App from './App.vue'

import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const app = createApp(App)
const vuetify = createVuetify({
    components,
    directives,
    iconfont: 'mdi',
})

app.use(vuetify)
app.use(router)

app.mount('#app')