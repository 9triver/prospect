<template>
    <div class="row justify-content-center">
        <el-card class="form" shadow="hover">
            <el-row>
                <el-col :span="8">
                    <el-form-item label="外协合同" :rules="[{ required: true, message: '请输入', trigger: 'blur' }]">
                    <el-select
                        collapse-tags
                        value-key="id"
                        id="contract-payment-paymentApplication"
                        data-cy="paymentApplication"
                        name="paymentApplication"
                        v-model="contractPayment.paymentApplication"
                    >
                        <el-option v-bind:value="null"></el-option>
                        <el-option
                        v-bind:value="
                            contractPayment.paymentApplication && paymentApplicationOption.id === contractPayment.paymentApplication.id
                            ? contractPayment.paymentApplication
                            : paymentApplicationOption
                        "
                        v-for="paymentApplicationOption in paymentApplications"
                        :key="paymentApplicationOption.id"
                        :label="paymentApplicationOption.id"
                        >{{ paymentApplicationOption.id }}</el-option
                        >
                    </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-card>

        <!-- 下半部分：Tab 页 -->
        <!-- <el-card v-if="isSaving" class="table-container" shadow="hover"> -->
        <el-card class="table-container" shadow="hover">
        <!-- Tab 页切换 -->
        <el-tabs v-model="activeTab">
            <el-tab-pane label="系统内付款分解" name="PaymentCost">
            <el-table :data="paymentCostLists" style="width: 100%" border>
                <el-table-column label="序号" prop="id" min-width="20px">
                </el-table-column>

                <el-table-column label="科目" prop="subjectname">
                </el-table-column>

                <el-table-column label="预算金额" prop="budgetamount">
                <template #default="{ row }">
                    <el-input v-model="row.budgetamount" placeholder="请输入"  />
                </template>
                </el-table-column>

                <!-- <el-table-column label="估算金额" prop="estimatedamount">
                <template #default="{ row }">
                    <el-input v-model="row.estimatedamount" placeholder="请输入"  />
                </template>
                </el-table-column> -->

                <el-table-column label="百分比" prop="percentage">
                <template #default="{ row }">
                    <el-input v-model="row.percentage" placeholder="请输入电话" size="small" />
                </template>
                </el-table-column>
            </el-table>
            <el-button type="success" @click="saveSubject()"  style="width: auto; float: left;">保存</el-button>
            </el-tab-pane>

            <el-tab-pane label="费用来源" name="fundSource">
            <el-table :data="fundSourceLists" style="width: 100%" border>
                <el-table-column label="项目名称" prop="wbsname">
                </el-table-column>

                <el-table-column label="项目编号" prop="wbsid">
                </el-table-column>

                <!-- <el-table-column label="科目" prop="subjectname">
                <template #default="{ row }">
                    <el-input v-model="row.subjectname" placeholder="请输入" size="small" />
                </template>
                </el-table-column> -->

                <el-table-column label="科目" prop="subjectname">
                <template #default="{ row }">
                    <!-- 使用 el-select 来代替 el-input，显示 subject.name，保存 subject.id -->
                    <!-- <el-select v-model="row.subjectname" placeholder="请选择" size="small">
                    <el-option
                        v-for="subject in subjects"
                        :key="subject.id"
                        :label="subject.name"
                        :value="subject.id"
                    ></el-option>
                    </el-select> -->
                </template>
                </el-table-column>


                <el-table-column label="单位" prop="unit">
                <template #default="{ row }">
                    <el-input v-model="row.unit" placeholder="请输入" size="small" />
                </template>
                </el-table-column>

                <el-table-column label="数量" prop="number">
                <template #default="{ row }">
                    <el-input v-model="row.number" placeholder="请输入" size="small" />
                </template>
                </el-table-column>

                <el-table-column label="单价（元）" prop="unitprice">
                <template #default="{ row }">
                    <el-input v-model="row.unitprice" placeholder="请输入" size="small" />
                </template>
                </el-table-column>

                <el-table-column label="预算金额" prop="budgetamount">
                <template #default="{ row }">
                    <el-input v-model="row.budgetamount" placeholder="请输入" size="small" />
                </template>
                </el-table-column>

            </el-table>
            <el-button type="success" @click="saveProject()" style="width: auto; float: left;">保存</el-button>
            </el-tab-pane>
        </el-tabs>
        </el-card>
    </div>
</template>

<script lang="ts" src="./contract-payment-create.component.ts"></script>