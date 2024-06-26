<template>
  <el-container>
    <el-header>
      <span>项目管理系统甘特图</span>
    </el-header>
  </el-container>
  <el-container>
    <el-aside>
      <el-col :span="12">
        <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
          <el-submenu index="1">
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">项目周期计划</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-col>
    </el-aside>
    <el-main style="color: #2eaabb">
      <span>甘特图</span>
      <div ref="ganttContainer" style="width: 100%; height: 600px"></div>
    </el-main>
  </el-container>
</template>

<!-- <script lang="ts" src="./projectgantt.component.ts"></script> -->
<script>
import 'dhtmlx-gantt';
import 'dhtmlx-gantt/codebase/dhtmlxgantt.css';
//项目进展
import CycleplanService from '../cycleplan/cycleplan.service';

export default {
  name: 'GanttChart',
  data() {
    return {
      gantt: null,
      tasks: [
        // { id: 1, text: 'Task 1', start_date: '2024-06-25', duration: 3 },
        // { id: 2, text: 'Task 2', start_date: '2024-06-28', duration: 2 }
        // 添加更多任务...
      ],
    };
  },
  mounted() {
    this.gantt = window.gantt; // 注意：获取 gantt 实例
    this.gantt.init(this.$refs.ganttContainer);
    // this.gantt.parse({ data: this.tasks });
    //获取数据并处理
    this.retrieveCycleplans();
  },
  methods: {
    async retrieveCycleplans() {
      try {
        const cycleplanService = new CycleplanService(); // 直接实例化服务
        const res = await cycleplanService.retrieve(); // 调用服务方法获取数据
        //遍历 res 数组，将每条数据转换为甘特图任务对象并添加到 tasks 中
        if (Array.isArray(res.data)) {
          this.tasks = res.data.map(item => ({
            id: item.id,
            text: item.cycleplanname, // 适当地替换为从 res 中取得的任务名字段
            start_date: new Date(item.starttime), // 替换为从 res 中取得的开始日期字段
            duration: item.cycleplanid, // 替换为从 res 中取得的持续时间字段
          }));
        } else {
          console.error('Unexpected response format:', res);
          // 可以根据实际情况处理异常，比如显示错误信息
        }

        // 解析甘特图数据
        this.gantt.parse({ data: this.tasks });
      } catch (err) {
        alert('解析甘特图数据时异常:' + JSON.stringify(err));
        alert('解析甘特图数据时异常:' + err);
      }
    },
  },
  beforeDestroy() {
    if (this.gantt) {
      this.gantt.clearAll();
    }
  },
};
</script>

<style lang="scss">
.firstLevelTask {
  border: none;

  .gantt_task_content {
    font-size: 13px;
  }
}

.secondLevelTask {
  border: none;
}

.thirdLevelTask {
  border: 2px solid #da645d;
  color: #da645d;
  background: #da645d;
}

.milestone-default {
  border: none;
  background: rgba(0, 0, 0, 0.45);
}

.milestone-unfinished {
  border: none;
  background: #5692f0;
}

.milestone-finished {
  border: none;
  background: #84bd54;
}

.milestone-canceled {
  border: none;
  background: #da645d;
}

html,
body {
  margin: 0px;
  padding: 0px;
  height: 100%;
  overflow: hidden;
}

.container {
  height: 100%;
  width: 100%;
  position: relative;
  .gantt-container {
    height: 100%;
  }
  .gantt_grid_head_cell {
    padding-left: 20px;
    text-align: left !important;
    font-size: 14px;
    color: #333;
  }

  .select-wrap {
    position: absolute;
    top: 5px;
    z-index: 99;
    width: 90px;
    left: 180px;

    .el-input__inner {
      // border: none;
    }
  }

  .left-container {
    height: 100%;
  }

  // .parent {
  //     .gantt_tree_icon {
  //         &.gantt_folder_open {
  //             background-image: url(assets/gantt-icon.svg) !important;
  //         }
  //         &.gantt_folder_closed{
  //             background-image: url(assets/gantt-icon-up.svg) !important;
  //         }
  //     }
  // }

  .green,
  .yellow,
  .pink,
  .popular {
    .gantt_tree_icon.gantt_file {
      background: none;
      position: relative;

      &::before {
        content: '';
        width: 10px;
        height: 10px;
        border-radius: 50%;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
      }
    }
  }

  .green {
    .gantt_tree_icon.gantt_file {
      &::before {
        background: #84bd54;
      }
    }
  }

  .yellow {
    .gantt_tree_icon.gantt_file {
      &::before {
        background: #fcca02;
      }
    }
  }

  .pink {
    .gantt_tree_icon.gantt_file {
      &::before {
        background: #da645d;
      }
    }
  }

  .popular {
    .gantt_tree_icon.gantt_file {
      &::before {
        background: #d1a6ff;
      }
    }
  }
}

.left-container {
  height: 100%;
}

.gantt_task_content {
  text-align: left;
  padding-left: 10px;
}
.gantt_tooltip {
  z-index: 999;
}
.gantt_tree_content {
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
<style lang="scss">
:root {
  --gantt-border-style: 1px solid #ffffff44;
}
.dark {
  .gantt_container,
  .gantt_grid_scale,
  .gantt_task_scale,
  .gantt_task_vscroll,
  .gantt_row,
  .gantt_task_row {
    background: none;
  }
  .gantt_grid_data .gantt_row.gantt_selected,
  .gantt_grid_data .gantt_row.odd.gantt_selected,
  .gantt_task_row.gantt_selected,
  .gantt_grid_data .gantt_row.odd:hover,
  .gantt_grid_data .gantt_row:hover {
    background: #ffffff34;
  }
  .gantt_container {
    border: var(--gantt-border-style);
  }
  .gantt_grid_scale,
  .gantt_task_scale,
  .gantt_row,
  .gantt_task_row {
    border-bottom: var(--gantt-border-style);
  }
  .gantt_layout_cell_border_right,
  .gantt_task_cell,
  .gantt_task .gantt_task_scale .gantt_scale_cell {
    border-right: var(--gantt-border-style);
  }
  .gantt_task_row.gantt_selected .gantt_task_cell {
    border-right: var(--gantt-border-style);
    border-right-color: #ffffff78;
  }
  .gantt_grid_scale,
  .gantt_task_scale,
  .gantt_task .gantt_task_scale .gantt_scale_cell,
  .gantt_grid_data .gantt_cell,
  .gantt_grid_head_cell {
    color: #fff;
    // font-size: 1.3em;
  }
}
</style>
