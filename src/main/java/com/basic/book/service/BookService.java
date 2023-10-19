package com.basic.book.service;

import com.basic.book.dto.BookReqDTO;
import com.basic.book.dto.BookReqForm;
import com.basic.book.dto.BookResDTO;
import com.basic.book.entity.Book;
import com.basic.book.exeception.BusinessException;
import com.basic.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Transactional
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookResDTO saveBook(BookReqDTO bookReqDTO){
        Book book = modelMapper.map(bookReqDTO, Book.class);
        Book saveBook = bookRepository.save(book);
        return modelMapper.map(saveBook, BookResDTO.class);
    }
    @Transactional(readOnly = true)
    public BookResDTO getBookById(Long id){
        Book bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + "Book Not Found", HttpStatus.NOT_FOUND));
        BookResDTO bookResDTO = modelMapper.map(bookEntity, BookResDTO.class);
        return bookResDTO;
    }

    @Transactional(readOnly = true)
    public BookResDTO getBookByIsbn(String isbn){
        Book bookEntity = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException(isbn + "Book Not Found", HttpStatus.NOT_FOUND));
        BookResDTO bookResDTO = modelMapper.map(bookEntity, BookResDTO.class);
        return bookResDTO;
    }

    @Transactional(readOnly = true)
    public List<BookResDTO> getBooks() {
        //List<User> => List<UserResDTO> 로 바꾸기
        List<Book> bookList = bookRepository.findAll();

        List<BookResDTO> bookResDTOList = bookList.stream()       //Stream<User>
                .map(book -> modelMapper.map(book, BookResDTO.class))   // Stream<UserResDTO>
                .collect(toList());//List<UserResDTO> 를 반환
        return bookResDTOList;
    }

    public void updateBook(Long id, BookReqDTO bookReqDTO){
        Book existBook = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(id + "Book Not Found", HttpStatus.NOT_FOUND));
        existBook.setAuthor(bookReqDTO.getAuthor());
        existBook.setTitle(bookReqDTO.getTitle());
    }
//    public void updateBookForm(BookReqForm bookReqForm){
//        Book existBook = bookRepository.findById(bookReqForm.getId())
//                .orElseThrow(() ->
//                        new BusinessException(bookReqForm.getId() + " User Not Found", HttpStatus.NOT_FOUND));
//        existBook.setAuthor(bookReqForm.getAuthor());
//    }


    public void deleteBook(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
    }



}
