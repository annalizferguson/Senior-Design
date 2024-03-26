import { createRouter, createWebHistory } from 'vue-router'
import Register from '../views/RegisterPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/LandingPage.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/customer-login',
      name: 'customer-login',
      component: () => import('../views/customer-views/CustomerLoginPage.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/RegisterPage.vue')
    },
    {
      path: '/customer-dash',
      name: 'customer-dash',
      component: () => import('../views/customer-views/CustomerDashboard.vue')
    },
    {
      path: '/transfers',
      name: 'transfers',
      component: () => import('../views/TransfersPage.vue')
    },
    {
      path: '/accounts',
      name: 'accounts',
      component: () => import('../views/customer-views/CustomerAccountsPage.vue')
    },
    {
      path: '/bills',
      name: 'bills',
      component: () => import('../views/customer-views/CustomerBillsPage.vue')
    },
    {
      path: '/teller-dash',
      name: 'teller-dash',
      component: () => import('../views/TellerDashboard.vue')
    },
    {
      path: '/admin-dash',
      name: 'admin-dash',
      component: () => import('../views/AdminDashboard.vue')
    },
    {
      path: '/customer-settings',
      name: 'customer-settings',
      component: () => import('../views/customer-views/CustomerSettingsPage.vue')
    }
  ]
})

export default router
