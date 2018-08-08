package com.vaibhav.otp.utils;

import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

import com.vaibhav.otp.dao.OtpRepository;
import com.vaibhav.otp.models.OTP;

public class OtpUtils {
	
	public static String createOtp() {
		Random random = new Random();
		int[] otp = new int[5];
		for(int i=0 ; i< otp.length ; i++){
			otp[i] = random.nextInt(9);
		}
		
		return Arrays.stream(otp).mapToObj(String::valueOf).reduce((a,b) -> a.concat(b)).get();
	}
	
	public static Boolean saveOtp(OtpRepository otpRepository, String email, String otpGenerated) {
		
		OTP otp = otpRepository.findByEmail(email);
		if(otp != null) {
			otp.setOtp(otpGenerated);
			otp.setStatus("InActive");
		} else {
			otp = new OTP(email, otpGenerated, "InActive");
		}
		
		otpRepository.save(otp);
		
		return true;
		
	}
	
	public static String verifyOtp(OtpRepository otpRepository, String otp, String email) throws Exception {
		
		OTP otpSaved = otpRepository.findByEmail(email);
		if(otpSaved != null) {
			if(otpSaved.getStatus().equals("Active")) {
				return "Already verified";
			}else{
				Instant otpExpiryTime = otpSaved.getUpdatedAt().plusSeconds(900l);
				Instant current = Instant.now();
				if(current.compareTo(otpExpiryTime) >= 0) {
					return "Your OTP has expired please generate again";
				} else {
					if(otp.equals(otpSaved.getOtp())) {
						otpSaved.setStatus("Active");
						otpRepository.save(otpSaved);
						return "Verified "+email;
					}else {
						return "InValid OTP";
					}
				}
			}
		}else {
			return "Use the other API to generate an OTP first!!!";
		}
		
	}
	

}
