package com.softtech.tdp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackExtraInfoDTO {
	
	private Integer idFeedback;
	private LocalDateTime registrationDate;
	private String title;
	private String description;
	private boolean state;
	
	private String createdBy;
}
