<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.fundsavailability.home.createOrEditLabel"
          data-cy="FundsavailabilityCreateUpdateHeading"
          v-text="t$('jHipster0App.fundsavailability.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="fundsavailability.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="fundsavailability.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.fundsavailability.fundsid')" for="fundsavailability-fundsid"></label>
            <input
              type="text"
              class="form-control"
              name="fundsid"
              id="fundsavailability-fundsid"
              data-cy="fundsid"
              :class="{ valid: !v$.fundsid.$invalid, invalid: v$.fundsid.$invalid }"
              v-model="v$.fundsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.fundsavailability.year')" for="fundsavailability-year"></label>
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
            <label class="form-control-label" v-text="t$('jHipster0App.fundsavailability.budgit')" for="fundsavailability-budgit"></label>
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
            <label class="form-control-label" v-text="t$('jHipster0App.fundsavailability.funding')" for="fundsavailability-funding"></label>
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
              v-text="t$('jHipster0App.fundsavailability.auditedbudget')"
              for="fundsavailability-auditedbudget"
            ></label>
            <select
              class="form-control"
              id="fundsavailability-auditedbudget"
              data-cy="auditedbudget"
              name="auditedbudget"
              v-model="fundsavailability.auditedbudget"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsavailability.auditedbudget && auditedbudgetOption.id === fundsavailability.auditedbudget.id
                    ? fundsavailability.auditedbudget
                    : auditedbudgetOption
                "
                v-for="auditedbudgetOption in auditedbudgets"
                :key="auditedbudgetOption.id"
              >
                {{ auditedbudgetOption.id }}
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
