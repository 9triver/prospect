<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.projectpbs.home.createOrEditLabel"
          data-cy="ProjectpbsCreateUpdateHeading"
          v-text="t$('jy1App.projectpbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectpbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="projectpbs.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.pbsname')" for="projectpbs-pbsname"></label>
            <el-input
              type="text"
              class="form-control"
              name="pbsname"
              id="projectpbs-pbsname"
              data-cy="pbsname"
              :class="{ valid: !v$.pbsname.$invalid, invalid: v$.pbsname.$invalid }"
              v-model="v$.pbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.parentpbsid')" for="projectpbs-parentpbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="parentpbsid"
              id="projectpbs-parentpbsid"
              data-cy="parentpbsid"
              :class="{ valid: !v$.parentpbsid.$invalid, invalid: v$.parentpbsid.$invalid }"
              v-model="v$.parentpbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.secretlevel')" for="projectpbs-secretlevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="projectpbs-secretlevel"
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
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.starttime')" for="projectpbs-starttime"></label>
            <el-date-picker v-model="v$.starttime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.endtime')" for="projectpbs-endtime"></label>
            <el-date-picker v-model="v$.endtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.productlevel')" for="projectpbs-productlevel"></label>
            <el-input
              type="text"
              class="form-control"
              name="productlevel"
              id="projectpbs-productlevel"
              data-cy="productlevel"
              :class="{ valid: !v$.productlevel.$invalid, invalid: v$.productlevel.$invalid }"
              v-model="v$.productlevel.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.iskey')" for="projectpbs-iskey"></label>
            <el-input
              type="number"
              class="form-control"
              name="iskey"
              id="projectpbs-iskey"
              data-cy="iskey"
              :class="{ valid: !v$.iskey.$invalid, invalid: v$.iskey.$invalid }"
              v-model.number="v$.iskey.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.isimportant')" for="projectpbs-isimportant"></label>
            <el-input
              type="number"
              class="form-control"
              name="isimportant"
              id="projectpbs-isimportant"
              data-cy="isimportant"
              :class="{ valid: !v$.isimportant.$invalid, invalid: v$.isimportant.$invalid }"
              v-model.number="v$.isimportant.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.description')" for="projectpbs-description"></label>
            <el-input
              type="text"
              class="form-control"
              name="description"
              id="projectpbs-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.progress')" for="projectpbs-progress"></label>
            <el-input
              type="number"
              class="form-control"
              name="progress"
              id="projectpbs-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.type')" for="projectpbs-type"></label>
            <el-input
              type="number"
              class="form-control"
              name="type"
              id="projectpbs-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model.number="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.priorty')" for="projectpbs-priorty"></label>
            <el-input
              type="number"
              class="form-control"
              name="priorty"
              id="projectpbs-priorty"
              data-cy="priorty"
              :class="{ valid: !v$.priorty.$invalid, invalid: v$.priorty.$invalid }"
              v-model.number="v$.priorty.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.status')" for="projectpbs-status"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="projectpbs-status"
              data-cy="status"
            >
              <el-option
                v-for="projectStatus in projectStatusValues"
                :key="projectStatus"
                v-bind:value="projectStatus"
                v-bind:label="t$('jy1App.ProjectStatus.' + projectStatus)"
                >{{ projectStatus }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.auditStatus')" for="projectpbs-auditStatus"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="projectpbs-auditStatus"
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
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.projectwbs')" for="projectpbs-projectwbs"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectpbs-projectwbs"
              data-cy="projectwbs"
              name="projectwbs"
              v-model="projectpbs.projectwbs"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectpbs.projectwbs && projectwbsOption.id === projectpbs.projectwbs.id ? projectpbs.projectwbs : projectwbsOption
                "
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.technicaldirector')" for="projectpbs-technicaldirector"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectpbs-technicaldirector"
              data-cy="technicaldirector"
              name="technicaldirector"
              v-model="projectpbs.technicaldirector"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectpbs.technicaldirector && hrManagementOption.id === projectpbs.technicaldirector.id
                    ? projectpbs.technicaldirector
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectpbs.administrativedirector')"
              for="projectpbs-administrativedirector"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectpbs-administrativedirector"
              data-cy="administrativedirector"
              name="administrativedirector"
              v-model="projectpbs.administrativedirector"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectpbs.administrativedirector && hrManagementOption.id === projectpbs.administrativedirector.id
                    ? projectpbs.administrativedirector
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.knowingpeople')" for="projectpbs-knowingpeople"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectpbs-knowingpeople"
              data-cy="knowingpeople"
              name="knowingpeople"
              v-model="projectpbs.knowingpeople"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectpbs.knowingpeople && hrManagementOption.id === projectpbs.knowingpeople.id
                    ? projectpbs.knowingpeople
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.auditorid')" for="projectpbs-auditorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectpbs-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="projectpbs.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectpbs.auditorid && hrManagementOption.id === projectpbs.auditorid.id ? projectpbs.auditorid : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectpbs.responsibledepartment')"
              for="projectpbs-responsibledepartment"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectpbs-responsibledepartment"
              data-cy="responsibledepartment"
              name="responsibledepartment"
              v-model="projectpbs.responsibledepartment"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectpbs.responsibledepartment && departmentOption.id === projectpbs.responsibledepartment.id
                    ? projectpbs.responsibledepartment
                    : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
                >{{ departmentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectpbs.relevantdepartment')" for="projectpbs-relevantdepartment"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectpbs-relevantdepartments"
              data-cy="relevantdepartment"
              multiple
              name="relevantdepartment"
              v-if="projectpbs.relevantdepartments !== undefined"
              v-model="projectpbs.relevantdepartments"
            >
              <el-option
                v-bind:value="getSelected(projectpbs.relevantdepartments, departmentOption, 'id')"
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
                >{{ departmentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectpbs.project')" for="projectpbs-project"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectpbs-projects"
              data-cy="project"
              multiple
              name="project"
              v-if="projectpbs.projects !== undefined"
              v-model="projectpbs.projects"
            >
              <el-option
                v-bind:value="getSelected(projectpbs.projects, projectOption, 'id')"
                v-for="projectOption in projects"
                :key="projectOption.id"
                :label="projectOption.id"
                >{{ projectOption.id }}</el-option
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
<script lang="ts" src="./projectpbs-update.component.ts"></script>
