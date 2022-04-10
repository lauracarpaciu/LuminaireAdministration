package com.laura.carpaciu.poitcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutExpression {

	@Pointcut("execution(* ro.laura.carpaciu.security.provider.UserNamePasswordProvider.authenticate(..))")
	public void userLoginWithUsername() {

	}

	@Pointcut("execution(* ro.laura.carpaciu.security.provider.EmailProvider.authenticate(..))")
	public void userLoginWithEmail() {

	}

	@Pointcut("execution(* com.laura.carpaciu.services.impl.user.UserServiceImpl.createUser(..))")
	public void createUser() {

	}

	@Pointcut("execution(* ro.gini.iordache.security.provider.*.*(..))")
	private void securityModule() {

	}

	@Pointcut("execution(* com.laura.carpaciu.*.*.*.*.*(..))")
	private void persistenceModule() {

	}

	@Pointcut("execution(* com.laura.carpaciu.service.*.*(..))")
	private void invoiceModule() {

	}

	@Pointcut("securityModule() || persistenceModule() || invoiceModule()")
	public void modulesExceptions() {

	}

}