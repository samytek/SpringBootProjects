package com.example.demo.job;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.contants.AppConstants;
import com.example.demo.exception.ErrorHandler;
import com.example.demo.model.EmailOtpTracker;
import com.example.demo.repository.EmailOtpTrackerDAO;

//@Configuration
//@EnableScheduling
public class OtpExpiryJob {

	private static final Logger logger = LoggerFactory.getLogger(OtpExpiryJob.class);

	@Autowired
	EmailOtpTrackerDAO emailOtpTrackerDAO;

	/*
	 * @Scheduled(fixedDelay = 60000) // Every 01 minute public void execute() {
	 * logger.
	 * info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> Otp Expiry Job Job Start >>>>>>>>>>>>>>>>>>>>>>> "
	 * + new Date()); try { isOTPExpired(); } catch (Exception e) {
	 * logger.error(ErrorHandler.getErrorMessageLog(new Object() { }.getClass(),
	 * e)); } logger.
	 * info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> Otp Expiry Job Job End >>>>>>>>>>>>>>>>>>>>>>> "
	 * + new Date()); }
	 */

	public void isOTPExpired() {
		long currentTimeInMillis = System.currentTimeMillis();
		List<EmailOtpTracker> emailOtpTrackerList = emailOtpTrackerDAO.findAll();
		if (emailOtpTrackerList.size() > 0) {
			for (EmailOtpTracker emailOtpTrackerInst : emailOtpTrackerList) {
				if(!emailOtpTrackerInst.isAuthenticated() && !emailOtpTrackerInst.isExpired()) {
					long otpRequestedTimeInMillis = emailOtpTrackerInst.getOtpGenerateDateTime().getTime();
					if (otpRequestedTimeInMillis + AppConstants.OTP_VALID_DURATION < currentTimeInMillis) {
						emailOtpTrackerInst.setExpired(true);
						emailOtpTrackerDAO.save(emailOtpTrackerInst);
					}
				}
			}
		}
	}
}
