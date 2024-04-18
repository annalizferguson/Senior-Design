<template>
    <div class="d-flex justify-center">
        <v-card width="80%" class="mt-4">
            <v-card-title style="background-color: #4097f5; color: #ffffff" class="d-flex justify-space-between">
                Settings
                <v-btn
                        variant="flat"
                        color="#4097f5"
                        to="/customer-dash"
                >
                    Return to Dashboard
                </v-btn>
            </v-card-title>
            <v-container>
                <v-row>
                    <v-col>
                        <v-tabs
                                v-model="tab"
                                color="primary"
                                direction="vertical"
                        >
                            <v-tab value="0">
                                <v-icon start>
                                    mdi-lock
                                </v-icon>
                                Change Login Information
                            </v-tab>
                            <v-tab value="1">
                                <v-icon start>
                                    mdi-account
                                </v-icon>
                                Change Personal Information
                            </v-tab>
                        </v-tabs>
                    </v-col>
                    <v-col>
                        <v-window v-model="tab">
                            <v-window-item value="0">
                                <v-card variant="flat">
                                    <v-form>
                                        <v-text-field
                                                v-model="username"
                                                name="username"
                                                label="Username"
                                                type="text"
                                                placeholder="Enter a username"
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="oldPassword"
                                                name="oldPassword"
                                                label="Old Password"
                                                type="password"
                                                placeholder="Enter a password"
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="newPassword"
                                                name="newPassword"
                                                label="New Password"
                                                type="password"
                                                placeholder="Enter a password"
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="newConfirmPassword"
                                                name="confirmPassword"
                                                label="Confirm New Password"
                                                type="password"
                                                placeholder="Confirm your password"
                                        ></v-text-field>
                                        <v-alert
                                                v-if="mismatchedPassAlert"
                                                closable
                                                text="New password must match confirmed password."
                                                type="error"
                                                @close="mismatchedPassAlert = false"
                                        />
                                        <v-alert
                                                v-if="wrongPassAlert"
                                                closable
                                                text="Old password is required to enter a new password."
                                                type="error"
                                                @close="wrongPassAlert = false"
                                        />
                                        <v-alert
                                                v-if="successUP"
                                                closable
                                                text="Information successfully updated."
                                                type="success"
                                                @close="successUP = false"
                                        />
                                        <v-alert
                                                v-if="failUP"
                                                closable
                                                text="Information failed to update."
                                                type="error"
                                                @close="failUP = false"
                                        />
                                    </v-form>
                                    <v-card-actions class="d-flex justify-end">
                                        <v-btn variant="tonal" color="#4097f5" @click="updateUsernamePassword">
                                            Confirm Changes
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-window-item>
                            <v-window-item value="1">
                                <v-card variant="flat">
                                    <v-form v-model="validForm" ref="form">
                                        <v-text-field
                                            v-model="firstName"
                                            name="getFirstName"
                                            label="First Name"
                                            type="text"
                                            placeholder="Enter your first name"
                                        ></v-text-field>
                                        <v-text-field
                                            v-model="lastName"
                                            name="getLastName"
                                            label="Last Name"
                                            type="text"
                                            placeholder="Enter your last name"
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="email"
                                                name="getEmail"
                                                label="Email"
                                                type="text"
                                                placeholder="Enter your email"
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="phoneNumber"
                                                name="getPhoneNumber"
                                                label="Phone Number"
                                                type="text"
                                                placeholder="Enter your phone number"
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="cellNumber"
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
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="mailingAddress"
                                                name="getMailingAddress"
                                                label="Mailing Address"
                                                type="text"
                                                placeholder="Enter your mailing address"
                                                disable="!checked"
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="dob"
                                                name="getDOB"
                                                label="Date of Birth"
                                                type="text"
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="ssn"
                                                name="getSSN"
                                                label="SSN"
                                                type="password"
                                                readonly
                                        ></v-text-field>
                                        <v-alert
                                            v-if="successRest"
                                            closable
                                            text="Information successfully updated"
                                            type="success"
                                            @close="successRest = false"
                                        />
                                        <v-alert
                                            v-if="failRest"
                                            closable
                                            text="Information failed to update."
                                            type="error"
                                            @close="failRest = false"
                                        />
                                    </v-form>
                                    <v-card-actions class="d-flex justify-space-between">
                                        <v-btn variant="tonal" color="red" @click="resetFromStore">
                                            Discard Changes
                                        </v-btn>
                                        <v-btn variant="tonal" color="#4097f5" @click="updateCustomerInfo">
                                            Confirm Changes
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-window-item>
                        </v-window>
                    </v-col>
                </v-row>
            </v-container>
        </v-card>
    </div>
