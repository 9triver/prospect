<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.riskidentification.home.createOrEditLabel"
          data-cy="RiskidentificationCreateUpdateHeading"
          v-text="t$('jHipster0App.riskidentification.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="riskidentification.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="riskidentification.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskidentification.projectname')"
              for="riskidentification-projectname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="projectname"
              id="riskidentification-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.riskidentification.year')" for="riskidentification-year"></label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="riskidentification-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskidentification.nodename')"
              for="riskidentification-nodename"
            ></label>
            <input
              type="text"
              class="form-control"
              name="nodename"
              id="riskidentification-nodename"
              data-cy="nodename"
              :class="{ valid: !v$.nodename.$invalid, invalid: v$.nodename.$invalid }"
              v-model="v$.nodename.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskidentification.risktype')"
              for="riskidentification-risktype"
            ></label>
            <input
              type="number"
              class="form-control"
              name="risktype"
              id="riskidentification-risktype"
              data-cy="risktype"
              :class="{ valid: !v$.risktype.$invalid, invalid: v$.risktype.$invalid }"
              v-model.number="v$.risktype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskidentification.decumentid')"
              for="riskidentification-decumentid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="decumentid"
              id="riskidentification-decumentid"
              data-cy="decumentid"
              :class="{ valid: !v$.decumentid.$invalid, invalid: v$.decumentid.$invalid }"
              v-model.number="v$.decumentid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskidentification.version')"
              for="riskidentification-version"
            ></label>
            <input
              type="number"
              class="form-control"
              name="version"
              id="riskidentification-version"
              data-cy="version"
              :class="{ valid: !v$.version.$invalid, invalid: v$.version.$invalid }"
              v-model.number="v$.version.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskidentification.usetime')"
              for="riskidentification-usetime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="riskidentification-usetime"
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
                id="riskidentification-usetime"
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
              v-text="t$('jHipster0App.riskidentification.systemlevel')"
              for="riskidentification-systemlevel"
            ></label>
            <input
              type="number"
              class="form-control"
              name="systemlevel"
              id="riskidentification-systemlevel"
              data-cy="systemlevel"
              :class="{ valid: !v$.systemlevel.$invalid, invalid: v$.systemlevel.$invalid }"
              v-model.number="v$.systemlevel.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskidentification.risklevel')"
              for="riskidentification-risklevel"
            ></label>
            <select
              class="form-control"
              name="risklevel"
              :class="{ valid: !v$.risklevel.$invalid, invalid: v$.risklevel.$invalid }"
              v-model="v$.risklevel.$model"
              id="riskidentification-risklevel"
              data-cy="risklevel"
            >
              <option
                v-for="risklevel in risklevelValues"
                :key="risklevel"
                v-bind:value="risklevel"
                v-bind:label="t$('jHipster0App.Risklevel.' + risklevel)"
              >
                {{ risklevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskidentification.limitationtime')"
              for="riskidentification-limitationtime"
            ></label>
            <input
              type="text"
              class="form-control"
              name="limitationtime"
              id="riskidentification-limitationtime"
              data-cy="limitationtime"
              :class="{ valid: !v$.limitationtime.$invalid, invalid: v$.limitationtime.$invalid }"
              v-model="v$.limitationtime.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskidentification.closetype')"
              for="riskidentification-closetype"
            ></label>
            <input
              type="number"
              class="form-control"
              name="closetype"
              id="riskidentification-closetype"
              data-cy="closetype"
              :class="{ valid: !v$.closetype.$invalid, invalid: v$.closetype.$invalid }"
              v-model.number="v$.closetype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskidentification.creatorid')"
              for="riskidentification-creatorid"
            ></label>
            <select
              class="form-control"
              id="riskidentification-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="riskidentification.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  riskidentification.creatorid && officersOption.id === riskidentification.creatorid.id
                    ? riskidentification.creatorid
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
              v-text="t$('jHipster0App.riskidentification.responsibleid')"
              for="riskidentification-responsibleid"
            ></label>
            <select
              class="form-control"
              id="riskidentification-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="riskidentification.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  riskidentification.responsibleid && officersOption.id === riskidentification.responsibleid.id
                    ? riskidentification.responsibleid
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
              v-text="t$('jHipster0App.riskidentification.auditorid')"
              for="riskidentification-auditorid"
            ></label>
            <select
              class="form-control"
              id="riskidentification-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="riskidentification.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  riskidentification.auditorid && officersOption.id === riskidentification.auditorid.id
                    ? riskidentification.auditorid
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
<script lang="ts" src="./riskidentification-update.component.ts"></script>
