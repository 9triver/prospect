<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.qualityObjectives.home.createOrEditLabel"
          data-cy="QualityObjectivesCreateUpdateHeading"
          v-text="t$('jy1App.qualityObjectives.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualityObjectives.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="qualityObjectives.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityObjectives.name')" for="quality-objectives-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="quality-objectives-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.objectives')"
              for="quality-objectives-objectives"
            ></label>
            <input
              type="text"
              class="form-control"
              name="objectives"
              id="quality-objectives-objectives"
              data-cy="objectives"
              :class="{ valid: !v$.objectives.$invalid, invalid: v$.objectives.$invalid }"
              v-model="v$.objectives.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.qualitytype')"
              for="quality-objectives-qualitytype"
            ></label>
            <select
              class="form-control"
              name="qualitytype"
              :class="{ valid: !v$.qualitytype.$invalid, invalid: v$.qualitytype.$invalid }"
              v-model="v$.qualitytype.$model"
              id="quality-objectives-qualitytype"
              data-cy="qualitytype"
            >
              <option
                v-for="qualityType in qualityTypeValues"
                :key="qualityType"
                v-bind:value="qualityType"
                v-bind:label="t$('jy1App.QualityType.' + qualityType)"
              >
                {{ qualityType }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.secretlevel')"
              for="quality-objectives-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="quality-objectives-secretlevel"
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
            <label class="form-control-label" v-text="t$('jy1App.qualityObjectives.target')" for="quality-objectives-target"></label>
            <input
              type="number"
              class="form-control"
              name="target"
              id="quality-objectives-target"
              data-cy="target"
              :class="{ valid: !v$.target.$invalid, invalid: v$.target.$invalid }"
              v-model.number="v$.target.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.statisticalmethod')"
              for="quality-objectives-statisticalmethod"
            ></label>
            <input
              type="text"
              class="form-control"
              name="statisticalmethod"
              id="quality-objectives-statisticalmethod"
              data-cy="statisticalmethod"
              :class="{ valid: !v$.statisticalmethod.$invalid, invalid: v$.statisticalmethod.$invalid }"
              v-model="v$.statisticalmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.statisticalfrequency')"
              for="quality-objectives-statisticalfrequency"
            ></label>
            <input
              type="text"
              class="form-control"
              name="statisticalfrequency"
              id="quality-objectives-statisticalfrequency"
              data-cy="statisticalfrequency"
              :class="{ valid: !v$.statisticalfrequency.$invalid, invalid: v$.statisticalfrequency.$invalid }"
              v-model="v$.statisticalfrequency.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityObjectives.istarget')" for="quality-objectives-istarget"></label>
            <input
              type="number"
              class="form-control"
              name="istarget"
              id="quality-objectives-istarget"
              data-cy="istarget"
              :class="{ valid: !v$.istarget.$invalid, invalid: v$.istarget.$invalid }"
              v-model.number="v$.istarget.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityObjectives.progress')" for="quality-objectives-progress"></label>
            <input
              type="number"
              class="form-control"
              name="progress"
              id="quality-objectives-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.description')"
              for="quality-objectives-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="quality-objectives-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityObjectives.problems')" for="quality-objectives-problems"></label>
            <input
              type="text"
              class="form-control"
              name="problems"
              id="quality-objectives-problems"
              data-cy="problems"
              :class="{ valid: !v$.problems.$invalid, invalid: v$.problems.$invalid }"
              v-model="v$.problems.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.improvementmeasures')"
              for="quality-objectives-improvementmeasures"
            ></label>
            <input
              type="text"
              class="form-control"
              name="improvementmeasures"
              id="quality-objectives-improvementmeasures"
              data-cy="improvementmeasures"
              :class="{ valid: !v$.improvementmeasures.$invalid, invalid: v$.improvementmeasures.$invalid }"
              v-model="v$.improvementmeasures.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.returntime')"
              for="quality-objectives-returntime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="quality-objectives-returntime"
                  v-model="v$.returntime.$model"
                  name="returntime"
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
                id="quality-objectives-returntime"
                data-cy="returntime"
                type="text"
                class="form-control"
                name="returntime"
                :class="{ valid: !v$.returntime.$invalid, invalid: v$.returntime.$invalid }"
                v-model="v$.returntime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.createtime')"
              for="quality-objectives-createtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="quality-objectives-createtime"
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
                id="quality-objectives-createtime"
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
              v-text="t$('jy1App.qualityObjectives.auditStatus')"
              for="quality-objectives-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="quality-objectives-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jy1App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.responsibleperson')"
              for="quality-objectives-responsibleperson"
            ></label>
            <select
              class="form-control"
              id="quality-objectives-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="qualityObjectives.responsibleperson"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualityObjectives.responsibleperson && officersOption.id === qualityObjectives.responsibleperson.id
                    ? qualityObjectives.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.qualityObjectives.auditorid')" for="quality-objectives-auditorid"></label>
            <select
              class="form-control"
              id="quality-objectives-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="qualityObjectives.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualityObjectives.auditorid && officersOption.id === qualityObjectives.auditorid.id
                    ? qualityObjectives.auditorid
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
            <label v-text="t$('jy1App.qualityObjectives.projectwbs')" for="quality-objectives-projectwbs"></label>
            <select
              class="form-control"
              id="quality-objectives-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="qualityObjectives.projectwbs !== undefined"
              v-model="qualityObjectives.projectwbs"
            >
              <option
                v-bind:value="getSelected(qualityObjectives.projectwbs, projectwbsOption, 'id')"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
              >
                {{ projectwbsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.qualityObjectives.qualityReturns')" for="quality-objectives-qualityReturns"></label>
            <select
              class="form-control"
              id="quality-objectives-qualityReturns"
              data-cy="qualityReturns"
              multiple
              name="qualityReturns"
              v-if="qualityObjectives.qualityReturns !== undefined"
              v-model="qualityObjectives.qualityReturns"
            >
              <option
                v-bind:value="getSelected(qualityObjectives.qualityReturns, qualityReturnsOption, 'id')"
                v-for="qualityReturnsOption in qualityReturns"
                :key="qualityReturnsOption.id"
              >
                {{ qualityReturnsOption.id }}
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
<script lang="ts" src="./quality-objectives-update.component.ts"></script>
