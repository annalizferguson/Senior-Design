// Styles
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'

import { createApp } from "vue";

// Vuetify
import { createVuetify } from 'vuetify'

const customBankTheme = {
    primary: "#1565c0",
    secondary: "#0d47a1",
    accent: "#82b1ff",
}

export default createVuetify({
    theme: {
        defaultTheme: "customBankTheme",
        themes: {
            customBankTheme
        },
    }
})

/*
    primary: "#1565c0",
    secondary: "#0d47a1",
    accent: "#82b1ff",
 */
