<template>
    <CustomerLogin />
</template>

<script>
import { defineComponent } from 'vue';

// Components
import CustomerLogin from '../../components/customer-components/CustomerLoginComponent.vue';

export default defineComponent({
    name: 'CustomerLoginView',

    components: {
        CustomerLogin,
    },
});
</script>
