package org.example.myblog.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.myblog.domain.Permission;
import org.example.myblog.dto.PermissionDto;
import org.example.myblog.mapper.PermissionMapper;
import org.example.myblog.repository.PermissionRepository;
import org.example.myblog.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissionServiceimpl implements PermissionService {

    final PermissionRepository permissionRepository;
    final PermissionMapper permissionMapper;

    // Create
    @Override
    public PermissionDto.CreateResDto create(PermissionDto.CreateSevDto createSevDto) {
        PermissionDto.CreateResDto res = permissionRepository.save(createSevDto.toEntity()).toCreateResDto();

        return res;
    }

    // Detail
    @Override
    public PermissionDto.DetailResDto detail(PermissionDto.DetailSevDto detailSevDto){
        PermissionDto.DetailResDto res = permissionMapper.detail(detailSevDto);

        return res;
    }

    // List
    @Override
    public List<PermissionDto.ListResDto> list(PermissionDto.ListReqDto listReqDto) {
        listReqDto.setDeleted(false);
        List<PermissionDto.ListResDto> res = permissionMapper.list(listReqDto);

        return res;
    }

    // Update
    @Override
    public void update(PermissionDto.UpdateSevDto updateSevDto){
        Permission permission = permissionRepository.findById(updateSevDto.getId()).orElse(null);
        if(permission == null){
            throw new RuntimeException("no data");
        }

        if(updateSevDto.getTitle() != null){
            permission.setTitle(updateSevDto.getTitle());
        }
        if(updateSevDto.getContent() != null){
            permission.setContent(updateSevDto.getContent());
        }

        permissionRepository.save(permission);
    }

    // Delete
    @Override
    public void delete(PermissionDto.DeleteSevDto deleteSevDto){
        Permission permission = permissionRepository.findById(deleteSevDto.getId()).orElse(null);
        if(permission == null){
            throw new RuntimeException("no data");
        }

        permission.setDeleted(true);

        permissionRepository.save(permission);
    }
}
