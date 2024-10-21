<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.unQualityAudit.home.createOrEditLabel"
          data-cy="UnQualityAuditCreateUpdateHeading"
          v-text="t$('jy1App.unQualityAudit.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="unQualityAudit.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="unQualityAudit.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.workbagid')" for="un-quality-audit-workbagid"></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="un-quality-audit-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.belongwbsid')" for="un-quality-audit-belongwbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="belongwbsid"
              id="un-quality-audit-belongwbsid"
              data-cy="belongwbsid"
              :class="{ valid: !v$.belongwbsid.$invalid, invalid: v$.belongwbsid.$invalid }"
              v-model="v$.belongwbsid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.outsourcingcontractid')"
              for="un-quality-audit-outsourcingcontractid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="outsourcingcontractid"
              id="un-quality-audit-outsourcingcontractid"
              data-cy="outsourcingcontractid"
              :class="{ valid: !v$.outsourcingcontractid.$invalid, invalid: v$.outsourcingcontractid.$invalid }"
              v-model="v$.outsourcingcontractid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.unqualityid')" for="un-quality-audit-unqualityid"></label>
            <el-input
              type="text"
              class="form-control"
              name="unqualityid"
              id="un-quality-audit-unqualityid"
              data-cy="unqualityid"
              :class="{ valid: !v$.unqualityid.$invalid, invalid: v$.unqualityid.$invalid }"
              v-model="v$.unqualityid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.unqualityname')"
              for="un-quality-audit-unqualityname"
            ></label>
            <el-input
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
              v-text="t$('jy1App.unQualityAudit.unqualityunit')"
              for="un-quality-audit-unqualityunit"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="unqualityunit"
              id="un-quality-audit-unqualityunit"
              data-cy="unqualityunit"
              :class="{ valid: !v$.unqualityunit.$invalid, invalid: v$.unqualityunit.$invalid }"
              v-model="v$.unqualityunit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.unqualitytrialgroup')"
              for="un-quality-audit-unqualitytrialgroup"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="unqualitytrialgroup"
              id="un-quality-audit-unqualitytrialgroup"
              data-cy="unqualitytrialgroup"
              :class="{ valid: !v$.unqualitytrialgroup.$invalid, invalid: v$.unqualitytrialgroup.$invalid }"
              v-model="v$.unqualitytrialgroup.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.inspector')" for="un-quality-audit-inspector"></label>
            <el-input
              type="text"
              class="form-control"
              name="inspector"
              id="un-quality-audit-inspector"
              data-cy="inspector"
              :class="{ valid: !v$.inspector.$invalid, invalid: v$.inspector.$invalid }"
              v-model="v$.inspector.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.unqualitystage')"
              for="un-quality-audit-unqualitystage"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="unqualitystage"
              id="un-quality-audit-unqualitystage"
              data-cy="unqualitystage"
              :class="{ valid: !v$.unqualitystage.$invalid, invalid: v$.unqualitystage.$invalid }"
              v-model="v$.unqualitystage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.unqualitynumber')"
              for="un-quality-audit-unqualitynumber"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="unqualitynumber"
              id="un-quality-audit-unqualitynumber"
              data-cy="unqualitynumber"
              :class="{ valid: !v$.unqualitynumber.$invalid, invalid: v$.unqualitynumber.$invalid }"
              v-model.number="v$.unqualitynumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.unqualityintroduction')"
              for="un-quality-audit-unqualityintroduction"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="unqualityintroduction"
              id="un-quality-audit-unqualityintroduction"
              data-cy="unqualityintroduction"
              :class="{ valid: !v$.unqualityintroduction.$invalid, invalid: v$.unqualityintroduction.$invalid }"
              v-model="v$.unqualityintroduction.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.unqualitycategory')"
              for="un-quality-audit-unqualitycategory"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="unqualitycategory"
              id="un-quality-audit-unqualitycategory"
              data-cy="unqualitycategory"
              :class="{ valid: !v$.unqualitycategory.$invalid, invalid: v$.unqualitycategory.$invalid }"
              v-model="v$.unqualitycategory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.handlingopinion')"
              for="un-quality-audit-handlingopinion"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="handlingopinion"
              id="un-quality-audit-handlingopinion"
              data-cy="handlingopinion"
              :class="{ valid: !v$.handlingopinion.$invalid, invalid: v$.handlingopinion.$invalid }"
              v-model="v$.handlingopinion.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.applicant')" for="un-quality-audit-applicant"></label>
            <el-input
              type="text"
              class="form-control"
              name="applicant"
              id="un-quality-audit-applicant"
              data-cy="applicant"
              :class="{ valid: !v$.applicant.$invalid, invalid: v$.applicant.$invalid }"
              v-model="v$.applicant.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.applicationdate')"
              for="un-quality-audit-applicationdate"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="applicationdate"
              id="un-quality-audit-applicationdate"
              data-cy="applicationdate"
              :class="{ valid: !v$.applicationdate.$invalid, invalid: v$.applicationdate.$invalid }"
              v-model="v$.applicationdate.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.auditStatus')" for="un-quality-audit-auditStatus"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="un-quality-audit-auditStatus"
              data-cy="auditStatus"
            >
              <el-option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jy1App.AuditStatus.' + auditStatus)"
                >{{ auditStatus }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.attachment')" for="un-quality-audit-attachment"></label>
            <el-input
              type="text"
              class="form-control"
              name="attachment"
              id="un-quality-audit-attachment"
              data-cy="attachment"
              :class="{ valid: !v$.attachment.$invalid, invalid: v$.attachment.$invalid }"
              v-model="v$.attachment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.disposalmethod')"
              for="un-quality-audit-disposalmethod"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="disposalmethod"
              id="un-quality-audit-disposalmethod"
              data-cy="disposalmethod"
              :class="{ valid: !v$.disposalmethod.$invalid, invalid: v$.disposalmethod.$invalid }"
              v-model="v$.disposalmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.causeanalysis')"
              for="un-quality-audit-causeanalysis"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="causeanalysis"
              id="un-quality-audit-causeanalysis"
              data-cy="causeanalysis"
              :class="{ valid: !v$.causeanalysis.$invalid, invalid: v$.causeanalysis.$invalid }"
              v-model="v$.causeanalysis.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.correctivemeasures')"
              for="un-quality-audit-correctivemeasures"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="correctivemeasures"
              id="un-quality-audit-correctivemeasures"
              data-cy="correctivemeasures"
              :class="{ valid: !v$.correctivemeasures.$invalid, invalid: v$.correctivemeasures.$invalid }"
              v-model="v$.correctivemeasures.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.remarks')" for="un-quality-audit-remarks"></label>
            <el-input
              type="text"
              class="form-control"
              name="remarks"
              id="un-quality-audit-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.workbag')" for="un-quality-audit-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="un-quality-audit-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="unQualityAudit.workbag"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  unQualityAudit.workbag && workbagOption.id === unQualityAudit.workbag.id ? unQualityAudit.workbag : workbagOption
                "
                v-for="workbagOption in workbags"
                :key="workbagOption.id"
                :label="workbagOption.id"
                >{{ workbagOption.id }}</el-option
              >
            </el-select>
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
