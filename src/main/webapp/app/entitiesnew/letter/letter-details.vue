<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="letter">
        <h2 class="jh-entity-heading" data-cy="letterDetailsHeading">
          <span v-text="t$('jy1App.letter.detail.title')"></span> {{ letter.id }}
        </h2>
        <dl class="row jh-entity-details last-2">
          <dt class="field">
            <span v-text="t$('jy1App.letter.lettername')"></span>
          </dt>
          <dd class="field">
            <span>{{ letter.lettername }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.letter.letternumber')"></span>
          </dt>
          <dd class="field">
            <span>{{ letter.letternumber }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.letter.lettertype')"></span>
          </dt>
          <dd class="field">
            <span>{{ letter.lettertype }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.letter.secretlevel')"></span>
          </dt>
          <dd class="field">
            <span v-text="t$('jy1App.Secretlevel.' + letter.secretlevel)"></span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.letter.lettercontent')"></span>
          </dt>
          <dd class="field">
            <span>{{ letter.lettercontent }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.letter.letterstatus')"></span>
          </dt>
          <dd class="field">
            <span>{{ letter.letterstatus }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.letter.lettertime')"></span>
          </dt>
          <dd class="field">
            <span>{{ letter.lettertime }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.letter.previousfile')"></span>
          </dt>
          <dd class="field">
            <span>{{ letter.previousfile }}</span>
          </dd>
          <dt class="field">
            <span v-text="t$('jy1App.letter.datarecordstatus')"></span>
          </dt>
          <dd class="field">
            <span>{{ letter.datarecordstatus }}</span>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.letter.wbsid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="letter.wbsid">
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: letter.wbsid.id } }">{{ letter.wbsid.id }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.letter.workbagid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="letter.workbagid">
              <router-link :to="{ name: 'WorkbagView', params: { workbagId: letter.workbagid.id } }">{{ letter.workbagid.id }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.letter.frontlineid')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="letter.frontlineid">
              <router-link :to="{ name: 'FrontlineView', params: { frontlineId: letter.frontlineid.id } }">{{
                letter.frontlineid.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.letter.receivingunit')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="letter.receivingunit">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: letter.receivingunit.id } }">{{
                letter.receivingunit.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.letter.sendingunit')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="letter.sendingunit">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: letter.sendingunit.id } }">{{
                letter.sendingunit.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.letter.lettermaker')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="letter.lettermaker">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: letter.lettermaker.id } }">{{
                letter.lettermaker.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.letter.letterreceiver')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="letter.letterreceiver">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: letter.letterreceiver.id } }">{{
                letter.letterreceiver.id
              }}</router-link>
            </div>
          </dd>
          <dt class="relationship">
            <span v-text="t$('jy1App.letter.letterhandler')"></span>
          </dt>
          <dd class="relationship">
            <div v-if="letter.letterhandler">
              <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: letter.letterhandler.id } }">{{
                letter.letterhandler.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="letter.id" :to="{ name: 'LetterEdit', params: { letterId: letter.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./letter-details.component.ts"></script>
