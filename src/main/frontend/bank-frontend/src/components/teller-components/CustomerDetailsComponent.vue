<template>
    <v-card width="80%">
        <v-card
                width="100%"
                height="10%"
                variant="elevated"
                color="#4097f5"
        >
            <v-card-title
                    class="d-flex justify-space-between"
            >
                <div>Account Information for <b>{{ customer.firstName }} {{ customer.lastName }}</b></div>
                <div>
                    <v-btn
                            v-if="readonly"
                            class="mr-3"
                            variant="outlined"
                            @click="readonly = false"
                    >
                        Edit Customer
                    </v-btn>
                    <v-btn
                            v-if="!readonly"
                            disabled
                            class="mr-3"
                            variant="outlined"
                            @click="readonly = false"
                    >
                        Editing Customer...
                    </v-btn>
                    <v-btn
                            variant="outlined"
                            to="/teller-dash"
                    >
                        Return to Dashboard
                    </v-btn>
                </div>
            </v-card-title>
        </v-card>
        <v-container
                scrollable
                class="overflow-y-auto"
                style="height: calc(100vh - 260px)">
            <v-form>
                <v-text-field
                        v-model="username"
                        name="username"
                        label="Username"
                        type="text"
                        placeholder="Enter updated username"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="password"
                        name="password"
                        label="Password"
                        type="password"
                        placeholder="Enter old password"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-if="false"
                        name="newPassword"
                        label="New Password"
                        type="password"
                        placeholder="Enter new password"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-if="false"
                        name="confirmPassword"
                        label="Confirm New Password"
                        type="password"
                        placeholder="Confirm new password"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="email"
                        name="email"
                        label="Customer Email"
                        type="text"
                        placeholder="Enter updated email"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="phoneNumber"
                        name="phone"
                        label="Phone Number"
                        type="text"
                        placeholder="Enter updated phone number"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="cellNumber"
                        name="cellphone"
                        label="Cell Phone Number"
                        type="text"
                        placeholder="Enter updated cellphone number"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="address"
                        name="address"
                        label="Address"
                        type="text"
                        placeholder="Enter updated address"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="mailingAddress"
                        name="mailingAddress"
                        label="Mailing Address"
                        type="text"
                        placeholder="Enter updated mailing address"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="doB"
                        name="dob"
                        label="Date of Birth"
                        type="text"
                        placeholder="Enter updated date of birth"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="ssn"
                        name="ssn"
                        label="SSN"
                        type="text"
                        placeholder="Enter updated SSN"
                        :readonly="readonly"
                ></v-text-field>
            </v-form>
        </v-container>
        <v-container
                width="100%"
                class="d-flex justify-space-between"
        >
            <v-btn
                    v-if="!readonly"
                    type="button"
                    class="mt-2"
                    color="red"
                    variant="tonal"
                    @click="discardChanges"
            >
                Discard Changes
            </v-btn>
            <v-alert
                v-if="customerEditSuccess"
                closable
                text="Customer information successfully changed."
                type="success"
            />
            <v-alert
                v-if="customerEditFail"
                closable
                text="Error: Empty or Invalid fields."
                type="error"
            />
            <v-btn
                    v-if="!readonly"
                    type="button"
                    class="mt-2"
                    color="primary"
                    variant="tonal"
                    value="log in"
                    @click="updateCustomerInfo"
            >
                Confirm Changes
            </v-btn>
        </v-container>
    </v-card>
</template>

<script>
import {useTellerStore} from "@/states/TellerStore.js";
import axios from 'axios';

export default {
    name: "CustomerDetailsComponent.vue",
    data: () => {
        const store = useTellerStore()
        const customerID = store.getCustomerID
        return {
            store: store,
            customerID: customerID,
            customerEditSuccess: false,
            customerEditFail: false,
            readonly: true,
            customer: {},
            originalCustomer: {},
            id: "",
            username: "",
            password: "",
            firstName: "",
            lastName: "",
            ssn: "",
            email: "",
            address: "",
            mailingAddress: "",
            phoneNumber: "",
            cellNumber: "",
            doB: "",
        }
    },
    methods: {
        async loadCustomerInfo() {
            axios.get(`/api/customers/${this.customerID}`).then((response) => {
                console.log(response.data)
                this.customer = response.data
                this.originalCustomer = response.data
                this.id = this.customer.id
                this.username = this.customer.username
                this.password = this.customer.password
                this.firstName = this.customer.firstName
                this.lastName = this.customer.lastName
                this.ssn = this.customer.ssn
                this.email = this.customer.email
                this.address = this.customer.address
                this.mailingAddress = this.customer.mailingAddress
                this.phoneNumber = this.customer.phoneNumber
                this.cellNumber = this.customer.cellPhoneNumber
                this.doB = this.customer.doB

            }).catch(() => {
                console.log("ERROR: Customer not found.")
            })
        },
        async updateCustomerInfo() {
            const editedCustomer = {
                "id": this.customer.id,
                "username": this.username,
                "password": this.password,
                "firstName": this.firstName,
                "lastName": this.lastName,
                "ssn": this.ssn,
                "email": this.email,
                "address": this.address,
                "mailingAddress": this.mailingAddress,
                "phoneNumber": this.phoneNumber,
                "cellNumber": this.cellNumber,
                "doB": this.doB,
            }
            axios.put(`/api/customersupdate/${this.id}`, editedCustomer).then((response) => {
                this.readonly = true
                console.log("Customer update successful.")
                this.successAlert()
            }).catch((e) => {
                console.log("Error in customer update.")
                this.failAlert()
            })
        },
        discardChanges() {
            this.readonly = true
            this.customer = this.originalCustomer
            this.id = this.customer.id
            this.username = this.customer.username
            this.password = this.customer.password
            this.firstName = this.customer.firstName
            this.lastName = this.customer.lastName
            this.ssn = this.customer.ssn
            this.email = this.customer.email
            this.address = this.customer.address
            this.mailingAddress = this.customer.mailingAddress
            this.phoneNumber = this.customer.phoneNumber
            this.cellNumber = this.customer.cellPhoneNumber
            this.doB = this.customer.doB
        },
        successAlert() {
            this.customerEditSuccess = true
            setTimeout(() => {this.customerEditSuccess = false}, 2000)
            this.loadCustomerInfo()
        },
        failAlert() {
            this.customerEditFail = true
            setTimeout(() => {this.customerEditFail = false}, 3000)
        }
    },
    beforeMount() {
        this.loadCustomerInfo()
    }
}
</script>

<style scoped>

</style>