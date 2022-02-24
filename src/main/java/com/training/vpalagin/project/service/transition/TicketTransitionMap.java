package com.training.vpalagin.project.service.transition;

import com.training.vpalagin.project.dto.ticket.TicketViewDto;
import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.UserRole;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TicketTransitionMap {
    public State getTransientState(Ticket ticket, User user, Action action) {
        Map<State, Map<UserRole, Map<Action, State>>> actionStateMap = Map.of(
                State.DRAFT, Map.of(
                        UserRole.EMPLOYEE, Map.of(
                                Action.SUBMIT, State.NEW,
                                Action.CANCEL, State.CANCELED
                        ),
                        UserRole.MANAGER, Map.of(
                                Action.SUBMIT, State.NEW,
                                Action.CANCEL, State.CANCELED
                        )
                )
                ,
                State.NEW, Map.of(
                        UserRole.MANAGER, Map.of(
                                Action.APPROVE, State.APPROVED,
                                Action.DECLINE, State.DECLINED,
                                Action.CANCEL, State.CANCELED
                        )
                )
                ,
                State.APPROVED, Map.of(
                        UserRole.ENGINEER, Map.of(
                                Action.ASSIGN_TO_ME, State.IN_PROGRESS,
                                Action.CANCEL, State.CANCELED
                        )
                )
                ,
                State.DECLINED, Map.of(
                        UserRole.EMPLOYEE, Map.of(
                                Action.SUBMIT, State.NEW,
                                Action.CANCEL, State.CANCELED
                        ),
                        UserRole.MANAGER, Map.of(
                                Action.SUBMIT, State.NEW,
                                Action.CANCEL, State.CANCELED
                        )
                )
                ,
                State.IN_PROGRESS, Map.of(
                        UserRole.ENGINEER, Map.of(
                                Action.DONE, State.DONE
                        )
                )
        );
        return actionStateMap.get(ticket.getState())
                .get(user.getRole())
                .get(action);
    }

    public List<Action> getActions(TicketViewDto ticket, User user) {
        Map<State, Map<UserRole, List<Action>>> actionStateMap = Map.of(
                State.DRAFT, Map.of(
                        UserRole.EMPLOYEE, new ArrayList<>() {{
                            add(Action.SUBMIT);
                            add(Action.CANCEL);
                        }},
                        UserRole.MANAGER, new ArrayList<>() {{
                            add(Action.SUBMIT);
                            add(Action.CANCEL);
                        }}
                )
                ,
                State.NEW, Map.of(
                        UserRole.MANAGER, new ArrayList<>() {{
                            add(Action.APPROVE);
                            add(Action.DECLINE);
                            add(Action.CANCEL);
                        }}
                )
                ,
                State.APPROVED, Map.of(
                        UserRole.ENGINEER, new ArrayList<>() {{
                            add(Action.ASSIGN_TO_ME);
                            add(Action.CANCEL);
                        }}
                )
                ,
                State.DECLINED, Map.of(
                        UserRole.EMPLOYEE, new ArrayList<>() {{
                            add(Action.SUBMIT);
                            add(Action.CANCEL);
                        }},
                        UserRole.MANAGER, new ArrayList<>() {{
                            add(Action.SUBMIT);
                            add(Action.CANCEL);
                        }}
                )
                ,
                State.IN_PROGRESS, Map.of(
                        UserRole.ENGINEER, new ArrayList<>() {{
                            add(Action.DONE);
                        }}
                ),
                State.DONE, Map.of(
                        UserRole.ENGINEER, new ArrayList<>() {{
                            add(Action.DONE);
                        }}
                )
        );
        return actionStateMap.get(ticket.getState())
                .getOrDefault(user.getRole(), new ArrayList<>());
    }
}
