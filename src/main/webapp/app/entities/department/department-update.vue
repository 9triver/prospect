<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.department.home.createOrEditLabel"
          data-cy="DepartmentCreateUpdateHeading"
          v-text="t$('jy1App.department.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="department.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="department.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.department.name')" for="department-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="department-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.department.officersnum')" for="department-officersnum"></label>
            <el-input
              type="number"
              class="form-control"
              name="officersnum"
              id="department-officersnum"
              data-cy="officersnum"
              :class="{ valid: !v$.officersnum.$invalid, invalid: v$.officersnum.$invalid }"
              v-model.number="v$.officersnum.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.department.superior')" for="department-superior"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="department-superior"
              data-cy="superior"
              name="superior"
              v-model="department.superior"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  department.superior && departmentOption.id === department.superior.id ? department.superior : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
                >{{ departmentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.department.officers')" for="department-officers"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="department-officers"
              data-cy="officers"
              multiple
              name="officers"
              v-if="department.officers !== undefined"
              v-model="department.officers"
            >
              <el-option
                v-bind:value="getSelected(department.officers, officersOption, 'id')"
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
<script lang="ts" src="./department-update.component.ts"></script>
