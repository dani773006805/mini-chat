package com.minichat.attractor.services;

import com.minichat.attractor.model.Message;
import com.minichat.attractor.repostory.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public Message save(Message message){
        message.setDate(LocalDateTime.now());
        return messageRepository.save(message);
    }
    public Page<Message> getMessagePage(Long chatId,LocalDateTime date, Pageable pageable){
        String sortField="date";
        Sort sort=Sort.by(sortField);
        var sort1=sort.descending();
        Pageable pageable1= PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort1);
        var pa=messageRepository.findAllByChatIdAndAndDateAfter(chatId,date,pageable);
        return pa;
    }
    public Page<Message> getMessagePage20(Long chatId){
        String sortField="date";
        Sort sort=Sort.by(sortField);
        var sort1=sort.descending();
        var pageable=PageRequest.of(0,20,sort1);
        return messageRepository.findAllByChatId(chatId,pageable);
    }
    public void deleteAll(){
        messageRepository.deleteAll();
    }
}
