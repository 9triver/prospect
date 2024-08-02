<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="outsourcingContractual">
        <h2 class="jh-entity-heading" data-cy="outsourcingContractualDetailsHeading">
          <span v-text="t$('jy1App.outsourcingContractual.detail.title')"></span> {{ outsourcingContractual.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.department')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingContractual.department }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.year')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingContractual.year }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.starttime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingContractual.starttime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.endtime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingContractual.endtime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.status')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingContractual.status }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.secretlevel')"></span>
          </dt>
          <dd>
            <span v-text="t$('jy1App.Secretlevel.' + outsourcingContractual.secretlevel)"></span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.foreigncurrency')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingContractual.foreigncurrency }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.totalbudget')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingContractual.totalbudget }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.fundsinplace')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingContractual.fundsinplace }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.responsibleunitname')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingContractual.responsibleunitname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.audittime')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingContractual.audittime }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.accountbank')"></span>
          </dt>
          <dd>
            <span>{{ outsourcingContractual.accountbank }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.creatorid')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingContractual.creatorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingContractual.creatorid.id } }">{{
                outsourcingContractual.creatorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="outsourcingContractual.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: outsourcingContractual.auditorid.id } }">{{
                outsourcingContractual.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.outsourcingContractual.projectwbs')"></span>
          </dt>
          <dd>
            <span v-for="(projectwbs, i) in outsourcingContractual.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="outsourcingContractual.id"
          :to="{ name: 'OutsourcingContractualEdit', params: { outsourcingContractualId: outsourcingContractual.id } }"
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

<script lang="ts" src="./outsourcing-contractual-details.component.ts"></script>
