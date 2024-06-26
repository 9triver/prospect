<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.evaluationCriteria.home.createOrEditLabel"
          data-cy="EvaluationCriteriaCreateUpdateHeading"
          v-text="t$('jHipster0App.evaluationCriteria.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="evaluationCriteria.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="evaluationCriteria.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.evaluationCriteria.standardtype')"
              for="evaluation-criteria-standardtype"
            ></label>
            <input
              type="number"
              class="form-control"
              name="standardtype"
              id="evaluation-criteria-standardtype"
              data-cy="standardtype"
              :class="{ valid: !v$.standardtype.$invalid, invalid: v$.standardtype.$invalid }"
              v-model.number="v$.standardtype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.evaluationCriteria.standardname')"
              for="evaluation-criteria-standardname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="standardname"
              id="evaluation-criteria-standardname"
              data-cy="standardname"
              :class="{ valid: !v$.standardname.$invalid, invalid: v$.standardname.$invalid }"
              v-model="v$.standardname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.evaluationCriteria.mark')" for="evaluation-criteria-mark"></label>
            <input
              type="number"
              class="form-control"
              name="mark"
              id="evaluation-criteria-mark"
              data-cy="mark"
              :class="{ valid: !v$.mark.$invalid, invalid: v$.mark.$invalid }"
              v-model.number="v$.mark.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.evaluationCriteria.department')"
              for="evaluation-criteria-department"
            ></label>
            <select
              class="form-control"
              id="evaluation-criteria-department"
              data-cy="department"
              name="department"
              v-model="evaluationCriteria.department"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  evaluationCriteria.department && departmentOption.id === evaluationCriteria.department.id
                    ? evaluationCriteria.department
                    : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.id }}
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
<script lang="ts" src="./evaluation-criteria-update.component.ts"></script>
