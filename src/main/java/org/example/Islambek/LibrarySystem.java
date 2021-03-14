package org.example.Islambek;

import org.example.Islambek.Dao.LoginDao;
import org.example.Islambek.configuration.SConfig;
import org.example.Islambek.controller.BooksController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LibrarySystem {

    private static Boolean bool = true;
    private static Boolean userBool = true;
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SConfig.class
        );

        LoginDao loginDao = context.getBean("loginDao", LoginDao.class);
        BooksController booksController = context.getBean("booksController", BooksController.class);

        System.out.println("1.  Login ADMIN");
        System.out.println("2.  Login User");
        System.out.println("3.  User Register ");

        String choice = read.readLine();

        if (choice.equals("1")) {

            if(booksController.loginAsAdmin()){

                while (bool) {
                    System.out.println("1. To Return all Books");
                    System.out.println("2. To update Books");
                    System.out.println("3. to Add new Books");
                    System.out.println("4. To delete Books");
                    System.out.println("0. To Quit");
                    String AdminChoice = read.readLine();

                    switch (AdminChoice) {
                        case "1":
                           booksController.showAllBooks();
                           break;
                        case "2":
                            booksController.updateBook();
                            break;
                        case "3":
                            booksController.addBook();
                            break;
                        case "4":
                            booksController.deleteBook();
                            break;
                        default:
                            bool = false;
                    }
                }

            } else
                System.out.println("WRONG Input data  ");


        }

        else if (choice.equals("2")) {


            if (booksController.loginAsUser()) {


                System.out.println("You logged in as an User");

                while (userBool) {
                    System.out.println("1. To Return all Books");
                    System.out.println("2. To Issue books");
                    System.out.println("3. To Return Issued books");
                    System.out.println("0. To Quit");
                    String userChoice = read.readLine();

                    switch (userChoice) {
                        case "1":
                            booksController.showAllBooks();
                            break;
                        case "2":
                            booksController.issueBook();
                            break;
                        case "3":
                            booksController.showAllIssuedBookByMe();
                            break;
                        default:
                            userBool = false;

                    }


                }

            }
            else
                System.out.println("WRONG Input data");

        }


        else if (choice.equals("3")) {
          booksController.registerUser();
        } else
            System.out.println("exit");
        return;

        }
    }

























