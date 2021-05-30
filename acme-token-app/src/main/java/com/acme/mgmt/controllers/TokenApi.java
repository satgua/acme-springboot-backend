/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.acme.mgmt.controllers;

import com.acme.mgmt.model.APIData;
import com.acme.mgmt.model.APITokenGenerationResponse;
import com.acme.mgmt.model.ApiErrorResponse;
import com.acme.mgmt.model.GenerateApiTokenRequest;
import com.acme.mgmt.model.InlineObject;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-28T22:20:10.938+10:00[Australia/Sydney]")

@Validated
@Api(value = "token", description = "the token API")
public interface TokenApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /token/generate : Generates a new API Token
     * This endpoint will generate an API token and persist it
     *
     * @param body  (required)
     * @return API token generated (status code 200)
     *         or Invalid request parameters (status code 400)
     *         or Invalid JWT Token (status code 403)
     *         or Failed to generate API token (status code 500)
     */
    @ApiOperation(value = "Generates a new API Token", nickname = "tokenGeneratePost", notes = "This endpoint will generate an API token and persist it", response = APITokenGenerationResponse.class, authorizations = {
        @Authorization(value = "Authorization")
    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "API token generated", response = APITokenGenerationResponse.class),
        @ApiResponse(code = 400, message = "Invalid request parameters", response = ApiErrorResponse.class),
        @ApiResponse(code = 403, message = "Invalid JWT Token"),
        @ApiResponse(code = 500, message = "Failed to generate API token", response = ApiErrorResponse.class) })
    @RequestMapping(value = "/token/generate",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<APITokenGenerationResponse> tokenGeneratePost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody GenerateApiTokenRequest body) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"apiToken\" : \"4d4f434841\", \"validTo\" : \"12/12/2018 23:15:10\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /token/validate : Validates an API Token
     * API tokens can expire or can be maliciously created. This endpoint helps with validating the real ones.
     *
     * @param body  (required)
     * @return API Token is valid (status code 200)
     *         or Invalid API token provided (status code 400)
     *         or API token  Expired (status code 401)
     *         or API token  Not Found (status code 404)
     *         or Internal Server Error (status code 500)
     */
    @ApiOperation(value = "Validates an API Token", nickname = "tokenValidatePost", notes = "API tokens can expire or can be maliciously created. This endpoint helps with validating the real ones.", response = APIData.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "API Token is valid", response = APIData.class),
        @ApiResponse(code = 400, message = "Invalid API token provided", response = ApiErrorResponse.class),
        @ApiResponse(code = 401, message = "API token  Expired", response = ApiErrorResponse.class),
        @ApiResponse(code = 404, message = "API token  Not Found", response = ApiErrorResponse.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ApiErrorResponse.class) })
    @RequestMapping(value = "/token/validate",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<APIData> tokenValidatePost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody InlineObject body) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"appUrl\" : \"https://api.acme-corp.com/1.0\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
