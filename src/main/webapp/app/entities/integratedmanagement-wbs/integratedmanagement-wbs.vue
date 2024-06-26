<template>
  <div>
    <h2 id="page-heading" data-cy="IntegratedmanagementWbsHeading">
      <span v-text="t$('jHipster0App.integratedmanagementWbs.home.title')" id="integratedmanagement-wbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.integratedmanagementWbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'IntegratedmanagementWbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-integratedmanagement-wbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.integratedmanagementWbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && integratedmanagementWbs && integratedmanagementWbs.length === 0">
      <span v-text="t$('jHipster0App.integratedmanagementWbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="integratedmanagementWbs && integratedmanagementWbs.length > 0">
      <table class="table table-striped" aria-describedby="integratedmanagementWbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare1')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare2')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare3')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare4')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare5')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.planstrategy')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.comprehensivecontrol')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.document')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.comprehensiveledger')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.cycleplan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.annualplan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.monthplan')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.planreturns')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.planmonitor')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.planexecute')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.integratedmanagementWbs.projectcharge')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="integratedmanagementWbs in integratedmanagementWbs" :key="integratedmanagementWbs.id" data-cy="entityTable">
            <td>
              <router-link
                :to="{ name: 'IntegratedmanagementWbsView', params: { integratedmanagementWbsId: integratedmanagementWbs.id } }"
                >{{ integratedmanagementWbs.id }}</router-link
              >
            </td>
            <td>{{ integratedmanagementWbs.wbsspare1 }}</td>
            <td>{{ integratedmanagementWbs.wbsspare2 }}</td>
            <td>{{ integratedmanagementWbs.wbsspare3 }}</td>
            <td>{{ integratedmanagementWbs.wbsspare4 }}</td>
            <td>{{ integratedmanagementWbs.wbsspare5 }}</td>
            <td>
              <div v-if="integratedmanagementWbs.planstrategy">
                <router-link :to="{ name: 'PlanstrategyView', params: { planstrategyId: integratedmanagementWbs.planstrategy.id } }">{{
                  integratedmanagementWbs.planstrategy.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="integratedmanagementWbs.comprehensivecontrol">
                <router-link
                  :to="{
                    name: 'ComprehensivecontrolView',
                    params: { comprehensivecontrolId: integratedmanagementWbs.comprehensivecontrol.id },
                  }"
                  >{{ integratedmanagementWbs.comprehensivecontrol.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="integratedmanagementWbs.document">
                <router-link :to="{ name: 'DocumentView', params: { documentId: integratedmanagementWbs.document.id } }">{{
                  integratedmanagementWbs.document.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="integratedmanagementWbs.comprehensiveledger">
                <router-link
                  :to="{
                    name: 'ComprehensiveledgerView',
                    params: { comprehensiveledgerId: integratedmanagementWbs.comprehensiveledger.id },
                  }"
                  >{{ integratedmanagementWbs.comprehensiveledger.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="integratedmanagementWbs.cycleplan">
                <router-link :to="{ name: 'CycleplanView', params: { cycleplanId: integratedmanagementWbs.cycleplan.id } }">{{
                  integratedmanagementWbs.cycleplan.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="integratedmanagementWbs.annualplan">
                <router-link :to="{ name: 'AnnualplanView', params: { annualplanId: integratedmanagementWbs.annualplan.id } }">{{
                  integratedmanagementWbs.annualplan.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="integratedmanagementWbs.monthplan">
                <router-link :to="{ name: 'MonthplanView', params: { monthplanId: integratedmanagementWbs.monthplan.id } }">{{
                  integratedmanagementWbs.monthplan.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="integratedmanagementWbs.planreturns">
                <router-link :to="{ name: 'PlanreturnsView', params: { planreturnsId: integratedmanagementWbs.planreturns.id } }">{{
                  integratedmanagementWbs.planreturns.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="integratedmanagementWbs.planmonitor">
                <router-link :to="{ name: 'PlanmonitorView', params: { planmonitorId: integratedmanagementWbs.planmonitor.id } }">{{
                  integratedmanagementWbs.planmonitor.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="integratedmanagementWbs.planexecute">
                <router-link :to="{ name: 'PlanexecuteView', params: { planexecuteId: integratedmanagementWbs.planexecute.id } }">{{
                  integratedmanagementWbs.planexecute.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="integratedmanagementWbs.projectcharge">
                <router-link :to="{ name: 'ProjectchargeView', params: { projectchargeId: integratedmanagementWbs.projectcharge.id } }">{{
                  integratedmanagementWbs.projectcharge.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'IntegratedmanagementWbsView', params: { integratedmanagementWbsId: integratedmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'IntegratedmanagementWbsEdit', params: { integratedmanagementWbsId: integratedmanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(integratedmanagementWbs)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="jHipster0App.integratedmanagementWbs.delete.question"
          data-cy="integratedmanagementWbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-integratedmanagementWbs-heading"
          v-text="t$('jHipster0App.integratedmanagementWbs.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-integratedmanagementWbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeIntegratedmanagementWbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./integratedmanagement-wbs.component.ts"></script>
