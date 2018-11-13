package com.bpm.rest.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bpm.rest.service.ModelerService;

@Controller
public class ActivitiModelController {
 
	private static final Logger logger = LoggerFactory.getLogger(ActivitiModelController.class);

    @Resource
    private ModelerService modelerService;

    /**
     * 创建流程模型
     * @param name
     * @param key
     * @param description
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public void createModel(@RequestParam("name") String name, @RequestParam("key") String key,
                            @RequestParam("description") String description,
                            HttpServletRequest request, HttpServletResponse response) {
        logger.info("创建空modeler：name:{},key:{},description:{}", name, key, description);
        try {
            //创建空模型
            String modelId = modelerService.crateModel(name, key, description);
            if(StringUtils.isBlank(modelId)) {
                throw new RuntimeException("创建modeler失败modelId:" + modelId);
            }

            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelId);
        }catch (Exception e) {
            logger.error("创建模型失败",e );
            throw new RuntimeException(e);
        }
    }

    /**
     * 模型列表
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @ResponseBody
    public Object modelList() {
        List<Model> list = modelerService.queryModelList();
        return list;
    }

 
}