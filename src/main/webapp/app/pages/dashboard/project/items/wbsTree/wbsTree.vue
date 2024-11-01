<template>
  <div class="title">WBS列表</div>
  <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick" highlight-current
    :expand-on-click-node="false" default-expand-all></el-tree>
</template>

<script>
export default {
  data() {
    return {
      data: [
        {
          "label": "重大项目1",
          "children": [
            {
              "label": "子项目1-1",
              "children": [
                {
                  "label": "小项目1-1-1"
                }
              ]
            }
          ]
        },
        {
          "label": "重大项目2",
          "children": [
            {
              "label": "子项目2-1",
              "children": [
                {
                  "label": "小项目2-1-1"
                }
              ]
            },
            {
              "label": "子项目2-2",
              "children": [
                {
                  "label": "小项目2-2-1"
                }
              ]
            }
          ]
        },
        {
          "label": "重大项目3",
          "children": [
            {
              "label": "子项目3-1",
              "children": [
                {
                  "label": "小项目3-1-1"
                }
              ]
            },
            {
              "label": "子项目3-2",
              "children": [
                {
                  "label": "小项目3-2-1"
                }
              ]
            }
          ]
        }
      ],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    };
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
    }
  }
};
</script>

<style>
.title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px
}
</style>