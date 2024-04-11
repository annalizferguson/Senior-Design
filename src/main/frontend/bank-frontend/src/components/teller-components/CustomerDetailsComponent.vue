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
                            v-if="readonly"
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
                        v-model="customer.username"
                        name="username"
                        label="Username"
                        type="text"
                        placeholder="Enter updated username"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="customer.password"
                        name="password"
                        label="Password"
                        type="password"
                        placeholder="Enter old password"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-if="!readonly"
                        name="newPassword"
                        label="New Password"
                        type="password"
                        placeholder="Enter new password"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-if="!readonly"
                        name="confirmPassword"
                        label="Confirm New Password"
                        type="password"
                        placeholder="Confirm new password"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="customer.email"
                        name="email"
                        label="Customer Email"
                        type="text"
                        placeholder="Enter updated email"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="customer.phoneNumber"
                        name="phone"
                        label="Phone Number"
                        type="text"
                        placeholder="Enter updated phone number"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="customer.cellNumber"
                        name="cellphone"
                        label="Cell Phone Number"
                        type="text"
                        placeholder="Enter updated cellphone number"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="customer.address"
                        name="address"
                        label="Address"
                        type="text"
                        placeholder="Enter updated address"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="customer.mailingAddress"
                        name="mailingAddress"
                        label="Mailing Address"
                        type="text"
                        placeholder="Enter updated mailing address"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="customer.doB"
                        name="dob"
                        label="Date of Birth"
                        type="text"
                        placeholder="Enter updated date of birth"
                        :readonly="readonly"
                ></v-text-field>
                <v-text-field
                        v-model="customer.ssn"
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
                    type="button"
                    class="mt-2"
                    color="red"
                    variant="tonal"
                    @click="discardChanges"
            >
                Discard Changes
            </v-btn>
            <v-btn
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
            readonly: true,
            customer: {},
            originalCustomer: {}
        }
    },
    methods: {
        async loadCustomerInfo() {
            axios.get(`/api/customers/${this.customerID}`).then((response) => {
                console.log(response.data)
                this.customer = response.data
                this.originalCustomer = response.data
            }).catch(() => {
                console.log("ERROR: Customer not found.")
            })
        },
        async updateCustomerInfo() {
            const editedCustomer = {
                "id": this.customer.id,
                "username": this.customer.username,
                "password": this.customer.password,
                "firstName": this.customer.firstName,
                "lastName": this.customer.lastName,
                "ssn": this.customer.ssn,
                "email": this.customer.email,
                "address": this.customer.address,
                "mailingAddress": this.customer.mailingAddress,
                "phoneNumber": this.customer.phoneNumber,
                "cellNumber": this.customer.cellPhoneNumber,
                "doB": this.customer.doB,
            }
            axios.put("/api/customers", editedCustomer).then((response) => {
                console.log(response.data)
                this.readonly = true
                console.log("Customer update successful.")
            }).catch((e) => {
                console.log("Error in customer update.")
            })
        },
        discardChanges() {
            this.readonly = true
            this.customer = this.originalCustomer
        }
    },
    beforeMount() {
        this.loadCustomerInfo()
    }
}
</script>

<style scoped>

</style>