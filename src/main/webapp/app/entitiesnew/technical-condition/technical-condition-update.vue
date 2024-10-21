<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.technicalCondition.home.createOrEditLabel"
          data-cy="TechnicalConditionCreateUpdateHeading"
          v-text="t$('jy1App.technicalCondition.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="technicalCondition.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="technicalCondition.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.workbagid')"
              for="technical-condition-workbagid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="technical-condition-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.belongwbsid')"
              for="technical-condition-belongwbsid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="belongwbsid"
              id="technical-condition-belongwbsid"
              data-cy="belongwbsid"
              :class="{ valid: !v$.belongwbsid.$invalid, invalid: v$.belongwbsid.$invalid }"
              v-model="v$.belongwbsid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.outsourcingcontractid')"
              for="technical-condition-outsourcingcontractid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="outsourcingcontractid"
              id="technical-condition-outsourcingcontractid"
              data-cy="outsourcingcontractid"
              :class="{ valid: !v$.outsourcingcontractid.$invalid, invalid: v$.outsourcingcontractid.$invalid }"
              v-model="v$.outsourcingcontractid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.technicalid')"
              for="technical-condition-technicalid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="technicalid"
              id="technical-condition-technicalid"
              data-cy="technicalid"
              :class="{ valid: !v$.technicalid.$invalid, invalid: v$.technicalid.$invalid }"
              v-model="v$.technicalid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.technicalname')"
              for="technical-condition-technicalname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="technicalname"
              id="technical-condition-technicalname"
              data-cy="technicalname"
              :class="{ valid: !v$.technicalname.$invalid, invalid: v$.technicalname.$invalid }"
              v-model="v$.technicalname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.changedfilename')"
              for="technical-condition-changedfilename"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="changedfilename"
              id="technical-condition-changedfilename"
              data-cy="changedfilename"
              :class="{ valid: !v$.changedfilename.$invalid, invalid: v$.changedfilename.$invalid }"
              v-model="v$.changedfilename.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.applicant')"
              for="technical-condition-applicant"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="applicant"
              id="technical-condition-applicant"
              data-cy="applicant"
              :class="{ valid: !v$.applicant.$invalid, invalid: v$.applicant.$invalid }"
              v-model="v$.applicant.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.applicationdate')"
              for="technical-condition-applicationdate"
            ></label>
            <el-date-picker v-model="v$.applicationdate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.changedreason')"
              for="technical-condition-changedreason"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="changedreason"
              id="technical-condition-changedreason"
              data-cy="changedreason"
              :class="{ valid: !v$.changedreason.$invalid, invalid: v$.changedreason.$invalid }"
              v-model="v$.changedreason.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.changedbefore')"
              for="technical-condition-changedbefore"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="changedbefore"
              id="technical-condition-changedbefore"
              data-cy="changedbefore"
              :class="{ valid: !v$.changedbefore.$invalid, invalid: v$.changedbefore.$invalid }"
              v-model="v$.changedbefore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.changedafter')"
              for="technical-condition-changedafter"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="changedafter"
              id="technical-condition-changedafter"
              data-cy="changedafter"
              :class="{ valid: !v$.changedafter.$invalid, invalid: v$.changedafter.$invalid }"
              v-model="v$.changedafter.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.distributionrange')"
              for="technical-condition-distributionrange"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="distributionrange"
              id="technical-condition-distributionrange"
              data-cy="distributionrange"
              :class="{ valid: !v$.distributionrange.$invalid, invalid: v$.distributionrange.$invalid }"
              v-model="v$.distributionrange.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.technicalCondition.remarks')" for="technical-condition-remarks"></label>
            <el-input
              type="text"
              class="form-control"
              name="remarks"
              id="technical-condition-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.technicalCondition.auditStatus')"
              for="technical-condition-auditStatus"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="technical-condition-auditStatus"
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
            <label class="form-control-label" v-text="t$('jy1App.technicalCondition.workbag')" for="technical-condition-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="technical-condition-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="technicalCondition.workbag"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  technicalCondition.workbag && workbagOption.id === technicalCondition.workbag.id
                    ? technicalCondition.workbag
                    : workbagOption
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
<script lang="ts" src="./technical-condition-update.component.ts"></script>
