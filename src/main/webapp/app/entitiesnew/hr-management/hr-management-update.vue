<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.hrManagement.home.createOrEditLabel"
          data-cy="HrManagementCreateUpdateHeading"
          v-text="t$('jy1App.hrManagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="hrManagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="hrManagement.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.officersid')" for="hr-management-officersid"></label>
            <el-input
              type="text"
              class="form-control"
              name="officersid"
              id="hr-management-officersid"
              data-cy="officersid"
              :class="{ valid: !v$.officersid.$invalid, invalid: v$.officersid.$invalid }"
              v-model="v$.officersid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.officersname')" for="hr-management-officersname"></label>
            <el-input
              type="text"
              class="form-control"
              name="officersname"
              id="hr-management-officersname"
              data-cy="officersname"
              :class="{ valid: !v$.officersname.$invalid, invalid: v$.officersname.$invalid }"
              v-model="v$.officersname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.projectid')" for="hr-management-projectid"></label>
            <el-input
              type="number"
              class="form-control"
              name="projectid"
              id="hr-management-projectid"
              data-cy="projectid"
              :class="{ valid: !v$.projectid.$invalid, invalid: v$.projectid.$invalid }"
              v-model.number="v$.projectid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.projectname')" for="hr-management-projectname"></label>
            <el-input
              type="text"
              class="form-control"
              name="projectname"
              id="hr-management-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.projectrole')" for="hr-management-projectrole"></label>
            <el-input
              type="text"
              class="form-control"
              name="projectrole"
              id="hr-management-projectrole"
              data-cy="projectrole"
              :class="{ valid: !v$.projectrole.$invalid, invalid: v$.projectrole.$invalid }"
              v-model="v$.projectrole.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.jobgrade')" for="hr-management-jobgrade"></label>
            <el-input
              type="text"
              class="form-control"
              name="jobgrade"
              id="hr-management-jobgrade"
              data-cy="jobgrade"
              :class="{ valid: !v$.jobgrade.$invalid, invalid: v$.jobgrade.$invalid }"
              v-model="v$.jobgrade.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.departmentid')" for="hr-management-departmentid"></label>
            <el-input
              type="text"
              class="form-control"
              name="departmentid"
              id="hr-management-departmentid"
              data-cy="departmentid"
              :class="{ valid: !v$.departmentid.$invalid, invalid: v$.departmentid.$invalid }"
              v-model="v$.departmentid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.departmentname')" for="hr-management-departmentname"></label>
            <el-input
              type="text"
              class="form-control"
              name="departmentname"
              id="hr-management-departmentname"
              data-cy="departmentname"
              :class="{ valid: !v$.departmentname.$invalid, invalid: v$.departmentname.$invalid }"
              v-model="v$.departmentname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.frontlineid')" for="hr-management-frontlineid"></label>
            <el-input
              type="text"
              class="form-control"
              name="frontlineid"
              id="hr-management-frontlineid"
              data-cy="frontlineid"
              :class="{ valid: !v$.frontlineid.$invalid, invalid: v$.frontlineid.$invalid }"
              v-model="v$.frontlineid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.frontlinename')" for="hr-management-frontlinename"></label>
            <el-input
              type="text"
              class="form-control"
              name="frontlinename"
              id="hr-management-frontlinename"
              data-cy="frontlinename"
              :class="{ valid: !v$.frontlinename.$invalid, invalid: v$.frontlinename.$invalid }"
              v-model="v$.frontlinename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.jobduty')" for="hr-management-jobduty"></label>
            <el-input
              type="text"
              class="form-control"
              name="jobduty"
              id="hr-management-jobduty"
              data-cy="jobduty"
              :class="{ valid: !v$.jobduty.$invalid, invalid: v$.jobduty.$invalid }"
              v-model="v$.jobduty.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.annualworktime')" for="hr-management-annualworktime"></label>
            <el-input
              type="number"
              class="form-control"
              name="annualworktime"
              id="hr-management-annualworktime"
              data-cy="annualworktime"
              :class="{ valid: !v$.annualworktime.$invalid, invalid: v$.annualworktime.$invalid }"
              v-model.number="v$.annualworktime.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.hrManagement.annualtasktarget')"
              for="hr-management-annualtasktarget"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="annualtasktarget"
              id="hr-management-annualtasktarget"
              data-cy="annualtasktarget"
              :class="{ valid: !v$.annualtasktarget.$invalid, invalid: v$.annualtasktarget.$invalid }"
              v-model="v$.annualtasktarget.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.hrManagement.officers')" for="hr-management-officers"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="hr-management-officers"
              data-cy="officers"
              name="officers"
              v-model="hrManagement.officers"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  hrManagement.officers && officersOption.id === hrManagement.officers.id ? hrManagement.officers : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.id"
                >{{ officersOption.id }}</el-option
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
<script lang="ts" src="./hr-management-update.component.ts"></script>
