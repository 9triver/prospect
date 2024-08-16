<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.fundsEstimation.home.createOrEditLabel"
          data-cy="FundsEstimationCreateUpdateHeading"
          v-text="t$('jy1App.fundsEstimation.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="fundsEstimation.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="fundsEstimation.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundsEstimation.name')" for="funds-estimation-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="funds-estimation-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundsEstimation.source')" for="funds-estimation-source"></label>
            <el-input
              type="text"
              class="form-control"
              name="source"
              id="funds-estimation-source"
              data-cy="source"
              :class="{ valid: !v$.source.$invalid, invalid: v$.source.$invalid }"
              v-model="v$.source.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundsEstimation.unit')" for="funds-estimation-unit"></label>
            <el-input
              type="text"
              class="form-control"
              name="unit"
              id="funds-estimation-unit"
              data-cy="unit"
              :class="{ valid: !v$.unit.$invalid, invalid: v$.unit.$invalid }"
              v-model="v$.unit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundsEstimation.number')" for="funds-estimation-number"></label>
            <el-input
              type="text"
              class="form-control"
              name="number"
              id="funds-estimation-number"
              data-cy="number"
              :class="{ valid: !v$.number.$invalid, invalid: v$.number.$invalid }"
              v-model="v$.number.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundsEstimation.unitprice')" for="funds-estimation-unitprice"></label>
            <el-input
              type="number"
              class="form-control"
              name="unitprice"
              id="funds-estimation-unitprice"
              data-cy="unitprice"
              :class="{ valid: !v$.unitprice.$invalid, invalid: v$.unitprice.$invalid }"
              v-model.number="v$.unitprice.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundsEstimation.materialfee')" for="funds-estimation-materialfee"></label>
            <el-input
              type="number"
              class="form-control"
              name="materialfee"
              id="funds-estimation-materialfee"
              data-cy="materialfee"
              :class="{ valid: !v$.materialfee.$invalid, invalid: v$.materialfee.$invalid }"
              v-model.number="v$.materialfee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundsEstimation.specialfee')" for="funds-estimation-specialfee"></label>
            <el-input
              type="number"
              class="form-control"
              name="specialfee"
              id="funds-estimation-specialfee"
              data-cy="specialfee"
              :class="{ valid: !v$.specialfee.$invalid, invalid: v$.specialfee.$invalid }"
              v-model.number="v$.specialfee.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.fundsEstimation.outsourcingfee')"
              for="funds-estimation-outsourcingfee"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="outsourcingfee"
              id="funds-estimation-outsourcingfee"
              data-cy="outsourcingfee"
              :class="{ valid: !v$.outsourcingfee.$invalid, invalid: v$.outsourcingfee.$invalid }"
              v-model.number="v$.outsourcingfee.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.fundsEstimation.totalbudgetprice')"
              for="funds-estimation-totalbudgetprice"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="totalbudgetprice"
              id="funds-estimation-totalbudgetprice"
              data-cy="totalbudgetprice"
              :class="{ valid: !v$.totalbudgetprice.$invalid, invalid: v$.totalbudgetprice.$invalid }"
              v-model.number="v$.totalbudgetprice.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.fundsEstimation.responsibleperson')"
              for="funds-estimation-responsibleperson"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="funds-estimation-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="fundsEstimation.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  fundsEstimation.responsibleperson && officersOption.id === fundsEstimation.responsibleperson.id
                    ? fundsEstimation.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.fundsEstimation.auditorid')" for="funds-estimation-auditorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="funds-estimation-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="fundsEstimation.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  fundsEstimation.auditorid && officersOption.id === fundsEstimation.auditorid.id
                    ? fundsEstimation.auditorid
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
            <label v-text="t$('jy1App.fundsEstimation.projectwbs')" for="funds-estimation-projectwbs"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="funds-estimation-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="fundsEstimation.projectwbs !== undefined"
              v-model="fundsEstimation.projectwbs"
            >
              <el-option
                v-bind:value="getSelected(fundsEstimation.projectwbs, projectwbsOption, 'id')"
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
<script lang="ts" src="./funds-estimation-update.component.ts"></script>
