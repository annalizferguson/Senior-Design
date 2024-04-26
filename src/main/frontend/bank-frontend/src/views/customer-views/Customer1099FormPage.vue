<template>
    <v-container>
        <v-select
            v-model="chosenYear"
            :items="years"
        />
    </v-container>
    <v-container v-if="chosenYear">
        <INTForm :year="chosenYear" :key="chosenYear" />
    </v-container>
</template>

<script>
import INTForm from "@/components/customer-components/INTForm.vue";
export default {
    name: "Customer1099FormPage.vue",
    components: {INTForm},
    data: () => {
        return {
            years: ["2020", "2021", "2022", "2023", "2024"],
            chosenYear: "",
        }
    }
}
</script>

<style scoped>

</style>