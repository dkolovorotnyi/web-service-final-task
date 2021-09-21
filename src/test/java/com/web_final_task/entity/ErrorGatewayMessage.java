package com.web_final_task.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class ErrorGatewayMessage {

	private String errorMessage;
	private String errorCode;
}