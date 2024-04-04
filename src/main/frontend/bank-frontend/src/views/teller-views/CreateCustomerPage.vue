<template>
    <div
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 50px)"
    >
        <v-card
                width="80%"
        >
            <v-card
                    width="100%"
                    height="10%"
                    variant="elevated"
                    color="#4097f5"
            >
                <v-card-title
                        class="d-flex justify-space-between"
                >
                    Create New Customer
                    <v-btn
                            variant="outlined"
                            to="/teller-dash"
                    >
                        Return to Dashboard
                    </v-btn>
                </v-card-title>
            </v-card>
            <v-card
                    scrollable
                    style="height:calc(100vh - 200px)"
                    class="overflow-y-auto"
            >
                <v-container>
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
                                type="number"
                                placeholder="Enter your phone number"
                                hide-spin-buttons
                                :rules="rules.required"
                        ></v-text-field>
                        <v-text-field
                                v-model="cellPhoneNumber"
                                name="getCellPhoneNumber"
                                label="Cell Phone Number"
                                type="number"
                                placeholder="Enter your cell phone number"
                                hide-spin-buttons
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
                                type="number"
                                placeholder="Enter your SSN"
                                hide-spin-buttons
                                :rules="rules.required"
                        ></v-text-field>
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
                                Create New Customer
                            </v-btn>
                        </v-container>
                    </v-form>
                </v-container>
            </v-card>
        </v-card>
    </div>
</template>

<script>

export default {
    name: "CreateCustomerPage.vue",
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
    }

}
</script>

<style scoped>

</style>