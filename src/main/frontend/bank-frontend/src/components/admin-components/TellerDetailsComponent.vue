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
        <div>Account Information for <b>{{ teller.firstName }} {{ teller.lastName }}</b></div>
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
              v-if="!readonly"
              disabled
              class="mr-3"
              variant="outlined"
              @click="readonly = false"
          >
            Editing Teller...
          </v-btn>
          <v-btn
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
            v-model="firstName"
            name="getFirstName"
            label="First Name"
            type="text"
            placeholder="Enter first name"
            :readonly="readonly"
        ></v-text-field>
        <v-text-field
            v-model="lastName"
            name="getLastName"
            label="Last Name"
            type="text"
            placeholder="Enter last name"
            :readonly="readonly"
        ></v-text-field>
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
            placeholder="Enter old password"
            hint="Passwords must be greater than 8 characters and include a capital letter, a lowercase letter, and a special character."
            :type="showPassword"
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
          v-if="tellerEditSuccess"
          closable
          text="Teller information successfully changed."
          type="success"
      />
      <v-alert
          v-if="tellerEditFail"
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

export default {
  name: "TellerDetailsComponent.vue",
  data: () => {
    const store = useAdminStore()
    const tellerID = store.getTellerID
    return {
      store: store,
      tellerID: tellerID,
      tellerEditSuccess: false,
      tellerEditFail: false,
      readonly: true,
      teller: {},
      originalTeller: {},
      id: "",
      username: "",
      password: "",
      firstName: "",
      lastName: "",
      showPassword: "password",
    }
  },
  methods: {
    async loadTellerInfo() {
      axios.get(`/api/tellers/${this.tellerID}`).then((response) => {
        console.log(response.data)
        this.teller = response.data
        this.originalTeller = response.data
        this.id = this.teller.id
        this.username = this.teller.username
        this.password = this.teller.password
        this.firstName = this.teller.firstName
        this.lastName = this.teller.lastName
      }).catch(() => {
        console.log("ERROR: Teller not found.")
      })
    },
    async updateTellerInfo() {
      const editedTeller = {
        "id": this.teller.id,
        "username": this.username,
        "password": this.password,
        "firstName": this.firstName,
        "lastName": this.lastName,
      }
      axios.put(`/api/tellersupdate/${this.id}`, editedTeller).then(() => {
        this.readonly = true
        console.log("Teller update successful.")
        this.successAlert()
      }).catch((e) => {
        console.log("Error in teller update.")
        console.log(e)
        this.failAlert()
      })
    },
    discardChanges() {
      this.readonly = true
      this.teller = this.originalTeller
      this.id = this.teller.id
      this.username = this.teller.username
      this.password = this.teller.password
      this.firstName = this.teller.firstName
      this.lastName = this.teller.lastName
    },
    successAlert() {
      this.tellerEditSuccess = true
      setTimeout(() => {
        this.tellerEditSuccess = false
      }, 2000)
      this.loadTellerInfo()
    },
    failAlert() {
      this.tellerEditFail = true
      setTimeout(() => {
        this.tellerEditFail = false
      }, 3000)
    }
  },
  beforeMount() {
    this.loadTellerInfo()
  }
}
</script>

<style scoped>

</style>