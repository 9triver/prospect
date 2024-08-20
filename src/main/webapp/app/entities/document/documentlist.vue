<template>
  <div>
    <h2>File List</h2>
    <ul>
      <li v-for="file in files" :key="file">
        <a :href="`/api/files/${file}`" download>{{ file }}</a>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      files: []
    };
  },
  created() {
    this.fetchFileList();
  },
  methods: {
    fetchFileList() {
      axios.get('/api/files')
        .then(response => {
          this.files = response.data.map(file => file.toString());
        })
        .catch(error => {
          console.error('Error fetching file list:', error);
        });
    }
  }
};
</script>
