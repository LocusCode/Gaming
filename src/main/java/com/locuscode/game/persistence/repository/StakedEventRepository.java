package com.locuscode.game.persistence.repository;

import com.locuscode.game.persistence.model.StakedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StakedEventRepository extends JpaRepository<StakedEvent, Long> {
    @Query(nativeQuery = true,
    value = "SELECT * FROM STAKED_EVENT where USERNAME = :user")
    List<StakedEvent> findByUsername(String user);


}