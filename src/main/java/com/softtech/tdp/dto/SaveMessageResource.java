package com.softtech.tdp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class SaveMessageResource {
    private LocalDate registerDate;

    @NotBlank
    @NotNull
    @Size(max = 350)
    private String content;
}
