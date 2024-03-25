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
                <v-form v-model="validForm" ref="form">
                    <v-text-field
                            v-model="username"
                            name="username"
                            label="Username"
                            type="text"
                            placeholder="Enter a username"
                            :rules="rules.required"
                    ></v-text-field>
                    <v-text-field
                            v-model="password"
                            name="password"
                            label="Password"
                            type="password"
                            placeholder="Enter a password"
                            :rules="rules.required"
                    ></v-text-field>
                    <v-text-field v-if="isRegister"
                                  v-model="confirmPassword"
                                  name="confirmPassword"
                                  label="Confirm Password"
                                  type="password"
                                  placeholder="Confirm your password"
                                  :rules="rules.required"
                    ></v-text-field>
                    <v-text-field
                            v-model="email"
                            name="getEmail"
                            label="Email"
                            type="text"
                            placeholder="Enter your email"
                            :rules="rules.required"
                    ></v-text-field>
                    <v-text-field
                            v-model="phoneNumber"
                            name="getPhoneNumber"
                            label="Phone Number"
                            type="text"
                            placeholder="Enter your phone number"
                            :rules="rules.required"
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
                            :rules="rules.required"
                    ></v-text-field>
                    <v-text-field
                            v-model="mailingAddress"
                            name="getMailingAddress"
                            label="Mailing Address"
                            type="text"
                            placeholder="Enter your mailing address"
                            class="mr-3"
                            disable="!checked"
                            :rules="rules.required"
                    ></v-text-field>
                    <v-text-field
                            v-model="dob"
                            name="getDOB"
                            label="Date of Birth"
                            type="text"
                            placeholder="Enter your date of birth"
                            :rules="rules.required"
                    ></v-text-field>
                    <v-text-field
                            v-model="ssn"
                            name="getSSN"
                            label="SSN"
                            type="password"
                            placeholder="Enter your SSN"
                            :rules="rules.required"
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
                                    value="log in"
                                    onClick="checkIfFormIsFilled"
                            >
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
            validForm: false,
            rules: {
                required: [value => !!value || "Required."]
            },
            checked: false,
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
            ssn: "",
        }
    },
    methods: {
        checkIfFieldsFilled: function () {
            this.$refs.form.validate();
        },
    }
}

</script>
