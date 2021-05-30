package com.acme.mgmt.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.annotation.Order;

import com.acme.mgmt.model.APITokenGenerationResponse;
import com.acme.mgmt.model.GenerateApiTokenRequest;
import com.acme.mgmt.repository.ApiTokenDataRepository;
import com.acme.mgmt.util.TOKEN_STATUS;


@ExtendWith(MockitoExtension.class)
class TokenServiceTest {
	@InjectMocks
	TokenService tokenService;
	
	@Mock
	private ApiTokenDataRepository repository;
	
	@Test
	@Order(1)
	void testValidateToken() {
		String tokenToBeValidated = "PfvRDv5xPP1";
		APITokenGenerationResponse mockTokens =new APITokenGenerationResponse("PfvRDv5xPP1","2021-06-05 20:11:11",1);
		when(repository.findByApiToken(tokenToBeValidated)).thenReturn(mockTokens);
		TOKEN_STATUS validateTokenStatus = tokenService.validateToken(tokenToBeValidated);
		System.out.println("Token Status: "+validateTokenStatus);
		assertEquals(TOKEN_STATUS.VALID,validateTokenStatus);
	}

	@Test
	@Order(3)
	void testGetTokens() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		APITokenGenerationResponse token1 = new APITokenGenerationResponse("sas1212",LocalDateTime.now().format(formatter),1);
		APITokenGenerationResponse token2 = new APITokenGenerationResponse("sas1212",LocalDateTime.now().format(formatter),1);
		
		List<APITokenGenerationResponse> listTokens = new ArrayList<APITokenGenerationResponse>();
		listTokens.add(token1);
		listTokens.add(token2);
		
		when(repository.findAll()).thenReturn(listTokens);
		
		List<APITokenGenerationResponse> tokens = tokenService.getTokens();
		assertNotNull(tokens);
	}
	
	@Test
	@Order(3)
	void testGenToken() {
		GenerateApiTokenRequest req = new GenerateApiTokenRequest();
		req.setAppUrl("http://dummy");
		
		String apiToken="dummy";
		String tokenValidity="dummy";
		
		APITokenGenerationResponse tobeSaved = new APITokenGenerationResponse(apiToken,tokenValidity);
		
		when(repository.save(tobeSaved)).thenReturn(tobeSaved);
		
		APITokenGenerationResponse genToken = tokenService.genToken(req);
		System.out.println("generated token: "+genToken);
		//verify(repository, atLeastOnce()).save(new APITokenGenerationResponse(apiToken,tokenValidity));
		assertNotNull(genToken);
	}

	
}
