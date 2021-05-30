package com.acme.mgmt.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acme.mgmt.model.APITokenGenerationResponse;
import com.acme.mgmt.model.GenerateApiTokenRequest;
import com.acme.mgmt.repository.ApiTokenDataRepository;
import com.acme.mgmt.util.TOKEN_STATUS;

import antlr.StringUtils;

@Service
public class TokenService {
	
	Logger logger = LoggerFactory.getLogger(TokenService.class);

//	@Autowired
//	APITokenGenerationResponse resp;
	
	@Autowired
	ApiTokenDataRepository repository;
	
	public APITokenGenerationResponse genToken(GenerateApiTokenRequest req){
		logger.info("req: {}", req);
		String apiToken = genApiToken();
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime validityDateTime = localDateTime.plusDays(7);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String tokenValidity = validityDateTime.format(formatter);
		 
		logger.info("apiToken: {} , validity: {}", apiToken, tokenValidity);
		APITokenGenerationResponse tokenTobeSaved = new APITokenGenerationResponse(apiToken,tokenValidity);
		APITokenGenerationResponse resp = new APITokenGenerationResponse();
		resp = repository.save(tokenTobeSaved);
		
		return resp;
	}
	
	public TOKEN_STATUS validateToken(String inApiToken) {
		logger.info("token received: {}", inApiToken);
		APITokenGenerationResponse resp = repository.findByApiToken(inApiToken);
		
		if(resp!=null) {
			logger.info("repository value ApiToken: {}", resp.getApiToken());
			logger.info("repository value TokenValidity: {}", resp.getValidTo());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			LocalDateTime tokeValidDate = LocalDateTime.parse(resp.getValidTo(),formatter);
			LocalDateTime today = LocalDateTime.now();
			logger.info("today: {}", today);
			logger.info("tokeValidDate: {}", tokeValidDate);
			
			 int intervalGap = today.compareTo(tokeValidDate);
			 logger.info("difference: {}", intervalGap);
			 
			 if(inApiToken.equals( resp.getApiToken())) {
				 if(intervalGap<=0) {
					 return TOKEN_STATUS.VALID;
				 }else{
					 return TOKEN_STATUS.EXPIRED;
				 }
			 }
		}
		 
		 return TOKEN_STATUS.INVALID;
	}
	
	public List<APITokenGenerationResponse> getTokens() {
		List<APITokenGenerationResponse> resp = repository.findAll();
		 return resp;
	}
	
	public APITokenGenerationResponse updateToken(APITokenGenerationResponse req) {
		logger.info("req received: {}", req);
//		LocalDateTime currentDateTime = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		String updatedDateTime = currentDateTime.format(formatter);
//		req.setValidTo(updatedDateTime);
		
		APITokenGenerationResponse resp = repository.save(req);
		 return resp;
	}
	
	private String genApiToken() {
		 int length = 10;
		 boolean useLetters = true;
		 boolean useNumbers = true;
		 String generatedApiToken = RandomStringUtils.random(length, useLetters, useNumbers);
		 return generatedApiToken;
	}
	
	
}
