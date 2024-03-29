<template>
    <v-app>
        <v-app-bar
                class="d-flex"
                style="background-color: #1565c0; color: #ffffff"
        >
            <v-app-bar-title>
                <router-link to="/" style="text-decoration: none; color: inherit;">Charger United Credit Union
                </router-link>
            </v-app-bar-title>
            <div class="mr-2">
                Routing Number: #123456789
            </div>
            <v-btn
                    to="/"
            >
                Logout
            </v-btn>
        </v-app-bar>
        <v-main>
            <router-view/>
        </v-main>
    </v-app>
</template>

<script>
export default {}
</script>
