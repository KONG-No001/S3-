
/*
 * 基本的app模板，如果创建页面繁琐且代码高度一致，则建
 * 议使用该模板以减少创建的负担。
 */
/*
 * 注意！！ 请不要更改模板！！！
 */
/**
 *
 * @type {string}
 */
const INDEX_HTML = "public/demo.html";


module.exports = {
    // 打包配置
    pages: {
        // URL
        "backstage/login": {
            // 打包入口
            entry: "src/backstage/login/index.js",
            // 打包模板
            template: INDEX_HTML,
            // 标题
            title: "后台管理系统/员工登录"
        },
        "backstage/home": {
            // 打包入口
            entry: "src/backstage/home/index.js",

            // 打包模板
            template: INDEX_HTML,
            // 标题
            title: "后台管理系统"
        },
        "reception/spfl": {
            // 打包入口
            entry: "src/reception/spfl/spfl.js",

            // 打包模板
            template: INDEX_HTML,
            // 标题
            title: "商品分类"
        },
        "reception/spxq": {
            // 打包入口
            entry: "src/reception/spxq/spxq.js",

            // 打包模板
            template: INDEX_HTML,
            // 标题
            title: "商品详情"
        },
        "demo1":{
            entry: "src/demo/demo1.js",
            template: INDEX_HTML,
        },
    },
    // 服务器配置
    devServer: {
        // 端口号
        port: "8090",
        // 热部署
        hot: true,
        // 启动完毕时打开浏览器
        open: true,
        // 打开浏览器时访问的页面
        openPage: 'backstage/login',
        // 代理
        proxy: {
            //需要代理的URL
            "/application":{
                // 跳转的连接
                target: "http://localhost:8080/",
                // 允许跨域
                changeOrigin: true,
            }
        }
    }
}