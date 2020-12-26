package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016110400790778";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkVUIYXWdUp5gW1P6t3N915LkcWvskJxHkv5kFI8k0+/ULqcVu8N39w4AR4QRVxno3DYlplpccHfR6nTg0WKY83MAmOZD6ei4Oi0ygkX5XqF0hnCZtrR+snZfSsEycEw0dXX8TJCVW/ML2v3h++sm2K3X1Md1hn2ygbqZ/y/w/sp8zgXnr1DqwyabhJA9GzhWyM3rNvguLvfWWHkPIPZTXts03aUFA1OuLa6GU035V99cNMww0y6vOLkmVCmr3ni2baT1F92O8y3vtZtFBZzTS4OdZloF3dVK7MHf/oiUF8zU1J3drMWt+udvwJgNr0okbjVPIHnjgKS0Ok27Q7hwpAgMBAAECggEAeDJAENAopxuaO4D48rQt4e40kw/cx1moVdbqFn9RSObNGgXH+dhFlrmq7+jUoeR1OdU137wctJ0YV6KtHGSG2tdf19WdPAenrk2m837XmUf1LoMm/Gt3UmtGQ/1dW373xbXAH4D9qr1f4Ni1SrMmXI83TOt3EFNqQYINWEYcL33LpIQwJyRiSetpZysK5pvbfo8KoRvjGPdRpUnHuMdQBWNP5+sX+iGtFIOMdtMIui0wbgsFRtCLSw4bBlZKgfXVLxPrE6NKlxPDid7EsAsUHG25gPxvr+EwuJcRb/lkxqdTYwCF8n/j6urufjz/s1ImCwOc1B33PQhi0d9ti98V/QKBgQDRnm6q44WOjoVf+rsZb7zMvGESKQ9IfQik5q4rkyYP98V1uX8Jd6Kc7/lSsFNqtHKmBvjEB3s7l4qMrEL4rbNQrRB2ym1k3B+HBL67R1c8oayiRHwrfn9gmt27/p9GRaeDjPdh/FyarbNVQfF2ylsuCg8AdIXNrBjbkaSYfNZZ2wKBgQDIsaxQea3nLtA0EOyCPXal/D5mOoJsaFY1BfVFrefszGZbeYUGgz4HSc1Lphvxh8IBsetJtevm0EJC8ca4kFuQbuo9piOGZHr1g6YZ/RNhnLzCUJz1BgY/UelVNWG27SJwGYwR5FtODyMXMJ/lIKWy//eyLoiwGcYubmDrjj4rSwKBgQC+8wf0+9XKCuJMkGanwTXHe0Mh++1EJ0IclVbLsvvVR+vEVfn3cdfcBm6keKNEbKSTwX4TdI05t84qXzGSvwi90SlSTQxh+HNv106340EApnZQSvqh1FXMdsbOXiBRL1ptZ5hi1jS1Ys4eODfx8lwlEFyiGYNZ3A+41G+PyQiqSQKBgAucvCur3rQRsVpR4F1o0QSpo7KmkAVVxCctQRPXDCRXb1S7o0bNMTmau5Y09kjzYfZXJRYOwKeKRUs6UhU7xRCGWmdWxZdxGOw7wdCfJA/x0bosj+hdgN8JDkcv34eGEQgLcl9WpzHNDmq5FYdcbDn/NYXJy37k/uKQ4GrAwDghAoGAaLGCptPXhn9CDYPwdtrKjCjWoEgGWjk0306nKBknv4CzcneJLp4hZU31uHX/VhcR1xU8QlJg56iCS+zmacv14foA6FMpG+L9hyKm0f/B59MubpouOxud2BUs+ObHX0mDbS4SOJOnM3aEenY0noovmmgc/bahXBWF3OFWjEl2YSQ=";


    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi0WJcSyViObXgkbi7gSifOdXbW9qgPiznBl1LqlYxzjUZtDgZfKWJxhBfZk7qdxZbsxg24nEZWGyXyEFaYJM6EVLn30NjwZyKiIHe25MMowNtoGW19JwoNWM2bo5ozmEKpkGMibt+t0f58gClmrNcPwRMiqsmpIyOGS6e0sFsEUgNNHxji+VPEstDyLGfjjT3Q3zKXeJpyHG6h67xwyJ9GmkVi1pVCWFgthPMEs1dsj1hEUldlm0U4kMAd5myArx17VqcjH23jaIXuZwKRhkZOSELnyhkHt6Mim6TJRwACu/Wd1l87GlJWd+jOaPLJnhbgVq3yJys/8zdVg6VUMY2wIDAQAB";


    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/application/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/application/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
