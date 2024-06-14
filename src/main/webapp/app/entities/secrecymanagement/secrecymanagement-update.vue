<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.secrecymanagement.home.createOrEditLabel"
          data-cy="SecrecymanagementCreateUpdateHeading"
          v-text="t$('jHipster3App.secrecymanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="secrecymanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="secrecymanagement.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.secrecymanagement.secrecyid')"
              for="secrecymanagement-secrecyid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="secrecyid"
              id="secrecymanagement-secrecyid"
              data-cy="secrecyid"
              :class="{ valid: !v$.secrecyid.$invalid, invalid: v$.secrecyid.$invalid }"
              v-model.number="v$.secrecyid.$model"
            />
            <div v-if="v$.secrecyid.$anyDirty && v$.secrecyid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.secrecyid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.secrecymanagement.publishedby')"
              for="secrecymanagement-publishedby"
            ></label>
            <input
              type="text"
              class="form-control"
              name="publishedby"
              id="secrecymanagement-publishedby"
              data-cy="publishedby"
              :class="{ valid: !v$.publishedby.$invalid, invalid: v$.publishedby.$invalid }"
              v-model="v$.publishedby.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.secrecymanagement.documentname')"
              for="secrecymanagement-documentname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="documentname"
              id="secrecymanagement-documentname"
              data-cy="documentname"
              :class="{ valid: !v$.documentname.$invalid, invalid: v$.documentname.$invalid }"
              v-model="v$.documentname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.secrecymanagement.documenttype')"
              for="secrecymanagement-documenttype"
            ></label>
            <input
              type="number"
              class="form-control"
              name="documenttype"
              id="secrecymanagement-documenttype"
              data-cy="documenttype"
              :class="{ valid: !v$.documenttype.$invalid, invalid: v$.documenttype.$invalid }"
              v-model.number="v$.documenttype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.secrecymanagement.documentsize')"
              for="secrecymanagement-documentsize"
            ></label>
            <input
              type="number"
              class="form-control"
              name="documentsize"
              id="secrecymanagement-documentsize"
              data-cy="documentsize"
              :class="{ valid: !v$.documentsize.$invalid, invalid: v$.documentsize.$invalid }"
              v-model.number="v$.documentsize.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.secrecymanagement.secretlevel')"
              for="secrecymanagement-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="secrecymanagement-secretlevel"
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
              v-text="t$('jHipster3App.secrecymanagement.auditStatus')"
              for="secrecymanagement-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="secrecymanagement-auditStatus"
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
              v-text="t$('jHipster3App.secrecymanagement.creatorid')"
              for="secrecymanagement-creatorid"
            ></label>
            <select
              class="form-control"
              id="secrecymanagement-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="secrecymanagement.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  secrecymanagement.creatorid && officersOption.id === secrecymanagement.creatorid.id
                    ? secrecymanagement.creatorid
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
              v-text="t$('jHipster3App.secrecymanagement.auditorid')"
              for="secrecymanagement-auditorid"
            ></label>
            <select
              class="form-control"
              id="secrecymanagement-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="secrecymanagement.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  secrecymanagement.auditorid && officersOption.id === secrecymanagement.auditorid.id
                    ? secrecymanagement.auditorid
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
<script lang="ts" src="./secrecymanagement-update.component.ts"></script>
