<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.managementCapacityEvaluation.home.createOrEditLabel"
          data-cy="ManagementCapacityEvaluationCreateUpdateHeading"
          v-text="t$('jHipster3App.managementCapacityEvaluation.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="managementCapacityEvaluation.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="managementCapacityEvaluation.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.year')"
              for="management-capacity-evaluation-year"
            ></label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="management-capacity-evaluation-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.deprotment')"
              for="management-capacity-evaluation-deprotment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="deprotment"
              id="management-capacity-evaluation-deprotment"
              data-cy="deprotment"
              :class="{ valid: !v$.deprotment.$invalid, invalid: v$.deprotment.$invalid }"
              v-model="v$.deprotment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.createtime')"
              for="management-capacity-evaluation-createtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="management-capacity-evaluation-createtime"
                  v-model="v$.createtime.$model"
                  name="createtime"
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
                id="management-capacity-evaluation-createtime"
                data-cy="createtime"
                type="text"
                class="form-control"
                name="createtime"
                :class="{ valid: !v$.createtime.$invalid, invalid: v$.createtime.$invalid }"
                v-model="v$.createtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.status')"
              for="management-capacity-evaluation-status"
            ></label>
            <input
              type="number"
              class="form-control"
              name="status"
              id="management-capacity-evaluation-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model.number="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.totalmark')"
              for="management-capacity-evaluation-totalmark"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalmark"
              id="management-capacity-evaluation-totalmark"
              data-cy="totalmark"
              :class="{ valid: !v$.totalmark.$invalid, invalid: v$.totalmark.$invalid }"
              v-model.number="v$.totalmark.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.mark')"
              for="management-capacity-evaluation-mark"
            ></label>
            <input
              type="number"
              class="form-control"
              name="mark"
              id="management-capacity-evaluation-mark"
              data-cy="mark"
              :class="{ valid: !v$.mark.$invalid, invalid: v$.mark.$invalid }"
              v-model.number="v$.mark.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.ratingpersonname')"
              for="management-capacity-evaluation-ratingpersonname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="ratingpersonname"
              id="management-capacity-evaluation-ratingpersonname"
              data-cy="ratingpersonname"
              :class="{ valid: !v$.ratingpersonname.$invalid, invalid: v$.ratingpersonname.$invalid }"
              v-model="v$.ratingpersonname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.ratingdepartment')"
              for="management-capacity-evaluation-ratingdepartment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="ratingdepartment"
              id="management-capacity-evaluation-ratingdepartment"
              data-cy="ratingdepartment"
              :class="{ valid: !v$.ratingdepartment.$invalid, invalid: v$.ratingdepartment.$invalid }"
              v-model="v$.ratingdepartment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.ratingtimg')"
              for="management-capacity-evaluation-ratingtimg"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="management-capacity-evaluation-ratingtimg"
                  v-model="v$.ratingtimg.$model"
                  name="ratingtimg"
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
                id="management-capacity-evaluation-ratingtimg"
                data-cy="ratingtimg"
                type="text"
                class="form-control"
                name="ratingtimg"
                :class="{ valid: !v$.ratingtimg.$invalid, invalid: v$.ratingtimg.$invalid }"
                v-model="v$.ratingtimg.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.evaluationCriteria')"
              for="management-capacity-evaluation-evaluationCriteria"
            ></label>
            <select
              class="form-control"
              id="management-capacity-evaluation-evaluationCriteria"
              data-cy="evaluationCriteria"
              name="evaluationCriteria"
              v-model="managementCapacityEvaluation.evaluationCriteria"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  managementCapacityEvaluation.evaluationCriteria &&
                  evaluationCriteriaOption.id === managementCapacityEvaluation.evaluationCriteria.id
                    ? managementCapacityEvaluation.evaluationCriteria
                    : evaluationCriteriaOption
                "
                v-for="evaluationCriteriaOption in evaluationCriteria"
                :key="evaluationCriteriaOption.id"
              >
                {{ evaluationCriteriaOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.project')"
              for="management-capacity-evaluation-project"
            ></label>
            <select
              class="form-control"
              id="management-capacity-evaluation-project"
              data-cy="project"
              name="project"
              v-model="managementCapacityEvaluation.project"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  managementCapacityEvaluation.project && projectOption.id === managementCapacityEvaluation.project.id
                    ? managementCapacityEvaluation.project
                    : projectOption
                "
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.creatorid')"
              for="management-capacity-evaluation-creatorid"
            ></label>
            <select
              class="form-control"
              id="management-capacity-evaluation-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="managementCapacityEvaluation.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  managementCapacityEvaluation.creatorid && officersOption.id === managementCapacityEvaluation.creatorid.id
                    ? managementCapacityEvaluation.creatorid
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.responsibleid')"
              for="management-capacity-evaluation-responsibleid"
            ></label>
            <select
              class="form-control"
              id="management-capacity-evaluation-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="managementCapacityEvaluation.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  managementCapacityEvaluation.responsibleid && officersOption.id === managementCapacityEvaluation.responsibleid.id
                    ? managementCapacityEvaluation.responsibleid
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.managementCapacityEvaluation.ratingperson')"
              for="management-capacity-evaluation-ratingperson"
            ></label>
            <select
              class="form-control"
              id="management-capacity-evaluation-ratingperson"
              data-cy="ratingperson"
              name="ratingperson"
              v-model="managementCapacityEvaluation.ratingperson"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  managementCapacityEvaluation.ratingperson && officersOption.id === managementCapacityEvaluation.ratingperson.id
                    ? managementCapacityEvaluation.ratingperson
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
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
<script lang="ts" src="./management-capacity-evaluation-update.component.ts"></script>
