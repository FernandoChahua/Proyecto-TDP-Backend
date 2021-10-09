package com.softtech.tdp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime registerDate;

    @NotBlank
    @NotNull
    @Size(max = 350)
    private String content;

    @ManyToOne
    private Assignment assignment;

    @Enumerated(EnumType.STRING)
    private AppUserRole sentBy;
}
