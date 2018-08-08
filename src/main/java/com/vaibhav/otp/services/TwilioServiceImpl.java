package com.vaibhav.otp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.vaibhav.otp.dao.OtpRepository;
import com.vaibhav.otp.utils.OtpUtils;

@Service
public class TwilioServiceImpl implements TwilioService {
	
	public static final String ACCOUNT_SID = "***...***7**8";
	public static final String AUTH_TOKEN = "***...***3**1";
	
	@Autowired
	OtpRepository otpRepository;
	
	@Override
	public String sendOtp(String email, String otp, String phoneNumber) throws Exception {
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
		Message message = Message.creator(
				new com.twilio.type.PhoneNumber(phoneNumber), 
				new com.twilio.type.PhoneNumber("+yourPurchasedNumber"), 
				"Your otp is: "+otp+" , this will expire in next 15minutes!!!")
				.create();
		
		OtpUtils.saveOtp(otpRepository, email, otp);
		return message.getSid();
		
	}
	
	
}
