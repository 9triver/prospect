<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.integratedmanagementWbs.home.createOrEditLabel"
          data-cy="IntegratedmanagementWbsCreateUpdateHeading"
          v-text="t$('jHipster0App.integratedmanagementWbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="integratedmanagementWbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="integratedmanagementWbs.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare1')"
              for="integratedmanagement-wbs-wbsspare1"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare1"
              id="integratedmanagement-wbs-wbsspare1"
              data-cy="wbsspare1"
              :class="{ valid: !v$.wbsspare1.$invalid, invalid: v$.wbsspare1.$invalid }"
              v-model="v$.wbsspare1.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare2')"
              for="integratedmanagement-wbs-wbsspare2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare2"
              id="integratedmanagement-wbs-wbsspare2"
              data-cy="wbsspare2"
              :class="{ valid: !v$.wbsspare2.$invalid, invalid: v$.wbsspare2.$invalid }"
              v-model="v$.wbsspare2.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare3')"
              for="integratedmanagement-wbs-wbsspare3"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare3"
              id="integratedmanagement-wbs-wbsspare3"
              data-cy="wbsspare3"
              :class="{ valid: !v$.wbsspare3.$invalid, invalid: v$.wbsspare3.$invalid }"
              v-model="v$.wbsspare3.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare4')"
              for="integratedmanagement-wbs-wbsspare4"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare4"
              id="integratedmanagement-wbs-wbsspare4"
              data-cy="wbsspare4"
              :class="{ valid: !v$.wbsspare4.$invalid, invalid: v$.wbsspare4.$invalid }"
              v-model="v$.wbsspare4.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.wbsspare5')"
              for="integratedmanagement-wbs-wbsspare5"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare5"
              id="integratedmanagement-wbs-wbsspare5"
              data-cy="wbsspare5"
              :class="{ valid: !v$.wbsspare5.$invalid, invalid: v$.wbsspare5.$invalid }"
              v-model="v$.wbsspare5.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.planstrategy')"
              for="integratedmanagement-wbs-planstrategy"
            ></label>
            <select
              class="form-control"
              id="integratedmanagement-wbs-planstrategy"
              data-cy="planstrategy"
              name="planstrategy"
              v-model="integratedmanagementWbs.planstrategy"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagementWbs.planstrategy && planstrategyOption.id === integratedmanagementWbs.planstrategy.id
                    ? integratedmanagementWbs.planstrategy
                    : planstrategyOption
                "
                v-for="planstrategyOption in planstrategies"
                :key="planstrategyOption.id"
              >
                {{ planstrategyOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.comprehensivecontrol')"
              for="integratedmanagement-wbs-comprehensivecontrol"
            ></label>
            <select
              class="form-control"
              id="integratedmanagement-wbs-comprehensivecontrol"
              data-cy="comprehensivecontrol"
              name="comprehensivecontrol"
              v-model="integratedmanagementWbs.comprehensivecontrol"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagementWbs.comprehensivecontrol &&
                  comprehensivecontrolOption.id === integratedmanagementWbs.comprehensivecontrol.id
                    ? integratedmanagementWbs.comprehensivecontrol
                    : comprehensivecontrolOption
                "
                v-for="comprehensivecontrolOption in comprehensivecontrols"
                :key="comprehensivecontrolOption.id"
              >
                {{ comprehensivecontrolOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.document')"
              for="integratedmanagement-wbs-document"
            ></label>
            <select
              class="form-control"
              id="integratedmanagement-wbs-document"
              data-cy="document"
              name="document"
              v-model="integratedmanagementWbs.document"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagementWbs.document && documentOption.id === integratedmanagementWbs.document.id
                    ? integratedmanagementWbs.document
                    : documentOption
                "
                v-for="documentOption in documents"
                :key="documentOption.id"
              >
                {{ documentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.comprehensiveledger')"
              for="integratedmanagement-wbs-comprehensiveledger"
            ></label>
            <select
              class="form-control"
              id="integratedmanagement-wbs-comprehensiveledger"
              data-cy="comprehensiveledger"
              name="comprehensiveledger"
              v-model="integratedmanagementWbs.comprehensiveledger"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagementWbs.comprehensiveledger &&
                  comprehensiveledgerOption.id === integratedmanagementWbs.comprehensiveledger.id
                    ? integratedmanagementWbs.comprehensiveledger
                    : comprehensiveledgerOption
                "
                v-for="comprehensiveledgerOption in comprehensiveledgers"
                :key="comprehensiveledgerOption.id"
              >
                {{ comprehensiveledgerOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.cycleplan')"
              for="integratedmanagement-wbs-cycleplan"
            ></label>
            <select
              class="form-control"
              id="integratedmanagement-wbs-cycleplan"
              data-cy="cycleplan"
              name="cycleplan"
              v-model="integratedmanagementWbs.cycleplan"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagementWbs.cycleplan && cycleplanOption.id === integratedmanagementWbs.cycleplan.id
                    ? integratedmanagementWbs.cycleplan
                    : cycleplanOption
                "
                v-for="cycleplanOption in cycleplans"
                :key="cycleplanOption.id"
              >
                {{ cycleplanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.annualplan')"
              for="integratedmanagement-wbs-annualplan"
            ></label>
            <select
              class="form-control"
              id="integratedmanagement-wbs-annualplan"
              data-cy="annualplan"
              name="annualplan"
              v-model="integratedmanagementWbs.annualplan"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagementWbs.annualplan && annualplanOption.id === integratedmanagementWbs.annualplan.id
                    ? integratedmanagementWbs.annualplan
                    : annualplanOption
                "
                v-for="annualplanOption in annualplans"
                :key="annualplanOption.id"
              >
                {{ annualplanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.monthplan')"
              for="integratedmanagement-wbs-monthplan"
            ></label>
            <select
              class="form-control"
              id="integratedmanagement-wbs-monthplan"
              data-cy="monthplan"
              name="monthplan"
              v-model="integratedmanagementWbs.monthplan"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagementWbs.monthplan && monthplanOption.id === integratedmanagementWbs.monthplan.id
                    ? integratedmanagementWbs.monthplan
                    : monthplanOption
                "
                v-for="monthplanOption in monthplans"
                :key="monthplanOption.id"
              >
                {{ monthplanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.planreturns')"
              for="integratedmanagement-wbs-planreturns"
            ></label>
            <select
              class="form-control"
              id="integratedmanagement-wbs-planreturns"
              data-cy="planreturns"
              name="planreturns"
              v-model="integratedmanagementWbs.planreturns"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagementWbs.planreturns && planreturnsOption.id === integratedmanagementWbs.planreturns.id
                    ? integratedmanagementWbs.planreturns
                    : planreturnsOption
                "
                v-for="planreturnsOption in planreturns"
                :key="planreturnsOption.id"
              >
                {{ planreturnsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.planmonitor')"
              for="integratedmanagement-wbs-planmonitor"
            ></label>
            <select
              class="form-control"
              id="integratedmanagement-wbs-planmonitor"
              data-cy="planmonitor"
              name="planmonitor"
              v-model="integratedmanagementWbs.planmonitor"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagementWbs.planmonitor && planmonitorOption.id === integratedmanagementWbs.planmonitor.id
                    ? integratedmanagementWbs.planmonitor
                    : planmonitorOption
                "
                v-for="planmonitorOption in planmonitors"
                :key="planmonitorOption.id"
              >
                {{ planmonitorOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.planexecute')"
              for="integratedmanagement-wbs-planexecute"
            ></label>
            <select
              class="form-control"
              id="integratedmanagement-wbs-planexecute"
              data-cy="planexecute"
              name="planexecute"
              v-model="integratedmanagementWbs.planexecute"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagementWbs.planexecute && planexecuteOption.id === integratedmanagementWbs.planexecute.id
                    ? integratedmanagementWbs.planexecute
                    : planexecuteOption
                "
                v-for="planexecuteOption in planexecutes"
                :key="planexecuteOption.id"
              >
                {{ planexecuteOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.integratedmanagementWbs.projectcharge')"
              for="integratedmanagement-wbs-projectcharge"
            ></label>
            <select
              class="form-control"
              id="integratedmanagement-wbs-projectcharge"
              data-cy="projectcharge"
              name="projectcharge"
              v-model="integratedmanagementWbs.projectcharge"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  integratedmanagementWbs.projectcharge && projectchargeOption.id === integratedmanagementWbs.projectcharge.id
                    ? integratedmanagementWbs.projectcharge
                    : projectchargeOption
                "
                v-for="projectchargeOption in projectcharges"
                :key="projectchargeOption.id"
              >
                {{ projectchargeOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./integratedmanagement-wbs-update.component.ts"></script>
