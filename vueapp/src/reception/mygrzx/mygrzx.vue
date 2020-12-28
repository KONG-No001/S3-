<template>
    <div style="width: 100%">
        <div style="width: 100%;height: 110px">
            <h1>个人中心</h1>
            <el-row style="margin-left: 1300px">
                <el-button type="primary" round>登录</el-button>
                <el-button type="danger" round>注销</el-button>
            </el-row>
        </div>
        <div>
            <el-tabs type="border-card">
                <el-tab-pane label="待付款">
                    <el-table
                            :data="tableData"
                            height="250"
                            border
                            style="width: 100%">
                        <el-table-column
                                prop="id"
                                label="ID"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="name"
                                label="姓名"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="address"
                                label="地址">
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane label="待发货">
                    <el-table
                            :data="tableData"
                            height="250"
                            border
                            style="width: 100%">
                        <el-table-column
                                prop="date"
                                label="日期"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="name"
                                label="姓名"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="address"
                                label="地址">
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane label="待提货">
                    <el-table
                            :data="tableData"
                            height="250"
                            border
                            style="width: 100%">
                        <el-table-column
                                prop="date"
                                label="日期"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="name"
                                label="姓名"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="address"
                                label="地址">
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane label="已提货">
                    <el-table
                            :data="tableData"
                            height="250"
                            border
                            style="width: 100%">
                        <el-table-column
                                prop="date"
                                label="日期"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="name"
                                label="姓名"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="address"
                                label="地址">
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
    export default {
        name: "mygrzx",
        data(){
            return{
                circleUrl:"./logo.png"
            }
        }
    }
</script>

<style scoped>
    h1{
        text-align: center;
    }
    .el-header{
        background-color: #409EFF;

    }
    .el-main {
        color: #333;
        text-align: center;

    }
    .el-tabs{

    }
</style>