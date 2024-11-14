<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <div>
          <div class="form-group" v-if="subject.id">
            <label for="id">编号</label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="subject.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="subject-name">科目名称</label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="subject-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          

          <div class="form-group">
            <label class="form-control-label" for="subject-status">状态</label>
            <el-select
              type="number"
              class="form-control"
              name="status"
              id="subject-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            >
              <el-option label="有效" :value=1></el-option>
              <el-option label="无效" :value=2></el-option>
            </el-select>
          </div>

          <div class="form-group">
            <label class="form-control-label" for="subject-remark">备注</label>
            <el-input
              type="text"
              class="form-control"
              name="remark"
              id="subject-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
          </div>
        </div>

        <div class="my-4"></div>
        
        <div class="d-flex justify-content-between">
          <el-button 
            type="button"  
            id="cancel-save" 
            data-cy="entityCreateCancelButton" 
            class="btn btn-secondary" 
            @click="previousState()"
          >
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>取消</span>
          </el-button>
          <el-button 
            type="primary" 
            id="save-entity" 
            data-cy="entityCreateSaveButton" 
            :disabled="v$.$invalid || isSaving" 
            class="btn btn-primary"
            @click="save"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>保存</span>
          </el-button>
        </div>


      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./subject-update.component.ts"></script>
