package com.company.clickup.dto;

import com.company.clickup.entity.Icon;
import com.company.clickup.entity.User;
import com.company.clickup.entity.enums.AccessType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SpaceDto {
    @NotNull
    @Min(value = 3)
    private String name;
    private String color;
    @NotNull
    private Long workspaceId;

    private Icon icon;

    private Long avatarId;

    private AccessType accessType;
}
