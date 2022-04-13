package com.sparta.webminiproject27jo.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sparta.webminiproject27jo.Dto.PostDto;
import com.sparta.webminiproject27jo.Dto.PostEditRequestDto;
import com.sparta.webminiproject27jo.Dto.PostRequestDto;
import com.sparta.webminiproject27jo.Model.Comment;
import com.sparta.webminiproject27jo.Model.Post;
import com.sparta.webminiproject27jo.Repository.CommentRepository;
import com.sparta.webminiproject27jo.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
//    private final ImageUrlRepository imageUrlRepository;

    private final AmazonS3Client amazonS3Client;


    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름


    // Image 업로드
    public void upload(PostRequestDto postRequestDto, String dirName) throws IOException {
        System.out.println(postRequestDto.getFile());

        File uploadFile = convert(postRequestDto.getFile())  // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException("잘못된 파일 형식입니다."));

        PostDto postDto = upload(uploadFile, dirName);

        postDto.setContent(postRequestDto.getContent());
        postDto.setNickName(postRequestDto.getNickName());
        postDto.setUserId(postRequestDto.getUserId());
        registImage(postDto);
    }


    // S3로 파일 업로드하기
    private PostDto upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        removeNewFile(uploadFile);
        return new PostDto(fileName, uploadImageUrl);
    }


    // Image Entity 저장
    public void registImage(PostDto postDto){
//        Image image = new Image(imageDto);
        Post post = new Post(postDto);
        System.out.println(post.getImageUrl());
        postRepository.save(post);
    }


//    //S3 삭제
//    public void deleteFile(Long imageId){
//        String fileName = imageRepository.findById(imageId).orElseThrow(IllegalArgumentException::new).getFileName();
//        DeleteObjectRequest request = new DeleteObjectRequest(bucket, fileName);
//        amazonS3Client.deleteObject(request);
//    }


    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }


    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
    }


    // 로컬에 파일 업로드 하기
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }

        return Optional.empty();
    }
    //글 작성
//    @Transactional
//    public Post createPost(
//            PostRequestDto requestDto) {
//
//
//
////        String content = requestDto.getContent();
////        List<Comment> comments = commentRepository.findAllByPostId(postId);
////        if (requestDto.getContent() == null) {
////            throw new IllegalArgumentException("내용을 입력해주세요.");
////        }
////        if (content.length() > 1000) {
////            throw new IllegalArgumentException("1000자 이하로 입력해주세요.");
////        }
//
//        Post post = new Post(requestDto);
//
//        return postRepository.save(post);
//    }


    //수정
    @Transactional
    public Post updatePost(
            Long id,
            PostEditRequestDto requestDto
    ) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일기가 존재하지 않습니다.")
        );
        post.updatePost(requestDto);
        return post;
    }

    //삭제
    @Transactional
    public void deletePost(Long postId
//            , UserDetailsImpl userDetails
    ) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("일기가 존재하지 않습니다.")
        );
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        for(Comment comment : comments){
            Long temp = comment.getId();
            commentRepository.deleteById(temp);
        }
        postRepository.deleteById(postId);
    }
}