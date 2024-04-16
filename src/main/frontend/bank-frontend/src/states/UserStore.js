import {defineStore} from 'pinia'

export const useCustomerStore = defineStore('userStore', {
    state: () => {
        return {
            authenticated: false,
            customer: {
                id: "",
                address: "",
                cellNumber: "",
                cellPhoneNumber: "",
                doB: "",
                email: "",
                ssn: "",
                firstName: "",
                lastName: "",
                mailingAddress: "",
                username: "",
                password: ""
            }
        }
    },
    getters: {
        getAuthenticated(state) {
            return state.authenticated;
        },
        getCustomer(state) {
            return state.customer;
        },
        getID(state) {
            return state.customer.id;
        },
        getAddress(state) {
            return state.customer.address;
        },
        getCellNumber(state) {
            return state.customer.cellNumber;
        },
        getDoB(state) {
            return state.customer.doB;
        },
        getEmail(state) {
            return state.customer.email;
        },
        getFirstName(state) {
            return state.customer.firstName;
        },
        getLastName(state) {
            return state.customer.lastName;
        },
        getMailingAddress(state) {
            return state.customer.mailingAddress;
        },
        getPhoneNumber(state) {
            return state.customer.phoneNumber
        },
        getSSN(state) {
            return state.customer.ssn
        },
        getUsername(state) {
            return state.customer.username
        },
        getPassword(state) {
            return state.customer.password
        }
    },
    actions: {
        addCustomer(customer) {
            this.customer = customer
        },
        setAuthenticated() {
            this.authenticated = true
        }
    },
    persist: true,
})