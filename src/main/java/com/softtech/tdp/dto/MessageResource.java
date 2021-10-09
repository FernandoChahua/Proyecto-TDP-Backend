package com.softtech.tdp.dto;

import lombok.Data;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.softtech.tdp.model.AppUserRole;

@Data
public class MessageResource {
    private Integer id;
    private LocalDateTime registerDate;
    private String content;
    
    @Enumerated(EnumType.STRING)
    private AppUserRole sentBy;
}
