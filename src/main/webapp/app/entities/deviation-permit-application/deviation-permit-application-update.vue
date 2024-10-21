<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.deviationPermitApplication.home.createOrEditLabel"
          data-cy="DeviationPermitApplicationCreateUpdateHeading"
          v-text="t$('jy1App.deviationPermitApplication.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="deviationPermitApplication.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="deviationPermitApplication.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.wbsid')"
              for="deviation-permit-application-wbsid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsid"
              id="deviation-permit-application-wbsid"
              data-cy="wbsid"
              :class="{ valid: !v$.wbsid.$invalid, invalid: v$.wbsid.$invalid }"
              v-model="v$.wbsid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.technicalfileid')"
              for="deviation-permit-application-technicalfileid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="technicalfileid"
              id="deviation-permit-application-technicalfileid"
              data-cy="technicalfileid"
              :class="{ valid: !v$.technicalfileid.$invalid, invalid: v$.technicalfileid.$invalid }"
              v-model="v$.technicalfileid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.applicationunit')"
              for="deviation-permit-application-applicationunit"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="applicationunit"
              id="deviation-permit-application-applicationunit"
              data-cy="applicationunit"
              :class="{ valid: !v$.applicationunit.$invalid, invalid: v$.applicationunit.$invalid }"
              v-model="v$.applicationunit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.applicant')"
              for="deviation-permit-application-applicant"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="applicant"
              id="deviation-permit-application-applicant"
              data-cy="applicant"
              :class="{ valid: !v$.applicant.$invalid, invalid: v$.applicant.$invalid }"
              v-model="v$.applicant.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.applicationdate')"
              for="deviation-permit-application-applicationdate"
            ></label>
            <el-date-picker v-model="v$.applicationdate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.permitcontent')"
              for="deviation-permit-application-permitcontent"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="permitcontent"
              id="deviation-permit-application-permitcontent"
              data-cy="permitcontent"
              :class="{ valid: !v$.permitcontent.$invalid, invalid: v$.permitcontent.$invalid }"
              v-model="v$.permitcontent.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.permitreason')"
              for="deviation-permit-application-permitreason"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="permitreason"
              id="deviation-permit-application-permitreason"
              data-cy="permitreason"
              :class="{ valid: !v$.permitreason.$invalid, invalid: v$.permitreason.$invalid }"
              v-model="v$.permitreason.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.projectinfluence')"
              for="deviation-permit-application-projectinfluence"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="projectinfluence"
              id="deviation-permit-application-projectinfluence"
              data-cy="projectinfluence"
              :class="{ valid: !v$.projectinfluence.$invalid, invalid: v$.projectinfluence.$invalid }"
              v-model="v$.projectinfluence.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.contractinfluence')"
              for="deviation-permit-application-contractinfluence"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractinfluence"
              id="deviation-permit-application-contractinfluence"
              data-cy="contractinfluence"
              :class="{ valid: !v$.contractinfluence.$invalid, invalid: v$.contractinfluence.$invalid }"
              v-model="v$.contractinfluence.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.permitrange')"
              for="deviation-permit-application-permitrange"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="permitrange"
              id="deviation-permit-application-permitrange"
              data-cy="permitrange"
              :class="{ valid: !v$.permitrange.$invalid, invalid: v$.permitrange.$invalid }"
              v-model="v$.permitrange.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.implementationdate')"
              for="deviation-permit-application-implementationdate"
            ></label>
            <el-date-picker v-model="v$.implementationdate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.remarks')"
              for="deviation-permit-application-remarks"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="remarks"
              id="deviation-permit-application-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.auditStatus')"
              for="deviation-permit-application-auditStatus"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="deviation-permit-application-auditStatus"
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
            <label
              class="form-control-label"
              v-text="t$('jy1App.deviationPermitApplication.projectwbs')"
              for="deviation-permit-application-projectwbs"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="deviation-permit-application-projectwbs"
              data-cy="projectwbs"
              name="projectwbs"
              v-model="deviationPermitApplication.projectwbs"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  deviationPermitApplication.projectwbs && projectwbsOption.id === deviationPermitApplication.projectwbs.id
                    ? deviationPermitApplication.projectwbs
                    : projectwbsOption
                "
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.id }}</el-option
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
<script lang="ts" src="./deviation-permit-application-update.component.ts"></script>
