package com.epsilon.training.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class loggerAspect {

	public loggerAspect()
	{
		log.info("LoggerAspect instantiated");
	}
	@Before("execution (* com.epsilon..ProductDao.*(..))")
	public void logBefore(JoinPoint jp)
	{	
		log.info("enetring {}() with argumwnts {}...",jp.getSignature().getName(),jp.getArgs());
	 }
	@After("execution (* com.epsilon..ProductDao.*(..))")
	public void logAfter(JoinPoint jp)
	{	
		log.info("Existing {}() method....",jp.getSignature().getName(),jp.getArgs());
	 }
	
}
