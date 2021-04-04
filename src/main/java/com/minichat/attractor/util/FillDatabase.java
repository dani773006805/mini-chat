package com.minichat.attractor.util;

import com.minichat.attractor.model.Chat;
import com.minichat.attractor.model.Message;
import com.minichat.attractor.services.ChatService;
import com.minichat.attractor.services.MessageService;
import com.minichat.attractor.services.UserService;
import com.minichat.attractor.user.CrmUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FillDatabase {

   private UserService userService;
   private MessageService messageService;
   private ChatService chatService;

    public FillDatabase(UserService userService, MessageService messageService, ChatService chatService) {
        this.userService = userService;
        this.messageService = messageService;
        this.chatService = chatService;
    }

    @Bean
    public void doFill(){
        messageService.deleteAll();
        chatService.deleteAll();
        userService.deleteAll();
        for(int i=0;i<2;i++){
            var user=new CrmUser();
            user.setUserName("user"+i+"@gmail.com");
            user.setPassword("password");
            user.setMatchingPassword("password");
            userService.save(user);
        }
        fillThemes();
    }
    private void fillThemes(){
        var user=userService.findAll().get(0);
        for(int i=0;i<2;i++){
            var chat= Chat.builder()
                    .name("classmates"+i)
                    .users(List.of(user))
                    .build();
            chatService.save(chat);
        }
        fillComments();
    }
    private void fillComments(){
        var user=userService.findAll().get(0);
        var chat=chatService.findAll().get(0);
        for(int i=0;i<20;i++){
            var message= Message.builder()
                    .text("comment service"+i)
                    .user(user)
                    .chat(chat)
                    .build();
            messageService.save(message);
        }

    }
}
