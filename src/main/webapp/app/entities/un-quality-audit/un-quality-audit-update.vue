<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.unQualityAudit.home.createOrEditLabel"
          data-cy="UnQualityAuditCreateUpdateHeading"
          v-text="t$('jHipster3App.unQualityAudit.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="unQualityAudit.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="unQualityAudit.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unQualityAudit.unqualityid')"
              for="un-quality-audit-unqualityid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="unqualityid"
              id="un-quality-audit-unqualityid"
              data-cy="unqualityid"
              :class="{ valid: !v$.unqualityid.$invalid, invalid: v$.unqualityid.$invalid }"
              v-model.number="v$.unqualityid.$model"
            />
            <div v-if="v$.unqualityid.$anyDirty && v$.unqualityid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.unqualityid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unQualityAudit.unqualityname')"
              for="un-quality-audit-unqualityname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="unqualityname"
              id="un-quality-audit-unqualityname"
              data-cy="unqualityname"
              :class="{ valid: !v$.unqualityname.$invalid, invalid: v$.unqualityname.$invalid }"
              v-model="v$.unqualityname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unQualityAudit.unqualitytype')"
              for="un-quality-audit-unqualitytype"
            ></label>
            <input
              type="number"
              class="form-control"
              name="unqualitytype"
              id="un-quality-audit-unqualitytype"
              data-cy="unqualitytype"
              :class="{ valid: !v$.unqualitytype.$invalid, invalid: v$.unqualitytype.$invalid }"
              v-model.number="v$.unqualitytype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unQualityAudit.belongunitid')"
              for="un-quality-audit-belongunitid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="belongunitid"
              id="un-quality-audit-belongunitid"
              data-cy="belongunitid"
              :class="{ valid: !v$.belongunitid.$invalid, invalid: v$.belongunitid.$invalid }"
              v-model.number="v$.belongunitid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unQualityAudit.belongunitname')"
              for="un-quality-audit-belongunitname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="belongunitname"
              id="un-quality-audit-belongunitname"
              data-cy="belongunitname"
              :class="{ valid: !v$.belongunitname.$invalid, invalid: v$.belongunitname.$invalid }"
              v-model="v$.belongunitname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.unQualityAudit.auditteam')" for="un-quality-audit-auditteam"></label>
            <input
              type="text"
              class="form-control"
              name="auditteam"
              id="un-quality-audit-auditteam"
              data-cy="auditteam"
              :class="{ valid: !v$.auditteam.$invalid, invalid: v$.auditteam.$invalid }"
              v-model="v$.auditteam.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unQualityAudit.auditperson')"
              for="un-quality-audit-auditperson"
            ></label>
            <input
              type="text"
              class="form-control"
              name="auditperson"
              id="un-quality-audit-auditperson"
              data-cy="auditperson"
              :class="{ valid: !v$.auditperson.$invalid, invalid: v$.auditperson.$invalid }"
              v-model="v$.auditperson.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unQualityAudit.unqualitynum')"
              for="un-quality-audit-unqualitynum"
            ></label>
            <input
              type="number"
              class="form-control"
              name="unqualitynum"
              id="un-quality-audit-unqualitynum"
              data-cy="unqualitynum"
              :class="{ valid: !v$.unqualitynum.$invalid, invalid: v$.unqualitynum.$invalid }"
              v-model.number="v$.unqualitynum.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unQualityAudit.creatorname')"
              for="un-quality-audit-creatorname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="un-quality-audit-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.unQualityAudit.auditStatus')"
              for="un-quality-audit-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="un-quality-audit-auditStatus"
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
            <label class="form-control-label" v-text="t$('jHipster3App.unQualityAudit.inspector')" for="un-quality-audit-inspector"></label>
            <select
              class="form-control"
              id="un-quality-audit-inspector"
              data-cy="inspector"
              name="inspector"
              v-model="unQualityAudit.inspector"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  unQualityAudit.inspector && officersOption.id === unQualityAudit.inspector.id ? unQualityAudit.inspector : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.unQualityAudit.auditorid')" for="un-quality-audit-auditorid"></label>
            <select
              class="form-control"
              id="un-quality-audit-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="unQualityAudit.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  unQualityAudit.auditorid && officersOption.id === unQualityAudit.auditorid.id ? unQualityAudit.auditorid : officersOption
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
<script lang="ts" src="./un-quality-audit-update.component.ts"></script>
