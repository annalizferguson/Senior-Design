<template>
  <div
      class="d-flex justify-center align-center"
      style="height: calc(100vh - 100px)">
    <v-card
        class="d-flex flex-wrap mb-1"
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
        <v-card-title>
          Delete Teller Account
        </v-card-title>
      </v-card>
      <v-card-text
          style="font-size: 26px;"
      >
        <u><b>Are you sure you want to delete the teller account for {{teller.firstName}} {{teller.lastName}}?</b></u>
        <div>
          <v-btn
              color="primary"
              class="mr-5"
              style="margin-top: 20px;"
              size="x-large"
              @click="confirmDelete"
              to="/admin-dash"
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
        </div>
      </v-card-text>
    </v-card>
  </div>
</template>
<script>
import {useAdminStore} from "@/states/AdminStore.js";
import axios from 'axios';
import TellerSearchDashboardComponent from "@/components/admin-components/TellerSearchDashboardComponent.vue";
import CustomerSearchDashboardComponent from "@/components/teller-components/CustomerSearchDashboardComponent.vue";

export default{
  name: "TellerDeleteComponent.vue",
  components: {CustomerSearchDashboardComponent, TellerSearchDashboardComponent},
  data: () => {
    const store = useAdminStore()
    const tellerID = store.getTellerID
    return{
      store: store,
      tellerID: tellerID,
      readonly: true,
      teller: {},
      originalTeller: {}
    }
  },
  methods: {
    async loadTellerInfo(){
      axios.get(`/api/tellers/${this.tellerID}`).then((response) => {
        console.log(response.data)
        this.teller = response.data
        this.originalTeller = response.data
      }).catch(() => {
        console.log("ERROR: Teller not found.")
      })
    },
    confirmDelete(){
      axios.delete(`/api/tellers/${this.tellerID}`).then((response) => {
        console.log(response)
      })
    }
  },
  beforeMount(){
    this.loadTellerInfo()
  }
}
</script>

<style scoped>

</style>