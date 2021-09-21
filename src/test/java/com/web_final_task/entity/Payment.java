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
public class Payment {

	@JsonProperty("id")
	private long id;

	@JsonProperty(value = "cardNumber", required = true)
	private String cardNumber;

	@JsonProperty(value = "expiryYear", required = true)
	private int expiryYear;

	@JsonProperty(value = "expiryMonth", required = true)
	private int expiryMonth;

	@JsonProperty("cardHolder")
	private String cardHolder;

	@JsonProperty("cvv")
	private String cvv;

	@JsonProperty("verified")
	private boolean verified;

	@JsonProperty("userId")
	private Long userId;

}