</template>

<script>
import {useCustomerStore} from "@/states/UserStore.js";
import axios from "axios";

export default {
    name: "CustomerSettingsPage.vue",
    data: () => {
        const store = useCustomerStore()
        return {
            store: store,
            tab: 0,
            validForm: false,
            id: store.getID,
            oldPassword: "",
            newPassword: "",
            newConfirmPassword: "",
            username: store.getUsername,
            firstName: store.getFirstName,
            lastName: store.getLastName,
            dob: store.getDoB,
            ssn: store.getSSN,
            email: store.getEmail,
            phoneNumber: store.getPhoneNumber,
            cellNumber: store.getCellNumber,
            address: store.getAddress,
            mailingAddress: store.getMailingAddress,
            mismatchedPassAlert: false,
            wrongPassAlert: false,
            successUP: false,
            failUP: false,
            successRest: false,
            failRest: false,
        }
    },
    methods: {
        async loadCustomerInfo() {
            axios.get(`/api/customers/${this.id}`).then(() => {
                this.username = this.response.data.username
                this.password = this.response.data.password
                this.firstName = this.response.data.firstName
                this.lastName = this.response.data.lastName
                this.ssn = this.response.data.ssn
                this.email = this.response.data.email
                this.address = this.response.data.address
                this.mailingAddress = this.response.data.mailingAddress
                this.phoneNumber = this.response.data.phoneNumber
                this.cellNumber = this.response.data.cellPhoneNumber
                this.dob = this.response.data.doB

            }).catch(() => {
                console.log("ERROR: Customer not found.")
            })
        },
        async updateUsernamePassword() {
            if (this.newPassword !== this.newConfirmPassword) {
                this.mismatchedPassAlert = true
                setTimeout(() => {
                    this.mismatchedPassAlert = false
                }, 2000)
            } else if (this.oldPassword !== this.store.getPassword) {
                this.wrongPassAlert = true
                setTimeout(() => {
                    this.wrongPassAlert = false
                }, 2000)
            } else {
                const editedCustomer = {
                    "id": this.id,
                    "username": this.username,
                    "password": this.newPassword,
                    "firstName": this.firstName,
                    "lastName": this.lastName,
                    "ssn": this.ssn,
                    "email": this.email,
                    "address": this.address,
                    "mailingAddress": this.mailingAddress,
                    "phoneNumber": this.phoneNumber,
                    "cellNumber": this.cellNumber,
                    "doB": this.dob,
                }
                axios.put(`/api/customersupdate/${this.store.getID}`, editedCustomer).then(() => {
                    console.log("Customer update successful.")
                    this.successAlert()
                    this.updateStore()
                }).catch(() => {
                    console.log("Error in customer update.")
                    this.failAlert()
                })
            }
        },
        async updateCustomerInfo() {
            const editedCustomer = {
                    "id": this.id,
                    "username": this.username,
                    "password": this.newPassword,
                    "firstName": this.firstName,
                    "lastName": this.lastName,
                    "ssn": this.ssn,
                    "email": this.email,
                    "address": this.address,
                    "mailingAddress": this.mailingAddress,
                    "phoneNumber": this.phoneNumber,
                    "cellNumber": this.cellNumber,
                    "doB": this.dob,
                }
            axios.put(`/api/customersupdate/${this.id}`, editedCustomer).then(() => {
                console.log("Customer update successful.")
                this.updateStore()
                this.successRest = true
                this.successUP = true
            }).catch(() => {
                console.log("Error in customer update.")
                this.failRest = true
                this.failUP = true
            })
        },
        updateStore() {
            const customer = {
                "id": this.id,
                "username": this.username,
                "password": this.newPassword,
                "firstName": this.firstName,
                "lastName": this.lastName,
                "ssn": this.ssn,
                "email": this.email,
                "address": this.address,
                "mailingAddress": this.mailingAddress,
                "phoneNumber": this.phoneNumber,
                "cellNumber": this.cellNumber,
                "doB": this.dob,
            }
            this.store.addCustomer(customer)
        },
        resetFromStore() {
            this.username = this.store.getUsername
            this.password = this.store.getPassword
            this.firstName = this.store.getFirstName
            this.lastName = this.store.getLastName
            this.ssn = this.store.getSSN
            this.email = this.store.getEmail
            this.address = this.store.getAddress
            this.mailingAddress = this.store.getMailingAddress
            this.phoneNumber = this.store.getPhoneNumber
            this.cellNumber = this.store.getCellNumber
            this.dob = this.store.getDoB
        }
    }
}
</script>

<style scoped>

</style>