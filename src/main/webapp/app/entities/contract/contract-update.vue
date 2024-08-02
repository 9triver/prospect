<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.contract.home.createOrEditLabel"
          data-cy="ContractCreateUpdateHeading"
          v-text="t$('jy1App.contract.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="contract.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="contract.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.contractname')" for="contract-contractname"></label>
            <input
              type="text"
              class="form-control"
              name="contractname"
              id="contract-contractname"
              data-cy="contractname"
              :class="{ valid: !v$.contractname.$invalid, invalid: v$.contractname.$invalid }"
              v-model="v$.contractname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.year')" for="contract-year"></label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="contract-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.starttime')" for="contract-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="contract-starttime"
                  v-model="v$.starttime.$model"
                  name="starttime"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="contract-starttime"
                data-cy="starttime"
                type="text"
                class="form-control"
                name="starttime"
                :class="{ valid: !v$.starttime.$invalid, invalid: v$.starttime.$invalid }"
                v-model="v$.starttime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.endtime')" for="contract-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="contract-endtime"
                  v-model="v$.endtime.$model"
                  name="endtime"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="contract-endtime"
                data-cy="endtime"
                type="text"
                class="form-control"
                name="endtime"
                :class="{ valid: !v$.endtime.$invalid, invalid: v$.endtime.$invalid }"
                v-model="v$.endtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.contractbudgetcost')" for="contract-contractbudgetcost"></label>
            <input
              type="number"
              class="form-control"
              name="contractbudgetcost"
              id="contract-contractbudgetcost"
              data-cy="contractbudgetcost"
              :class="{ valid: !v$.contractbudgetcost.$invalid, invalid: v$.contractbudgetcost.$invalid }"
              v-model.number="v$.contractbudgetcost.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.secretlevel')" for="contract-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="contract-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jy1App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.status')" for="contract-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="contract-status"
              data-cy="status"
            >
              <option
                v-for="contractStatus in contractStatusValues"
                :key="contractStatus"
                v-bind:value="contractStatus"
                v-bind:label="t$('jy1App.ContractStatus.' + contractStatus)"
              >
                {{ contractStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.contract.costControlSystem')" for="contract-costControlSystem"></label>
            <select
              class="form-control"
              id="contract-costControlSystems"
              data-cy="costControlSystem"
              multiple
              name="costControlSystem"
              v-if="contract.costControlSystems !== undefined"
              v-model="contract.costControlSystems"
            >
              <option
                v-bind:value="getSelected(contract.costControlSystems, costControlSystemOption, 'id')"
                v-for="costControlSystemOption in costControlSystems"
                :key="costControlSystemOption.id"
              >
                {{ costControlSystemOption.id }}
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
<script lang="ts" src="./contract-update.component.ts"></script>
