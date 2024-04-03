import { defineStore } from 'pinia'

export const useTellerStore = defineStore('tellerStore', {
    state: () => {
        return {
            teller: {
                id: "",
                username: "",
                firstName: "",
                lastName: ""
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
        }
    },
    actions: {
        addTeller(teller) {
            this.teller = teller
        }
    },
    persist: true,
})