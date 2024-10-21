<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.documentmenu.home.createOrEditLabel"
          data-cy="DocumentmenuCreateUpdateHeading"
          v-text="t$('jy1App.documentmenu.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="documentmenu.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="documentmenu.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.menuid')" for="documentmenu-menuid"></label>
            <el-input
              type="text"
              class="form-control"
              name="menuid"
              id="documentmenu-menuid"
              data-cy="menuid"
              :class="{ valid: !v$.menuid.$invalid, invalid: v$.menuid.$invalid }"
              v-model="v$.menuid.$model"
              required
            />
            <div v-if="v$.menuid.$anyDirty && v$.menuid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.menuid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.belongtype')" for="documentmenu-belongtype"></label>
            <el-input
              type="text"
              class="form-control"
              name="belongtype"
              id="documentmenu-belongtype"
              data-cy="belongtype"
              :class="{ valid: !v$.belongtype.$invalid, invalid: v$.belongtype.$invalid }"
              v-model="v$.belongtype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.menuname')" for="documentmenu-menuname"></label>
            <el-input
              type="text"
              class="form-control"
              name="menuname"
              id="documentmenu-menuname"
              data-cy="menuname"
              :class="{ valid: !v$.menuname.$invalid, invalid: v$.menuname.$invalid }"
              v-model="v$.menuname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.parentmenuid')" for="documentmenu-parentmenuid"></label>
            <el-input
              type="text"
              class="form-control"
              name="parentmenuid"
              id="documentmenu-parentmenuid"
              data-cy="parentmenuid"
              :class="{ valid: !v$.parentmenuid.$invalid, invalid: v$.parentmenuid.$invalid }"
              v-model="v$.parentmenuid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.createtime')" for="documentmenu-createtime"></label>
            <el-date-picker v-model="v$.createtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.creatorid')" for="documentmenu-creatorid"></label>
            <el-input
              type="text"
              class="form-control"
              name="creatorid"
              id="documentmenu-creatorid"
              data-cy="creatorid"
              :class="{ valid: !v$.creatorid.$invalid, invalid: v$.creatorid.$invalid }"
              v-model="v$.creatorid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.creatorname')" for="documentmenu-creatorname"></label>
            <el-input
              type="text"
              class="form-control"
              name="creatorname"
              id="documentmenu-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.type')" for="documentmenu-type"></label>
            <el-input
              type="number"
              class="form-control"
              name="type"
              id="documentmenu-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model.number="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.filenum')" for="documentmenu-filenum"></label>
            <el-input
              type="number"
              class="form-control"
              name="filenum"
              id="documentmenu-filenum"
              data-cy="filenum"
              :class="{ valid: !v$.filenum.$invalid, invalid: v$.filenum.$invalid }"
              v-model.number="v$.filenum.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.fileurl')" for="documentmenu-fileurl"></label>
            <el-input
              type="text"
              class="form-control"
              name="fileurl"
              id="documentmenu-fileurl"
              data-cy="fileurl"
              :class="{ valid: !v$.fileurl.$invalid, invalid: v$.fileurl.$invalid }"
              v-model="v$.fileurl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.departmentid')" for="documentmenu-departmentid"></label>
            <el-input
              type="text"
              class="form-control"
              name="departmentid"
              id="documentmenu-departmentid"
              data-cy="departmentid"
              :class="{ valid: !v$.departmentid.$invalid, invalid: v$.departmentid.$invalid }"
              v-model="v$.departmentid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.departmentname')" for="documentmenu-departmentname"></label>
            <el-input
              type="text"
              class="form-control"
              name="departmentname"
              id="documentmenu-departmentname"
              data-cy="departmentname"
              :class="{ valid: !v$.departmentname.$invalid, invalid: v$.departmentname.$invalid }"
              v-model="v$.departmentname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.spare1')" for="documentmenu-spare1"></label>
            <el-date-picker v-model="v$.spare1.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.spare2')" for="documentmenu-spare2"></label>
            <el-input
              type="number"
              class="form-control"
              name="spare2"
              id="documentmenu-spare2"
              data-cy="spare2"
              :class="{ valid: !v$.spare2.$invalid, invalid: v$.spare2.$invalid }"
              v-model.number="v$.spare2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.documentmenu.spare3')" for="documentmenu-spare3"></label>
            <el-input
              type="text"
              class="form-control"
              name="spare3"
              id="documentmenu-spare3"
              data-cy="spare3"
              :class="{ valid: !v$.spare3.$invalid, invalid: v$.spare3.$invalid }"
              v-model="v$.spare3.$model"
            />
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
<script lang="ts" src="./documentmenu-update.component.ts"></script>
