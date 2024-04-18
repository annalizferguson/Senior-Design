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
          Create New Teller
          <v-btn
              variant="outlined"
              to="/admin-dash"
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
                v-model="firstName"
                name="firstname"
                label="First Name"
                type="text"
                placeholder="Enter first name"
            ></v-text-field>
            <v-text-field
                v-model="lastName"
                name="lastname"
                label="Last Name"
                type="text"
                placeholder="Enter last name"
            ></v-text-field>
            <v-text-field
                v-model="username"
                name="username"
                label="Username"
                type="text"
                placeholder="Enter a username"
            ></v-text-field>
            <v-text-field
                v-model="password"
                name="password"
                label="Password"
                type="password"
                placeholder="Enter a password"
                hint="Passwords must be greater than 8 characters and include a capital letter, a lowercase letter, and a special character."
            ></v-text-field>
            <v-text-field
                v-model="confirmPassword"
                name="confirmPassword"
                label="Confirm Password"
                type="password"
                placeholder="Confirm password"
            ></v-text-field>
            <v-container v-if="showRegisterFail">
              <v-alert
                  closable
                  title="Invalid Registration"
                  text="Teller registration was invalid due to empty fields or not meeting the password requirements."
                  type="error"
                  @click:close="showRegisterFail = false"
              />
            </v-container>
            <v-container v-if="creationSuccess">
              <v-alert
                  closable
                  title="Teller Creation Successful"
                  text="New teller account created successfully."
                  type="success"
              />
            </v-container>
            <v-container
                width="100%"
                class="d-flex justify-end"
            >
              <v-btn
                  type="button"
                  class="mt-2"
                  color="primary"
                  variant="elevated"
                  @click="registerTeller"
              >
                Create New Teller
              </v-btn>
            </v-container>
          </v-form>
        </v-container>
      </v-card>
    </v-card>
  </div>
</template>

<script>
import {useRoute, useRouter} from "vue-router";
import axios from "axios";

export default {
  name: "CreateTellerPage.vue",
  data: () => {
    const router = useRouter()
    return {
      router: router,
      showRegisterFail: false,
      creationSuccess: false,
      validForm: false,
      checked: false,
      username: "",
      password: "",
      confirmPassword: "",
      firstName: "",
      lastName: "",
      isRegister: true,
      errorMessage: "Error",
    }
  },
  methods: {
    async registerTeller() {
      const newTeller = {
        "username": this.username,
        "password": this.password,
        "firstName": this.firstName,
        "lastName": this.lastName
      }
      axios.post('/api/tellers', newTeller).then((response) => {
        console.log(response)
        this.creationSuccess = true
        setTimeout(() => this.router.push('/admin-dash'), 2000)
      }).catch((e) => {
        console.log("Register Invalid.")
        this.showRegisterFail = true
      })
    }
  }

}
</script>

<style scoped>

</style>