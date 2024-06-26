<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.auditedbudget.home.createOrEditLabel"
          data-cy="AuditedbudgetCreateUpdateHeading"
          v-text="t$('jHipster0App.auditedbudget.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="auditedbudget.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="auditedbudget.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.createtime')" for="auditedbudget-createtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="auditedbudget-createtime"
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
                id="auditedbudget-createtime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.creatorname')" for="auditedbudget-creatorname"></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="auditedbudget-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.secretlevel')" for="auditedbudget-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="auditedbudget-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jHipster0App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.year')" for="auditedbudget-year"></label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="auditedbudget-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.budgit')" for="auditedbudget-budgit"></label>
            <input
              type="number"
              class="form-control"
              name="budgit"
              id="auditedbudget-budgit"
              data-cy="budgit"
              :class="{ valid: !v$.budgit.$invalid, invalid: v$.budgit.$invalid }"
              v-model.number="v$.budgit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.auditedbudget.dapartmentid')"
              for="auditedbudget-dapartmentid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="dapartmentid"
              id="auditedbudget-dapartmentid"
              data-cy="dapartmentid"
              :class="{ valid: !v$.dapartmentid.$invalid, invalid: v$.dapartmentid.$invalid }"
              v-model="v$.dapartmentid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.auditedbudget.draftapproval')"
              for="auditedbudget-draftapproval"
            ></label>
            <input
              type="number"
              class="form-control"
              name="draftapproval"
              id="auditedbudget-draftapproval"
              data-cy="draftapproval"
              :class="{ valid: !v$.draftapproval.$invalid, invalid: v$.draftapproval.$invalid }"
              v-model.number="v$.draftapproval.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.auditedbudget.totalbudgetid')"
              for="auditedbudget-totalbudgetid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="totalbudgetid"
              id="auditedbudget-totalbudgetid"
              data-cy="totalbudgetid"
              :class="{ valid: !v$.totalbudgetid.$invalid, invalid: v$.totalbudgetid.$invalid }"
              v-model.number="v$.totalbudgetid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.auditedbudget.unitbudgetid')"
              for="auditedbudget-unitbudgetid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="unitbudgetid"
              id="auditedbudget-unitbudgetid"
              data-cy="unitbudgetid"
              :class="{ valid: !v$.unitbudgetid.$invalid, invalid: v$.unitbudgetid.$invalid }"
              v-model.number="v$.unitbudgetid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.documentid')" for="auditedbudget-documentid"></label>
            <input
              type="text"
              class="form-control"
              name="documentid"
              id="auditedbudget-documentid"
              data-cy="documentid"
              :class="{ valid: !v$.documentid.$invalid, invalid: v$.documentid.$invalid }"
              v-model="v$.documentid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.auditedbudget.maintainerid')"
              for="auditedbudget-maintainerid"
            ></label>
            <input
              type="text"
              class="form-control"
              name="maintainerid"
              id="auditedbudget-maintainerid"
              data-cy="maintainerid"
              :class="{ valid: !v$.maintainerid.$invalid, invalid: v$.maintainerid.$invalid }"
              v-model="v$.maintainerid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.auditStatus')" for="auditedbudget-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="auditedbudget-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jHipster0App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.totalbudget')" for="auditedbudget-totalbudget"></label>
            <select
              class="form-control"
              id="auditedbudget-totalbudget"
              data-cy="totalbudget"
              name="totalbudget"
              v-model="auditedbudget.totalbudget"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  auditedbudget.totalbudget && totalbudgetOption.id === auditedbudget.totalbudget.id
                    ? auditedbudget.totalbudget
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
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.unitbudget')" for="auditedbudget-unitbudget"></label>
            <select
              class="form-control"
              id="auditedbudget-unitbudget"
              data-cy="unitbudget"
              name="unitbudget"
              v-model="auditedbudget.unitbudget"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  auditedbudget.unitbudget && unitbudgetOption.id === auditedbudget.unitbudget.id
                    ? auditedbudget.unitbudget
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
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.document')" for="auditedbudget-document"></label>
            <select class="form-control" id="auditedbudget-document" data-cy="document" name="document" v-model="auditedbudget.document">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  auditedbudget.document && documentOption.id === auditedbudget.document.id ? auditedbudget.document : documentOption
                "
                v-for="documentOption in documents"
                :key="documentOption.id"
              >
                {{ documentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.creatorid')" for="auditedbudget-creatorid"></label>
            <select
              class="form-control"
              id="auditedbudget-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="auditedbudget.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  auditedbudget.creatorid && officersOption.id === auditedbudget.creatorid.id ? auditedbudget.creatorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.auditedbudget.auditorid')" for="auditedbudget-auditorid"></label>
            <select
              class="form-control"
              id="auditedbudget-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="auditedbudget.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  auditedbudget.auditorid && officersOption.id === auditedbudget.auditorid.id ? auditedbudget.auditorid : officersOption
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
<script lang="ts" src="./auditedbudget-update.component.ts"></script>
