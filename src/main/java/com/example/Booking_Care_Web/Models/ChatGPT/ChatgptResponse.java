package com.example.Booking_Care_Web.Models.ChatGPT;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatgptResponse {
    public List<Choice> choices;
}
