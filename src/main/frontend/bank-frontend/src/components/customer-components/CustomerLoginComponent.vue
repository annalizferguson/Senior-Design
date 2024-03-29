<template>
    <div
            class="d-flex justify-center align-center"
            width="100%"
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
                            width="100%"
                            class="d-flex justify-space-between align-center"
                    >
                        <div>
                            Don't have an account?
                            <router-link to="/register">Register Here</router-link>
                        </div>
                        <div
                                width="100%"
                                class="justify-end"
                        >
                            <router-link
                                    to="/customer-dash">
                                <v-btn
                                        type="submit"
                                        class="mt-2"
                                        color="primary"
                                        value="log in">
                                    Login
                                </v-btn>
                            </router-link>
                        </div>
                    </div>
                </v-form>
            </v-card-text>
        </v-card>
    </div>
</template>


<script>

export default {
    name: 'CustomerLoginComponent',

    data: () => {
        return {
            username: "",
            password: "",
            errorMessage: "Error",
        }
    },

}

</script>
<script setup>
</script>