import 'vuetify/styles'
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/dist/vuetify.min.css'
import {createApp} from 'vue'
import {createVuetify} from "vuetify"
import router from './router/index.js'
import App from './App.vue'
import {createPinia} from "pinia";
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const app = createApp(App)
const vuetify = createVuetify({
    components,
    directives,
    iconfont: 'mdi',
})
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

app.use(vuetify)
app.use(router)
app.use(pinia)

app.mount('#app')