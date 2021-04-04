package com.minichat.attractor.repostory;

import com.minichat.attractor.model.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
    Page<Chat> findAll(Pageable pageable);
    }
