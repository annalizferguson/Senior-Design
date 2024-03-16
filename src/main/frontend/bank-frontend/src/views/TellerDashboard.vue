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
                <v-card-title
                        class="d-flex justify-space-between"
                >
                    Your Dashboard
                    <div>
                        <v-btn
                            variant="outlined"
                        >
                            Create New Customer
                        </v-btn>
                    </div>
                </v-card-title>
            </v-card>
            <v-card
                    class="mr-6"
                    width="48%"
                    height="85%"
                    variant="outlined"
            >
                <v-card-title>Customer Search</v-card-title>
                <v-card-text>hello</v-card-text>
            </v-card>
            <v-card
                    width="50%"
                    height="85%"
                    variant="outlined"
            >
                <v-card-title>Alerts</v-card-title>
                <v-card-text>hello</v-card-text>
            </v-card>
        </v-card>
    </div>
</template>

<script>
export default {
    name: "TellerDashboard.vue"
}
</script>

<style scoped>

</style>