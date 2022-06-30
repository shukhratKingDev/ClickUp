package com.company.clickup.controller;

import com.company.clickup.dto.Response;
import com.company.clickup.dto.SpaceDto;
import com.company.clickup.entity.Space;
import com.company.clickup.entity.User;
import com.company.clickup.entity.exceptions.SpaceNotFoundException;
import com.company.clickup.security.annotation.CurrentUser;
import com.company.clickup.service.SpaceService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/space")
public class SpaceController {
    private SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addSpace(@CurrentUser User user, @RequestBody SpaceDto spaceDto){
        Response response=spaceService.addSpace(user,spaceDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> editSpace(@CurrentUser User user,@PathVariable UUID id,@RequestBody SpaceDto spaceDto){
       Response response= spaceService.editSpace(user,id,spaceDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteSpace(@PathVariable UUID id,@CurrentUser User user){
        Response response=spaceService.deleteSpace(id,user);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Space>> getAllSpaces(@CurrentUser User user) throws SpaceNotFoundException {
        return ResponseEntity.ok(spaceService.getAllSpaces(user));
    }
    @GetMapping("{id}")
    public ResponseEntity<Space> getById(@CurrentUser User user,@PathVariable UUID id){
        return ResponseEntity.ok(spaceService.getById(user,id));
    }

}
