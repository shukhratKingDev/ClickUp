package com.company.clickup.controller;

import com.company.clickup.dto.MemberDto;
import com.company.clickup.dto.Response;
import com.company.clickup.dto.WorkspaceDto;
import com.company.clickup.entity.*;
import com.company.clickup.entity.enums.ActionType;
import com.company.clickup.entity.enums.WorkspaceRoleName;
import com.company.clickup.security.annotation.CurrentUser;
import com.company.clickup.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    @PostMapping("/{id}")
    public ResponseEntity<Response> addOrEditOrRemoveWorkspaceOwner(@RequestBody MemberDto memberDto , @PathVariable Long id){
        Response response=workspaceService.addOrEditOrRemoveWorkspaceOwner(memberDto,id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @PutMapping("/join")
    public ResponseEntity<Response> joinViaEmail(@RequestParam Long id , @CurrentUser User user){
        Response response=workspaceService.joinViaEmail(id,user);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/allWorkspace")
    public ResponseEntity<List<Workspace>> getAllWorkspaces(@CurrentUser User user){
    List<Workspace>workspaces=workspaceService.getAllWorkspaces(user);
            return ResponseEntity.ok(workspaces);
    }

    @GetMapping("/allMembers")
    public ResponseEntity<List<WorkspaceUser>> getAllMembers(@CurrentUser User user){
        List<WorkspaceUser>workspaceUser=workspaceService.getAllMembers(user);
        return ResponseEntity.ok(workspaceUser);
    }

    @GetMapping("/allGuests")
    public ResponseEntity<List<WorkspaceUser>> getAllGuests(@CurrentUser User user){
        List<WorkspaceUser>workspaceUser=workspaceService.getAllGuests(user);
        return ResponseEntity.ok(workspaceUser);
    }

    @PutMapping("/addPermission")
    public ResponseEntity<Response> addOrDeletePermission(@CurrentUser User user, @RequestBody Long workspaceId,@RequestBody String roleName, @RequestBody WorkspaceRoleName workspaceRoleName, @RequestBody UUID workspacePermissionId, @RequestBody ActionType actionType){

    Response response=workspaceService.addOrDeleteWorkspacePermission(user,workspaceId,roleName,workspaceRoleName,workspacePermissionId,actionType);
    return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }



}
