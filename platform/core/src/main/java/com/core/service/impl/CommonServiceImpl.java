package com.core.service.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.core.service.CommonService;

/**
 * @ClassName: CommonService1Impl
 * @Description: TODO
 * @author cs
 * @date 2018年9月27日
 * @version V5.3
 */
@Service
public class CommonServiceImpl implements CommonService{
	
	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private EntityManager entityManager;


	@Override
	public Object getOne(String id, Class<?> clazz) {
		Object object = null;
		//判断是MongoDB 还是mysql
		Annotation annotation = clazz.getAnnotation(Entity.class);
		if(annotation == null){
			object = mongoTemplate.findById(id, clazz);
		}else{
			object = entityManager.find(clazz, id);
		}
		return object;
	}

	@Override
	public void save(Class<?> clazz, Object object, String[] strs) throws Exception {
		Annotation annotation = clazz.getAnnotation(Entity.class);
		JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(object));
		if(strs != null && strs.length > 0){
			Query query = new Query();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT * ");
			sb.append(" FROM ");
			sb.append(clazz.getName());
			sb.append(" WHERE 1 = 1");
			for(String filedName : strs){
				try {
					Field declaredField = FieldUtils.getField(clazz, filedName, true);
					if(declaredField != null){						
						if(getFieldType(declaredField)){
							//基础类型
							String value = jsonObj.getString(filedName).toString();
							query.addCriteria(Criteria.where(filedName).is(value));
							sb.append(" AND " + filedName + " = " + value );
						}else{
							//只有MongoDB 有保存对象
							String value = JSONObject.parseObject(JSON.toJSONString(jsonObj.get(filedName))).getString("id");
							query.addCriteria(Criteria.where(filedName + ".id").is(ObjectId.isValid(value) ? new ObjectId(value) : value));
						}
					}
				} catch (Exception e) {
					logger.error("保存反射组装查询语句异常" + e);
					throw new Exception("保存反射组装查询语句异常");
				}
			}
			//排除自己
			if(jsonObj.get("id") != null){
				query.addCriteria(Criteria.where("id").ne(jsonObj.get("id")));
				sb.append(" AND " + "id" + " <> " + jsonObj.get("id"));
			}	
			if(annotation == null){
				Long count = mongoTemplate.count(query, clazz);
				if(count > 0) throw new Exception("Tips.errorExist");	
			}else{
				int count = entityManager.createNativeQuery(sb.toString()).getMaxResults();
				if(count > 0) throw new Exception("Tips.errorExist");
			}
			
		}
		//更新基数数据状态
		Field rowSts = FieldUtils.getField(clazz, "rowSts", true);
		if(rowSts != null){
			if(jsonObj.get("rowSts") == null){
				jsonObj.put("rowSts", 5);
			}
		}	
		//保存数据
		Object entity = JSON.parseObject(jsonObj.toJSONString(), clazz);
		//状态初始化
		
		if(annotation == null){
			mongoTemplate.save(entity);
		}else{
			entityManager.persist(entity);
		}
		
	}

	/**
	 * logic 逻辑删除 true 物理删除 false
	 */
	@Override
	public void delete(String id, Class<?> clazz, Boolean logic) {
		 //查询出来
		 Object object = getOne(id, clazz);
		 if(object == null) return;
		 Annotation annotation = clazz.getAnnotation(Entity.class);
		//物理删除
		 if(annotation == null){
			 mongoTemplate.remove(object);
		 }else{
			 entityManager.remove(object);
		 }	
	}
	

	/**
	 * rowSts 失效false  激活 true
	 */
	@Override
	public void modify(String id, Class<?> clazz, Boolean rowSts) {
		if(rowSts == null) return;
		//查询出来
		 Object object = getOne(id, clazz);
		 if(object == null) return;
		 Annotation annotation = clazz.getAnnotation(Entity.class);
		 Integer _rowSts = null;
		 if(rowSts){
			 _rowSts = 5;			 
		 }else{
			 _rowSts = 10;
		 }		 
		 if(annotation == null){
			 Query query = new Query();
			 query.addCriteria(Criteria.where("id").is(id));
			 Update update = new Update();
			 update.set("rowSts", _rowSts);
			 mongoTemplate.updateFirst(query, update, clazz); 
		 }else{
			 StringBuffer sb = new StringBuffer("UPDATE ");
			 sb.append(clazz.getName());
			 sb.append(" SET rowSts = :rowSts WHERE id = :id");
			 javax.persistence.Query query = entityManager.createQuery(sb.toString());
			 query.setParameter("rowSts", _rowSts);
			 query.setParameter("id", id);
			 query.executeUpdate();
		 }

	}

	/**
	 * 
	 * @Title: getFieldType
	 * @Description: 判断是否是基本类型
	 * @param field
	 * @return  
	 * @return Boolean    返回类型
	 * @throws
	 */
	public Boolean getFieldType(Field field){
		
		Boolean result = Boolean.TRUE;
		
		if(field.getType() == String.class){
			return result;
		}		
		return field.getType().isPrimitive();
	}

}
