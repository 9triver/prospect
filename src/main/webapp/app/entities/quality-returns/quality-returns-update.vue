<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.qualityReturns.home.createOrEditLabel"
          data-cy="QualityReturnsCreateUpdateHeading"
          v-text="t$('jy1App.qualityReturns.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualityReturns.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="qualityReturns.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.name')" for="quality-returns-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="quality-returns-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.objectives')" for="quality-returns-objectives"></label>
            <input
              type="text"
              class="form-control"
              name="objectives"
              id="quality-returns-objectives"
              data-cy="objectives"
              :class="{ valid: !v$.objectives.$invalid, invalid: v$.objectives.$invalid }"
              v-model="v$.objectives.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.qualitytype')" for="quality-returns-qualitytype"></label>
            <select
              class="form-control"
              name="qualitytype"
              :class="{ valid: !v$.qualitytype.$invalid, invalid: v$.qualitytype.$invalid }"
              v-model="v$.qualitytype.$model"
              id="quality-returns-qualitytype"
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
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.secretlevel')" for="quality-returns-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="quality-returns-secretlevel"
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
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.target')" for="quality-returns-target"></label>
            <input
              type="number"
              class="form-control"
              name="target"
              id="quality-returns-target"
              data-cy="target"
              :class="{ valid: !v$.target.$invalid, invalid: v$.target.$invalid }"
              v-model.number="v$.target.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.statisticalmethod')"
              for="quality-returns-statisticalmethod"
            ></label>
            <input
              type="text"
              class="form-control"
              name="statisticalmethod"
              id="quality-returns-statisticalmethod"
              data-cy="statisticalmethod"
              :class="{ valid: !v$.statisticalmethod.$invalid, invalid: v$.statisticalmethod.$invalid }"
              v-model="v$.statisticalmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.statisticalfrequency')"
              for="quality-returns-statisticalfrequency"
            ></label>
            <input
              type="text"
              class="form-control"
              name="statisticalfrequency"
              id="quality-returns-statisticalfrequency"
              data-cy="statisticalfrequency"
              :class="{ valid: !v$.statisticalfrequency.$invalid, invalid: v$.statisticalfrequency.$invalid }"
              v-model="v$.statisticalfrequency.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.istarget')" for="quality-returns-istarget"></label>
            <input
              type="number"
              class="form-control"
              name="istarget"
              id="quality-returns-istarget"
              data-cy="istarget"
              :class="{ valid: !v$.istarget.$invalid, invalid: v$.istarget.$invalid }"
              v-model.number="v$.istarget.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.progress')" for="quality-returns-progress"></label>
            <input
              type="number"
              class="form-control"
              name="progress"
              id="quality-returns-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.description')" for="quality-returns-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="quality-returns-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.problems')" for="quality-returns-problems"></label>
            <input
              type="text"
              class="form-control"
              name="problems"
              id="quality-returns-problems"
              data-cy="problems"
              :class="{ valid: !v$.problems.$invalid, invalid: v$.problems.$invalid }"
              v-model="v$.problems.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.improvementmeasures')"
              for="quality-returns-improvementmeasures"
            ></label>
            <input
              type="text"
              class="form-control"
              name="improvementmeasures"
              id="quality-returns-improvementmeasures"
              data-cy="improvementmeasures"
              :class="{ valid: !v$.improvementmeasures.$invalid, invalid: v$.improvementmeasures.$invalid }"
              v-model="v$.improvementmeasures.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.returntime')" for="quality-returns-returntime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="quality-returns-returntime"
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
                id="quality-returns-returntime"
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
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.createtime')" for="quality-returns-createtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="quality-returns-createtime"
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
                id="quality-returns-createtime"
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
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.auditStatus')" for="quality-returns-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="quality-returns-auditStatus"
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
              v-text="t$('jy1App.qualityReturns.responsibleperson')"
              for="quality-returns-responsibleperson"
            ></label>
            <select
              class="form-control"
              id="quality-returns-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="qualityReturns.responsibleperson"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualityReturns.responsibleperson && officersOption.id === qualityReturns.responsibleperson.id
                    ? qualityReturns.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.auditorid')" for="quality-returns-auditorid"></label>
            <select
              class="form-control"
              id="quality-returns-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="qualityReturns.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualityReturns.auditorid && officersOption.id === qualityReturns.auditorid.id ? qualityReturns.auditorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.creatorid')" for="quality-returns-creatorid"></label>
            <select
              class="form-control"
              id="quality-returns-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="qualityReturns.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualityReturns.creatorid && officersOption.id === qualityReturns.creatorid.id ? qualityReturns.creatorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.qualityReturns.qualityObjectives')" for="quality-returns-qualityObjectives"></label>
            <select
              class="form-control"
              id="quality-returns-qualityObjectives"
              data-cy="qualityObjectives"
              multiple
              name="qualityObjectives"
              v-if="qualityReturns.qualityObjectives !== undefined"
              v-model="qualityReturns.qualityObjectives"
            >
              <option
                v-bind:value="getSelected(qualityReturns.qualityObjectives, qualityObjectivesOption, 'id')"
                v-for="qualityObjectivesOption in qualityObjectives"
                :key="qualityObjectivesOption.id"
              >
                {{ qualityObjectivesOption.id }}
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
<script lang="ts" src="./quality-returns-update.component.ts"></script>
