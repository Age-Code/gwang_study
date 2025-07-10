package org.example.myblog.service;

import org.example.myblog.dto.PermissionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {
    PermissionDto.CreateResDto create(PermissionDto.CreateSevDto createSevDto);
    PermissionDto.DetailResDto detail(PermissionDto.DetailSevDto detailSevDto);
    List<PermissionDto.ListResDto> list(PermissionDto.ListReqDto listReqDto);
    void update(PermissionDto.UpdateSevDto updateSevDto);
    void delete(PermissionDto.DeleteSevDto deleteSevDto);
}
