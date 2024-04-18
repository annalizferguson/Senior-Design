import { createRouter, createWebHistory } from 'vue-router'
import {useCustomerStore} from "@/states/UserStore.js";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import('../views/LandingPage.vue')
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
          path: '/register-success',
          name: 'register-success',
          component: () => import('../views/customer-views/RegisterConfirmedPage.vue')
        },
        {
            path: '/customer-dash',
            name: 'customer-dash',
            beforeEnter: (to,from,next) => {
                const store = useCustomerStore()
                if (store.getAuthenticated) {
                    next()
                } else {
                    next({name: 'home'})
                }
            },
            component: () => import('../views/customer-views/CustomerDashboard.vue'),
        },
        {
            path: '/customer-settings',
            name: 'customer-settings',
            beforeEnter: (to,from,next) => {
                const store = useCustomerStore()
                if (store.getAuthenticated) {
                    next()
                } else {
                    next({name: 'home'})
                }
            },
            component: () => import('../views/customer-views/CustomerSettingsPage.vue')
        },
        {
            path: '/accounts',
            name: 'accounts',
            beforeEnter: (to,from,next) => {
                const store = useCustomerStore()
                if (store.getAuthenticated) {
                    next()
                } else {
                    next({name: 'home'})
                }
            },
            component: () => import('../views/customer-views/CustomerAccountsPage.vue')
        },
        {
            path: '/bills',
            name: 'bills',
            beforeEnter: (to,from,next) => {
                const store = useCustomerStore()
                if (store.getAuthenticated) {
                    next()
                } else {
                    next({name: 'home'})
                }
            },
            component: () => import('../views/customer-views/CustomerBillsPage.vue')
        },
        {
          path: '/teller-login',
          name: 'teller-login',
          component: () => import('../views/teller-views/TellerLoginPage.vue')
        },
        {
            path: '/teller-dash',
            name: 'teller-dash',
            component: () => import('../views/teller-views/TellerDashboard.vue')
        },
        {
          path: '/create-customer',
          name: 'create-customer',
          component: () => import('../views/teller-views/CreateCustomerPage.vue')
        },
        {
          path: '/customer-info',
          name: 'customer-info',
          component: () => import('../views/teller-views/CustomerDetailsPage.vue')
        },
        {
          path: '/customer-accounts',
          name: 'customer-accounts',
          component: () => import('../views/teller-views/ViewCustomerAccountsPage.vue')
        },
        {
            path: '/admin-dash',
            name: 'admin-dash',
            component: () => import('../views/AdminDashboard.vue')
        },
        {
            path: '/teller-info',
            name: 'teller-info',
            component: ()  => import('../views/admin-views/TellerDetailsPage.vue')
        },
        {
            path: '/teller-delete',
            name: 'teller-delete',
            component: () => import('../views/admin-views/TellerDeletePage.vue')
        },
        {
            path: '/create-teller',
            name: 'create-teller',
            component: () => import('../views/admin-views/CreateTellerPage.vue')
        }
    ]
})

export default router
