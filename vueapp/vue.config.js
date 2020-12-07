module.exports={
    pages: {
        "login": "src/login.js",
    },
    devServer: {
        port: "8090",
        proxy: {
            "/application":{
                target: "http://localhost:8080/",
                changeOrigin: true,
            }
        }
    }
}