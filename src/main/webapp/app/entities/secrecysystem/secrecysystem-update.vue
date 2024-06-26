<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.secrecysystem.home.createOrEditLabel"
          data-cy="SecrecysystemCreateUpdateHeading"
          v-text="t$('jHipster0App.secrecysystem.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="secrecysystem.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="secrecysystem.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.secrecysystem.publishedby')" for="secrecysystem-publishedby"></label>
            <input
              type="text"
              class="form-control"
              name="publishedby"
              id="secrecysystem-publishedby"
              data-cy="publishedby"
              :class="{ valid: !v$.publishedby.$invalid, invalid: v$.publishedby.$invalid }"
              v-model="v$.publishedby.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.secrecysystem.documentname')"
              for="secrecysystem-documentname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="documentname"
              id="secrecysystem-documentname"
              data-cy="documentname"
              :class="{ valid: !v$.documentname.$invalid, invalid: v$.documentname.$invalid }"
              v-model="v$.documentname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.secrecysystem.documenttype')"
              for="secrecysystem-documenttype"
            ></label>
            <input
              type="number"
              class="form-control"
              name="documenttype"
              id="secrecysystem-documenttype"
              data-cy="documenttype"
              :class="{ valid: !v$.documenttype.$invalid, invalid: v$.documenttype.$invalid }"
              v-model.number="v$.documenttype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.secrecysystem.documentsize')"
              for="secrecysystem-documentsize"
            ></label>
            <input
              type="number"
              class="form-control"
              name="documentsize"
              id="secrecysystem-documentsize"
              data-cy="documentsize"
              :class="{ valid: !v$.documentsize.$invalid, invalid: v$.documentsize.$invalid }"
              v-model.number="v$.documentsize.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.secrecysystem.secretlevel')" for="secrecysystem-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="secrecysystem-secretlevel"
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
            <label class="form-control-label" v-text="t$('jHipster0App.secrecysystem.auditStatus')" for="secrecysystem-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="secrecysystem-auditStatus"
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
            <label class="form-control-label" v-text="t$('jHipster0App.secrecysystem.creatorid')" for="secrecysystem-creatorid"></label>
            <select
              class="form-control"
              id="secrecysystem-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="secrecysystem.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  secrecysystem.creatorid && officersOption.id === secrecysystem.creatorid.id ? secrecysystem.creatorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.secrecysystem.auditorid')" for="secrecysystem-auditorid"></label>
            <select
              class="form-control"
              id="secrecysystem-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="secrecysystem.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  secrecysystem.auditorid && officersOption.id === secrecysystem.auditorid.id ? secrecysystem.auditorid : officersOption
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
<script lang="ts" src="./secrecysystem-update.component.ts"></script>
