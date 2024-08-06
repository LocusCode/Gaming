package com.locuscode.game.graphql.data;

import com.locuscode.game.persistence.model.ClaimedEvent;
import com.locuscode.game.persistence.model.StakedEvent;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UsersData {
    private List<StakedEvent> stakedEvents;
    private List<ClaimedEvent> claimedEvents;
}
