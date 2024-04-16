import { defineStore } from 'pinia'

export const useTellerStore = defineStore('tellerStore', {
    state: () => {
        return {
            teller: {
                id: "",
                username: "",
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
    },
    actions: {
        addTeller(teller) {
            this.teller = teller
        },
        setCustomer(id, firstname, lastname) {
            this.customerFocus.id = id
            this.customerFocus.firstName = firstname
            this.customerFocus.lastName = lastname
        }
    },
    persist: true,
})