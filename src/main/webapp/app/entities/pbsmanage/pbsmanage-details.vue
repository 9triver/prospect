<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="pbsmanage">
        <h2 class="jh-entity-heading" data-cy="pbsmanageDetailsHeading">
          <span v-text="t$('jHipster0App.pbsmanage.detail.title')"></span> {{ pbsmanage.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.pbsname')"></span>
          </dt>
          <dd>
            <span>{{ pbsmanage.pbsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.number')"></span>
          </dt>
          <dd>
            <span>{{ pbsmanage.number }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.type')"></span>
          </dt>
          <dd>
            <span>{{ pbsmanage.type }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.starttime')"></span>
          </dt>
          <dd>
            <span>{{ pbsmanage.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.endtime')"></span>
          </dt>
          <dd>
            <span>{{ pbsmanage.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.administratorid')"></span>
          </dt>
          <dd>
            <span>{{ pbsmanage.administratorid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.administratorname')"></span>
          </dt>
          <dd>
            <span>{{ pbsmanage.administratorname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.responsiblename')"></span>
          </dt>
          <dd>
            <span>{{ pbsmanage.responsiblename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.department')"></span>
          </dt>
          <dd>
            <span>{{ pbsmanage.department }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.Secretlevel.' + pbsmanage.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster0App.AuditStatus.' + pbsmanage.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.auditUserid')"></span>
          </dt>
          <dd>
            <span>{{ pbsmanage.auditUserid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.pbssubmanage')"></span>
          </dt>
          <dd>
            <div v-if="pbsmanage.pbssubmanage">
              <router-link :to="{ name: 'PbssubmanageView', params: { pbssubmanageId: pbsmanage.pbssubmanage.id } }">{{
                pbsmanage.pbssubmanage.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="pbsmanage.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: pbsmanage.responsibleid.id } }">{{
                pbsmanage.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.pbsmanage.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="pbsmanage.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: pbsmanage.auditorid.id } }">{{
                pbsmanage.auditorid.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="pbsmanage.id"
          :to="{ name: 'PbsmanageEdit', params: { pbsmanageId: pbsmanage.id } }"
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

<script lang="ts" src="./pbsmanage-details.component.ts"></script>
