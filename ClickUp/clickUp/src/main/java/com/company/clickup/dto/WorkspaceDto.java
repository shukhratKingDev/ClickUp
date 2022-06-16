package com.company.clickup.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class WorkspaceDto {
    @NotNull
    @Min(value = 3)
    @Max(30)
    private String name;
    @NotNull
   private String color;
   private UUID avatarId;
}
