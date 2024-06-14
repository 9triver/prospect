<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.contractualfunds.home.createOrEditLabel"
          data-cy="ContractualfundsCreateUpdateHeading"
          v-text="t$('jHipster3App.contractualfunds.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="contractualfunds.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="contractualfunds.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.contractualfunds.contractualid')"
              for="contractualfunds-contractualid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="contractualid"
              id="contractualfunds-contractualid"
              data-cy="contractualid"
              :class="{ valid: !v$.contractualid.$invalid, invalid: v$.contractualid.$invalid }"
              v-model.number="v$.contractualid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.contractualfunds.department')"
              for="contractualfunds-department"
            ></label>
            <input
              type="number"
              class="form-control"
              name="department"
              id="contractualfunds-department"
              data-cy="department"
              :class="{ valid: !v$.department.$invalid, invalid: v$.department.$invalid }"
              v-model.number="v$.department.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.contractualfunds.year')" for="contractualfunds-year"></label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="contractualfunds-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.contractualfunds.starttime')"
              for="contractualfunds-starttime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="contractualfunds-starttime"
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
                id="contractualfunds-starttime"
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
            <label class="form-control-label" v-text="t$('jHipster3App.contractualfunds.endtime')" for="contractualfunds-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="contractualfunds-endtime"
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
                id="contractualfunds-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster3App.contractualfunds.status')" for="contractualfunds-status"></label>
            <input
              type="number"
              class="form-control"
              name="status"
              id="contractualfunds-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model.number="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.contractualfunds.secretlevel')"
              for="contractualfunds-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="contractualfunds-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jHipster3App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.contractualfunds.foreigncurrency')"
              for="contractualfunds-foreigncurrency"
            ></label>
            <input
              type="number"
              class="form-control"
              name="foreigncurrency"
              id="contractualfunds-foreigncurrency"
              data-cy="foreigncurrency"
              :class="{ valid: !v$.foreigncurrency.$invalid, invalid: v$.foreigncurrency.$invalid }"
              v-model.number="v$.foreigncurrency.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.contractualfunds.totalbudget')"
              for="contractualfunds-totalbudget"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalbudget"
              id="contractualfunds-totalbudget"
              data-cy="totalbudget"
              :class="{ valid: !v$.totalbudget.$invalid, invalid: v$.totalbudget.$invalid }"
              v-model.number="v$.totalbudget.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.contractualfunds.fundsinplace')"
              for="contractualfunds-fundsinplace"
            ></label>
            <input
              type="number"
              class="form-control"
              name="fundsinplace"
              id="contractualfunds-fundsinplace"
              data-cy="fundsinplace"
              :class="{ valid: !v$.fundsinplace.$invalid, invalid: v$.fundsinplace.$invalid }"
              v-model.number="v$.fundsinplace.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.contractualfunds.responsibleunitname')"
              for="contractualfunds-responsibleunitname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="responsibleunitname"
              id="contractualfunds-responsibleunitname"
              data-cy="responsibleunitname"
              :class="{ valid: !v$.responsibleunitname.$invalid, invalid: v$.responsibleunitname.$invalid }"
              v-model="v$.responsibleunitname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.contractualfunds.audittime')"
              for="contractualfunds-audittime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="contractualfunds-audittime"
                  v-model="v$.audittime.$model"
                  name="audittime"
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
                id="contractualfunds-audittime"
                data-cy="audittime"
                type="text"
                class="form-control"
                name="audittime"
                :class="{ valid: !v$.audittime.$invalid, invalid: v$.audittime.$invalid }"
                v-model="v$.audittime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.contractualfunds.accountbank')"
              for="contractualfunds-accountbank"
            ></label>
            <input
              type="number"
              class="form-control"
              name="accountbank"
              id="contractualfunds-accountbank"
              data-cy="accountbank"
              :class="{ valid: !v$.accountbank.$invalid, invalid: v$.accountbank.$invalid }"
              v-model.number="v$.accountbank.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.contractualfunds.creatorid')"
              for="contractualfunds-creatorid"
            ></label>
            <select
              class="form-control"
              id="contractualfunds-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="contractualfunds.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  contractualfunds.creatorid && officersOption.id === contractualfunds.creatorid.id
                    ? contractualfunds.creatorid
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
              v-text="t$('jHipster3App.contractualfunds.auditorid')"
              for="contractualfunds-auditorid"
            ></label>
            <select
              class="form-control"
              id="contractualfunds-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="contractualfunds.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  contractualfunds.auditorid && officersOption.id === contractualfunds.auditorid.id
                    ? contractualfunds.auditorid
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
<script lang="ts" src="./contractualfunds-update.component.ts"></script>
