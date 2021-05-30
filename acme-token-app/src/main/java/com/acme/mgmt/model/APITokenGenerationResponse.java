package com.acme.mgmt.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * APITokenGenerationResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-28T22:20:10.938+10:00[Australia/Sydney]")

@Entity
@Table(name="tbl_token")
public class APITokenGenerationResponse   {
  @JsonProperty("apiToken")
  @Column(name="api_token")
  private String apiToken;

  @JsonProperty("validTo")
  @Column(name="valid_to")
  private String validTo;

  @Id
  @GeneratedValue
  private int id;
  
  public APITokenGenerationResponse() {
  }
  
  
  public APITokenGenerationResponse(String apiToken, String validTo, int id) {
	super();
	this.apiToken = apiToken;
	this.validTo = validTo;
	this.id = id;
  }

public APITokenGenerationResponse(String apiToken, String validTo) {
	super();
	this.apiToken = apiToken;
	this.validTo = validTo;
}


public APITokenGenerationResponse apiToken(String apiToken) {
    this.apiToken = apiToken;
    return this;
  }

  /**
   * The generated API token
   * @return apiToken
  */
  @ApiModelProperty(example = "4d4f434841", required = true, value = "The generated API token")
  @NotNull
  public String getApiToken() {
    return apiToken;
  }

  public void setApiToken(String apiToken) {
    this.apiToken = apiToken;
  }

  public APITokenGenerationResponse validTo(String validTo) {
    this.validTo = validTo;
    return this;
  }

  /**
   * Timestamp till this API token is valid
   * @return validTo
  */
  @ApiModelProperty(example = "12/12/2018 23:15:10", required = true, value = "Timestamp till this API token is valid")
  @NotNull
  public String getValidTo() {
    return validTo;
  }

  public void setValidTo(String validTo) {
    this.validTo = validTo;
  }


  
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    APITokenGenerationResponse apITokenGenerationResponse = (APITokenGenerationResponse) o;
    return Objects.equals(this.apiToken, apITokenGenerationResponse.apiToken) &&
        Objects.equals(this.validTo, apITokenGenerationResponse.validTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiToken, validTo);
  }
//
//  @Override
//  public String toString() {
//    StringBuilder sb = new StringBuilder();
//    sb.append("class APITokenGenerationResponse {\n");
//    
//    sb.append("    apiToken: ").append(toIndentedString(apiToken)).append("\n");
//    sb.append("    validTo: ").append(toIndentedString(validTo)).append("\n");
//    sb.append("}");
//    return sb.toString();
//  }
//
//  /**
//   * Convert the given object to string with each line indented by 4 spaces
//   * (except the first line).
//   */
//  private String toIndentedString(java.lang.Object o) {
//    if (o == null) {
//      return "null";
//    }
//    return o.toString().replace("\n", "\n    ");
//  }
}

