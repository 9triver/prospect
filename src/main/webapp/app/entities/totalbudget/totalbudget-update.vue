<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.totalbudget.home.createOrEditLabel"
          data-cy="TotalbudgetCreateUpdateHeading"
          v-text="t$('jHipster0App.totalbudget.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="totalbudget.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="totalbudget.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.totalbudget.valuationsubjects')"
              for="totalbudget-valuationsubjects"
            ></label>
            <input
              type="text"
              class="form-control"
              name="valuationsubjects"
              id="totalbudget-valuationsubjects"
              data-cy="valuationsubjects"
              :class="{ valid: !v$.valuationsubjects.$invalid, invalid: v$.valuationsubjects.$invalid }"
              v-model="v$.valuationsubjects.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.totalbudget.budget')" for="totalbudget-budget"></label>
            <input
              type="number"
              class="form-control"
              name="budget"
              id="totalbudget-budget"
              data-cy="budget"
              :class="{ valid: !v$.budget.$invalid, invalid: v$.budget.$invalid }"
              v-model.number="v$.budget.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.totalbudget.percentage')" for="totalbudget-percentage"></label>
            <input
              type="number"
              class="form-control"
              name="percentage"
              id="totalbudget-percentage"
              data-cy="percentage"
              :class="{ valid: !v$.percentage.$invalid, invalid: v$.percentage.$invalid }"
              v-model.number="v$.percentage.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.totalbudget.remarks')" for="totalbudget-remarks"></label>
            <input
              type="text"
              class="form-control"
              name="remarks"
              id="totalbudget-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
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
<script lang="ts" src="./totalbudget-update.component.ts"></script>
