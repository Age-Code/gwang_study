package org.example.myblog.mapper;

import org.example.myblog.dto.PostDto;

import java.util.List;

public interface PostMapper {
    PostDto.DetailResDto detail(PostDto.DetailReqDto detailReqDto);
    List<PostDto.ListResDto> list(PostDto.ListReqDto listReqDto);
}
