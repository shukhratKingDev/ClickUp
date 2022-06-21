package com.company.clickup.dto;

import com.company.clickup.entity.enums.ActionType;
import lombok.Data;

import java.util.UUID;

@Data
public class MemberDto {
    private UUID memberId;
    private UUID roleId;
    private ActionType actionType;
}

