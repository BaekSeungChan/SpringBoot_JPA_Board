package com.example.springboot_jpa_board.uploadTest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    // 제품의 고유 번호
    private Long pno;

    // 제품 이름
    private String pname;

    // 제품 가격
    private int price;

    // 제품 설명
    private String pdesc;

    // 제품 삭제 플래그
    private boolean delFlag;

    // 제품에 첨부된 파일 리스트
    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

    // 업로드된 파일 이름 리스트
    @Builder.Default
    private List<String> uploadedFileNames = new ArrayList<>();
}

