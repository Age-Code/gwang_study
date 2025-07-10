package org.example.myblog.mapper;

import org.example.myblog.dto.PermissionDto;

import java.util.List;

public interface PermissionMapper {
    PermissionDto.DetailResDto detail(PermissionDto.DetailReqDto detailReqDto);
    List<PermissionDto.ListResDto> list(PermissionDto.ListReqDto listReqDto);
}
