<template>
    <v-card height="10%" class="mb-4" style="background-color: #1565c0; color: white">
        <v-card-title class="d-flex justify-space-between">
            Form 1099-INT for Calendar Year {{ year }}
        </v-card-title>
    </v-card>
    <v-card v-if="formLoaded">
        <v-container>
            <v-text-field
                    v-model="info1099.payerName"
                    name="Payer Name"
                    label="Payer Name"
                    type="text"
                    variant="underlined"
                    readonly
            />
            <v-text-field
                    v-model="info1099.payerAddress"
                    name="Payer Address"
                    label="Payer Address"
                    type="text"
                    variant="underlined"
                    readonly
            />
            <v-row no-gutters>
                <v-text-field
                        v-model="info1099.payerTin"
                        name="Payer TIN"
                        label="Payer TIN"
                        type="text"
                        variant="underlined"
                        readonly
                />
                <v-text-field
                        v-model="info1099.recipientTin"
                        name="Recipient TIN"
                        label="Recipient TIN"
                        type="text"
                        variant="underlined"
                        readonly
                />
            </v-row>
            <v-text-field
                    v-model="info1099.recipientName"
                    name="Recipient Name"
                    label="Recipient Name"
                    type="text"
                    variant="underlined"
                    readonly
            />
            <v-text-field
                    v-model="info1099.recipientAddress"
                    name="Recipient Address"
                    label="Recipient Address"
                    type="text"
                    variant="underlined"
                    readonly
            />
            <v-text-field
                    v-model="info1099.interestIncome"
                    name="Interest income"
                    label="Interest income"
                    type="text"
                    variant="underlined"
                    readonly
            >$
            </v-text-field>
            <v-text-field
                    v-model="info1099.earlyWithdrawalPenalty"
                    name="Early withdrawal penalty"
                    label="Early withdrawal penalty"
                    type="text"
                    variant="underlined"
                    readonly
            >$
            </v-text-field>
            <v-text-field
                    v-model="info1099.bondTreasuryInterest"
                    name="Bond Treasury Interest"
                    label="Interest on U.S. Savings Bonds and Treasury Obligations"
                    type="text"
                    variant="underlined"
                    readonly
            >$
            </v-text-field>
            <v-row no-gutters>
                <v-text-field
                        v-model="info1099.federalWithheld"
                        name="federalWithheld"
                        label="Federal income tax withheld"
                        type="text"
                        variant="underlined"
                        readonly
                >$
                </v-text-field>
                <v-text-field
                        v-model="info1099.investmentExpenses"
                        name="investmentExpenses"
                        label="Investment expenses"
                        type="text"
                        variant="underlined"
                        readonly
                >$
                </v-text-field>
            </v-row>
            <v-row no-gutters>
                <v-text-field
                        v-model="info1099.foreignTax"
                        name="foreignTax"
                        label="Foreign tax paid"
                        type="text"
                        variant="underlined"
                        readonly
                >$
                </v-text-field>
                <v-text-field
                        v-model="info1099.foreignPossession"
                        name="foreignPossession"
                        label="Foreign country or U.S. possession"
                        type="text"
                        variant="underlined"
                        readonly
                >$
                </v-text-field>
            </v-row>
            <v-row no-gutters>
                <v-text-field
                        v-model="info1099.taxExemptInterest"
                        name="taxExemptInterest"
                        label="Tax-exempt interest"
                        type="text"
                        variant="underlined"
                        readonly
                >$
                </v-text-field>
                <v-text-field
                        v-model="info1099.privateInterest"
                        name="privateInterest"
                        label="Specified private activity bond interest"
                        type="text"
                        variant="underlined"
                        readonly
                >$
                </v-text-field>
            </v-row>
            <v-row no-gutters>
                <v-text-field
                        v-model="info1099.marketDiscount"
                        name="marketDiscount"
                        label="Market Discount"
                        type="text"
                        variant="underlined"
                        readonly
                >$
                </v-text-field>
                <v-text-field
                        v-model="info1099.bondPremium"
                        name="bondPremium"
                        label="Bond premium"
                        type="text"
                        variant="underlined"
                        readonly
                >$
                </v-text-field>
            </v-row>
            <v-row no-gutters>
                <v-text-field
                        v-model="info1099.treasuryObligations"
                        name="treasuryObligations"
                        label="Bond premium on Treasury obligations"
                        type="text"
                        variant="underlined"
                        readonly
                >$
                </v-text-field>
                <v-text-field
                        v-model="info1099.taxExemptPremium"
                        name="Bond premium or tax-exempt bond"
                        label="Interest on U.S. Savings Bonds and Treasury Obligations"
                        type="text"
                        variant="underlined"
                        readonly
                >$
                </v-text-field>
            </v-row>
            <v-row no-gutters>
                <v-text-field
                        v-if="info1099.cusip !== ''"
                        v-model="info1099.cusip"
                        name="cusip"
                        label="Tax-exempt and tax credit bond CUSIP no."
                        type="text"
                        variant="underlined"
                        readonly
                ></v-text-field>
                <v-text-field
                        v-model="info1099.state"
                        name="State"
                        label="State"
                        type="text"
                        variant="underlined"
                        readonly
                ></v-text-field>
                <v-text-field
                        v-model="info1099.stateID"
                        name="Bond Treasury Interest"
                        label="State identification no."
                        type="text"
                        variant="underlined"
                        readonly
                ></v-text-field>
            </v-row>
            <v-text-field
                    v-model="info1099.stateWithheld"
                    name="stateWithHeld"
                    label="State tax withheld"
                    type="text"
                    variant="underlined"
                    readonly
            >$
            </v-text-field>
        </v-container>
    </v-card>
</template>

<script>
import TransfersComponent from "@/components/customer-components/TransfersComponent.vue";
import {useCustomerStore} from "@/states/UserStore.js";
import {useTellerStore} from "@/states/TellerStore.js";
import axios from "axios";

export default {
    name: "1099INTForm.vue",
    components: {TransfersComponent},
    props: {
        year: {
            type: String
        }
    },
    data: () => {
        const customerStore = useCustomerStore()
        const tellerStore = useTellerStore()
        let id;
        if (tellerStore.getCustomerID) {
            id = tellerStore.getCustomerID
        } else {
            id = customerStore.getID
        }
        return {
            customerStore: customerStore,
            customerID: id,
            info1099: {},
            formLoaded: false,
        }
    },
    methods: {
        async loadINT() {
            await axios.post(`/api/taxform/${this.customerID}/${this.year}`).then((response) => {
                console.log("1099 INT loaded successfully")
                this.info1099 = response.data
                this.formLoaded = true
            }).catch(() => {
                console.log("1099 failed to load.")
            })
        }
    },
    beforeMount() {
        this.loadINT()
    }
}
</script>

<style scoped>

</style>