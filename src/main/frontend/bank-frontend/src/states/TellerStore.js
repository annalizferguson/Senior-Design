import { defineStore } from 'pinia'

export const useTellerStore = defineStore('tellerStore', {
    state: () => {
        return {
            teller: {
                id: "",
                username: "",
                password: "",
                firstName: "",
                lastName: ""
            },
            customerFocus: {
                id: "",
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
        }
    },
    actions: {
        addTeller(teller) {
            this.teller = teller
        },
        setCustomer(id) {
            this.customerFocus.id = id
        }
    },
    persist: true,
})