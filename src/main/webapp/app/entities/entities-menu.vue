<template>
  <div>
    <b-dropdown-item to="/officers">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.officers')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/department">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.department')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/project">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.project')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/projectpbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectpbs')"></span>
    </b-dropdown-item>
    <b-dropdown-item to="/projectwbs">
      <font-awesome-icon icon="asterisk" />
      <span v-text="t$('global.menu.entities.projectwbs')"></span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
