<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.riskReturn.home.createOrEditLabel"
          data-cy="RiskReturnCreateUpdateHeading"
          v-text="t$('jy1App.riskReturn.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="riskReturn.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="riskReturn.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReturn.belongriskid')" for="risk-return-belongriskid"></label>
            <el-input
              type="number"
              class="form-control"
              name="belongriskid"
              id="risk-return-belongriskid"
              data-cy="belongriskid"
              :class="{ valid: !v$.belongriskid.$invalid, invalid: v$.belongriskid.$invalid }"
              v-model.number="v$.belongriskid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReturn.status')" for="risk-return-status"></label>
            <el-input
              type="text"
              class="form-control"
              name="status"
              id="risk-return-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReturn.closestatus')" for="risk-return-closestatus"></label>
            <el-input
              type="text"
              class="form-control"
              name="closestatus"
              id="risk-return-closestatus"
              data-cy="closestatus"
              :class="{ valid: !v$.closestatus.$invalid, invalid: v$.closestatus.$invalid }"
              v-model="v$.closestatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReturn.evidencefile')" for="risk-return-evidencefile"></label>
            <el-input
              type="text"
              class="form-control"
              name="evidencefile"
              id="risk-return-evidencefile"
              data-cy="evidencefile"
              :class="{ valid: !v$.evidencefile.$invalid, invalid: v$.evidencefile.$invalid }"
              v-model="v$.evidencefile.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReturn.riskid')" for="risk-return-riskid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="risk-return-riskid"
              data-cy="riskid"
              name="riskid"
              v-model="riskReturn.riskid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="riskReturn.riskid && projectRiskOption.id === riskReturn.riskid.id ? riskReturn.riskid : projectRiskOption"
                v-for="projectRiskOption in projectRisks"
                :key="projectRiskOption.id"
                :label="projectRiskOption.id"
                >{{ projectRiskOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReturn.creatorid')" for="risk-return-creatorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="risk-return-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="riskReturn.creatorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  riskReturn.creatorid && hrManagementOption.id === riskReturn.creatorid.id ? riskReturn.creatorid : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
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
<script lang="ts" src="./risk-return-update.component.ts"></script>
