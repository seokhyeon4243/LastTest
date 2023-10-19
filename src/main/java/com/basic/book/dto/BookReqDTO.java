package com.basic.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookReqDTO {

    private Long id;

    @NotEmpty(message = "title은 필수 입력항목입니다.")
    private String title;

    @NotBlank(message = "author은 필수 입력항목입니다.")
    private String author;

    @NotBlank(message = "isbn은 필수 입력항목입니다.")
    private String isbn;



}
