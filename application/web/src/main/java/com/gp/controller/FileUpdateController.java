package com.gp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gp.utils.FileUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class FileUpdateController {

    /**
     *
     * @param file
     * @param folder
     * @param uuid
     * @param request
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/FileUpdate/directUpdate.action", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> directUpdate(
            MultipartFile file,
            @RequestParam(defaultValue = "") String folder,
            @RequestParam(defaultValue = "") String uuid,
            @RequestParam(defaultValue = "") String fileName,
            HttpServletRequest request) throws Throwable {

        String savePath = folder+Objects.requireNonNull("".equals(fileName)?file.getOriginalFilename():fileName).replaceAll("\\.\\w*\\b$",uuid+"$0");

        String[] paths = FileUpdate.getLocalPathAndServletPath(
                request,
                null,
                "\\src\\main\\webapp\\",
                "\\");

        FileUpdate.executeUpdate(
                (InputStream) file.getInputStream(),
                paths[0]+savePath,
                paths[1]+savePath);

        Map<String,Object> map = new HashMap<>();
        map.put("servletUrl","/"+savePath);

        return map;
    }

    @RequestMapping(value = "/FileUpdate/jsonUpdate.action", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> jsonUpdate(MultipartFile[] file, @RequestParam("data")String data, HttpServletRequest request) throws JsonProcessingException {
        System.out.println(Arrays.toString(file));
        System.out.println(data);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.readValue(data,Map.class));
        return null;
    }

}
