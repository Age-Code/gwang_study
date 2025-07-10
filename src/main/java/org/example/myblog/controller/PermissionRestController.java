package org.example.myblog.controller;

import lombok.RequiredArgsConstructor;
import org.example.myblog.dto.PermissionDto;
import org.example.myblog.security.PrincipalDetails;
import org.example.myblog.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/Permission")
@RestController
public class PermissionRestController {

    final PermissionService permissionService;

    public Long getReqUserId(PrincipalDetails principalDetails) {
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            return null;
        }

        return principalDetails.getUser().getId();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<PermissionDto.CreateResDto> create(@RequestBody PermissionDto.CreateReqDto createReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long reqUserId = getReqUserId(principalDetails);

        PermissionDto.CreateSevDto createSevDto = PermissionDto.CreateSevDto.builder().reqUserId(reqUserId).build();
        createSevDto = (PermissionDto.CreateSevDto) createSevDto.afterBuild(createReqDto);

        return ResponseEntity.ok(permissionService.create(createSevDto));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/detail")
    public ResponseEntity<PermissionDto.DetailResDto> detail(PermissionDto.DetailReqDto detailReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);

        System.out.println("PermissionRestController.detail: " + reqUserId);

        PermissionDto.DetailSevDto detailSevDto = PermissionDto.DetailSevDto.builder().reqUserId(reqUserId).build();
        detailSevDto = (PermissionDto.DetailSevDto) detailSevDto.afterBuild(detailReqDto);

        return ResponseEntity.ok(permissionService.detail(detailSevDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<PermissionDto.ListResDto>> list(PermissionDto.ListReqDto listReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(permissionService.list(listReqDto));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PermissionDto.UpdateReqDto updateReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);

        PermissionDto.UpdateSevDto updateSevDto = PermissionDto.UpdateSevDto.builder().reqUserId(reqUserId).build();
        updateSevDto = (PermissionDto.UpdateSevDto) updateSevDto.afterBuild(updateReqDto);
        permissionService.update(updateSevDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PermissionDto.DeleteReqDto deleteReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = getReqUserId(principalDetails);

        PermissionDto.DeleteSevDto deleteSevDto = PermissionDto.DeleteSevDto.builder().reqUserId(reqUserId).build();
        deleteSevDto = (PermissionDto.DeleteSevDto) deleteSevDto.afterBuild(deleteReqDto);
        permissionService.delete(deleteSevDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
