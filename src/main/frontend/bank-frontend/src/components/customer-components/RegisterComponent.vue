<template>
    <div
            class="d-flex justify-center align-center"
            width="100%"
            style="height: calc(100vh - 70px)"
    >
        <v-card
                width="70%"
                height="80%"
                class="overflow-y-auto"
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
                    <v-text-field
                            v-model="email"
                            name="getEmail"
                            label="Email"
                            type="text"
                            placeholder="Enter your email"
                            required
                    ></v-text-field>
                    <v-text-field
                            v-model="phoneNumber"
                            name="getPhoneNumber"
                            label="Phone Number"
                            type="text"
                            placeholder="Enter your phone number"
                            required
                    ></v-text-field>
                    <v-text-field
                            v-model="cellPhoneNumber"
                            name="getCellPhoneNumber"
                            label="Cell Phone Number"
                            type="text"
                            placeholder="Enter your cell phone number"
                    ></v-text-field>
                    <v-text-field
                            v-model="address"
                            name="getAddress"
                            label="Address"
                            type="text"
                            placeholder="Enter your home address"
                            required
                    ></v-text-field>
                    <v-text-field
                            v-model="mailingAddress"
                            name="getMailingAddress"
                            label="Mailing Address"
                            type="text"
                            placeholder="Enter your mailing address"
                            required
                    ></v-text-field>
                    <v-text-field
                            v-model="dob"
                            name="getDOB"
                            label="Date of Birth"
                            type="text"
                            placeholder="Enter your date of birth"
                            required
                    ></v-text-field>
                    <v-text-field
                            v-model="ssn"
                            name="getSSN"
                            label="SSN"
                            type="password"
                            placeholder="Enter your SSN"
                            required
                    ></v-text-field>
                    <v-container
                            width="100%"
                            class="d-flex justify-space-between align-center"
                    >
                        <v-container>
                            Already have an account?
                            <router-link to="/customer-login">Login Here</router-link>
                        </v-container>
                        <v-container
                                width="100%"
                                class="d-flex justify-end"
                        >
                            <v-btn
                                    type="submit"
                                    class="mt-2"
                                    color="primary"
                                    variant="elevated"
                                    value="log in">
                                Register
                            </v-btn>
                        </v-container>
                    </v-container>
                </v-form>
            </v-card-text>
        </v-card>
    </div>
</template>

<script>

export default {
    name: 'RegisterComponent',

    data: () => {
        return {
            username: "",
            password: "",
            confirmPassword: "",
            isRegister: true,
            errorMessage: "Error",
            address: "",
            mailingAddress: "",
            email: "",
            phoneNumber: "",
            cellPhoneNumber: "",
            dob: "",
            ssn: ""
        }
    },

}

</script>
