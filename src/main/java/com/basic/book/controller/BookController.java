package com.basic.book.controller;

import com.basic.book.dto.BookReqDTO;
import com.basic.book.dto.BookReqForm;
import com.basic.book.dto.BookResDTO;
import com.basic.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/first")
    public String leaf(Model model){
        model.addAttribute("name", "스프링부트");
        return "leaf";
    }
    @GetMapping("/index")
    public ModelAndView index(){
        List<BookResDTO> bookResDTOList = bookService.getBooks();
        return new ModelAndView("index", "books", bookResDTOList);
    }
    @GetMapping("/signup")
    public String showSignUpForm(BookReqDTO book){
        return "add-book";
    }
    @PostMapping("/addbook")
    public String addBook(@Valid BookReqDTO book, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-book";
        }
        bookService.saveBook(book);
        model.addAttribute("books", bookService.getBooks());
        return "index";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model){
        BookResDTO bookResDTO = bookService.getBookById(id);
        model.addAttribute("book", bookResDTO);
        return "update-book";
    }
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid BookReqDTO book
    , BindingResult result, Model model){
        if(result.hasErrors()){
            return "update-book";
        }
        bookService.updateBook(id, book);
        List<BookResDTO> bookResDTOList = bookService.getBooks();
        model.addAttribute("books", bookResDTOList);
        return "index";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model){
        bookService.deleteBook(id);
        return "redirect:/books/index";
    }
    @GetMapping("/detail/{id}")
    public String detailBook(@PathVariable("id") long id, Model model){
        model.addAttribute("books", bookService.getBookById(id));
        return "detail-book";

    }
}
