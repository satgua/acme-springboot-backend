package com.acme.mgmt.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * APIData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-28T22:20:10.938+10:00[Australia/Sydney]")
@Component
public class APIData   {
  @JsonProperty("appUrl")
  private String appUrl;

  public APIData appUrl(String appUrl) {
    this.appUrl = appUrl;
    return this;
  }

  /**
   * Environment url for this token
   * @return appUrl
  */
  @ApiModelProperty(example = "https://api.acme-corp.com/1.0", required = true, value = "Environment url for this token")
  @NotNull


  public String getAppUrl() {
    return appUrl;
  }

  public void setAppUrl(String appUrl) {
    this.appUrl = appUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    APIData apIData = (APIData) o;
    return Objects.equals(this.appUrl, apIData.appUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class APIData {\n");
    
    sb.append("    appUrl: ").append(toIndentedString(appUrl)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

