<template>
    <div
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 146px)"
    >
        <v-card
                width="60%"
        >
            <v-toolbar>
                <v-toolbar-title v-if="!loginAdmin">Login as Teller</v-toolbar-title>
                <v-toolbar-title v-if="loginAdmin">Login as Admin</v-toolbar-title>
            </v-toolbar>
            <v-card-text>
                <v-form ref="form">
                    <v-text-field
                            v-model="username"
                            name="username"
                            label="Username"
                            type="text"
                            placeholder="Enter a username"
                            required
                    ></v-text-field>
                    <v-text-field
                            v-model="password"
                            name="password"
                            label="Password"
                            type="password"
                            placeholder="Enter a password"
                            required
                    ></v-text-field>
                    <v-container
                            width="100%"
                            class="d-flex justify-space-between"
                    >
                        <v-switch v-model="loginAdmin" label="Login as Admin" color="primary"></v-switch>
                        <v-btn
                                type="button"
                                class="mt-2"
                                color="primary"
                                value="log in"
                                @click="login"
                        >
                            Login
                        </v-btn>
                    </v-container>
                </v-form>
            </v-card-text>
        </v-card>
    </div>
</template>

<script>
import axios from "axios";
import {useTellerStore} from "@/states/TellerStore.js";
import {useRouter} from "vue-router";

export default {
    name: "TellerLoginPage.vue",
    data: () => {
        const tellerStore = useTellerStore()
        const router = useRouter()
        return {
            tellerStore: tellerStore,
            router: router,
            loginAdmin: false,
            username: "",
            password: "",
            errorMessage: "Error",
        }
    },
    methods: {
        login: function () {
            if (!this.loginAdmin) {
                axios.post('/api/login', {
                    username: this.$data.username,
                    password: this.$data.password
                }).then((response) => {
                    if (response.data === '') {
                        console.log("login invalid")
                    } else {
                        console.log("login valid!")
                        this.tellerStore.addTeller(response.data)
                        this.tellerStore.setAuthenticated()
                        this.router.push("/teller-dash")
                    }
                }).catch(() => {
                    console.log("login invalid.")
                })
            }
        },
    }
}
</script>

<style scoped>

</style>