package com.minichat.attractor.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chats")
public class Chat extends BasicEntity {
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_chat",
            joinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users = new ArrayList<>();

    private LocalDateTime date;

}
