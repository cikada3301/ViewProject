package com.training.vpalagin.project.service.transition;

import com.training.vpalagin.project.model.Ticket;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.model.enums.Action;
import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.UserRole;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TicketTransitionMap {
    public Runnable getTransientState(Ticket ticket, User user, Action action) {
        Map<State, Map<UserRole, Map<Action, Runnable>>> actionStateMap = Map.of(
                State.DRAFT, Map.of(
                        UserRole.EMPLOYEE, Map.of(
                                Action.SUBMIT, () -> ticket.setState(State.NEW),
                                Action.CANCEL, () -> ticket.setState(State.CANCELED)
                        ),
                        UserRole.MANAGER, Map.of(
                                Action.SUBMIT, () -> ticket.setState(State.NEW),
                                Action.CANCEL, () -> ticket.setState(State.CANCELED)
                        )
                )
                ,
                State.NEW, Map.of(
                        UserRole.MANAGER, Map.of(
                                Action.APPROVE, () -> ticket.setState(State.APPROVED),
                                Action.DECLINE, () -> ticket.setState(State.DECLINED),
                                Action.CANCEL, () -> ticket.setState(State.CANCELED)
                        )
                )
                ,
                State.APPROVED, Map.of(
                        UserRole.ENGINEER, Map.of(
                                Action.ASSIGN_TOM_ME, () -> ticket.setState(State.IN_PROGRESS),
                                Action.CANCEL, () -> ticket.setState(State.CANCELED)
                        )
                )
                ,
                State.DECLINED, Map.of(
                        UserRole.EMPLOYEE, Map.of(
                                Action.SUBMIT, () -> ticket.setState(State.NEW),
                                Action.CANCEL, () -> ticket.setState(State.CANCELED)
                        ),
                        UserRole.MANAGER, Map.of(
                                Action.SUBMIT, () -> ticket.setState(State.NEW),
                                Action.CANCEL, () -> ticket.setState(State.CANCELED)
                        )
                )
                ,
                State.IN_PROGRESS, Map.of(
                        UserRole.ENGINEER, Map.of(
                                Action.DONE, () -> ticket.setState(State.DONE)
                        )
                )
        );
        return actionStateMap.get(ticket.getState())
                .get(user.getRole())
                .get(action);
    }
}
