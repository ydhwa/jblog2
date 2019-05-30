package com.cafe24.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 이 프로젝트에서는 사용하지 않음
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)	// 유효 기간
public @interface Auth {
	public enum Role { USER, ADMIN }
	public Role role() default Role.USER;
}
