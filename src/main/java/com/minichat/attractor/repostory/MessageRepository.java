package com.minichat.attractor.repostory;

import com.minichat.attractor.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findAllByChatIdAndAndDateAfter(Long chatId, LocalDateTime date, Pageable pageable);
    Page<Message> findAllByChatId(Long chatId, Pageable pageable);
}
