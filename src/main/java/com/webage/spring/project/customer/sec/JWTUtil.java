package com.webage.spring.project.customer.sec;

public interface JWTUtil {
	public boolean verifyToken(String jwt_token);
	public String getScopes(String jwt_token) ;
	//public Token createToken(String scopes) ;
}