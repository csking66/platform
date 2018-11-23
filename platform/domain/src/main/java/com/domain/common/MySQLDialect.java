package com.domain.common;

import java.sql.Types;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * 
* @ClassName: MySQLDialect
* @Description: mysql 可以保存json 格式， 如list map
* @date 2018年11月23日
*
 */
public class MySQLDialect extends MySQL5InnoDBDialect {

	public MySQLDialect() {

		super();

		/**
		 * 往TypeNames中添加jsonb类型
		 */
		this.registerColumnType(Types.JAVA_OBJECT, "json");

		/**
		 * 防止无类型时报错 No Dialect mapping for JDBC type: 1111
		 */
		this.registerHibernateType(Types.OTHER, "JsonUserType");
	}
}
