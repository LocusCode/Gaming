package com.locuscode.game.persistence.repository;

import com.locuscode.game.persistence.model.ClaimedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimedEventRepository extends JpaRepository<ClaimedEvent, Long> {
    List<ClaimedEvent> findByUsername(String user);
}
