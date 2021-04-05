package com.minichat.attractor.controller;

import com.minichat.attractor.dto.MessageDto;
import com.minichat.attractor.model.Message;
import com.minichat.attractor.services.ChatService;
import com.minichat.attractor.services.MessageService;
import com.minichat.attractor.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class MessageController {
    private static Long id=1L;
    private final MessageService messageService;
    private final UserService userService;
    private final ChatService chatService;

    public MessageController(MessageService messageService, UserService userService, ChatService chatService) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatService = chatService;
    }

    @GetMapping("/messages/{chatId}")
    public String showMessages(@PathVariable Long chatId, Model model) {
        model.addAttribute("chatId", chatId);
        model.addAttribute("message", new Message());
        id=chatId;
        return "messages";
    }

    @PostMapping("/messages/{chatId}")
    public String showMessages(@PathVariable Long chatId, @ModelAttribute Message message, Principal principal) {
        var chat = chatService.findById(chatId).orElseThrow(() ->
                new RuntimeException("not found"));
        if (principal.getName() == null) {
            throw new RuntimeException("user is invalid");
        }
        var user = userService.findByUserName(principal.getName()).orElseThrow(() ->
                new RuntimeException("not found"));
        message.setChat(chat);
        message.setUser(user);
        messageService.save(message);
        return "redirect:/messages/" + chatId;
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping("/message")
    public ResponseEntity<?> getMessages(Pageable pageable) {
        var date=LocalDateTime.now().minusSeconds(1);
        var messagesPage = messageService.getMessagePage(id,date, pageable);
        var messagesDtoPage = messagesPage
                .map(MessageDto::fromAll);
        return ResponseEntity.ok(messagesDtoPage);
    }
    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping("/message/last20")
    public ResponseEntity<?> getLastMessages(Pageable pageable) {
        var messagesPage = messageService.getMessagePage20(id,pageable);
        var messagesDtoPage = messagesPage
                .map(MessageDto::fromAll);
        return ResponseEntity.ok(messagesDtoPage);
    }
}
