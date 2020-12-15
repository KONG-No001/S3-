<template>
    <div>
        <el-table :data="userList" >
            <el-table-column label="NO" property="id" width="50" align="center">
            </el-table-column>
            <el-table-column label="账号名称" property="name" width="150">
            </el-table-column>
            <el-table-column label="所属部门" property="department" width="200">
            </el-table-column>
            <el-table-column label="头像" property="portrait" :width="120">
                <el-image src="#" style="width: 26mm;height: 32mm;"></el-image>
            </el-table-column>
            <el-table-column label="角色" property="null" width="200">
            </el-table-column>
            <el-table-column align="right">
                <template slot="header" slot-scope="scope">

                    <el-input
                            size="mini"
                            v-model="demo.type"
                            placeholder="输入关键字搜索">
                        {{scope.id}}
                        <el-select v-model="demo.search" slot="prepend" style="width: 80px" >
                            <el-option label="A" value="a"></el-option>
                            <el-option label="B" value="b"></el-option>
                        </el-select>
                    </el-input>
                </template>
            </el-table-column>
        </el-table>
<!--        <el-table :data="tableData">-->
<!--            <el-table-column-->
<!--                    label="Date"-->
<!--                    prop="date">-->
<!--            </el-table-column>-->
<!--            <el-table-column-->
<!--                    label="Name"-->
<!--                    prop="name">-->
<!--            </el-table-column>-->
<!--            <el-table-column align="right">-->
<!--&lt;!&ndash;                <template slot="header" slot-scope="scope">&ndash;&gt;-->
<!--&lt;!&ndash;                    <el-input&ndash;&gt;-->
<!--&lt;!&ndash;                            v-model="search"&ndash;&gt;-->
<!--&lt;!&ndash;                            size="mini"&ndash;&gt;-->
<!--&lt;!&ndash;                            placeholder="输入关键字搜索">{{scope.id}}</el-input>&ndash;&gt;-->
<!--&lt;!&ndash;                </template>&ndash;&gt;-->
<!--                <template slot="header" slot-scope="scope">-->
<!--                    <el-input-->
<!--                            v-model="search"-->
<!--                            size="mini"-->
<!--                            placeholder="输入关键字搜索">-->
<!--                        {{scope.id}}-->
<!--                    </el-input>-->
<!--                </template>-->
<!--                <template slot-scope="scope">-->
<!--                    <template v-if="scope.id"></template>-->
<!--                    <el-button-->
<!--                            size="mini">Edit</el-button>-->
<!--                    <el-button-->
<!--                            size="mini"-->
<!--                            type="danger">Delete</el-button>-->
<!--                </template>-->
<!--            </el-table-column>-->
<!--        </el-table>-->


    </div>
</template>

<script>
    import Axios from "axios";
    export default {
        name: "staffManagement",
        data: function(){
            return {
                userList: [],

                demo: {
                    search: "",
                    type:'',
                }


            }
        },
        created: function () {
            let _this = this;
            Axios.get("/application/user/listUser.action").then((request)=>{
                _this.userList = request.data;
                this.loading = false;
            })
        }
    }
    // export default {
    //     data() {
    //         return {
    //             tableData: [{
    //                 date: '2016-05-02',
    //                 name: '王小虎',
    //                 address: '上海市普陀区金沙江路 1518 弄'
    //             }, {
    //                 date: '2016-05-04',
    //                 name: '王小虎',
    //                 address: '上海市普陀区金沙江路 1517 弄'
    //             }, {
    //                 date: '2016-05-01',
    //                 name: '王小虎',
    //                 address: '上海市普陀区金沙江路 1519 弄'
    //             }, {
    //                 date: '2016-05-03',
    //                 name: '王小虎',
    //                 address: '上海市普陀区金沙江路 1516 弄'
    //             }],
    //             search: ''
    //         }
    //     },
    // }
</script>

<style scoped>

</style>