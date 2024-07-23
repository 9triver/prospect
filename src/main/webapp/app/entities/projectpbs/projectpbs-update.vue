<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <el-text class="mx-1" type="primary" style="font-size: 28px;">创建或编辑PBS</el-text>
        <div>
          <div class="form-group" v-if="projectpbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectpbs.id" readonly />
          </div>
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="名称">
              <el-input v-model="v$.pbsname.$model" placeholder="名称" clearable :style="{ width: '220px' }"/>
            </el-form-item>
            <el-form-item label="父级PBS">
              <el-input v-model="v$.parentpbsid.$model" placeholder="父级PBS" clearable :style="{ width: '220px' }"/>
            </el-form-item>
          </el-form>
          <el-form >
            <el-form-item label="描述">
              <el-input v-model="v$.description.$model" placeholder="描述" clearable :style="{ width: '540px' }"/>
            </el-form-item>
          </el-form>

          <el-form>
            <el-form-item label="起止时间">
              <el-col :span="7">
                <b-input-group class="mb-3">
                  <b-input-group-prepend>
                    <b-form-datepicker
                      aria-controls="projectpbs-starttime"
                      v-model="v$.starttime.$model"
                      name="starttime"
                      class="form-control"
                      :locale="currentLanguage"
                      button-only
                      today-button
                      reset-button
                      close-button
                    >
                    </b-form-datepicker>
                  </b-input-group-prepend>
                  <b-form-input
                    id="projectpbs-starttime"
                    data-cy="starttime"
                    type="text"
                    class="form-control"
                    name="starttime"
                    :class="{ valid: !v$.starttime.$invalid, invalid: v$.starttime.$invalid }"
                    v-model="v$.starttime.$model"
                  />
                </b-input-group>
              </el-col>

              <el-col :span="2" class="text-center">
                <span class="text-gray-500">-</span>
              </el-col>

              <el-col :span="7">
                <b-input-group class="mb-3">
                  <b-input-group-prepend>
                    <b-form-datepicker
                      aria-controls="projectpbs-endtime"
                      v-model="v$.endtime.$model"
                      name="endtime"
                      class="form-control"
                      :locale="currentLanguage"
                      button-only
                      today-button
                      reset-button
                      close-button
                    >
                    </b-form-datepicker>
                  </b-input-group-prepend>
                  <b-form-input
                    id="projectpbs-endtime"
                    data-cy="endtime"
                    type="text"
                    class="form-control"
                    name="endtime"
                    :class="{ valid: !v$.endtime.$invalid, invalid: v$.endtime.$invalid }"
                    v-model="v$.endtime.$model"
                  />
                </b-input-group>
              </el-col>
            </el-form-item>
          </el-form>



          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <!-- <el-form-item label="起止时间">
              <el-col :span="11" >
                <el-date-picker
                  v-model="projectpbs.starttime"
                  type="date"
                  placeholder="开始日期"
                  style="width: 100%"
                />
              </el-col>
              <el-col :span="2" class="text-center">
                <span class="text-gray-500">-</span>
              </el-col>
              <el-col :span="11" >
                <el-date-picker
                  v-model="projectpbs.endtime"
                  type="date"
                  placeholder="结束日期"
                  style="width: 100%"
                />
              </el-col>
            </el-form-item> -->
            <el-form-item label="进度">
              <el-input v-model="v$.progress.$model" placeholder="进度" clearable :style="{ width: '220px' }"/>
            </el-form-item>
            <el-form-item label="关联WBS">
              <el-input v-model="v$.wbsid.$model" placeholder="关联WBS" clearable :style="{ width: '220px' }"/>
            </el-form-item>
          </el-form>
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="类型">
              <el-select v-model="v$.type.$model" placeholder="请选择" :style="{ width: '220px' }">
                <el-option label="科研技术类" :value="1" />
                <el-option label="生产管理类" :value="2" />
              </el-select>
            </el-form-item>
            <el-form-item label="优先级">
              <el-select v-model="v$.priorty.$model" placeholder="请选择" :style="{ width: '220px' }">
                <el-option label="普通" :value="1" />
                <el-option label="中等" :value="2" />
                <el-option label="重要" :value="3" />
              </el-select>
            </el-form-item>
            <el-form-item label="密级">
              <el-select v-model="v$.secretlevel.$model" placeholder="请选择" :style="{ width: '220px' }">
                <el-option label="机密" value="SECRET" />
                <el-option label="非保密-内部" value="NOSECTET_INTERNAL" />
                <el-option label="公开" value="PUBLIC" />
              </el-select>
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="v$.status.$model" placeholder="请选择" :style="{ width: '220px' }">
                <el-option label="未开始" value="NOTSTART" />
                <el-option label="进行中" value="IN_PROGRESS" />
                <el-option label="已完成" value="COMPLETED" />
                <el-option label="已取消" value="CANCELED" />
                <el-option label="已超期" value="EXPIRED" />
              </el-select>
            </el-form-item>
            <el-form-item label="审核状态">
              <el-select v-model="v$.auditStatus.$model" placeholder="请选择" :style="{ width: '220px' }">
                <el-option label="未审核" value="Not_Audited" />
                <el-option label="审核中" value="In_Audit" />
                <el-option label="已通过" value="Approved" />
              </el-select>
            </el-form-item>
          </el-form>

          <el-form>
            <!-- <el-form-item label="管理WBS">
              <router-link to="/projectwbsSelect">选择</router-link>
              <el-input v-model="selectedWbsId" readonly></el-input>
            </el-form-item>
            <el-form-item>
              <router-link to="/projectwbsSelect" target="_blank">打开新页面</router-link>
              <button @click="openNewWindow">打开新窗口</button>
            </el-form-item> -->

            <el-form-item>
              <el-button plain @click="dialogVisible = true">
                关联WBS
              </el-button>
              <el-dialog
                v-model="dialogVisible"
                title="选择WBS"
                width="60%"
                :before-close="handleClose"
              >
                <my-content-component />
                <!-- <my-content-component /> -->
                <!-- <template #footer>
                  <div class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="dialogVisible = false">确认</el-button>
                  </div>
                </template> -->
              </el-dialog>
              <el-input v-model="selectedWbsId" readonly></el-input>
            </el-form-item>
          </el-form>

          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.workbag')" for="projectpbs-workbag"></label>
            <input
              type="number"
              class="form-control"
              name="workbag"
              id="projectpbs-workbag"
              data-cy="workbag"
              :class="{ valid: !v$.workbag.$invalid, invalid: v$.workbag.$invalid }"
              v-model.number="v$.workbag.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.responsibleid')" for="projectpbs-responsibleid"></label>
            <select
              class="form-control"
              id="projectpbs-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="projectpbs.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectpbs.responsibleid && officersOption.id === projectpbs.responsibleid.id ? projectpbs.responsibleid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.auditorid')" for="projectpbs-auditorid"></label>
            <select class="form-control" id="projectpbs-auditorid" data-cy="auditorid" name="auditorid" v-model="projectpbs.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="projectpbs.auditorid && officersOption.id === projectpbs.auditorid.id ? projectpbs.auditorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.department')" for="projectpbs-department"></label>
            <select class="form-control" id="projectpbs-department" data-cy="department" name="department" v-model="projectpbs.department">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectpbs.department && departmentOption.id === projectpbs.department.id ? projectpbs.department : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectpbs.project')" for="projectpbs-project"></label>
            <select
              class="form-control"
              id="projectpbs-projects"
              data-cy="project"
              multiple
              name="project"
              v-if="projectpbs.projects !== undefined"
              v-model="projectpbs.projects"
            >
              <option
                v-bind:value="getSelected(projectpbs.projects, projectOption, 'id')"
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.id }}
              </option>
            </select>
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
