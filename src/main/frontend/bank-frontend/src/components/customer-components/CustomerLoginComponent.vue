<template>
    <div
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 146px)"
    >
        <v-card
                width="70%"
        >
            <v-toolbar>
                <v-toolbar-title>Login to Account</v-toolbar-title>
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
                    <div
                            class="d-flex justify-space-between align-center"
                    >
                        <div>
                            Don't have an account?
                            <router-link to="/register">Register Here</router-link>
                        </div>
                        <div
                                class="justify-end"
                        >
                            <v-btn
                                    type="button"
                                    class="mt-2"
                                    color="primary"
                                    value="log in"
                                    @click="login"
                            >
                                Login
                            </v-btn>
                        </div>
                    </div>
                </v-form>
            </v-card-text>
        </v-card>
    </div>
</template>


<script>
import {useCustomerStore} from "@/states/UserStore.js";
import {useRouter} from "vue-router";
import axios from "axios";

export default {
    name: 'CustomerLoginComponent',

    data: () => {
        const router = useRouter()
        const store = useCustomerStore()
        return {
            username: "",
            password: "",
            errorMessage: "Error",
            router: router,
            store: store,
        }
    },

    methods: {
        login: function () {
            axios.post('/api/login', {
                username: this.$data.username,
                password: this.$data.password
            }).then((response) => {
                if (response.data === '') {
                    console.log("login invalid")
                } else {
                    console.log("login valid!")
                    this.store.addCustomer(response.data)
                    console.log(response.data)
                    this.store.setAuthenticated()
                    this.router.push("/customer-dash")
                }
            }).catch(() => {
                console.log("login invalid.")
            })
        },
    }

}

</script>
<script setup>
</script>