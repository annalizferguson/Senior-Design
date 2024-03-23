import {defineStore} from "pinia";
import {ref} from "vue";

export const useUserStore = defineStore("UserStore", {
    state: () => ({
        userid: ref({}),
    }),
    getters: {
        userID: (state) => {
            return state.userid
        }
    },
    actions: {
        setIDState(word) {
            this.userid = word
        }
    }
})