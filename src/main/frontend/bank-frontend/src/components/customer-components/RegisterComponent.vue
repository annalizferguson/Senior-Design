<template>
    <div
            class="d-flex justify-center align-center"
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
                            hint="Passwords must be greater than 8 characters and include a capital letter, a lowercase letter, and a special character."
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
                            v-model="firstName"
                            name="firstname"
                            label="First Name"
                            type="text"
                            placeholder="Enter your first name"
                            :rules="rules.required"
                    ></v-text-field>
                    <v-text-field
                            v-model="lastName"
                            name="lastname"
                            label="Last Name"
                            type="text"
                            placeholder="Enter your last name"
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
                            :rules="rules.required"
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
                            hint="mm-dd-yyyy"
                            :rules="rules.required"
                    ></v-text-field>
                    <v-text-field
                            v-model="ssn"
                            name="getSSN"
                            label="SSN"
                            type="text"
                            placeholder="Enter your SSN"
                            :rules="rules.required"
                    ></v-text-field>
                    <v-container v-if="showRegisterFail">
                        <v-alert
                            closable
                            title="Invalid Registration"
                            text="Your registration was invalid due to empty fields or not meeting the password requirements."
                            type="error"
                        />
                    </v-container>
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
                                    type="button"
                                    class="mt-2"
                                    color="primary"
                                    variant="elevated"
                                    value="log in"
                                    @click="() => {registerCustomer()}"
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
import axios from 'axios';
import {useRouter} from "vue-router";

export default {
    name: 'RegisterComponent',

    data: () => {
        const router = useRouter()
        return {
            router: router,
            validForm: false,
            rules: {
                required: [value => !!value || "Required."]
            },
            checked: false,
            firstName: "",
            lastName: "",
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
            showRegisterFail: false,
        }
    },
    methods: {
        checkIfFieldsFilled: function () {
            this.$refs.form.validate();
        },
        async registerCustomer() {
            const newCustomer = {
                "username": this.username,
                "password": this.password,
                "firstName": this.firstName,
                "lastName":  this.lastName,
                "ssn": this.ssn,
                "email": this.email,
                "address": this.address,
                "mailingAddress": this.mailingAddress,
                "phoneNumber": this.phoneNumber,
                "cellNumber": this.cellPhoneNumber,
                "doB": this.dob,
                "cellPhoneNumber": this.cellPhoneNumber
            }
                axios.post('/api/customers', newCustomer).then((response) => {
                    console.log(response)
                    this.router.push('/register-success')


                }).catch((e) => {
                    console.log("Register Invalid.")
                    this.showRegisterFail = true
                })
        }
    }
}

</script>
