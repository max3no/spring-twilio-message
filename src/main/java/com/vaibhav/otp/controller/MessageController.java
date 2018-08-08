package com.vaibhav.otp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.otp.bo.otpRequest;
import com.vaibhav.otp.dao.OtpRepository;
import com.vaibhav.otp.services.TwilioService;
import com.vaibhav.otp.utils.OtpUtils;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
	
	
	@Autowired
	TwilioService twilioService;
	
	@Autowired
	OtpRepository otpRepository;
	
	@PostMapping("/send")
	public ResponseEntity<?> createOtp(@RequestBody otpRequest request) {
		
		String otp = OtpUtils.createOtp();
		try{
			String response = twilioService.sendOtp(request.getEmail(), otp, request.getPhoneNumber());
			return ResponseEntity.ok().body(response);
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping("/verify")
	public ResponseEntity<?> verifyOtp(@RequestParam("otp") String otp, @RequestParam("email") String email) {
		
		try{
			String response = OtpUtils.verifyOtp(otpRepository, otp, email);
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
