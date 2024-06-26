import { defineStore } from 'pinia'

export const useTellerStore = defineStore('tellerStore', {
    state: () => {
        return {
            authenticated: false,
            customerSet: false,
            teller: {
                id: "",
                username: "",
                password: "",
                firstName: "",
                lastName: ""
            },
            customerFocus: {
                id: "",
                firstName: "",
                lastName: "",
            }
        }
    },
    getters: {
        getAuthenticated(state) {
            return state.authenticated
        },
        getTeller(state) {
            return state.teller;
        },
        getID(state) {
            return state.teller.id;
        },
        getUsername(state) {
            return state.teller.username;
        },
        getFirstName(state) {
            return state.teller.firstName;
        },
        getLastName(state) {
            return state.teller.lastName;
        },
        getCustomerID(state) {
            return state.customerFocus.id
        },
        getCustomerFirstName(state) {
            return state.customerFocus.firstName
        },
        getCustomerLastName(state) {
            return state.customerFocus.lastName
        },
        getCustomerSet(state) {
            return state.customerSet
        },
    },
    actions: {
        addTeller(teller) {
            this.teller = teller
        },
        setCustomer(id, firstname, lastname) {
            this.customerSet = true
            this.customerFocus.id = id
            this.customerFocus.firstName = firstname
            this.customerFocus.lastName = lastname
        },
        resetStore() {
          this.authenticated = false
          this.teller = {
              id: "",
              username: "",
              firstName: "",
              lastName: ""
          }
          this.customerFocus = {
              id: "",
              firstName: "",
              lastName: "",
          }
        },
        setAuthenticated() {
            this.authenticated = true;
        }
    },
    persist: true,
})