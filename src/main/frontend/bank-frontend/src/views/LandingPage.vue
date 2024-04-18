<template>
  <div class="d-flex flex-column align-center" style="height: calc(100vh - 100px)">
    <img
        src="../assets/bank_logo.png"
        alt="Company Logo"
        class="logo-top-middle"
    />
    <v-card
        class="d-flex flex-wrap justify-center align-center"
        height="40%"
        width="70%"
        variant="flat"
    >
      <v-btn
          width="100%"
          height="35%"
          color="#5ea4d6"
          to="/customer-login"
      >
        I am a Customer
      </v-btn>
      <div>OR</div>
      <v-btn
          width="100%"
          height="35%"
          color="#5ea4d6"
          to="/teller-login"
      >
        I am an Employee
      </v-btn>
    </v-card>
  </div>
</template>

<script>
import { useCustomerStore } from "@/states/UserStore.js";
import { useTellerStore } from "@/states/TellerStore.js";
export default {
  name: "LandingPage",
  data: () => ({
    customerStore: useCustomerStore(),
    tellerStore: useTellerStore()
  }),
  beforeMount() {
    this.customerStore.$reset();
    this.tellerStore.$reset();
  }
}
</script>

<style scoped>
.logo-top-middle {
  max-width: 300px;
  max-height: 3000px;
}
</style>