<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.outsourcingPurchasePlan.home.createOrEditLabel"
          data-cy="OutsourcingPurchasePlanCreateUpdateHeading"
          v-text="t$('jy1App.outsourcingPurchasePlan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="outsourcingPurchasePlan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="outsourcingPurchasePlan.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchasePlan.matarialname')"
              for="outsourcing-purchase-plan-matarialname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="matarialname"
              id="outsourcing-purchase-plan-matarialname"
              data-cy="matarialname"
              :class="{ valid: !v$.matarialname.$invalid, invalid: v$.matarialname.$invalid }"
              v-model="v$.matarialname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchasePlan.purchasingmethod')"
              for="outsourcing-purchase-plan-purchasingmethod"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="purchasingmethod"
              id="outsourcing-purchase-plan-purchasingmethod"
              data-cy="purchasingmethod"
              :class="{ valid: !v$.purchasingmethod.$invalid, invalid: v$.purchasingmethod.$invalid }"
              v-model.number="v$.purchasingmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchasePlan.budgit')"
              for="outsourcing-purchase-plan-budgit"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="budgit"
              id="outsourcing-purchase-plan-budgit"
              data-cy="budgit"
              :class="{ valid: !v$.budgit.$invalid, invalid: v$.budgit.$invalid }"
              v-model.number="v$.budgit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchasePlan.needtime')"
              for="outsourcing-purchase-plan-needtime"
            ></label>
            <el-date-picker v-model="v$.needtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchasePlan.planusetime')"
              for="outsourcing-purchase-plan-planusetime"
            ></label>
            <el-date-picker v-model="v$.planusetime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchasePlan.supplierid')"
              for="outsourcing-purchase-plan-supplierid"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="supplierid"
              id="outsourcing-purchase-plan-supplierid"
              data-cy="supplierid"
              :class="{ valid: !v$.supplierid.$invalid, invalid: v$.supplierid.$invalid }"
              v-model.number="v$.supplierid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchasePlan.price')"
              for="outsourcing-purchase-plan-price"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="price"
              id="outsourcing-purchase-plan-price"
              data-cy="price"
              :class="{ valid: !v$.price.$invalid, invalid: v$.price.$invalid }"
              v-model.number="v$.price.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchasePlan.secretlevel')"
              for="outsourcing-purchase-plan-secretlevel"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="outsourcing-purchase-plan-secretlevel"
              data-cy="secretlevel"
            >
              <el-option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jy1App.Secretlevel.' + secretlevel)"
                >{{ secretlevel }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchasePlan.auditStatus')"
              for="outsourcing-purchase-plan-auditStatus"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="outsourcing-purchase-plan-auditStatus"
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
              v-text="t$('jy1App.outsourcingPurchasePlan.responsibleperson')"
              for="outsourcing-purchase-plan-responsibleperson"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="outsourcing-purchase-plan-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="outsourcingPurchasePlan.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  outsourcingPurchasePlan.responsibleperson && officersOption.id === outsourcingPurchasePlan.responsibleperson.id
                    ? outsourcingPurchasePlan.responsibleperson
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.id"
                >{{ officersOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchasePlan.auditorid')"
              for="outsourcing-purchase-plan-auditorid"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="outsourcing-purchase-plan-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="outsourcingPurchasePlan.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  outsourcingPurchasePlan.auditorid && officersOption.id === outsourcingPurchasePlan.auditorid.id
                    ? outsourcingPurchasePlan.auditorid
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.id"
                >{{ officersOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.outsourcingPurchasePlan.projectwbs')" for="outsourcing-purchase-plan-projectwbs"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="outsourcing-purchase-plan-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="outsourcingPurchasePlan.projectwbs !== undefined"
              v-model="outsourcingPurchasePlan.projectwbs"
            >
              <el-option
                v-bind:value="getSelected(outsourcingPurchasePlan.projectwbs, projectwbsOption, 'id')"
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
<script lang="ts" src="./outsourcing-purchase-plan-update.component.ts"></script>
