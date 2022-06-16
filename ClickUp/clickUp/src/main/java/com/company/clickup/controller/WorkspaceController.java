package com.company.clickup.controller;

import com.company.clickup.dto.Response;
import com.company.clickup.dto.WorkspaceDto;
import com.company.clickup.entity.User;
import com.company.clickup.security.annotation.CurrentUser;
import com.company.clickup.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController

@RequestMapping("/api/workspace")
public class WorkspaceController {
    private WorkspaceService workspaceService;
@Autowired
    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addWorkspace(@Valid @RequestBody WorkspaceDto workspaceDto, @CurrentUser User user){
        Response response=workspaceService.addWorkspace(workspaceDto,user);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> editWorkspace(@Valid @RequestBody WorkspaceDto workspaceDto, @PathVariable Long id){
        Response response=workspaceService.editWorkspace(workspaceDto,id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteWorkspace(@PathVariable Long id){
        Response response=workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<Response> changeWorkspaceOwner(@RequestParam UUID ownerId, @PathVariable Long id){
        Response response=workspaceService.changeWorkspaceOwner(ownerId,id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }
}
