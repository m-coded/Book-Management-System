package com.Book.Book.Controller;


import com.Book.Book.Entity.BookEntity;
import com.Book.Book.Entity.MyBookList;
import com.Book.Book.Service.BookService;
import com.Book.Book.Service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class BookController {

    @Autowired
    private BookService service;


    @Autowired
    private MyBookListService mybooklistservice;
    @RequestMapping("/")
public   String home(){

        return "home";
    }

    @GetMapping("/book_register")
    public   String bookRegister(){

        return "bookRegister";
    }

    @GetMapping("/Available_Book")
    public ModelAndView getAllBook(){
    List<BookEntity> list=service.getALLBook();
    		ModelAndView m=new ModelAndView();
		m.setViewName("AvailableBook");m.addObject("book",list);
         return new ModelAndView("AvailableBook","book",list);
    }

     @PostMapping("/save")
    public  String addBook(@ModelAttribute BookEntity b){
        service.save(b);
        return "redirect:/Available_Book";
     }

     @GetMapping("/My_Book")
    public  String getMyBook( Model model ){
        List<MyBookList>List=mybooklistservice.getAllMyBooks();
        model.addAttribute("book" ,List);
        return "mybook";
     }
    @RequestMapping("/list/{id}")
 public  String getMyList(@PathVariable("id") int id){
        BookEntity b= service.getBookById(id);
     MyBookList mb= new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        mybooklistservice.saveMyBooks(mb);
        return"redirect:/My_Book";
 }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable ("id") int id ,Model model) {
    BookEntity b = service.getBookById(id);
    model.addAttribute("book" ,b );
        return "BookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id")int id) {
        service.deleteById(id);
        return "redirect:/Available_Book";
    }

}
