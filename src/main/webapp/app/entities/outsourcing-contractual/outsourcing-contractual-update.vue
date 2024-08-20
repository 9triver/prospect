<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.outsourcingContractual.home.createOrEditLabel"
          data-cy="OutsourcingContractualCreateUpdateHeading"
          v-text="t$('jy1App.outsourcingContractual.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="outsourcingContractual.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="outsourcingContractual.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContractual.department')"
              for="outsourcing-contractual-department"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="department"
              id="outsourcing-contractual-department"
              data-cy="department"
              :class="{ valid: !v$.department.$invalid, invalid: v$.department.$invalid }"
              v-model="v$.department.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.outsourcingContractual.year')" for="outsourcing-contractual-year"></label>
            <el-input
              type="number"
              class="form-control"
              name="year"
              id="outsourcing-contractual-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContractual.starttime')"
              for="outsourcing-contractual-starttime"
            ></label>
            <el-date-picker v-model="v$.starttime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContractual.endtime')"
              for="outsourcing-contractual-endtime"
            ></label>
            <el-date-picker v-model="v$.endtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContractual.status')"
              for="outsourcing-contractual-status"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="status"
              id="outsourcing-contractual-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model.number="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContractual.secretlevel')"
              for="outsourcing-contractual-secretlevel"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="outsourcing-contractual-secretlevel"
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
              v-text="t$('jy1App.outsourcingContractual.foreigncurrency')"
              for="outsourcing-contractual-foreigncurrency"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="foreigncurrency"
              id="outsourcing-contractual-foreigncurrency"
              data-cy="foreigncurrency"
              :class="{ valid: !v$.foreigncurrency.$invalid, invalid: v$.foreigncurrency.$invalid }"
              v-model.number="v$.foreigncurrency.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContractual.totalbudget')"
              for="outsourcing-contractual-totalbudget"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="totalbudget"
              id="outsourcing-contractual-totalbudget"
              data-cy="totalbudget"
              :class="{ valid: !v$.totalbudget.$invalid, invalid: v$.totalbudget.$invalid }"
              v-model.number="v$.totalbudget.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContractual.fundsinplace')"
              for="outsourcing-contractual-fundsinplace"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="fundsinplace"
              id="outsourcing-contractual-fundsinplace"
              data-cy="fundsinplace"
              :class="{ valid: !v$.fundsinplace.$invalid, invalid: v$.fundsinplace.$invalid }"
              v-model.number="v$.fundsinplace.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContractual.responsibleunitname')"
              for="outsourcing-contractual-responsibleunitname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="responsibleunitname"
              id="outsourcing-contractual-responsibleunitname"
              data-cy="responsibleunitname"
              :class="{ valid: !v$.responsibleunitname.$invalid, invalid: v$.responsibleunitname.$invalid }"
              v-model="v$.responsibleunitname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContractual.audittime')"
              for="outsourcing-contractual-audittime"
            ></label>
            <el-date-picker v-model="v$.audittime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContractual.accountbank')"
              for="outsourcing-contractual-accountbank"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="accountbank"
              id="outsourcing-contractual-accountbank"
              data-cy="accountbank"
              :class="{ valid: !v$.accountbank.$invalid, invalid: v$.accountbank.$invalid }"
              v-model="v$.accountbank.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContractual.creatorid')"
              for="outsourcing-contractual-creatorid"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="outsourcing-contractual-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="outsourcingContractual.creatorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  outsourcingContractual.creatorid && officersOption.id === outsourcingContractual.creatorid.id
                    ? outsourcingContractual.creatorid
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
              v-text="t$('jy1App.outsourcingContractual.auditorid')"
              for="outsourcing-contractual-auditorid"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="outsourcing-contractual-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="outsourcingContractual.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  outsourcingContractual.auditorid && officersOption.id === outsourcingContractual.auditorid.id
                    ? outsourcingContractual.auditorid
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
            <label v-text="t$('jy1App.outsourcingContractual.projectwbs')" for="outsourcing-contractual-projectwbs"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="outsourcing-contractual-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="outsourcingContractual.projectwbs !== undefined"
              v-model="outsourcingContractual.projectwbs"
            >
              <el-option
                v-bind:value="getSelected(outsourcingContractual.projectwbs, projectwbsOption, 'id')"
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
<script lang="ts" src="./outsourcing-contractual-update.component.ts"></script>
