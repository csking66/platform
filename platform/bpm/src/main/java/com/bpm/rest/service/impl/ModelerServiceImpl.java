package com.bpm.rest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bpm.rest.service.ModelerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
* @ClassName: ModelerServiceImpl
* @Description: 
* @date 2018年11月12日
*
*/
@Service
public class ModelerServiceImpl implements ModelerService{
	 
	 @Resource
	 private RepositoryService repositoryService;

	@SuppressWarnings("deprecation")
	@Override
	public String crateModel(String name, String key, String description) {
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id","canvas");
            editorNode.put("resourceId","canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            description = StringUtils.defaultString(description);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(name);
            modelData.setKey(StringUtils.defaultString(key));

            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

            return modelData.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}


	@Override
	public List<Model> queryModelList() {
		 List<Model> list = repositoryService.createModelQuery().list();
	     return list;
	}

}
