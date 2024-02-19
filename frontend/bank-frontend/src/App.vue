<template>
    <v-app>
        <v-app-bar
          class="d-flex"
        >
            <v-app-bar-title>
               Fake Bank Name
            </v-app-bar-title>
        </v-app-bar>
        <v-main>
            <router-view/>
        </v-main>
    </v-app>
</template>

<script>

export default {
    name: 'App',

    data: () => ({
        //
    }),
}
</script>
