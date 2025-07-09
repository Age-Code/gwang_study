package org.example.myblog.service;

import org.example.myblog.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostDto.CreateResDto create(PostDto.CreateSevDto createSevDto);
    PostDto.DetailResDto detail(PostDto.DetailSevDto detailSevDto);
    List<PostDto.ListResDto> list(PostDto.ListReqDto listReqDto);
    void update(PostDto.UpdateSevDto updateSevDto);
    void delete(PostDto.DeleteSevDto deleteSevDto);
}
