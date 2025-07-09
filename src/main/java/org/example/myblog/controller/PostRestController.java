package org.example.myblog.controller;

import lombok.RequiredArgsConstructor;
import org.example.myblog.dto.PostDto;
import org.example.myblog.security.PrincipalDetails;
import org.example.myblog.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/post")
@RestController
public class PostRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    final PostService postService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<PostDto.CreateResDto> create(@RequestBody PostDto.CreateReqDto createReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long reqUserId = principalDetails.getUser().getId();

        PostDto.CreateSevDto createSevDto = PostDto.CreateSevDto.builder().reqUserId(reqUserId).build();
        createSevDto = (PostDto.CreateSevDto) createSevDto.afterBuild(createReqDto);

        return ResponseEntity.ok(postService.create(createSevDto));
    }

    @GetMapping("/detail")
    public ResponseEntity<PostDto.DetailResDto> detail(PostDto.DetailReqDto detailReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = principalDetails.getUser().getId();

        System.out.println("PostRestController.detail: " + reqUserId);

        PostDto.DetailSevDto detailSevDto = PostDto.DetailSevDto.builder().reqUserId(reqUserId).build();
        detailSevDto = (PostDto.DetailSevDto) detailSevDto.afterBuild(detailReqDto);

        return ResponseEntity.ok(postService.detail(detailSevDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostDto.ListResDto>> list(PostDto.ListReqDto listReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(postService.list(listReqDto));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostDto.UpdateReqDto updateReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = principalDetails.getUser().getId();

        PostDto.UpdateSevDto updateSevDto = PostDto.UpdateSevDto.builder().reqUserId(reqUserId).build();
        updateSevDto = (PostDto.UpdateSevDto) updateSevDto.afterBuild(updateReqDto);
        postService.update(updateSevDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostDto.DeleteReqDto deleteReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long reqUserId = principalDetails.getUser().getId();

        PostDto.DeleteSevDto deleteSevDto = PostDto.DeleteSevDto.builder().reqUserId(reqUserId).build();
        deleteSevDto = (PostDto.DeleteSevDto) deleteSevDto.afterBuild(deleteReqDto);
        postService.delete(deleteSevDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
