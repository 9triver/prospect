<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.qualitytozero.home.createOrEditLabel"
          data-cy="QualitytozeroCreateUpdateHeading"
          v-text="t$('jy1App.qualitytozero.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualitytozero.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="qualitytozero.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualitytozero.workbagid')" for="qualitytozero-workbagid"></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="qualitytozero-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualitytozero.belongwbsid')" for="qualitytozero-belongwbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="belongwbsid"
              id="qualitytozero-belongwbsid"
              data-cy="belongwbsid"
              :class="{ valid: !v$.belongwbsid.$invalid, invalid: v$.belongwbsid.$invalid }"
              v-model="v$.belongwbsid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.outsourcingcontractid')"
              for="qualitytozero-outsourcingcontractid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="outsourcingcontractid"
              id="qualitytozero-outsourcingcontractid"
              data-cy="outsourcingcontractid"
              :class="{ valid: !v$.outsourcingcontractid.$invalid, invalid: v$.outsourcingcontractid.$invalid }"
              v-model="v$.outsourcingcontractid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.qualityproblemid')"
              for="qualitytozero-qualityproblemid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="qualityproblemid"
              id="qualitytozero-qualityproblemid"
              data-cy="qualityproblemid"
              :class="{ valid: !v$.qualityproblemid.$invalid, invalid: v$.qualityproblemid.$invalid }"
              v-model="v$.qualityproblemid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.qualityproblemname')"
              for="qualitytozero-qualityproblemname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="qualityproblemname"
              id="qualitytozero-qualityproblemname"
              data-cy="qualityproblemname"
              :class="{ valid: !v$.qualityproblemname.$invalid, invalid: v$.qualityproblemname.$invalid }"
              v-model="v$.qualityproblemname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.problemhappentime')"
              for="qualitytozero-problemhappentime"
            ></label>
            <el-date-picker v-model="v$.problemhappentime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.problemresponsibleperson')"
              for="qualitytozero-problemresponsibleperson"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="problemresponsibleperson"
              id="qualitytozero-problemresponsibleperson"
              data-cy="problemresponsibleperson"
              :class="{ valid: !v$.problemresponsibleperson.$invalid, invalid: v$.problemresponsibleperson.$invalid }"
              v-model="v$.problemresponsibleperson.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.problemresponsibleunit')"
              for="qualitytozero-problemresponsibleunit"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="problemresponsibleunit"
              id="qualitytozero-problemresponsibleunit"
              data-cy="problemresponsibleunit"
              :class="{ valid: !v$.problemresponsibleunit.$invalid, invalid: v$.problemresponsibleunit.$invalid }"
              v-model="v$.problemresponsibleunit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualitytozero.producttype')" for="qualitytozero-producttype"></label>
            <el-input
              type="text"
              class="form-control"
              name="producttype"
              id="qualitytozero-producttype"
              data-cy="producttype"
              :class="{ valid: !v$.producttype.$invalid, invalid: v$.producttype.$invalid }"
              v-model="v$.producttype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualitytozero.productname')" for="qualitytozero-productname"></label>
            <el-input
              type="text"
              class="form-control"
              name="productname"
              id="qualitytozero-productname"
              data-cy="productname"
              :class="{ valid: !v$.productname.$invalid, invalid: v$.productname.$invalid }"
              v-model="v$.productname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.problemphenomenon')"
              for="qualitytozero-problemphenomenon"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="problemphenomenon"
              id="qualitytozero-problemphenomenon"
              data-cy="problemphenomenon"
              :class="{ valid: !v$.problemphenomenon.$invalid, invalid: v$.problemphenomenon.$invalid }"
              v-model="v$.problemphenomenon.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualitytozero.problemtype')" for="qualitytozero-problemtype"></label>
            <el-input
              type="text"
              class="form-control"
              name="problemtype"
              id="qualitytozero-problemtype"
              data-cy="problemtype"
              :class="{ valid: !v$.problemtype.$invalid, invalid: v$.problemtype.$invalid }"
              v-model="v$.problemtype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualitytozero.qualitylevel')" for="qualitytozero-qualitylevel"></label>
            <el-input
              type="text"
              class="form-control"
              name="qualitylevel"
              id="qualitytozero-qualitylevel"
              data-cy="qualitylevel"
              :class="{ valid: !v$.qualitylevel.$invalid, invalid: v$.qualitylevel.$invalid }"
              v-model="v$.qualitylevel.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualitytozero.zerotype')" for="qualitytozero-zerotype"></label>
            <el-input
              type="text"
              class="form-control"
              name="zerotype"
              id="qualitytozero-zerotype"
              data-cy="zerotype"
              :class="{ valid: !v$.zerotype.$invalid, invalid: v$.zerotype.$invalid }"
              v-model="v$.zerotype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.problemreasonanalysis')"
              for="qualitytozero-problemreasonanalysis"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="problemreasonanalysis"
              id="qualitytozero-problemreasonanalysis"
              data-cy="problemreasonanalysis"
              :class="{ valid: !v$.problemreasonanalysis.$invalid, invalid: v$.problemreasonanalysis.$invalid }"
              v-model="v$.problemreasonanalysis.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.problemreasoncategory')"
              for="qualitytozero-problemreasoncategory"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="problemreasoncategory"
              id="qualitytozero-problemreasoncategory"
              data-cy="problemreasoncategory"
              :class="{ valid: !v$.problemreasoncategory.$invalid, invalid: v$.problemreasoncategory.$invalid }"
              v-model="v$.problemreasoncategory.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualitytozero.takemeasures')" for="qualitytozero-takemeasures"></label>
            <el-input
              type="text"
              class="form-control"
              name="takemeasures"
              id="qualitytozero-takemeasures"
              data-cy="takemeasures"
              :class="{ valid: !v$.takemeasures.$invalid, invalid: v$.takemeasures.$invalid }"
              v-model="v$.takemeasures.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.onebyonecategory')"
              for="qualitytozero-onebyonecategory"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="onebyonecategory"
              id="qualitytozero-onebyonecategory"
              data-cy="onebyonecategory"
              :class="{ valid: !v$.onebyonecategory.$invalid, invalid: v$.onebyonecategory.$invalid }"
              v-model="v$.onebyonecategory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.verificationeffect')"
              for="qualitytozero-verificationeffect"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="verificationeffect"
              id="qualitytozero-verificationeffect"
              data-cy="verificationeffect"
              :class="{ valid: !v$.verificationeffect.$invalid, invalid: v$.verificationeffect.$invalid }"
              v-model="v$.verificationeffect.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.qualityprojectreport')"
              for="qualitytozero-qualityprojectreport"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="qualityprojectreport"
              id="qualitytozero-qualityprojectreport"
              data-cy="qualityprojectreport"
              :class="{ valid: !v$.qualityprojectreport.$invalid, invalid: v$.qualityprojectreport.$invalid }"
              v-model="v$.qualityprojectreport.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.qualitytozeroreport')"
              for="qualitytozero-qualitytozeroreport"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="qualitytozeroreport"
              id="qualitytozero-qualitytozeroreport"
              data-cy="qualitytozeroreport"
              :class="{ valid: !v$.qualitytozeroreport.$invalid, invalid: v$.qualitytozeroreport.$invalid }"
              v-model="v$.qualitytozeroreport.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualitytozero.reviewopinion')" for="qualitytozero-reviewopinion"></label>
            <el-input
              type="text"
              class="form-control"
              name="reviewopinion"
              id="qualitytozero-reviewopinion"
              data-cy="reviewopinion"
              :class="{ valid: !v$.reviewopinion.$invalid, invalid: v$.reviewopinion.$invalid }"
              v-model="v$.reviewopinion.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualitytozero.implementationverificationtable')"
              for="qualitytozero-implementationverificationtable"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="implementationverificationtable"
              id="qualitytozero-implementationverificationtable"
              data-cy="implementationverificationtable"
              :class="{ valid: !v$.implementationverificationtable.$invalid, invalid: v$.implementationverificationtable.$invalid }"
              v-model="v$.implementationverificationtable.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualitytozero.auditStatus')" for="qualitytozero-auditStatus"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="qualitytozero-auditStatus"
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
            <label class="form-control-label" v-text="t$('jy1App.qualitytozero.workbag')" for="qualitytozero-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="qualitytozero-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="qualitytozero.workbag"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  qualitytozero.workbag && workbagOption.id === qualitytozero.workbag.id ? qualitytozero.workbag : workbagOption
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
<script lang="ts" src="./qualitytozero-update.component.ts"></script>
