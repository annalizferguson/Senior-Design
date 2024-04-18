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
        <div>Account Information for <b>{{teller.firstName}} {{teller.lastName}}</b></div>
        <div>
          <v-btn
              v-if="readonly"
              class="mr-3"
              variant="outlined"
              @click="readonly = false"
          >
            Edit Teller
          </v-btn>
          <v-btn
              v-if="readonly"
              variant="outlined"
              to="/admin-dash"
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
            v-model="teller.username"
            name="username"
            label="Username"
            type="text"
            placeholder="Enter updated username"
            :readonly="readonly"
          ></v-text-field>
          <v-text-field
            v-model="teller.password"
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
            v-model="teller.firstName"
            name="firstName"
            label="First Name"
            type="text"
            placeholder="Enter updated first name"
            :readonly="readonly"
          ></v-text-field>
          <v-text-field
              v-model="teller.lastName"
              name="lastName"
              label="Last Name"
              type="text"
              placeholder="Enter updated last name"
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
            @click="updateTellerInfo"
        >
          Confirm Changes
        </v-btn>
      </v-container>
  </v-card>
</template>
<script>
import {useAdminStore} from "@/states/AdminStore.js";
import axios from 'axios';

export default{
  name: "TellerDetailsComponent.vue",
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
    async updateTellerInfo(){
      const editedTeller = {
        "id": this.teller.id,
        "username": this.teller.username,
        "password": this.teller.password,
        "firstName": this.teller.firstName,
        "lastName": this.teller.lastName
      }
      axios.put("/api/tellers", editedTeller).then((response) => {
        console.log(response.data)
        this.readonly = true
        console.log("Teller update successful.")
      }).catch((e) => {
        console.log("Error in teller update.")
      })
    },
    discardChanges(){
      this.readonly = true
      this.teller = this.originalTeller
    }
  },
  beforeMount(){
    this.loadTellerInfo()
  }
}
</script>

<style scoped>

</style>