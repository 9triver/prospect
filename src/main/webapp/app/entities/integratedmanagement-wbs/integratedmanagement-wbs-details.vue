<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="integratedmanagementWbs">
        <h2 class="jh-entity-heading" data-cy="integratedmanagementWbsDetailsHeading">
          <span v-text="t$('jHipster0App.integratedmanagementWbs.detail.title')"></span> {{ integratedmanagementWbs.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare1')"></span>
          </dt>
          <dd>
            <span>{{ integratedmanagementWbs.wbsspare1 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare2')"></span>
          </dt>
          <dd>
            <span>{{ integratedmanagementWbs.wbsspare2 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare3')"></span>
          </dt>
          <dd>
            <span>{{ integratedmanagementWbs.wbsspare3 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare4')"></span>
          </dt>
          <dd>
            <span>{{ integratedmanagementWbs.wbsspare4 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare5')"></span>
          </dt>
          <dd>
            <span>{{ integratedmanagementWbs.wbsspare5 }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.planstrategy')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagementWbs.planstrategy">
              <router-link :to="{ name: 'PlanstrategyView', params: { planstrategyId: integratedmanagementWbs.planstrategy.id } }">{{
                integratedmanagementWbs.planstrategy.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.comprehensivecontrol')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagementWbs.comprehensivecontrol">
              <router-link
                :to="{
                  name: 'ComprehensivecontrolView',
                  params: { comprehensivecontrolId: integratedmanagementWbs.comprehensivecontrol.id },
                }"
                >{{ integratedmanagementWbs.comprehensivecontrol.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.document')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagementWbs.document">
              <router-link :to="{ name: 'DocumentView', params: { documentId: integratedmanagementWbs.document.id } }">{{
                integratedmanagementWbs.document.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.comprehensiveledger')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagementWbs.comprehensiveledger">
              <router-link
                :to="{ name: 'ComprehensiveledgerView', params: { comprehensiveledgerId: integratedmanagementWbs.comprehensiveledger.id } }"
                >{{ integratedmanagementWbs.comprehensiveledger.id }}</router-link
              >
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.cycleplan')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagementWbs.cycleplan">
              <router-link :to="{ name: 'CycleplanView', params: { cycleplanId: integratedmanagementWbs.cycleplan.id } }">{{
                integratedmanagementWbs.cycleplan.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.annualplan')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagementWbs.annualplan">
              <router-link :to="{ name: 'AnnualplanView', params: { annualplanId: integratedmanagementWbs.annualplan.id } }">{{
                integratedmanagementWbs.annualplan.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.monthplan')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagementWbs.monthplan">
              <router-link :to="{ name: 'MonthplanView', params: { monthplanId: integratedmanagementWbs.monthplan.id } }">{{
                integratedmanagementWbs.monthplan.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.planreturns')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagementWbs.planreturns">
              <router-link :to="{ name: 'PlanreturnsView', params: { planreturnsId: integratedmanagementWbs.planreturns.id } }">{{
                integratedmanagementWbs.planreturns.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.planmonitor')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagementWbs.planmonitor">
              <router-link :to="{ name: 'PlanmonitorView', params: { planmonitorId: integratedmanagementWbs.planmonitor.id } }">{{
                integratedmanagementWbs.planmonitor.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.planexecute')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagementWbs.planexecute">
              <router-link :to="{ name: 'PlanexecuteView', params: { planexecuteId: integratedmanagementWbs.planexecute.id } }">{{
                integratedmanagementWbs.planexecute.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.projectcharge')"></span>
          </dt>
          <dd>
            <div v-if="integratedmanagementWbs.projectcharge">
              <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: integratedmanagementWbs.projectcharge.id } }">{{
                integratedmanagementWbs.projectcharge.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="integratedmanagementWbs.id"
          :to="{ name: 'IntegratedmanagementWbsEdit', params: { integratedmanagementWbsId: integratedmanagementWbs.id } }"
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

<script lang="ts" src="./integratedmanagement-wbs-details.component.ts"></script>
