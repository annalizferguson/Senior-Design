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
                                                v-model="newUsername"
                                                name="username"
                                                label="Username"
                                                type="text"
                                                placeholder="Enter a username"
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="newPassword"
                                                name="password"
                                                label="Password"
                                                type="password"
                                                placeholder="Enter a password"
                                        ></v-text-field>
                                        <v-text-field v-if="isRegister"
                                                      v-model="newConfirmPassword"
                                                      name="confirmPassword"
                                                      label="Confirm Password"
                                                      type="password"
                                                      placeholder="Confirm your password"
                                        ></v-text-field>
                                    </v-form>
                                    <v-card-actions class="d-flex justify-end">
                                        <v-btn variant="tonal" color="#4097f5">
                                            Confirm Changes
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-window-item>
                            <v-window-item value="1">
                                <v-card variant="flat">
                                    <v-form v-model="validForm" ref="form">
                                        <v-text-field
                                                v-model="newEmail"
                                                name="getEmail"
                                                label="Email"
                                                type="text"
                                                placeholder="Enter your email"
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="newPhoneNumber"
                                                name="getPhoneNumber"
                                                label="Phone Number"
                                                type="number"
                                                placeholder="Enter your phone number"
                                                hide-spin-buttons
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="newCellPhoneNumber"
                                                name="getCellPhoneNumber"
                                                label="Cell Phone Number"
                                                type="number"
                                                placeholder="Enter your cell phone number"
                                                hide-spin-buttons
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="newAddress"
                                                name="getAddress"
                                                label="Address"
                                                type="text"
                                                placeholder="Enter your home address"
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="newMailingAddress"
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
                                                readonly
                                        ></v-text-field>
                                        <v-text-field
                                                v-model="ssn"
                                                name="getSSN"
                                                label="SSN"
                                                type="password"
                                                readonly
                                        ></v-text-field>
                                    </v-form>
                                    <v-card-actions class="d-flex justify-space-between">
                                        <v-btn variant="tonal" color="#4097f5">
                                            Confirm Changes
                                        </v-btn>
                                        <v-btn variant="tonal" color="red">
                                            Discard Changes
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
import testUser from "@/test-files/testUser.json"

export default {
    name: "CustomerSettingsPage.vue",
    data: () => {
        return {
            tab: 0,
            validForm: false,
            newUsername: "",
            newPassword: "",
            newConfirmPassword: "",
            isRegister: true,
            errorMessage: "Error",
            newAddress: "",
            newMailingAddress: "",
            newEmail: "",
            newPhoneNumber: "",
            newCellPhoneNumber: "",
            dob: testUser.dob,
            ssn: testUser.ssn,
            email: testUser.email,
            phoneNumber: testUser.phone,
            cellPhoneNumber: testUser.cellphone,
            address: testUser.address,
            mailingAddress: testUser.mailingAddress,
        }
    }
}
</script>

<style scoped>

</style>