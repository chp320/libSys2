<template>
  <div v-if="visible" class="custom-alert">
    <div class="alert-content">
      <p>{{ message }}</p>
      <button @click="closeAlert">OK</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    message: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      visible: true
    };
  },
  methods: {
    closeAlert() {
      this.visible = false;
    }
  }
};
</script>

<style scoped>
.custom-alert {
  position: fixed;
  top: 20px;
  left: 50%;
  transform: translateX(-50%);
  background-color: #f8d7da;
  color: #721c24;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}

.alert-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

button {
  background-color: #f5c6cb;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
  color: #721c24;
  border-radius: 3px;
}

button:hover {
  background-color: #f1b0b7;
}
</style>
