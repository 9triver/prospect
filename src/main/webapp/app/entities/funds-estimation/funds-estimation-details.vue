<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="fundsEstimation">
        <h2 class="jh-entity-heading" data-cy="fundsEstimationDetailsHeading">
          <span v-text="t$('jy1App.fundsEstimation.detail.title')"></span> {{ fundsEstimation.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.name')"></span>
          </dt>
          <dd>
            <span>{{ fundsEstimation.name }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.source')"></span>
          </dt>
          <dd>
            <span>{{ fundsEstimation.source }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.unit')"></span>
          </dt>
          <dd>
            <span>{{ fundsEstimation.unit }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.number')"></span>
          </dt>
          <dd>
            <span>{{ fundsEstimation.number }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.unitprice')"></span>
          </dt>
          <dd>
            <span>{{ fundsEstimation.unitprice }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.materialfee')"></span>
          </dt>
          <dd>
            <span>{{ fundsEstimation.materialfee }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.specialfee')"></span>
          </dt>
          <dd>
            <span>{{ fundsEstimation.specialfee }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.outsourcingfee')"></span>
          </dt>
          <dd>
            <span>{{ fundsEstimation.outsourcingfee }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.totalbudgetprice')"></span>
          </dt>
          <dd>
            <span>{{ fundsEstimation.totalbudgetprice }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.responsibleperson')"></span>
          </dt>
          <dd>
            <div v-if="fundsEstimation.responsibleperson">
              <router-link :to="{ name: 'OfficersView', params: { officersId: fundsEstimation.responsibleperson.id } }">{{
                fundsEstimation.responsibleperson.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="fundsEstimation.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: fundsEstimation.auditorid.id } }">{{
                fundsEstimation.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jy1App.fundsEstimation.projectwbs')"></span>
          </dt>
          <dd>
            <span v-for="(projectwbs, i) in fundsEstimation.projectwbs" :key="projectwbs.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{ projectwbs.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="fundsEstimation.id"
          :to="{ name: 'FundsEstimationEdit', params: { fundsEstimationId: fundsEstimation.id } }"
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

<script lang="ts" src="./funds-estimation-details.component.ts"></script>
