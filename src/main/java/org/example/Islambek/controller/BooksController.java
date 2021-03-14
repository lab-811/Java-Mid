package org.example.Islambek.controller;

import org.example.Islambek.Dao.BookDao;
import org.example.Islambek.Dao.LoginDao;
import org.example.Islambek.event.IssuedBookEvent;
import org.example.Islambek.model.Book;
import org.example.Islambek.model.Issued;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class BooksController implements ApplicationEventPublisherAware {
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    private final BookDao bookDao;
    private final LoginDao loginDao;
    private Long userId;
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public BooksController(BookDao bookDao, LoginDao loginDao) {
        this.bookDao = bookDao;
        this.loginDao = loginDao;
    }

    public void showAllBooks(){
        for(Book book : bookDao.showAll()){
            System.out.println( "id: " + book.getId() + "\n" + "title: " + book.getTitle() + "\n" + "author: " + book.getAuthor());
            System.out.println("---------------------------------------------------------------");
        }
    }

    public void showOneBook() throws IOException {
        System.out.println("PLS, Enter id of the book");
        Long id = Long.valueOf(read.readLine());
        System.out.println("id: " + bookDao.showOne(id).getId() + "\n" + bookDao.showOne(id).getTitle() + "\n" + bookDao.showOne(id).getAuthor());
    }



    public void addBook() throws IOException {
        System.out.println("Enter Title of the Book");
        String newBookTitle = read.readLine();
        System.out.println("ENter Author of the Book");
        String newBookAuthor = read.readLine();

        Book newBook = new Book(newBookTitle, newBookAuthor);

        bookDao.addBook(newBook);

    }


    public void updateBook() throws IOException {
        System.out.println("PLS, Enter id of the book");
        Long bookId = Long.valueOf(read.readLine());
        System.out.println("Current data");
        System.out.println("id: " + bookDao.showOne(bookId).getId() +  "title: " + "\n" + bookDao.showOne(bookId).getTitle() + "\n" + "author:" + bookDao.showOne(bookId).getAuthor());
        System.out.println("-----------------------------------------------------------");
        System.out.println("Enter new: title");
        String bookTitle = read.readLine();

        System.out.println("Enter new: Author");
        String bookAuthor = read.readLine();


        Book book = new Book(bookTitle, bookAuthor);

        bookDao.updateBook(bookId,book);
        System.out.println("Updated");
    }


    public void deleteBook() throws IOException {
        System.out.println("PLS, Enter book id");
        Long dBookId = Long.valueOf(read.readLine());

        bookDao.deleteBook(dBookId);
        System.out.println("DELETED");
    }





    public boolean loginAsAdmin() throws IOException {
        System.out.println("PLS, Enter your name:");
        String adminName = read.readLine();
        System.out.println("PLS, Enter your password:");
        String adminPassword = read.readLine();

        if(loginDao.checkForAdmin(adminName, adminPassword) != null){
            return true;
        }else {
            return false;
        }


    }

    public boolean loginAsUser() throws IOException {
        System.out.println("PLS, Enter your name:");
        String userName = read.readLine();
        System.out.println("PLS, Enter your password:");
        String userPassword = read.readLine();


        if(loginDao.checkForUser(userName, userPassword) != null){
            userId = loginDao.checkForUser(userName, userPassword).getId();

            return true;
        }else {
            return false;
        }
    }

    public void issueBook() throws IOException {
        System.out.println("Enter id of the book");
        Long bookId = Long.valueOf(read.readLine());

        bookDao.issueBook(userId, bookId);
        System.out.println("You Successfully issued");

        this.eventPublisher.publishEvent(new IssuedBookEvent(this, bookId));

    }

    public void showAllIssuedBookByMe(){
        System.out.println("YOUR Issued BOOKS");
        for(Issued issued : bookDao.selectAll(userId)){
            System.out.println( "id: " + issued.getBook_id());

        }

    }

    public void registerUser() throws IOException {
        System.out.println("PLS, Enter NAME:");
        String userName = read.readLine();
        System.out.println("PLS, Enter PASSWORD:");
        String userPassword = read.readLine();

        loginDao.registerUser(userName, userPassword);

        System.out.println("You Successfully registered");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}


















