package com.minichat.attractor.services;

import com.minichat.attractor.model.Chat;
import com.minichat.attractor.repostory.ChatRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatService {
    private ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Page<Chat> findPaginated(Integer pageNo){
        var pageable= PageRequest.of(pageNo-1,5);
        return chatRepository.findAll(pageable);
    }
    public Optional<Chat> findById(Long id){
        return chatRepository.findById(id);
    }
}
