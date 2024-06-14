<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="project">
        <h2 class="jh-entity-heading" data-cy="projectDetailsHeading">
          <span v-text="t$('jHipster3App.project.detail.title')"></span> {{ project.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster3App.project.projectid')"></span>
          </dt>
          <dd>
            <span>{{ project.projectid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.projectname')"></span>
          </dt>
          <dd>
            <span>{{ project.projectname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.description')"></span>
          </dt>
          <dd>
            <span>{{ project.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.number')"></span>
          </dt>
          <dd>
            <span>{{ project.number }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.projecttype')"></span>
          </dt>
          <dd>
            <span>{{ project.projecttype }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.responsiblename')"></span>
          </dt>
          <dd>
            <span>{{ project.responsiblename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.duedate')"></span>
          </dt>
          <dd>
            <span>{{ project.duedate }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.priorty')"></span>
          </dt>
          <dd>
            <span>{{ project.priorty }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.progressid')"></span>
          </dt>
          <dd>
            <span>{{ project.progressid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.returnsid')"></span>
          </dt>
          <dd>
            <span>{{ project.returnsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.qualityid')"></span>
          </dt>
          <dd>
            <span>{{ project.qualityid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.fundsid')"></span>
          </dt>
          <dd>
            <span>{{ project.fundsid }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.status')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.ProjectStatus.' + project.status)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.auditStatus')"></span>
          </dt>
          <dd>
            <span v-text="t$('jHipster3App.AuditStatus.' + project.auditStatus)"></span>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.cycleplan')"></span>
          </dt>
          <dd>
            <div v-if="project.cycleplan">
              <router-link :to="{ name: 'CycleplanView', params: { cycleplanId: project.cycleplan.id } }">{{
                project.cycleplan.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.progressmanagement')"></span>
          </dt>
          <dd>
            <div v-if="project.progressmanagement">
              <router-link :to="{ name: 'ProgressmanagementView', params: { progressmanagementId: project.progressmanagement.id } }">{{
                project.progressmanagement.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.qualitymanagement')"></span>
          </dt>
          <dd>
            <div v-if="project.qualitymanagement">
              <router-link :to="{ name: 'QualitymanagementView', params: { qualitymanagementId: project.qualitymanagement.id } }">{{
                project.qualitymanagement.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.fundsmanagement')"></span>
          </dt>
          <dd>
            <div v-if="project.fundsmanagement">
              <router-link :to="{ name: 'FundsmanagementView', params: { fundsmanagementId: project.fundsmanagement.id } }">{{
                project.fundsmanagement.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.technicalCondition')"></span>
          </dt>
          <dd>
            <div v-if="project.technicalCondition">
              <router-link :to="{ name: 'TechnicalConditionView', params: { technicalConditionId: project.technicalCondition.id } }">{{
                project.technicalCondition.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.contractualfunds')"></span>
          </dt>
          <dd>
            <div v-if="project.contractualfunds">
              <router-link :to="{ name: 'ContractualfundsView', params: { contractualfundsId: project.contractualfunds.id } }">{{
                project.contractualfunds.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.outsourcingmPurchaseExecute')"></span>
          </dt>
          <dd>
            <div v-if="project.outsourcingmPurchaseExecute">
              <router-link
                :to="{
                  name: 'OutsourcingmPurchaseExecuteView',
                  params: { outsourcingmPurchaseExecuteId: project.outsourcingmPurchaseExecute.id },
                }"
                >{{ project.outsourcingmPurchaseExecute.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.resourcemanagement')"></span>
          </dt>
          <dd>
            <div v-if="project.resourcemanagement">
              <router-link :to="{ name: 'ResourcemanagementView', params: { resourcemanagementId: project.resourcemanagement.id } }">{{
                project.resourcemanagement.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.riskmanagement')"></span>
          </dt>
          <dd>
            <div v-if="project.riskmanagement">
              <router-link :to="{ name: 'RiskmanagementView', params: { riskmanagementId: project.riskmanagement.id } }">{{
                project.riskmanagement.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.document')"></span>
          </dt>
          <dd>
            <div v-if="project.document">
              <router-link :to="{ name: 'DocumentView', params: { documentId: project.document.id } }">{{
                project.document.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.safetycheck')"></span>
          </dt>
          <dd>
            <div v-if="project.safetycheck">
              <router-link :to="{ name: 'SafetycheckView', params: { safetycheckId: project.safetycheck.id } }">{{
                project.safetycheck.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.department')"></span>
          </dt>
          <dd>
            <div v-if="project.department">
              <router-link :to="{ name: 'DepartmentView', params: { departmentId: project.department.id } }">{{
                project.department.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.evaluationCriteria')"></span>
          </dt>
          <dd>
            <div v-if="project.evaluationCriteria">
              <router-link :to="{ name: 'EvaluationCriteriaView', params: { evaluationCriteriaId: project.evaluationCriteria.id } }">{{
                project.evaluationCriteria.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.responsibleid')"></span>
          </dt>
          <dd>
            <div v-if="project.responsibleid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: project.responsibleid.id } }">{{
                project.responsibleid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.auditorid')"></span>
          </dt>
          <dd>
            <div v-if="project.auditorid">
              <router-link :to="{ name: 'OfficersView', params: { officersId: project.auditorid.id } }">{{
                project.auditorid.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster3App.project.projectSecrecy')"></span>
          </dt>
          <dd>
            <div v-if="project.projectSecrecy">
              <router-link :to="{ name: 'ProjectSecrecyView', params: { projectSecrecyId: project.projectSecrecy.id } }">{{
                project.projectSecrecy.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="project.id" :to="{ name: 'ProjectEdit', params: { projectId: project.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./project-details.component.ts"></script>
