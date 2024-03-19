<template>
  <RegisterComponent />
</template>

<script>
import { defineComponent } from 'vue';

// Components
import RegisterComponent from '../components/customer-components/RegisterComponent.vue';

export default defineComponent({
  name: 'RegisterView',

  components: {
    RegisterComponent,
  },
});
</script>
