<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.workbag.home.createOrEditLabel"
          data-cy="WorkbagCreateUpdateHeading"
          v-text="t$('jy1App.workbag.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="workbag.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="workbag.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="workbag-name">任务包名称</label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="workbag-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>

          <!-- <div class="form-group">
            <label v-text="t$('jy1App.workbag.wbsid')" for="workbag-wbsid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="workbag-wbsids"
              data-cy="wbsid"
              multiple
              name="wbsid"
              v-if="workbag.wbsids !== undefined"
              v-model="workbag.wbsids"
            >
              <el-option
                v-bind:value="getSelected(workbag.wbsids, projectwbsOption, 'id')"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.wbsname }}</el-option
              >
            </el-select>
          </div> -->
          <div class="form-group">
            <label for="workbag-wbsid">WBS</label>
            <el-tree-select
              v-model="selectedWbsIds"
              :data="projectwbsTree"
              :props="treeProps"
              :default-checked-keys="workbag.wbsids"
              placeholder="请选择WBS"
              multiple
              show-checkbox
              value-key="id"
              @change="handleChange"
            >
            </el-tree-select>
          </div>

          
          <div class="form-group">
            <label class="form-control-label" for="workbag-pbsid">PBS</label>
            <el-input
              type="text"
              class="form-control"
              name="pbsid"
              id="workbag-pbsid"
              data-cy="pbsid"
              :class="{ valid: !v$.pbsid.$invalid, invalid: v$.pbsid.$invalid }"
              v-model="v$.pbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="workbag-workbagtype">类型</label>
            <el-input
              type="number"
              class="form-control"
              name="workbagtype"
              id="workbag-workbagtype"
              data-cy="workbagtype"
              :class="{ valid: !v$.workbagtype.$invalid, invalid: v$.workbagtype.$invalid }"
              v-model.number="v$.workbagtype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="workbag-supplier">供应商</label>
            <el-input
              type="text"
              class="form-control"
              name="supplier"
              id="workbag-supplier"
              data-cy="supplier"
              :class="{ valid: !v$.supplier.$invalid, invalid: v$.supplier.$invalid }"
              v-model="v$.supplier.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="workbag-iskeyimportant">关键节点</label>
            <el-input
              type="number"
              class="form-control"
              name="iskeyimportant"
              id="workbag-iskeyimportant"
              data-cy="iskeyimportant"
              :class="{ valid: !v$.iskeyimportant.$invalid, invalid: v$.iskeyimportant.$invalid }"
              v-model.number="v$.iskeyimportant.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.workbag.keypbsname')" for="workbag-keypbsname"></label>
            <el-input
              type="text"
              class="form-control"
              name="keypbsname"
              id="workbag-keypbsname"
              data-cy="keypbsname"
              :class="{ valid: !v$.keypbsname.$invalid, invalid: v$.keypbsname.$invalid }"
              v-model="v$.keypbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.workbag.importantpbsname')" for="workbag-importantpbsname"></label>
            <el-input
              type="text"
              class="form-control"
              name="importantpbsname"
              id="workbag-importantpbsname"
              data-cy="importantpbsname"
              :class="{ valid: !v$.importantpbsname.$invalid, invalid: v$.importantpbsname.$invalid }"
              v-model="v$.importantpbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.workbag.secretlevel')" for="workbag-secretlevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="workbag-secretlevel"
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
            <label class="form-control-label" v-text="t$('jy1App.workbag.description')" for="workbag-description"></label>
            <el-input
              type="text"
              class="form-control"
              name="description"
              id="workbag-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.workbag.starttime')" for="workbag-starttime"></label>
            <el-date-picker v-model="v$.starttime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.workbag.endtime')" for="workbag-endtime"></label>
            <el-date-picker v-model="v$.endtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.workbag.estimatedpurchasingtime')"
              for="workbag-estimatedpurchasingtime"
            ></label>
            <el-date-picker v-model="v$.estimatedpurchasingtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.workbag.progress')" for="workbag-progress"></label>
            <el-input
              type="number"
              class="form-control"
              name="progress"
              id="workbag-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.workbag.issafetywork')" for="workbag-issafetywork"></label>
            <el-input
              type="number"
              class="form-control"
              name="issafetywork"
              id="workbag-issafetywork"
              data-cy="issafetywork"
              :class="{ valid: !v$.issafetywork.$invalid, invalid: v$.issafetywork.$invalid }"
              v-model.number="v$.issafetywork.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.workbag.remark')" for="workbag-remark"></label>
            <el-input
              type="text"
              class="form-control"
              name="remark"
              id="workbag-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.workbag.auditStatus')" for="workbag-auditStatus"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="workbag-auditStatus"
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
            <label class="form-control-label" v-text="t$('jy1App.workbag.responsibleperson')" for="workbag-responsibleperson"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="workbag-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="workbag.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  workbag.responsibleperson && hrManagementOption.id === workbag.responsibleperson.id
                    ? workbag.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.workbag.projectmanager')" for="workbag-projectmanager"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="workbag-projectmanager"
              data-cy="projectmanager"
              name="projectmanager"
              v-model="workbag.projectmanager"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  workbag.projectmanager && hrManagementOption.id === workbag.projectmanager.id
                    ? workbag.projectmanager
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
            <label class="form-control-label" v-text="t$('jy1App.workbag.knowingpeople')" for="workbag-knowingpeople"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="workbag-knowingpeople"
              data-cy="knowingpeople"
              name="knowingpeople"
              v-model="workbag.knowingpeople"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  workbag.knowingpeople && hrManagementOption.id === workbag.knowingpeople.id ? workbag.knowingpeople : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.workbag.auditorid')" for="workbag-auditorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="workbag-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="workbag.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="workbag.auditorid && hrManagementOption.id === workbag.auditorid.id ? workbag.auditorid : hrManagementOption"
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
              v-text="t$('jy1App.workbag.responsibledepartment')"
              for="workbag-responsibledepartment"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="workbag-responsibledepartment"
              data-cy="responsibledepartment"
              name="responsibledepartment"
              v-model="workbag.responsibledepartment"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  workbag.responsibledepartment && departmentOption.id === workbag.responsibledepartment.id
                    ? workbag.responsibledepartment
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
            <label class="form-control-label" v-text="t$('jy1App.workbag.department')" for="workbag-department"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="workbag-department"
              data-cy="department"
              name="department"
              v-model="workbag.department"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="workbag.department && departmentOption.id === workbag.department.id ? workbag.department : departmentOption"
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
                >{{ departmentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.workbag.projectdeliverables')" for="workbag-projectdeliverables"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="workbag-projectdeliverables"
              data-cy="projectdeliverables"
              multiple
              name="projectdeliverables"
              v-if="workbag.projectdeliverables !== undefined"
              v-model="workbag.projectdeliverables"
            >
              <el-option
                v-bind:value="getSelected(workbag.projectdeliverables, projectdeliverablesOption, 'id')"
                v-for="projectdeliverablesOption in projectdeliverables"
                :key="projectdeliverablesOption.id"
                :label="projectdeliverablesOption.id"
                >{{ projectdeliverablesOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.workbag.relevantdepartment')" for="workbag-relevantdepartment"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="workbag-relevantdepartments"
              data-cy="relevantdepartment"
              multiple
              name="relevantdepartment"
              v-if="workbag.relevantdepartments !== undefined"
              v-model="workbag.relevantdepartments"
            >
              <el-option
                v-bind:value="getSelected(workbag.relevantdepartments, departmentOption, 'id')"
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
                >{{ departmentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.workbag.work')" for="workbag-work"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="workbag-works"
              data-cy="work"
              multiple
              name="work"
              v-if="workbag.works !== undefined"
              v-model="workbag.works"
            >
              <el-option
                v-bind:value="getSelected(workbag.works, workOption, 'id')"
                v-for="workOption in works"
                :key="workOption.id"
                :label="workOption.id"
                >{{ workOption.id }}</el-option
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
<script lang="ts" src="./workbag-update.component.ts"></script>
