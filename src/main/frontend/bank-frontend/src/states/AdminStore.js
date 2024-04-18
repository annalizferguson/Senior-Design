import { defineStore } from 'pinia'

export const useAdminStore = defineStore('adminStore', {
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
            },
            tellerFocus:{
                id: "",
            }
        }
    },
    getters: {
        getAdmin(state) {
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
        getTellerID(state){
            return state.tellerFocus.id
        }
    },
    actions: {
        addAdmin(admin) {
            this.admin = admin
        },
        setCustomer(id) {
            this.customerFocus.id = id
        },
        setTeller(id){
            this.tellerFocus.id = id
        }
    },
    persist: true,
})