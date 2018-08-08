package com.vaibhav.otp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.otp.models.OTP;

public interface OtpRepository extends JpaRepository<OTP, Long> {
	
	public OTP findByEmail(String email);

}
