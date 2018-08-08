package com.vaibhav.otp.services;

public interface TwilioService {
	
	public String sendOtp(String email, String otp, String number) throws Exception;
}
