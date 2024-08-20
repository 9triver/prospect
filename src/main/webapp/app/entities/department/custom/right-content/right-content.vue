<template>
  <div>
    <el-tabs v-model="activeName" class="demo-tabs">
        <el-tab-pane label="组织机构" name="group">
            <tabGroup :curSelectTreeNodeData="curSelectTreeNodeData"/>
        </el-tab-pane>
        <el-tab-pane label="组用户" name="user">
            <tabUser/>
        </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup lang='ts'>
import { ref, reactive,defineProps} from 'vue'
import tabGroup from './tab-pane/group/tab-group.vue'
import tabUser from './tab-pane/user/tab-user.vue'
const activeName = ref('group')
import {type IDepartmentWithChildren } from '@/shared/model/department.model';
const props = defineProps<{curSelectTreeNodeData:IDepartmentWithChildren[]}>()
</script>

