package com.company.clickup.service;

import com.company.clickup.dto.Response;
import com.company.clickup.dto.SpaceDto;
import com.company.clickup.entity.Space;
import com.company.clickup.entity.User;
import com.company.clickup.entity.exceptions.SpaceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface SpaceService {

    Response addSpace(User user, SpaceDto spaceDto);

    Response editSpace(User user, UUID id,SpaceDto spaceDto);

    Response deleteSpace(UUID id, User user);

    List<Space> getAllSpaces(User user) throws SpaceNotFoundException;

    Space getById(User user, UUID id);
}
