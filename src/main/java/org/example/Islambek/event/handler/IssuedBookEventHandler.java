package org.example.Islambek.event.handler;

import org.example.Islambek.event.IssuedBookEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class IssuedBookEventHandler implements ApplicationListener<IssuedBookEvent> {

    @Override
    public void onApplicationEvent(IssuedBookEvent issuedBookEvent) {
        System.out.println("Book with id: " + issuedBookEvent.getBookId() + " has been issued");
    }
}
