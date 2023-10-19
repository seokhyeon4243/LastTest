package com.basic.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookReqForm {

    private Long id;

    @NotEmpty(message = "title은 필수 입력항목입니다.")
    private String title;

    @NotBlank(message = "author은 필수 입력항목입니다.")
    private String author;

    @NotBlank(message = "isbn은 필수 입력항목입니다.")
    private String isbn;

    @NotBlank(message = "genre은 필수 입력항목입니다.")
    private String genre;
}
