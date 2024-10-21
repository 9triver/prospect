<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.customerSatisfaction.home.createOrEditLabel"
          data-cy="CustomerSatisfactionCreateUpdateHeading"
          v-text="t$('jy1App.customerSatisfaction.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="customerSatisfaction.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="customerSatisfaction.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.customerSatisfaction.year')" for="customer-satisfaction-year"></label>
            <el-input
              type="number"
              class="form-control"
              name="year"
              id="customer-satisfaction-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.customerSatisfaction.satisfactionitem')"
              for="customer-satisfaction-satisfactionitem"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="satisfactionitem"
              id="customer-satisfaction-satisfactionitem"
              data-cy="satisfactionitem"
              :class="{ valid: !v$.satisfactionitem.$invalid, invalid: v$.satisfactionitem.$invalid }"
              v-model="v$.satisfactionitem.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.customerSatisfaction.score')" for="customer-satisfaction-score"></label>
            <el-input
              type="number"
              class="form-control"
              name="score"
              id="customer-satisfaction-score"
              data-cy="score"
              :class="{ valid: !v$.score.$invalid, invalid: v$.score.$invalid }"
              v-model.number="v$.score.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.customerSatisfaction.opinion')"
              for="customer-satisfaction-opinion"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="opinion"
              id="customer-satisfaction-opinion"
              data-cy="opinion"
              :class="{ valid: !v$.opinion.$invalid, invalid: v$.opinion.$invalid }"
              v-model="v$.opinion.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.customerSatisfaction.totalscore')"
              for="customer-satisfaction-totalscore"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="totalscore"
              id="customer-satisfaction-totalscore"
              data-cy="totalscore"
              :class="{ valid: !v$.totalscore.$invalid, invalid: v$.totalscore.$invalid }"
              v-model.number="v$.totalscore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.customerSatisfaction.surveytime')"
              for="customer-satisfaction-surveytime"
            ></label>
            <el-date-picker v-model="v$.surveytime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.customerSatisfaction.customer')"
              for="customer-satisfaction-customer"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="customer"
              id="customer-satisfaction-customer"
              data-cy="customer"
              :class="{ valid: !v$.customer.$invalid, invalid: v$.customer.$invalid }"
              v-model="v$.customer.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.customerSatisfaction.plonenumber')"
              for="customer-satisfaction-plonenumber"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="plonenumber"
              id="customer-satisfaction-plonenumber"
              data-cy="plonenumber"
              :class="{ valid: !v$.plonenumber.$invalid, invalid: v$.plonenumber.$invalid }"
              v-model="v$.plonenumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.customerSatisfaction.filename')"
              for="customer-satisfaction-filename"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="filename"
              id="customer-satisfaction-filename"
              data-cy="filename"
              :class="{ valid: !v$.filename.$invalid, invalid: v$.filename.$invalid }"
              v-model="v$.filename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.customerSatisfaction.wbsid')" for="customer-satisfaction-wbsid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="customer-satisfaction-wbsid"
              data-cy="wbsid"
              name="wbsid"
              v-model="customerSatisfaction.wbsid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  customerSatisfaction.wbsid && projectwbsOption.id === customerSatisfaction.wbsid.id
                    ? customerSatisfaction.wbsid
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
<script lang="ts" src="./customer-satisfaction-update.component.ts"></script>
