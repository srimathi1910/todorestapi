package com.sritodo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

public class Tododto 
{
   @NotBlank
   private String task;

public String getTask() {
	return task;
}

public void setTask(String task) {
	this.task = task;
}
   
}
