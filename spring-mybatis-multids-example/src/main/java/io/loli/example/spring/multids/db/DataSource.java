package io.loli.example.spring.multids.db;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 * 指定mapper使用哪个数据库
 * @author xwk
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DataSource {
	String value();
}
