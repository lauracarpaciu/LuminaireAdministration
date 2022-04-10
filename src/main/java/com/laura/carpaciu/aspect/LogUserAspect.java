package com.laura.carpaciu.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogUserAspect {

	@AfterReturning(pointcut = ("com.laura.carpacu.poitcut.PointcutExpression.userLoginWithEmail() || com.gini.iordache.poitcut.PointcutExpression.userLoginWithUsername()"), returning = "auth")
	public void logTheLogins(JoinPoint joinPoint, Authentication auth) {
		Logs.loginLogoutUser(auth, "logged at");
	}

	@AfterReturning(pointcut = ("com.laura.carpacu.poitcut.PointcutExpression.createUser()"), returning = "user")
	public void logCreateUser(JoinPoint joinPoint, User user) {
		Logs.createUser(user, "created at");
		Logs.sendEmail(user, "sent email at:");
	}

	@AfterThrowing(pointcut = "execution(* ro.laura.carpacu..email.sender.impl.EmailService.sendEmail(..))", throwing = "exc")
	public void logSendMail(JoinPoint JoinPoint, Throwable exc) {
		String message = exc.getMessage();
		Logs.writeEmailException(message);
	}

	@Before("execution(* ro.laura.carpacu..security.handler.SecurityLogoutHandler.logout(..))")
	public void logLogout(JoinPoint joinPoint) {

		Object[] obj = joinPoint.getArgs();
		Authentication auth = (Authentication) obj[2];
		Logs.loginLogoutUser(auth, "logged out at:");
	}

	@AfterThrowing(value = "com.laura.carpacu.poitcut.PointcutExpression.modulesExceptions()", throwing = "exc")
	public void logAllExceptions(JoinPoint joinPoint, Throwable exc) {

		String methodSignature = joinPoint.getSignature().toString();
		String message = exc.getMessage();
		String message2 = methodSignature + " " + message;

		Logs.writeExceptions(message2);

	}

}