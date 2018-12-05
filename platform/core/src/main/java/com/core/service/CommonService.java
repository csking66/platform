package com.core.service;

public interface CommonService {

	/**
	 * @param id    主键Id
	 * @param clazz 具体实体类
	 * @return Object    返回类型
	 * @throws
	 * @Title: getOne
	 * @Description: TODO
	 */
	Object getOne(String id, Class<?> clazz);

	/**
	 * @param clazz  具体实体类
	 * @param object 要保存的对象
	 * @param strs   保存验证唯一性字段
	 * @return void    返回类型
	 * @throws
	 * @Title: save
	 * @Description: 保存接口
	 */
	void save(Class<?> clazz, Object object, String[] strs) throws Exception;

	/**
	 * @param id    主键Id
	 * @param clazz 具体实体类
	 * @param logic 是否逻辑删除
	 * @return void    返回类型
	 * @throws
	 * @Title: delete
	 * @Description: 删除接口
	 */
	void delete(String id, Class<?> clazz, Boolean logic);

	/**
	 * @param id     主键Id
	 * @param clazz  具体实体类
	 * @param rowSts 失效 false 激活 true
	 * @return void    返回类型
	 * @throws
	 * @Title: modify
	 * @Description: 基础数据更新状态
	 */
	void modify(String id, Class<?> clazz, Boolean rowSts);
}
