package com.gp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class FileUpdate {

    /**
     * 直接提交文件的请求，默认只有文件参数。
     * @param file 文件
     * @return
     */
    @RequestMapping(value = "/FileUpdate/directSubmit.action", method = RequestMethod.POST)
    @ResponseBody
    public boolean directSubmit(MultipartFile file, HttpServletRequest request) throws Throwable {
        String[] paths = getLocalPathAndServletPath(
                request,
                null,
                "\\src\\main\\webapp\\assets\\",
                "\\assets\\");
        updateFile(
                (FileInputStream) file.getInputStream(),
                paths[0]+file.getOriginalFilename(),
                paths[1]+file.getOriginalFilename());
        return false;
    }


    /**
     *
     * <div style="width:400px; text-indent:30px">
     *     <p>获取本地仓库地址和服务端地址。</p>
     *     <p>
     *         逻辑为：基于IDEA目录发布\编译地址与本地库地址存在高度相关的特性,
     *         将发布\编译地址通过正则表达式间接将本地库地址获取，从而继续进行io等其他操作
     *     </p>
     * </div>
     * @param request http请求，用于获取服务端地址
     * @param regExp 正则表达式，将服务端地址经行匹配，匹配结果为本地地址
     * @param appendLocalPath 在匹配的本地地址上再添加的目录地址
     * @param appendServletPath 在获取的服务端地址上再添加的目录地址
     * @return 字符串集和，下标0为本地地址、1为服务端地址
     * @throws Throwable 若正则表达式不能匹配出结果，则将掏出该错误
     *
     */
    public String[] getLocalPathAndServletPath(HttpServletRequest request,String regExp, String appendLocalPath, String appendServletPath) throws Throwable {
        String pattern = "\\b.+:.*[\\\\]application[\\\\]web[\\\\]";
        String url = request.getServletContext().getRealPath("\\");
        String path;

        if(regExp!=null && !"".equals(regExp)) pattern = regExp;
        Matcher matcher = Pattern.compile(pattern).matcher(url);

        if(matcher.find(0)){
            path = matcher.group();
        }else {
            throw new Throwable("正则表达式无法将url截断。请检查表达式是否可将该url截断：url："+url+", regExp: "+pattern);
        }

        return new String[]{path+appendLocalPath,url+appendServletPath};
    }

    /**
     *
     * <p style="color:#FF7600">底层的基本实现，将文件流分别更新到本地仓库和服务端</p>
     * @param inputStream 文件流
     * @param localFilePath 本地仓库地址
     * @param servletFilePath 服务端地址
     */
    public void updateFile (FileInputStream inputStream, String localFilePath, String servletFilePath) throws IOException{
        BufferedInputStream bufferedInputStream = new BufferedInputStream((inputStream));
        File newLocalFile = new File(localFilePath);
        File newServletFile = new File(servletFilePath);

        if(newLocalFile.exists()) newLocalFile.mkdirs();
        if(newServletFile.exists()) newServletFile.mkdirs();

        BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(newLocalFile));
        BufferedOutputStream servletBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(newServletFile));

        byte[] stream = new byte[1048576];
        for (int i=bufferedInputStream.read(stream);i>-1;i=bufferedInputStream.read(stream)){
            localBufferedOutputStream.write(stream,0,i);
            servletBufferedOutputStream.write(stream,0,i);
        }
        bufferedInputStream.close();
        localBufferedOutputStream.flush();
        localBufferedOutputStream.close();
        servletBufferedOutputStream.flush();
        servletBufferedOutputStream.close();
    }

}
