package com.locuscode.game.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class StakedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String amount;

}
