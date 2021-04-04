package com.minichat.attractor.dto;

import com.minichat.attractor.model.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    private String id;
    private String name;
    public static ChatDto fromAll(Chat chat){
        return ChatDto.builder().id(chat.getId().toString())
                .name(chat.getName())
                .build();
    }
}
