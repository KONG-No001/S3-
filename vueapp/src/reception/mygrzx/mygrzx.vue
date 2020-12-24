<template>
    <div style="width: 100%">
        <el-container>
            <el-header>
                <h1>个人中心</h1>
                <el-row >
                    <div class="block"><el-avatar :size="50"  :src="circleUrl"></el-avatar></div>
                </el-row>
            </el-header>
            <el-main></el-main>
        </el-container>
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
        color: #FFFFFF;
    }
</style>