<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.outsourcingPurchaseExecute.home.createOrEditLabel"
          data-cy="OutsourcingPurchaseExecuteCreateUpdateHeading"
          v-text="t$('jy1App.outsourcingPurchaseExecute.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="outsourcingPurchaseExecute.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="outsourcingPurchaseExecute.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchaseExecute.matarialname')"
              for="outsourcing-purchase-execute-matarialname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="matarialname"
              id="outsourcing-purchase-execute-matarialname"
              data-cy="matarialname"
              :class="{ valid: !v$.matarialname.$invalid, invalid: v$.matarialname.$invalid }"
              v-model="v$.matarialname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchaseExecute.purchasingmethod')"
              for="outsourcing-purchase-execute-purchasingmethod"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="purchasingmethod"
              id="outsourcing-purchase-execute-purchasingmethod"
              data-cy="purchasingmethod"
              :class="{ valid: !v$.purchasingmethod.$invalid, invalid: v$.purchasingmethod.$invalid }"
              v-model.number="v$.purchasingmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchaseExecute.budgit')"
              for="outsourcing-purchase-execute-budgit"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="budgit"
              id="outsourcing-purchase-execute-budgit"
              data-cy="budgit"
              :class="{ valid: !v$.budgit.$invalid, invalid: v$.budgit.$invalid }"
              v-model.number="v$.budgit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchaseExecute.needtime')"
              for="outsourcing-purchase-execute-needtime"
            ></label>
            <el-date-picker v-model="v$.needtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchaseExecute.planusetime')"
              for="outsourcing-purchase-execute-planusetime"
            ></label>
            <el-date-picker v-model="v$.planusetime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchaseExecute.supplierid')"
              for="outsourcing-purchase-execute-supplierid"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="supplierid"
              id="outsourcing-purchase-execute-supplierid"
              data-cy="supplierid"
              :class="{ valid: !v$.supplierid.$invalid, invalid: v$.supplierid.$invalid }"
              v-model.number="v$.supplierid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchaseExecute.price')"
              for="outsourcing-purchase-execute-price"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="price"
              id="outsourcing-purchase-execute-price"
              data-cy="price"
              :class="{ valid: !v$.price.$invalid, invalid: v$.price.$invalid }"
              v-model.number="v$.price.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingPurchaseExecute.secretlevel')"
              for="outsourcing-purchase-execute-secretlevel"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="outsourcing-purchase-execute-secretlevel"
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
              v-text="t$('jy1App.outsourcingPurchaseExecute.responsibleperson')"
              for="outsourcing-purchase-execute-responsibleperson"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="outsourcing-purchase-execute-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="outsourcingPurchaseExecute.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  outsourcingPurchaseExecute.responsibleperson && officersOption.id === outsourcingPurchaseExecute.responsibleperson.id
                    ? outsourcingPurchaseExecute.responsibleperson
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
              v-text="t$('jy1App.outsourcingPurchaseExecute.outsourcingplanid')"
              for="outsourcing-purchase-execute-outsourcingplanid"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="outsourcing-purchase-execute-outsourcingplanid"
              data-cy="outsourcingplanid"
              name="outsourcingplanid"
              v-model="outsourcingPurchaseExecute.outsourcingplanid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  outsourcingPurchaseExecute.outsourcingplanid &&
                  outsourcingPurchasePlanOption.id === outsourcingPurchaseExecute.outsourcingplanid.id
                    ? outsourcingPurchaseExecute.outsourcingplanid
                    : outsourcingPurchasePlanOption
                "
                v-for="outsourcingPurchasePlanOption in outsourcingPurchasePlans"
                :key="outsourcingPurchasePlanOption.id"
                :label="outsourcingPurchasePlanOption.id"
                >{{ outsourcingPurchasePlanOption.id }}</el-option
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
<script lang="ts" src="./outsourcing-purchase-execute-update.component.ts"></script>
