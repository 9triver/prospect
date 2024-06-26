<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.outsourcingmanagementWbs.home.createOrEditLabel"
          data-cy="OutsourcingmanagementWbsCreateUpdateHeading"
          v-text="t$('jHipster0App.outsourcingmanagementWbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="outsourcingmanagementWbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="outsourcingmanagementWbs.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare1')"
              for="outsourcingmanagement-wbs-wbsspare1"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare1"
              id="outsourcingmanagement-wbs-wbsspare1"
              data-cy="wbsspare1"
              :class="{ valid: !v$.wbsspare1.$invalid, invalid: v$.wbsspare1.$invalid }"
              v-model="v$.wbsspare1.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare2')"
              for="outsourcingmanagement-wbs-wbsspare2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare2"
              id="outsourcingmanagement-wbs-wbsspare2"
              data-cy="wbsspare2"
              :class="{ valid: !v$.wbsspare2.$invalid, invalid: v$.wbsspare2.$invalid }"
              v-model="v$.wbsspare2.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare3')"
              for="outsourcingmanagement-wbs-wbsspare3"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare3"
              id="outsourcingmanagement-wbs-wbsspare3"
              data-cy="wbsspare3"
              :class="{ valid: !v$.wbsspare3.$invalid, invalid: v$.wbsspare3.$invalid }"
              v-model="v$.wbsspare3.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare4')"
              for="outsourcingmanagement-wbs-wbsspare4"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare4"
              id="outsourcingmanagement-wbs-wbsspare4"
              data-cy="wbsspare4"
              :class="{ valid: !v$.wbsspare4.$invalid, invalid: v$.wbsspare4.$invalid }"
              v-model="v$.wbsspare4.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingmanagementWbs.wbsspare5')"
              for="outsourcingmanagement-wbs-wbsspare5"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare5"
              id="outsourcingmanagement-wbs-wbsspare5"
              data-cy="wbsspare5"
              :class="{ valid: !v$.wbsspare5.$invalid, invalid: v$.wbsspare5.$invalid }"
              v-model="v$.wbsspare5.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingmanagementWbs.outsourcingPurchasePlan')"
              for="outsourcingmanagement-wbs-outsourcingPurchasePlan"
            ></label>
            <select
              class="form-control"
              id="outsourcingmanagement-wbs-outsourcingPurchasePlan"
              data-cy="outsourcingPurchasePlan"
              name="outsourcingPurchasePlan"
              v-model="outsourcingmanagementWbs.outsourcingPurchasePlan"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  outsourcingmanagementWbs.outsourcingPurchasePlan &&
                  outsourcingPurchasePlanOption.id === outsourcingmanagementWbs.outsourcingPurchasePlan.id
                    ? outsourcingmanagementWbs.outsourcingPurchasePlan
                    : outsourcingPurchasePlanOption
                "
                v-for="outsourcingPurchasePlanOption in outsourcingPurchasePlans"
                :key="outsourcingPurchasePlanOption.id"
              >
                {{ outsourcingPurchasePlanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingmanagementWbs.outsourcingPurchaseExecute')"
              for="outsourcingmanagement-wbs-outsourcingPurchaseExecute"
            ></label>
            <select
              class="form-control"
              id="outsourcingmanagement-wbs-outsourcingPurchaseExecute"
              data-cy="outsourcingPurchaseExecute"
              name="outsourcingPurchaseExecute"
              v-model="outsourcingmanagementWbs.outsourcingPurchaseExecute"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  outsourcingmanagementWbs.outsourcingPurchaseExecute &&
                  outsourcingPurchaseExecuteOption.id === outsourcingmanagementWbs.outsourcingPurchaseExecute.id
                    ? outsourcingmanagementWbs.outsourcingPurchaseExecute
                    : outsourcingPurchaseExecuteOption
                "
                v-for="outsourcingPurchaseExecuteOption in outsourcingPurchaseExecutes"
                :key="outsourcingPurchaseExecuteOption.id"
              >
                {{ outsourcingPurchaseExecuteOption.id }}
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
<script lang="ts" src="./outsourcingmanagement-wbs-update.component.ts"></script>
