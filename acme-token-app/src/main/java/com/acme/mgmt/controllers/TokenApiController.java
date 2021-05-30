package com.acme.mgmt.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.acme.mgmt.model.APIData;
import com.acme.mgmt.model.APITokenGenerationResponse;
import com.acme.mgmt.model.ApiErrorResponse;
import com.acme.mgmt.model.GenerateApiTokenRequest;
import com.acme.mgmt.model.InlineObject;
import com.acme.mgmt.service.TokenService;
import com.acme.mgmt.util.TOKEN_STATUS;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-28T22:20:10.938+10:00[Australia/Sydney]")



@RestController
@CrossOrigin
@RequestMapping("${openapi.aCMECorpAPITokenGeneration.base-path:/api}")
public class TokenApiController implements TokenApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TokenApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    Logger logger = LoggerFactory.getLogger(TokenApiController.class);
    private boolean validReq=false; 
    @Value("${api.url}")
    String apiUrl;
    
    @Autowired
    TokenService tokenService;
    
    @Autowired
    APIData apidata;
    
    @RequestMapping(value = "/token/generate",
	    produces = { "application/json" }, 
	    consumes = { "application/json" },
	    method = RequestMethod.POST)
    public ResponseEntity<APITokenGenerationResponse> tokenGeneratePost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody GenerateApiTokenRequest body) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                	validReq = true;
                	break;
                }else {
                	validReq=false;
                }
            }
        });
        
        if(validReq) {
        	APITokenGenerationResponse genTokenResp = tokenService.genToken(body);
        	 return new ResponseEntity<>(genTokenResp, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    

    @RequestMapping(value = "/token/validate",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    public ResponseEntity<APIData> tokenValidatePost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody InlineObject body) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                	validReq = true;
                	break;
                }else {
                	validReq=false;
                }
            }
        });
        
        
        if(validReq) {
        	TOKEN_STATUS tokenStatus = tokenService.validateToken(body.getApiToken());
        	
        	if(tokenStatus==TOKEN_STATUS.VALID) {
        		 apidata.setAppUrl(apiUrl);
        		 return new ResponseEntity<>(apidata, HttpStatus.OK);
        	}else if(tokenStatus==TOKEN_STATUS.EXPIRED){
        		throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"API token  Expired");
        	}else {
        		throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid API token provided");
        	}
        	
       }
       return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
    
    @ApiOperation(value = "Lists available tokens", nickname = "getTokensPost", notes = "This endpoint will list vailable API tokens", response = APITokenGenerationResponse.class, authorizations = {
            @Authorization(value = "Authorization")
        }, tags={  })
    @RequestMapping(value = "/token/all",
            produces = { "application/json" }, 
            consumes = { "application/json" },
            method = RequestMethod.POST)
        public ResponseEntity<List<APITokenGenerationResponse>> getTokensPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody GenerateApiTokenRequest body) {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    	validReq = true;
                    	break;
                    }else {
                    	validReq=false;
                    }
                }
            });
            
            
            if(validReq) {
            	 List<APITokenGenerationResponse> tokens = tokenService.getTokens();
            	
            	if(tokens == null || tokens.isEmpty()) {
            		throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"No record found");
            	}else {
            		return new ResponseEntity<>(tokens, HttpStatus.OK);
            	}
            	
           }
           return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        }
    
    @ApiOperation(value = "Upate token's validity", nickname = "updateTokensPost", notes = "This endpoint will disable/re-enable given token", response = APITokenGenerationResponse.class, authorizations = {
            @Authorization(value = "Authorization")
        }, tags={  })
    @RequestMapping(value = "/token/update",
            produces = { "application/json" }, 
            consumes = { "application/json" },
            method = RequestMethod.POST)
        public ResponseEntity<APITokenGenerationResponse> updateTokensPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody APITokenGenerationResponse body) {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    	validReq = true;
                    	break;
                    }else {
                    	validReq=false;
                    }
                }
            });
            
            
            if(validReq) {
            	 APITokenGenerationResponse resp = tokenService.updateToken(body);
            	
            	if(resp == null) {
            		throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"No record found");
            	}else {
            		return new ResponseEntity<>(resp, HttpStatus.OK);
            	}
            	
           }
           return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

        }
}
