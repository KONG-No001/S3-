package com.gp.controller;

import com.gp.utils.FileUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
public class FileUpdateController {

    /**
     * 直接提交文件的请求，默认只有文件参数。
     * @param file 文件
     * @return
     */
    @RequestMapping(value = "/FileUpdate/directSubmit.action", method = RequestMethod.POST)
    @ResponseBody
    public boolean directSubmit(MultipartFile file, HttpServletRequest request) throws Throwable {
        String[] paths = FileUpdate.getLocalPathAndServletPath(
                request,
                null,
                "\\src\\main\\webapp\\assets\\",
                "\\assets\\");

        FileUpdate.executeUpdate(
                (FileInputStream) file.getInputStream(),
                paths[0]+file.getOriginalFilename(),
                paths[1]+file.getOriginalFilename());
        return false;
    }
}
