package com.minichat.attractor.dto;

import com.minichat.attractor.model.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private String id;
    private String text;
    private String userName;
    private LocalDateTime date;
    public static MessageDto fromAll(Message message){
        return MessageDto.builder()
                .id(message.getId().toString())
                .date(message.getDate())
                .text(message.getText())
                .userName(message.getUser().getUsername())
                .build();

    }
}
