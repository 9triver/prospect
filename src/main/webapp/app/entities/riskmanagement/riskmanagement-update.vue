<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.riskmanagement.home.createOrEditLabel"
          data-cy="RiskmanagementCreateUpdateHeading"
          v-text="t$('jHipster3App.riskmanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="riskmanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="riskmanagement.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.riskmanagement.riskid')" for="riskmanagement-riskid"></label>
            <input
              type="number"
              class="form-control"
              name="riskid"
              id="riskmanagement-riskid"
              data-cy="riskid"
              :class="{ valid: !v$.riskid.$invalid, invalid: v$.riskid.$invalid }"
              v-model.number="v$.riskid.$model"
            />
            <div v-if="v$.riskid.$anyDirty && v$.riskid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.riskid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.riskmanagement.projectname')"
              for="riskmanagement-projectname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="projectname"
              id="riskmanagement-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.riskmanagement.year')" for="riskmanagement-year"></label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="riskmanagement-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.riskmanagement.nodename')" for="riskmanagement-nodename"></label>
            <input
              type="text"
              class="form-control"
              name="nodename"
              id="riskmanagement-nodename"
              data-cy="nodename"
              :class="{ valid: !v$.nodename.$invalid, invalid: v$.nodename.$invalid }"
              v-model="v$.nodename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.riskmanagement.risktype')" for="riskmanagement-risktype"></label>
            <input
              type="number"
              class="form-control"
              name="risktype"
              id="riskmanagement-risktype"
              data-cy="risktype"
              :class="{ valid: !v$.risktype.$invalid, invalid: v$.risktype.$invalid }"
              v-model.number="v$.risktype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.riskmanagement.decumentid')" for="riskmanagement-decumentid"></label>
            <input
              type="number"
              class="form-control"
              name="decumentid"
              id="riskmanagement-decumentid"
              data-cy="decumentid"
              :class="{ valid: !v$.decumentid.$invalid, invalid: v$.decumentid.$invalid }"
              v-model.number="v$.decumentid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.riskmanagement.version')" for="riskmanagement-version"></label>
            <input
              type="number"
              class="form-control"
              name="version"
              id="riskmanagement-version"
              data-cy="version"
              :class="{ valid: !v$.version.$invalid, invalid: v$.version.$invalid }"
              v-model.number="v$.version.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.riskmanagement.usetime')" for="riskmanagement-usetime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="riskmanagement-usetime"
                  v-model="v$.usetime.$model"
                  name="usetime"
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
                id="riskmanagement-usetime"
                data-cy="usetime"
                type="text"
                class="form-control"
                name="usetime"
                :class="{ valid: !v$.usetime.$invalid, invalid: v$.usetime.$invalid }"
                v-model="v$.usetime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.riskmanagement.systemlevel')"
              for="riskmanagement-systemlevel"
            ></label>
            <input
              type="number"
              class="form-control"
              name="systemlevel"
              id="riskmanagement-systemlevel"
              data-cy="systemlevel"
              :class="{ valid: !v$.systemlevel.$invalid, invalid: v$.systemlevel.$invalid }"
              v-model.number="v$.systemlevel.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.riskmanagement.risklevel')" for="riskmanagement-risklevel"></label>
            <select
              class="form-control"
              name="risklevel"
              :class="{ valid: !v$.risklevel.$invalid, invalid: v$.risklevel.$invalid }"
              v-model="v$.risklevel.$model"
              id="riskmanagement-risklevel"
              data-cy="risklevel"
            >
              <option
                v-for="risklevel in risklevelValues"
                :key="risklevel"
                v-bind:value="risklevel"
                v-bind:label="t$('jHipster3App.Risklevel.' + risklevel)"
              >
                {{ risklevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.riskmanagement.limitationtime')"
              for="riskmanagement-limitationtime"
            ></label>
            <input
              type="text"
              class="form-control"
              name="limitationtime"
              id="riskmanagement-limitationtime"
              data-cy="limitationtime"
              :class="{ valid: !v$.limitationtime.$invalid, invalid: v$.limitationtime.$invalid }"
              v-model="v$.limitationtime.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.riskmanagement.closetype')" for="riskmanagement-closetype"></label>
            <input
              type="number"
              class="form-control"
              name="closetype"
              id="riskmanagement-closetype"
              data-cy="closetype"
              :class="{ valid: !v$.closetype.$invalid, invalid: v$.closetype.$invalid }"
              v-model.number="v$.closetype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.riskmanagement.creatorid')" for="riskmanagement-creatorid"></label>
            <select
              class="form-control"
              id="riskmanagement-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="riskmanagement.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  riskmanagement.creatorid && officersOption.id === riskmanagement.creatorid.id ? riskmanagement.creatorid : officersOption
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
              v-text="t$('jHipster3App.riskmanagement.responsibleid')"
              for="riskmanagement-responsibleid"
            ></label>
            <select
              class="form-control"
              id="riskmanagement-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="riskmanagement.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  riskmanagement.responsibleid && officersOption.id === riskmanagement.responsibleid.id
                    ? riskmanagement.responsibleid
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
            <label class="form-control-label" v-text="t$('jHipster3App.riskmanagement.auditorid')" for="riskmanagement-auditorid"></label>
            <select
              class="form-control"
              id="riskmanagement-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="riskmanagement.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  riskmanagement.auditorid && officersOption.id === riskmanagement.auditorid.id ? riskmanagement.auditorid : officersOption
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
<script lang="ts" src="./riskmanagement-update.component.ts"></script>
