<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.fundsmanagement.home.createOrEditLabel"
          data-cy="FundsmanagementCreateUpdateHeading"
          v-text="t$('jHipster3App.fundsmanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="fundsmanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="fundsmanagement.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.fundsmanagement.fundsid')" for="fundsmanagement-fundsid"></label>
            <input
              type="number"
              class="form-control"
              name="fundsid"
              id="fundsmanagement-fundsid"
              data-cy="fundsid"
              :class="{ valid: !v$.fundsid.$invalid, invalid: v$.fundsid.$invalid }"
              v-model.number="v$.fundsid.$model"
            />
            <div v-if="v$.fundsid.$anyDirty && v$.fundsid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.fundsid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsmanagement.createtime')"
              for="fundsmanagement-createtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="fundsmanagement-createtime"
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
                id="fundsmanagement-createtime"
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
              v-text="t$('jHipster3App.fundsmanagement.creatorname')"
              for="fundsmanagement-creatorname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="fundsmanagement-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsmanagement.secretlevel')"
              for="fundsmanagement-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="fundsmanagement-secretlevel"
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
            <label class="form-control-label" v-text="t$('jHipster3App.fundsmanagement.year')" for="fundsmanagement-year"></label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="fundsmanagement-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.fundsmanagement.budgit')" for="fundsmanagement-budgit"></label>
            <input
              type="number"
              class="form-control"
              name="budgit"
              id="fundsmanagement-budgit"
              data-cy="budgit"
              :class="{ valid: !v$.budgit.$invalid, invalid: v$.budgit.$invalid }"
              v-model.number="v$.budgit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsmanagement.dapartmentid')"
              for="fundsmanagement-dapartmentid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="dapartmentid"
              id="fundsmanagement-dapartmentid"
              data-cy="dapartmentid"
              :class="{ valid: !v$.dapartmentid.$invalid, invalid: v$.dapartmentid.$invalid }"
              v-model="v$.dapartmentid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsmanagement.draftapproval')"
              for="fundsmanagement-draftapproval"
            ></label>
            <input
              type="number"
              class="form-control"
              name="draftapproval"
              id="fundsmanagement-draftapproval"
              data-cy="draftapproval"
              :class="{ valid: !v$.draftapproval.$invalid, invalid: v$.draftapproval.$invalid }"
              v-model.number="v$.draftapproval.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsmanagement.totalbudgetid')"
              for="fundsmanagement-totalbudgetid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalbudgetid"
              id="fundsmanagement-totalbudgetid"
              data-cy="totalbudgetid"
              :class="{ valid: !v$.totalbudgetid.$invalid, invalid: v$.totalbudgetid.$invalid }"
              v-model.number="v$.totalbudgetid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsmanagement.unitbudgetid')"
              for="fundsmanagement-unitbudgetid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="unitbudgetid"
              id="fundsmanagement-unitbudgetid"
              data-cy="unitbudgetid"
              :class="{ valid: !v$.unitbudgetid.$invalid, invalid: v$.unitbudgetid.$invalid }"
              v-model.number="v$.unitbudgetid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsmanagement.documentid')"
              for="fundsmanagement-documentid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="documentid"
              id="fundsmanagement-documentid"
              data-cy="documentid"
              :class="{ valid: !v$.documentid.$invalid, invalid: v$.documentid.$invalid }"
              v-model.number="v$.documentid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsmanagement.maintainerid')"
              for="fundsmanagement-maintainerid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="maintainerid"
              id="fundsmanagement-maintainerid"
              data-cy="maintainerid"
              :class="{ valid: !v$.maintainerid.$invalid, invalid: v$.maintainerid.$invalid }"
              v-model.number="v$.maintainerid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsmanagement.auditStatus')"
              for="fundsmanagement-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="fundsmanagement-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jHipster3App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsmanagement.totalbudget')"
              for="fundsmanagement-totalbudget"
            ></label>
            <select
              class="form-control"
              id="fundsmanagement-totalbudget"
              data-cy="totalbudget"
              name="totalbudget"
              v-model="fundsmanagement.totalbudget"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsmanagement.totalbudget && totalbudgetOption.id === fundsmanagement.totalbudget.id
                    ? fundsmanagement.totalbudget
                    : totalbudgetOption
                "
                v-for="totalbudgetOption in totalbudgets"
                :key="totalbudgetOption.id"
              >
                {{ totalbudgetOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.fundsmanagement.unitbudget')"
              for="fundsmanagement-unitbudget"
            ></label>
            <select
              class="form-control"
              id="fundsmanagement-unitbudget"
              data-cy="unitbudget"
              name="unitbudget"
              v-model="fundsmanagement.unitbudget"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsmanagement.unitbudget && unitbudgetOption.id === fundsmanagement.unitbudget.id
                    ? fundsmanagement.unitbudget
                    : unitbudgetOption
                "
                v-for="unitbudgetOption in unitbudgets"
                :key="unitbudgetOption.id"
              >
                {{ unitbudgetOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.fundsmanagement.document')" for="fundsmanagement-document"></label>
            <select
              class="form-control"
              id="fundsmanagement-document"
              data-cy="document"
              name="document"
              v-model="fundsmanagement.document"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsmanagement.document && documentOption.id === fundsmanagement.document.id ? fundsmanagement.document : documentOption
                "
                v-for="documentOption in documents"
                :key="documentOption.id"
              >
                {{ documentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.fundsmanagement.creatorid')" for="fundsmanagement-creatorid"></label>
            <select
              class="form-control"
              id="fundsmanagement-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="fundsmanagement.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsmanagement.creatorid && officersOption.id === fundsmanagement.creatorid.id
                    ? fundsmanagement.creatorid
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
            <label class="form-control-label" v-text="t$('jHipster3App.fundsmanagement.auditorid')" for="fundsmanagement-auditorid"></label>
            <select
              class="form-control"
              id="fundsmanagement-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="fundsmanagement.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsmanagement.auditorid && officersOption.id === fundsmanagement.auditorid.id
                    ? fundsmanagement.auditorid
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
<script lang="ts" src="./fundsmanagement-update.component.ts"></script>
