package com.basic.book.dto;

import lombok.*;

@NoArgsConstructor //기본생성자
@AllArgsConstructor //오버로딩된 생성자
@Getter //getter
@Setter //setter
@ToString //toString
@Builder
public class Book {
    private String title;
    private String author;
    private String isbn;
}