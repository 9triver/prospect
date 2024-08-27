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
          <!-- <div class="form-group" v-if="unQualityAudit.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="unQualityAudit.id" readonly />
          </div> -->
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
              v-text="t$('jy1App.unQualityAudit.unqualitytype')"
              for="un-quality-audit-unqualitytype"
            ></label>
            <el-input
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
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.belongunitid')" for="un-quality-audit-belongunitid"></label>
            <el-input
              type="text"
              class="form-control"
              name="belongunitid"
              id="un-quality-audit-belongunitid"
              data-cy="belongunitid"
              :class="{ valid: !v$.belongunitid.$invalid, invalid: v$.belongunitid.$invalid }"
              v-model="v$.belongunitid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.unQualityAudit.belongunitname')"
              for="un-quality-audit-belongunitname"
            ></label>
            <el-input
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
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.auditteam')" for="un-quality-audit-auditteam"></label>
            <el-input
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
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.auditperson')" for="un-quality-audit-auditperson"></label>
            <el-input
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
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.unqualitynum')" for="un-quality-audit-unqualitynum"></label>
            <el-input
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
            <label class="form-control-label" v-text="t$('jy1App.unQualityAudit.creatorname')" for="un-quality-audit-creatorname"></label>
            <el-input
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
