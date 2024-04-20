<template>
    <div
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 100px)">
        <v-card
                width="1250"
                style="height:calc(100vh - 200px)"
                variant="flat"
        >
            <v-card
                    width="100%"
                    height="10%"
                    variant="elevated"
                    color="#4097f5"
            >
                <v-card-title class="d-flex justify-space-between">
                    Delete Teller Account
                  <v-btn
                      class=""
                      variant="outlined"
                      to="/admin-dash"
                  >
                    Return to Dashboard
                  </v-btn>
                </v-card-title>
            </v-card>
            <v-container
                    width="100%"
                    class="d-flex justify-center"
                    style="font-size: 26px;"
            >
                <v-container
                  class="d-flex align-center"
                >
                <u><b>Are you sure you want to delete the teller account for {{ teller.firstName }}
                    {{ teller.lastName }}?</b></u>
                </v-container>
                <v-container>
                    <v-btn
                            color="primary"
                            class="mr-5"
                            style="margin-top: 20px;"
                            size="x-large"
                            @click="confirmDelete"
                    >
                        Yes
                    </v-btn>
                    <v-btn
                            variant="outlined"
                            color="primary"
                            class="justify-center"
                            style="margin-top: 20px;"
                            size="x-large"
                            to="/admin-dash"
                    >
                        No
                    </v-btn>
                </v-container>
            </v-container>
            <v-alert
                    v-if="success"
                    closable
                    title="Teller Deleted Successfully"
                    type="success"
            >
                <div>
                  <v-btn
                    variant="outlined"
                    to="/admin-dash"
                  >
                    Return to Dashboard
                  </v-btn>
                </div>
            </v-alert>
            <v-alert
                    v-if="fail"
                    closable
                    text="Failed to Delete Teller"
                    type="error"
            />
        </v-card>
    </div>
</template>
<script>
import {useAdminStore} from "@/states/AdminStore.js";
import axios from 'axios';
import TellerSearchDashboardComponent from "@/components/admin-components/TellerSearchDashboardComponent.vue";
import CustomerSearchDashboardComponent from "@/components/teller-components/CustomerSearchDashboardComponent.vue";

export default {
    name: "TellerDeleteComponent.vue",
    components: {CustomerSearchDashboardComponent, TellerSearchDashboardComponent},
    data: () => {
        const store = useAdminStore()
        const tellerID = store.getTellerID
        return {
            store: store,
            tellerID: tellerID,
            readonly: true,
            teller: {},
            originalTeller: {},
            success: false,
            fail: false
        }
    },
    methods: {
        async loadTellerInfo() {
            axios.get(`/api/tellers/${this.tellerID}`).then((response) => {
                console.log(response.data)
                this.teller = response.data
                this.originalTeller = response.data
            }).catch(() => {
                console.log("ERROR: Teller not found.")
            })
        },
        async confirmDelete() {
            axios.delete(`/api/tellers/${this.tellerID}`).then((response) => {
                console.log(response)
                this.successDelete()
            }).catch((e) => {
                console.log(e)
                console.log("Error deleting teller.")
                this.failDelete()
            })
        },
        successDelete() {
            this.success = true
            setTimeout(() => this.route.push('/admin-dash'), 2000)
        },
        failDelete() {
            this.fail = true
            setTimeout(() => {
                this.fail = false
            }, 5000)
        }
    },
    beforeMount() {
        this.loadTellerInfo()
    }
}
</script>

<style scoped>

</style>