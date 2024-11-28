package com.example.Booking_Care_Web.Models.ChatGPT;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChatgptRequest {
    private String model;
    private List<Message> message = new ArrayList<>();
    public ChatgptRequest(String model, String query) {
        this.model = model;
        this.message.add(new Message("user", query));
    }
}
