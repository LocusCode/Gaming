package com.locuscode.game.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ClaimedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String reward;
}
