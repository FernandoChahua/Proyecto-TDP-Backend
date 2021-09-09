package com.softtech.tdp.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MessageResource {
    private Integer id;
    private LocalDate registerDate;
    private String content;
}
