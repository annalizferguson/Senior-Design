import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import dns from 'dns'
import vue from '@vitejs/plugin-vue'
import dns from 'dns'

dns.setDefaultResultOrder('verbatim')

dns.setDefaultResultOrder('verbatim')

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  server: {
    host:'localhost',
    port:8000,
  },
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    host: 'localhost',
    port: 8000
  }
})
