<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.pbsmanage.home.createOrEditLabel"
          data-cy="PbsmanageCreateUpdateHeading"
          v-text="t$('jHipster0App.pbsmanage.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="pbsmanage.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="pbsmanage.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.pbsname')" for="pbsmanage-pbsname"></label>
            <input
              type="text"
              class="form-control"
              name="pbsname"
              id="pbsmanage-pbsname"
              data-cy="pbsname"
              :class="{ valid: !v$.pbsname.$invalid, invalid: v$.pbsname.$invalid }"
              v-model="v$.pbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.number')" for="pbsmanage-number"></label>
            <input
              type="number"
              class="form-control"
              name="number"
              id="pbsmanage-number"
              data-cy="number"
              :class="{ valid: !v$.number.$invalid, invalid: v$.number.$invalid }"
              v-model.number="v$.number.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.type')" for="pbsmanage-type"></label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="pbsmanage-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.starttime')" for="pbsmanage-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="pbsmanage-starttime"
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
                id="pbsmanage-starttime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.endtime')" for="pbsmanage-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="pbsmanage-endtime"
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
                id="pbsmanage-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.administratorid')" for="pbsmanage-administratorid"></label>
            <input
              type="text"
              class="form-control"
              name="administratorid"
              id="pbsmanage-administratorid"
              data-cy="administratorid"
              :class="{ valid: !v$.administratorid.$invalid, invalid: v$.administratorid.$invalid }"
              v-model="v$.administratorid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.pbsmanage.administratorname')"
              for="pbsmanage-administratorname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="administratorname"
              id="pbsmanage-administratorname"
              data-cy="administratorname"
              :class="{ valid: !v$.administratorname.$invalid, invalid: v$.administratorname.$invalid }"
              v-model="v$.administratorname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.responsiblename')" for="pbsmanage-responsiblename"></label>
            <input
              type="text"
              class="form-control"
              name="responsiblename"
              id="pbsmanage-responsiblename"
              data-cy="responsiblename"
              :class="{ valid: !v$.responsiblename.$invalid, invalid: v$.responsiblename.$invalid }"
              v-model="v$.responsiblename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.department')" for="pbsmanage-department"></label>
            <input
              type="text"
              class="form-control"
              name="department"
              id="pbsmanage-department"
              data-cy="department"
              :class="{ valid: !v$.department.$invalid, invalid: v$.department.$invalid }"
              v-model="v$.department.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.secretlevel')" for="pbsmanage-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="pbsmanage-secretlevel"
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
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.auditStatus')" for="pbsmanage-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="pbsmanage-auditStatus"
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
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.auditUserid')" for="pbsmanage-auditUserid"></label>
            <input
              type="text"
              class="form-control"
              name="auditUserid"
              id="pbsmanage-auditUserid"
              data-cy="auditUserid"
              :class="{ valid: !v$.auditUserid.$invalid, invalid: v$.auditUserid.$invalid }"
              v-model="v$.auditUserid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.pbssubmanage')" for="pbsmanage-pbssubmanage"></label>
            <select
              class="form-control"
              id="pbsmanage-pbssubmanage"
              data-cy="pbssubmanage"
              name="pbssubmanage"
              v-model="pbsmanage.pbssubmanage"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  pbsmanage.pbssubmanage && pbssubmanageOption.id === pbsmanage.pbssubmanage.id
                    ? pbsmanage.pbssubmanage
                    : pbssubmanageOption
                "
                v-for="pbssubmanageOption in pbssubmanages"
                :key="pbssubmanageOption.id"
              >
                {{ pbssubmanageOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.responsibleid')" for="pbsmanage-responsibleid"></label>
            <select
              class="form-control"
              id="pbsmanage-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="pbsmanage.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  pbsmanage.responsibleid && officersOption.id === pbsmanage.responsibleid.id ? pbsmanage.responsibleid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.pbsmanage.auditorid')" for="pbsmanage-auditorid"></label>
            <select class="form-control" id="pbsmanage-auditorid" data-cy="auditorid" name="auditorid" v-model="pbsmanage.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="pbsmanage.auditorid && officersOption.id === pbsmanage.auditorid.id ? pbsmanage.auditorid : officersOption"
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
<script lang="ts" src="./pbsmanage-update.component.ts"></script>
