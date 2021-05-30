package com.acme.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acme.mgmt.model.APITokenGenerationResponse;

public interface ApiTokenDataRepository extends JpaRepository<APITokenGenerationResponse, Integer>{
	APITokenGenerationResponse findByApiToken(String apiToken);
}