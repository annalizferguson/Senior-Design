<template>
    <CustomerLogin />
</template>

<script>
import { defineComponent } from 'vue';

// Components
import CustomerLogin from '../components/CustomerLoginComponent.vue';

export default defineComponent({
    name: 'CustomerLoginView',

    components: {
        CustomerLogin,
    },
});
</script>
