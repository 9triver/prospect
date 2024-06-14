<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.fundsavailability.home.createOrEditLabel"
          data-cy="FundsavailabilityCreateUpdateHeading"
          v-text="t$('jHipster3App.fundsavailability.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="fundsavailability.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="fundsavailability.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsavailability.fundsavailabilityid')"
              for="fundsavailability-fundsavailabilityid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="fundsavailabilityid"
              id="fundsavailability-fundsavailabilityid"
              data-cy="fundsavailabilityid"
              :class="{ valid: !v$.fundsavailabilityid.$invalid, invalid: v$.fundsavailabilityid.$invalid }"
              v-model.number="v$.fundsavailabilityid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.fundsavailability.fundsid')" for="fundsavailability-fundsid"></label>
            <input
              type="number"
              class="form-control"
              name="fundsid"
              id="fundsavailability-fundsid"
              data-cy="fundsid"
              :class="{ valid: !v$.fundsid.$invalid, invalid: v$.fundsid.$invalid }"
              v-model.number="v$.fundsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.fundsavailability.year')" for="fundsavailability-year"></label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="fundsavailability-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.fundsavailability.budgit')" for="fundsavailability-budgit"></label>
            <input
              type="number"
              class="form-control"
              name="budgit"
              id="fundsavailability-budgit"
              data-cy="budgit"
              :class="{ valid: !v$.budgit.$invalid, invalid: v$.budgit.$invalid }"
              v-model.number="v$.budgit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.fundsavailability.funding')" for="fundsavailability-funding"></label>
            <input
              type="number"
              class="form-control"
              name="funding"
              id="fundsavailability-funding"
              data-cy="funding"
              :class="{ valid: !v$.funding.$invalid, invalid: v$.funding.$invalid }"
              v-model.number="v$.funding.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsavailability.fundsmanagement')"
              for="fundsavailability-fundsmanagement"
            ></label>
            <select
              class="form-control"
              id="fundsavailability-fundsmanagement"
              data-cy="fundsmanagement"
              name="fundsmanagement"
              v-model="fundsavailability.fundsmanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsavailability.fundsmanagement && fundsmanagementOption.id === fundsavailability.fundsmanagement.id
                    ? fundsavailability.fundsmanagement
                    : fundsmanagementOption
                "
                v-for="fundsmanagementOption in fundsmanagements"
                :key="fundsmanagementOption.id"
              >
                {{ fundsmanagementOption.id }}
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
<script lang="ts" src="./fundsavailability-update.component.ts"></script>
