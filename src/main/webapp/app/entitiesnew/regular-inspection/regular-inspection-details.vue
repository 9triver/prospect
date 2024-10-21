<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="regularInspection">
        <h2 class="jh-entity-heading" data-cy="regularInspectionDetailsHeading">
          <span v-text="t$('jy1App.regularInspection.detail.title')"></span> {{ regularInspection.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.name')"></span>
          </dt>
          <dd class="field">
            <span>{{ regularInspection.name }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ regularInspection.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.workbagname')"></span>
          </dt>
          <dd class="field">
            <span>{{ regularInspection.workbagname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.type')"></span>
          </dt>
          <dd class="field">
            <span>{{ regularInspection.type }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + regularInspection.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.standard')"></span>
          </dt>
          <dd class="field">
            <span>{{ regularInspection.standard }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.measurementmethod')"></span>
          </dt>
          <dd class="field">
            <span>{{ regularInspection.measurementmethod }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.checkresult')"></span>
          </dt>
          <dd class="field">
            <span>{{ regularInspection.checkresult }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.checktarget')"></span>
          </dt>
          <dd class="field">
            <span>{{ regularInspection.checktarget }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.checktime')"></span>
          </dt>
          <dd class="field">
            <span>{{ regularInspection.checktime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.checkcompletion')"></span>
          </dt>
          <dd class="field">
            <span>{{ regularInspection.checkcompletion }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.checkstatus')"></span>
          </dt>
          <dd class="field">
            <span>{{ regularInspection.checkstatus }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.regularInspection.auditStatus')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.AuditStatus.' + regularInspection.auditStatus)"></span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.regularInspection.responsibleperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="regularInspection.responsibleperson">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: regularInspection.responsibleperson.id } }">{{
                regularInspection.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.regularInspection.designer')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="regularInspection.designer">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: regularInspection.designer.id } }">{{
                regularInspection.designer.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.regularInspection.checkperson')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="regularInspection.checkperson">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: regularInspection.checkperson.id } }">{{
                regularInspection.checkperson.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.regularInspection.workbag')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="regularInspection.workbag">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: regularInspection.workbag.id } }">{{
                regularInspection.workbag.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="regularInspection.id"
          :to="{ name: 'RegularInspectionEdit', params: { regularInspectionId: regularInspection.id } }"
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

<script lang="ts" src="./regular-inspection-details.component.ts"></script>
