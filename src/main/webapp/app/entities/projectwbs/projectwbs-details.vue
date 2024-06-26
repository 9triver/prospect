<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="projectwbs">
        <h2 class="jh-entity-heading" data-cy="projectwbsDetailsHeading">
          <span v-text="t$('jHipster0App.projectwbs.detail.title')"></span> {{ projectwbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.projectwbsname')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.projectwbsname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.wbsspare1')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.wbsspare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.wbsspare2')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.wbsspare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.wbsspare3')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.wbsspare3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.wbsspare4')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.wbsspare4 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.wbsspare5')"></span>
          </dt>
          <dd>
            <span>{{ projectwbs.wbsspare5 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.cycleplan')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.cycleplan">
              <router-link :to="{ name: 'CycleplanView', params: { cycleplanId: projectwbs.cycleplan.id } }">{{
                projectwbs.cycleplan.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.progressmanagement')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.progressmanagement">
              <router-link :to="{ name: 'ProgressmanagementView', params: { progressmanagementId: projectwbs.progressmanagement.id } }">{{
                projectwbs.progressmanagement.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.qualitymanagement')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.qualitymanagement">
              <router-link :to="{ name: 'QualitymanagementView', params: { qualitymanagementId: projectwbs.qualitymanagement.id } }">{{
                projectwbs.qualitymanagement.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.fundsmanagement')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.fundsmanagement">
              <router-link :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: projectwbs.fundsmanagement.id } }">{{
                projectwbs.fundsmanagement.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.technicalmanagement')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.technicalmanagement">
              <router-link
                :to="{ name: 'TechnicalmanagementView', params: { technicalmanagementId: projectwbs.technicalmanagement.id } }"
                >{{ projectwbs.technicalmanagement.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.contractualfunds')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.contractualfunds">
              <router-link :to="{ name: 'ContractualfundsView', params: { contractualfundsId: projectwbs.contractualfunds.id } }">{{
                projectwbs.contractualfunds.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.outsourcingmanagement')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.outsourcingmanagement">
              <router-link
                :to="{ name: 'OutsourcingmanagementView', params: { outsourcingmanagementId: projectwbs.outsourcingmanagement.id } }"
                >{{ projectwbs.outsourcingmanagement.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.resourcemanagement')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.resourcemanagement">
              <router-link :to="{ name: 'ResourcemanagementView', params: { resourcemanagementId: projectwbs.resourcemanagement.id } }">{{
                projectwbs.resourcemanagement.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.riskmanagement')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.riskmanagement">
              <router-link :to="{ name: 'RiskmanagementView', params: { riskmanagementId: projectwbs.riskmanagement.id } }">{{
                projectwbs.riskmanagement.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.securitymanagement')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.securitymanagement">
              <router-link :to="{ name: 'SecuritymanagementView', params: { securitymanagementId: projectwbs.securitymanagement.id } }">{{
                projectwbs.securitymanagement.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.document')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.document">
              <router-link :to="{ name: 'DocumentView', params: { documentId: projectwbs.document.id } }">{{
                projectwbs.document.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.safetycheck')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.safetycheck">
              <router-link :to="{ name: 'SafetycheckView', params: { safetycheckId: projectwbs.safetycheck.id } }">{{
                projectwbs.safetycheck.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.department')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.department">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectwbs.department.id } }">{{
                projectwbs.department.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.projectwbs.evaluationCriteria')"></span>
          </dt>
          <dd>
            <div v-if="projectwbs.evaluationCriteria">
              <router-link :to="{ name: 'EvaluationCriteriaView', params: { evaluationCriteriaId: projectwbs.evaluationCriteria.id } }">{{
                projectwbs.evaluationCriteria.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="projectwbs.id"
          :to="{ name: 'ProjectwbsEdit', params: { projectwbsId: projectwbs.id } }"
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

<script lang="ts" src="./projectwbs-details.component.ts"></script>
