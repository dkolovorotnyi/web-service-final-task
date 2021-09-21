package com.web_final_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

	@JsonProperty("id")
	private Long id;

	@JsonProperty(value = "userId")
	private Long userId;

	@JsonProperty(value = "state", required = true)
	private String state;

	@JsonProperty(value = "zip", required = true)
	private String zip;

	@JsonProperty(value = "city", required = true)
	private String city;

	@JsonProperty(value = "addressLine1", required = true)
	private String addressLine1;

	@JsonProperty("addressLine2")
	private String addressLine2;
}