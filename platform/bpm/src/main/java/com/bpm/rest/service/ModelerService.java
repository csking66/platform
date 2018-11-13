package com.bpm.rest.service;

import org.activiti.engine.repository.Model;

import java.util.List;

/**
* @ClassName: ModelerService
* @Description: 
* @date 2018年11月12日
*
*/

public interface ModelerService {

	 /**
     * 创建空model
     * @param name
     * @param key
     * @param description
     * @return
     */
    String crateModel(String name,String key,String description);


    /**
     * 获取模型列表
     * @return
     */
    List<Model> queryModelList();
}
