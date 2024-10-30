<template>
  <div id="gantt_here" style="width:100%; height:100%;"></div>
</template>

<script>
import gantt from 'dhtmlx-gantt'; // 引入包
import 'dhtmlx-gantt/codebase/dhtmlxgantt.css'; // 引入自带的css文件

export default {
  data() {
    return {
      tasks: {
        data: [

          {
            id: 1,
            text: 'DFB24019',
            progress: 0.8, //完成度
            start_date: '2021-10-16', //开始时间 日月年
            end_date: '2021-10-15',
            projectStatus: '进行中',
            open: true, //默认打开=
            number: '', //显示字段
            person: '韩鸿风',
            post: 'BA',
            department: '数字化一部',
            task: '0/20'
          },
          {
            // toolTipsTxt: '任务#101-001',
            id: 21, //任务id
            text: '任务1的撒的防守打法士大夫', //任务名称
            start_date: '2021-10-16', //开始时间 日月年
            end_date: '2021-12-26',
            parent: 1, //父级id
            progress: 1, //完成度
            open: true, //默认打开
            status: '已完成',
          },
          {
            // toolTipsTxt: '任务#101-001',
            id: 22, //任务id
            text: '第二个逾期阶段', //任务名称
            start_date: '2021-12-16', //开始时间 日月年
            end_date: '2022-01-26',
            parent: 1, //父级id
            progress: 1, //完成度
            open: true, //默认打开
            status: '逾期'
          },
          {
            // toolTipsTxt: '任务#101-001',
            id: 23, //任务id
            text: '第三个进行中阶段', //任务名称
            start_date: '2022-01-20', //开始时间 日月年
            end_date: '2022-03-03',
            parent: 1, //父级id
            progress: 0.7, //完成度
            open: true, //默认打开
            status: '进行中'
          },
          {
            id: 2,
            text: 'DFB45113',
            progress: 0.7, //完成度
            start_date: '2021-10-16', //开始时间 日月年
            projectStatus: '已完成',
            end_date: '2021-10-15',
            open: true, //默认打开
            number: '哈哈哈' //显示字段
          },
          {
            // toolTipsTxt: '任务#101-001',
            id: 11, //任务id
            text: '项目启动阶段', //任务名称
            start_date: '2022-04-06', //开始时间 日月年
            end_date: '2022-06-06',
            parent: 2, //父级id
            progress: 1, //完成度
            open: true, //默认打开
            status: '已完成'
          },
          {
            // toolTipsTxt: '任务#101-001',
            id: 12, //任务id
            text: '设计阶段', //任务名称
            start_date: '2022-04-20', //开始时间 日月年
            end_date: '2022-06-26',
            parent: 2, //父级id
            progress: 0.9, //完成度
            open: true, //默认打开
            status: '进行中'
          },
          {
            // toolTipsTxt: '任务#101-001',
            id: 13, //任务id
            text: '采购阶段', //任务名称
            start_date: '2022-05-14', //开始时间 日月年
            end_date: '2022-07-26',
            parent: 2, //父级id
            progress: 1, //完成度
            open: true, //默认打开
            status: '未开始'
          }
        ],

        // 任务之间连接线，目前注释掉了，需要的话打开
        // links: [
        // 	{ id: '10', source: '11', target: '12', type: '1' },
        // 	{ id: '11', source: '11', target: '13', type: '1' },
        // 	{ id: '12', source: '11', target: '14', type: '1' },
        // 	{ id: '13', source: '11', target: '15', type: '1' },
        // 	{ id: '14', source: '23', target: '16', type: '0' },
        // 	{ id: '15', source: '13', target: '17', type: '1' },
        // 	{ id: '16', source: '17', target: '18', type: '0' },
        // 	{ id: '17', source: '18', target: '19', type: '0' },
        // 	{ id: '18', source: '19', target: '20', type: '0' },
        // 	{ id: '19', source: '15', target: '21', type: '2' },
        // 	{ id: '20', source: '15', target: '22', type: '2' },
        // 	{ id: '21', source: '15', target: '23', type: '0' }
        // ]
      },

    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      gantt.init("gantt_here");
      // 配置甘特图数据
      var tasks = {
        data: [
          { id: 1, text: "Project #1", start_date: "01-04-2023", duration: 18, progress: 0.4 },
          { id: 2, text: "Task #1", start_date: "02-04-2023", duration: 8, progress: 0.6, parent: 1 },
          { id: 3, text: "Task #2", start_date: "11-04-2023", duration: 8, progress: 0.6, parent: 1 },
          { id: 4, text: "Project #2", start_date: "19-04-2023", duration: 10, progress: 0.2 },
          { id: 5, text: "Task #1", start_date: "20-04-2023", duration: 4, progress: 0.5, parent: 4 },
          { id: 6, text: "Task #2", start_date: "25-04-2023", duration: 4, progress: 0.4, parent: 4 }
        ],
        links: [
          { id: 1, source: 1, target: 2, type: "1" },
          { id: 2, source: 2, target: 3, type: "0" },
          { id: 3, source: 4, target: 5, type: "1" },
          { id: 4, source: 5, target: 6, type: "0" }
        ]
      };

      // 将数据加载到甘特图中
      gantt.parse(tasks);
    },
  }
}
</script>
