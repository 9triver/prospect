<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="keyNodeInspection">
        <h2 class="jh-entity-heading" data-cy="keyNodeInspectionDetailsHeading">
          <span v-text="t$('jy1App.keyNodeInspection.detail.title')"></span> {{ keyNodeInspection.id }}
        </h2>
        <dl class="row jh-entity-details last-0">
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.workbagname')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.workbagname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.belongwbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.belongwbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.projectlevel')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.projectlevel }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.iskey')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.iskey }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.isimplementationplan')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.isimplementationplan }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.isqualityplan')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.isqualityplan }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.istechniqueplan')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.istechniqueplan }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.implementationplanstatus')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.implementationplanstatus }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.isimplementationplanmaterial')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.isimplementationplanmaterial }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.technologyplanstatus')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.technologyplanstatus }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.istechnologymaterial')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.istechnologymaterial }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.firstcheckstatus')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.firstcheckstatus }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.isfirstcheckmaterial')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.isfirstcheckmaterial }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.productioncheckstatus')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.productioncheckstatus }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.isproductioncheckmaterial')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.isproductioncheckmaterial }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.keyNodeInspection.status')"></span>
          </dt>
          <dd class="field">
            <span>{{ keyNodeInspection.status }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="keyNodeInspection.id"
          :to="{ name: 'KeyNodeInspectionEdit', params: { keyNodeInspectionId: keyNodeInspection.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./key-node-inspection-details.component.ts"></script>
