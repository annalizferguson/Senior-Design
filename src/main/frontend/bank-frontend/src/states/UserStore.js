import { defineStore } from 'pinia'

export const useUserStore = defineStore('userStore', {
    state: () => {
        return {
            customer: null
        }
    },
    getters: {
        getID(state) {
            return state.customer.id;
        }
    },
    actions: {
      addCustomer(customer) {
          this.customer = customer
      }
    },
    persist: true,
})