package com.company.clickup.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AttachmentDto {
    @NotNull
    private String name;
    @NotNull
    private String originalName;
    @NotNull
    private Long size;
    @NotNull
    private String contentType;
}
