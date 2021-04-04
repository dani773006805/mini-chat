package com.minichat.attractor.controller;

import com.minichat.attractor.dto.ChatDto;
import com.minichat.attractor.services.ChatService;
import com.minichat.attractor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.context.Theme;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class ChatController {
    private UserService userService;
    private ChatService chatService;

    public ChatController(UserService userService, ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
    }

    @GetMapping("/chats")
    public String getChats(Model model, @RequestParam(name = "pageNo", required = false) Integer pageNo) {
        if (pageNo == null) {
            pageNo = 1;
        }
        var pageable = chatService.findPaginated(pageNo);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", pageable.getTotalPages());
        model.addAttribute("totalItems", pageable.getTotalElements());
        var dtos = pageable.getContent()
                .stream().map(ChatDto::fromAll).collect(Collectors.toList());
        model.addAttribute("listChats", dtos);
        return "chat";
    }

//    @GetMapping("create")
//    public String get(Model model) {
//        model.addAttribute("theme", new Theme());
//        return "addTheme";
//    }
//
//    @PostMapping("/create")
//    public String create(@ModelAttribute @Valid Theme theme, BindingResult result,
//                         Principal principal) {
//        if (principal.getName() == null) {
//            throw new RuntimeException("secured");
//        }
//        if (result.hasErrors()) {
//            return "addTheme";
//        }
//        var user = userService.findByUserName(principal.getName()).orElseThrow(() ->
//                new RuntimeException("not-allowed"));
//        theme.setUser(user);
//        themeService.save(theme);
//        return "redirect:/themes";
//    }
}
