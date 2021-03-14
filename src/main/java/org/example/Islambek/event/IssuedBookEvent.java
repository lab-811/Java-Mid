package org.example.Islambek.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;


public class IssuedBookEvent extends ApplicationEvent {
    private Long bookId;

    public IssuedBookEvent(Object source, Long bookId) {
        super(source);
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }
}
