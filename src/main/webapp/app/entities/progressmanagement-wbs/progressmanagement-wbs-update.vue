<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.progressmanagementWbs.home.createOrEditLabel"
          data-cy="ProgressmanagementWbsCreateUpdateHeading"
          v-text="t$('jHipster0App.progressmanagementWbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="progressmanagementWbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="progressmanagementWbs.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressmanagementWbs.wbsspare1')"
              for="progressmanagement-wbs-wbsspare1"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare1"
              id="progressmanagement-wbs-wbsspare1"
              data-cy="wbsspare1"
              :class="{ valid: !v$.wbsspare1.$invalid, invalid: v$.wbsspare1.$invalid }"
              v-model="v$.wbsspare1.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressmanagementWbs.wbsspare2')"
              for="progressmanagement-wbs-wbsspare2"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare2"
              id="progressmanagement-wbs-wbsspare2"
              data-cy="wbsspare2"
              :class="{ valid: !v$.wbsspare2.$invalid, invalid: v$.wbsspare2.$invalid }"
              v-model="v$.wbsspare2.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressmanagementWbs.wbsspare3')"
              for="progressmanagement-wbs-wbsspare3"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare3"
              id="progressmanagement-wbs-wbsspare3"
              data-cy="wbsspare3"
              :class="{ valid: !v$.wbsspare3.$invalid, invalid: v$.wbsspare3.$invalid }"
              v-model="v$.wbsspare3.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressmanagementWbs.wbsspare4')"
              for="progressmanagement-wbs-wbsspare4"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare4"
              id="progressmanagement-wbs-wbsspare4"
              data-cy="wbsspare4"
              :class="{ valid: !v$.wbsspare4.$invalid, invalid: v$.wbsspare4.$invalid }"
              v-model="v$.wbsspare4.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressmanagementWbs.wbsspare5')"
              for="progressmanagement-wbs-wbsspare5"
            ></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare5"
              id="progressmanagement-wbs-wbsspare5"
              data-cy="wbsspare5"
              :class="{ valid: !v$.wbsspare5.$invalid, invalid: v$.wbsspare5.$invalid }"
              v-model="v$.wbsspare5.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressmanagementWbs.progressplan')"
              for="progressmanagement-wbs-progressplan"
            ></label>
            <select
              class="form-control"
              id="progressmanagement-wbs-progressplan"
              data-cy="progressplan"
              name="progressplan"
              v-model="progressmanagementWbs.progressplan"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressmanagementWbs.progressplan && progressplanOption.id === progressmanagementWbs.progressplan.id
                    ? progressmanagementWbs.progressplan
                    : progressplanOption
                "
                v-for="progressplanOption in progressplans"
                :key="progressplanOption.id"
              >
                {{ progressplanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressmanagementWbs.progressplanreturns')"
              for="progressmanagement-wbs-progressplanreturns"
            ></label>
            <select
              class="form-control"
              id="progressmanagement-wbs-progressplanreturns"
              data-cy="progressplanreturns"
              name="progressplanreturns"
              v-model="progressmanagementWbs.progressplanreturns"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressmanagementWbs.progressplanreturns && progressplanreturnsOption.id === progressmanagementWbs.progressplanreturns.id
                    ? progressmanagementWbs.progressplanreturns
                    : progressplanreturnsOption
                "
                v-for="progressplanreturnsOption in progressplanreturns"
                :key="progressplanreturnsOption.id"
              >
                {{ progressplanreturnsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.progressmanagementWbs.progressbaseline')"
              for="progressmanagement-wbs-progressbaseline"
            ></label>
            <select
              class="form-control"
              id="progressmanagement-wbs-progressbaseline"
              data-cy="progressbaseline"
              name="progressbaseline"
              v-model="progressmanagementWbs.progressbaseline"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressmanagementWbs.progressbaseline && progressbaselineOption.id === progressmanagementWbs.progressbaseline.id
                    ? progressmanagementWbs.progressbaseline
                    : progressbaselineOption
                "
                v-for="progressbaselineOption in progressbaselines"
                :key="progressbaselineOption.id"
              >
                {{ progressbaselineOption.id }}
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
<script lang="ts" src="./progressmanagement-wbs-update.component.ts"></script>
