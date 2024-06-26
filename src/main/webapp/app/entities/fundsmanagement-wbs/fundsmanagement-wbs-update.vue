<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.fundsmanagementWbs.home.createOrEditLabel"
          data-cy="FundsmanagementWbsCreateUpdateHeading"
          v-text="t$('jHipster0App.fundsmanagementWbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="fundsmanagementWbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="fundsmanagementWbs.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare1')"
              for="fundsmanagement-wbs-wbsspare1"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare1"
              id="fundsmanagement-wbs-wbsspare1"
              data-cy="wbsspare1"
              :class="{ valid: !v$.wbsspare1.$invalid, invalid: v$.wbsspare1.$invalid }"
              v-model="v$.wbsspare1.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare2')"
              for="fundsmanagement-wbs-wbsspare2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare2"
              id="fundsmanagement-wbs-wbsspare2"
              data-cy="wbsspare2"
              :class="{ valid: !v$.wbsspare2.$invalid, invalid: v$.wbsspare2.$invalid }"
              v-model="v$.wbsspare2.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare3')"
              for="fundsmanagement-wbs-wbsspare3"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare3"
              id="fundsmanagement-wbs-wbsspare3"
              data-cy="wbsspare3"
              :class="{ valid: !v$.wbsspare3.$invalid, invalid: v$.wbsspare3.$invalid }"
              v-model="v$.wbsspare3.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare4')"
              for="fundsmanagement-wbs-wbsspare4"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare4"
              id="fundsmanagement-wbs-wbsspare4"
              data-cy="wbsspare4"
              :class="{ valid: !v$.wbsspare4.$invalid, invalid: v$.wbsspare4.$invalid }"
              v-model="v$.wbsspare4.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.fundsmanagementWbs.wbsspare5')"
              for="fundsmanagement-wbs-wbsspare5"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare5"
              id="fundsmanagement-wbs-wbsspare5"
              data-cy="wbsspare5"
              :class="{ valid: !v$.wbsspare5.$invalid, invalid: v$.wbsspare5.$invalid }"
              v-model="v$.wbsspare5.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.fundsmanagementWbs.auditedbudget')"
              for="fundsmanagement-wbs-auditedbudget"
            ></label>
            <select
              class="form-control"
              id="fundsmanagement-wbs-auditedbudget"
              data-cy="auditedbudget"
              name="auditedbudget"
              v-model="fundsmanagementWbs.auditedbudget"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsmanagementWbs.auditedbudget && auditedbudgetOption.id === fundsmanagementWbs.auditedbudget.id
                    ? fundsmanagementWbs.auditedbudget
                    : auditedbudgetOption
                "
                v-for="auditedbudgetOption in auditedbudgets"
                :key="auditedbudgetOption.id"
              >
                {{ auditedbudgetOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.fundsmanagementWbs.totalbudget')"
              for="fundsmanagement-wbs-totalbudget"
            ></label>
            <select
              class="form-control"
              id="fundsmanagement-wbs-totalbudget"
              data-cy="totalbudget"
              name="totalbudget"
              v-model="fundsmanagementWbs.totalbudget"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsmanagementWbs.totalbudget && totalbudgetOption.id === fundsmanagementWbs.totalbudget.id
                    ? fundsmanagementWbs.totalbudget
                    : totalbudgetOption
                "
                v-for="totalbudgetOption in totalbudgets"
                :key="totalbudgetOption.id"
              >
                {{ totalbudgetOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.fundsmanagementWbs.unitbudget')"
              for="fundsmanagement-wbs-unitbudget"
            ></label>
            <select
              class="form-control"
              id="fundsmanagement-wbs-unitbudget"
              data-cy="unitbudget"
              name="unitbudget"
              v-model="fundsmanagementWbs.unitbudget"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsmanagementWbs.unitbudget && unitbudgetOption.id === fundsmanagementWbs.unitbudget.id
                    ? fundsmanagementWbs.unitbudget
                    : unitbudgetOption
                "
                v-for="unitbudgetOption in unitbudgets"
                :key="unitbudgetOption.id"
              >
                {{ unitbudgetOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.fundsmanagementWbs.fundsavailability')"
              for="fundsmanagement-wbs-fundsavailability"
            ></label>
            <select
              class="form-control"
              id="fundsmanagement-wbs-fundsavailability"
              data-cy="fundsavailability"
              name="fundsavailability"
              v-model="fundsmanagementWbs.fundsavailability"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsmanagementWbs.fundsavailability && fundsavailabilityOption.id === fundsmanagementWbs.fundsavailability.id
                    ? fundsmanagementWbs.fundsavailability
                    : fundsavailabilityOption
                "
                v-for="fundsavailabilityOption in fundsavailabilities"
                :key="fundsavailabilityOption.id"
              >
                {{ fundsavailabilityOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.fundsmanagementWbs.contractualfunds')"
              for="fundsmanagement-wbs-contractualfunds"
            ></label>
            <select
              class="form-control"
              id="fundsmanagement-wbs-contractualfunds"
              data-cy="contractualfunds"
              name="contractualfunds"
              v-model="fundsmanagementWbs.contractualfunds"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsmanagementWbs.contractualfunds && contractualfundsOption.id === fundsmanagementWbs.contractualfunds.id
                    ? fundsmanagementWbs.contractualfunds
                    : contractualfundsOption
                "
                v-for="contractualfundsOption in contractualfunds"
                :key="contractualfundsOption.id"
              >
                {{ contractualfundsOption.id }}
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
<script lang="ts" src="./fundsmanagement-wbs-update.component.ts"></script>
