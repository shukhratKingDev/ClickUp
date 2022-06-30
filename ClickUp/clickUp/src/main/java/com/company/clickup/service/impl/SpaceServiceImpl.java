package com.company.clickup.service.impl;

import com.company.clickup.dto.Response;
import com.company.clickup.dto.SpaceDto;
import com.company.clickup.entity.Space;
import com.company.clickup.entity.User;
import com.company.clickup.entity.Workspace;
import com.company.clickup.entity.exceptions.SpaceNotFoundException;
import com.company.clickup.repository.SpaceRepository;
import com.company.clickup.repository.WorkspaceRepository;
import com.company.clickup.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpaceServiceImpl implements SpaceService {
    private WorkspaceRepository workspaceRepository;
    private SpaceRepository spaceRepository;
@Autowired
    public SpaceServiceImpl(WorkspaceRepository workspaceRepository, SpaceRepository spaceRepository) {
        this.workspaceRepository = workspaceRepository;
    this.spaceRepository = spaceRepository;
}

    @Override
    public Response addSpace(User user, SpaceDto spaceDto) {
        Optional<Workspace> optionalWorkspace = workspaceRepository.findByIdAndOwner_Id(spaceDto.getWorkspaceId(), user.getId());
        if (!optionalWorkspace.isPresent()) {
           return new Response("The workspace with this id and ownerId not found",false) ;
        }
        if (spaceRepository.existsByNameAndWorkspaceId(spaceDto.getName(),spaceDto.getWorkspaceId())) {
            return new Response("The space with this name already exists in this workspace",false);
        }
        Space space=new Space();
        space.setWorkspaceId(space.getWorkspaceId());
        space.setOwnerId(user);
        space.setColor(spaceDto.getColor());
        space.setAccessType(spaceDto.getAccessType());
        space.setIconId(spaceDto.getIcon());
        space.setName(spaceDto.getName());
        space.setAvatarId(spaceDto.getAvatarId());
        spaceRepository.save(space);
        return new Response("Space successfully saved",true);
    }

    @Override
    public Response editSpace(User user, UUID id,SpaceDto spaceDto) {
        Optional<Space> optionalSpace = spaceRepository.findByUser_IdAndId(user.getId(), id);
        if (!optionalSpace.isPresent()) {
            return new Response("Workspace with this id and user id not found",false);
        }
        Space space=optionalSpace.get();
        space.setAvatarId(spaceDto.getAvatarId());
        space.setColor(spaceDto.getColor());
        space.setName(spaceDto.getName());
        space.setAccessType(spaceDto.getAccessType());
        spaceRepository.save(space);
        return new Response("The space successfully updated",true);
    }

    @Override
    public Response deleteSpace(UUID id, User user) {
        if (!spaceRepository.findByUser_IdAndId(user.getId(),id).isPresent()) {
            return new Response("The space with this id not found",false);
        }
        spaceRepository.deleteById(id);
        return new Response("The space successfully deleted",true);
    }

    @Override
    public List<Space> getAllSpaces(User user) throws SpaceNotFoundException {
        return spaceRepository.findAllByUserId(user.getId()).orElseThrow(()->new SpaceNotFoundException("This user does not have any space"));
    }

    @Override
    public Space getById(User user, UUID id)  {
        Optional<Space> optionalSpace = spaceRepository.findByUser_IdAndId(user.getId(), id);
        if (!optionalSpace.isPresent()) {
             new SpaceNotFoundException("The space with this id not found");
        }
        return optionalSpace.get();
    }
}
