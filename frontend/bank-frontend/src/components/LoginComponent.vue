<template>
    <div
            class="d-flex justify-center align-center"
            width="100%"
            style="height: calc(100vh - 146px)"
    >
        <v-card
                variant="outlined"
                width="50%"
        >
            <v-toolbar>
                <v-toolbar-title>Register for an Account</v-toolbar-title>
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
                    <v-text-field v-if="isRegister"
                                  v-model="confirmPassword"
                                  name="confirmPassword"
                                  label="Confirm Password"
                                  type="password"
                                  placeholder="Confirm your password"
                                  required
                    ></v-text-field>
                    <div class="red--text"> {{ errorMessage }}</div>
                    <div
                            width="100%"
                            class="d-flex flex-row-reverse"
                    >
                        <v-btn
                                type="submit"
                                class="mt-2"
                                color="primary"
                                value="log in">
                            {{ isRegister ? stateObj.register.name : stateObj.login.name }}
                        </v-btn>
                    </div>
                </v-form>
            </v-card-text>
        </v-card>
    </div>
</template>

<script>

export default {
    name: 'LoginComponent',

    data: () => {
        return {
            username: "",
            password: "",
            confirmPassword: "",
            isRegister: true,
            stateObj: {
                register: {
                    name: 'Register',
                    message: 'Aleady have an Account? login.'
                },
                login: {
                    name: 'Login',
                    message: 'Register'
                }
            }
        }
    },

    computed: {
        toggleMessage: function () {
            return this.isRegister ? this.stateObj.register.message : this.stateObj.login.message
        }
    }
}

</script>
