<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="communicationRecord">
        <h2 class="jh-entity-heading" data-cy="communicationRecordDetailsHeading">
          <span v-text="t$('jy1App.communicationRecord.detail.title')"></span> {{ communicationRecord.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.communicationRecord.wbsid')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationRecord.wbsid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationRecord.wbsname')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationRecord.wbsname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationRecord.workbagid')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationRecord.workbagid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationRecord.associationmeetingname')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationRecord.associationmeetingname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationRecord.communicationtime')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationRecord.communicationtime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationRecord.communicationlocation')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationRecord.communicationlocation }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationRecord.communicationcontent')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationRecord.communicationcontent }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationRecord.auditorid')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationRecord.auditorid }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationRecord.auditorname')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationRecord.auditorname }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.communicationRecord.remarks')"></span>
          </dt>
          <dd class="field">
            <span>{{ communicationRecord.remarks }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.communicationRecord.projectwbs')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="communicationRecord.projectwbs">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: communicationRecord.projectwbs.id } }">{{
                communicationRecord.projectwbs.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.communicationRecord.workbag')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="communicationRecord.workbag">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: communicationRecord.workbag.id } }">{{
                communicationRecord.workbag.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.communicationRecord.communication')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="communicationRecord.communication">
              <router-link
                :to="{ name: 'CommunicationDictionaryView', params: { communicationDictionaryId: communicationRecord.communication.id } }"
                >{{ communicationRecord.communication.id }}</router-link
              >
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.communicationRecord.workCommunicationForm')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="communicationRecord.workCommunicationForm">
              <router-link
                :to="{
                  name: 'CommunicationFormDictionaryView',
                  params: { communicationFormDictionaryId: communicationRecord.workCommunicationForm.id },
                }"
                >{{ communicationRecord.workCommunicationForm.id }}</router-link
              >
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="communicationRecord.id"
          :to="{ name: 'CommunicationRecordEdit', params: { communicationRecordId: communicationRecord.id } }"
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

<script lang="ts" src="./communication-record-details.component.ts"></script>
