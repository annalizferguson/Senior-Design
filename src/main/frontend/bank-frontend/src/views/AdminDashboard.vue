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
                    Administrator Dashboard
                    <div>
                        <v-btn
                                variant="outlined"
                                @click="changeFocus()"
                        >
                            View {{oppositeFocus}}
                        </v-btn>
                    </div>
                </v-card-title>
            </v-card>
            <v-card
                    width="100%"
                    height="85%"
                    variant="outlined"
            >
                <v-card-title
                    class="d-flex"
                >
                    {{searchFocus}} Search
                    <v-btn
                        v-if="isTellerFocus"
                        class="ml-5"
                        color="#4097f5"
                    >
                        Create New Teller
                    </v-btn>
                </v-card-title>
                <v-card-text>hello</v-card-text>
            </v-card>
        </v-card>
    </div>
</template>

<script>
export default {
    name: "AdminDashboard.vue",

    data: () => {
        return {
            searchFocus: "Tellers",
            oppositeFocus: "Customers",
            isTellerFocus: true,
        }
    },

    methods: {
        changeFocus: function() {
            this.isTellerFocus = !this.isTellerFocus
            this.searchFocus = this.isTellerFocus? "Tellers":"Customers"
            this.oppositeFocus = this.isTellerFocus? "Customers":"Tellers"
        },
    }
}
</script>

<style scoped>

</style>