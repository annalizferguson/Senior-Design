<template>
    <div
            class="d-flex justify-center align-center"
            width="100%"
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
                        <router-link
                                to="/customer-dash">
                            <v-btn
                                    type="submit"
                                    class="mt-2"
                                    color="primary"
                                    value="log in"
                                    to="/teller-dash"
                            >
                                Login
                            </v-btn>
                        </router-link>
                    </v-container>
                </v-form>
            </v-card-text>
        </v-card>
    </div>
</template>

<script>
export default {
    name: "TellerLoginPage.vue",
    data: () => {
        return {
            loginAdmin: false,
            username: "",
            password: "",
            errorMessage: "Error",
        }
    },
}
</script>

<style scoped>

</style>