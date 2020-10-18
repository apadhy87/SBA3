package com.wf.apps.interviewApp.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class interviewAttendees {
	@NotBlank(message = "Interview Name should'nt be blank.")
	@Length(min = 3,max = 30,message = "Interview Name should be min 3 and max 30 characters.")
	private String interviewName;
	private List<Integer> userIds;
}
