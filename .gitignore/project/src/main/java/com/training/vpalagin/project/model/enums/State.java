package com.training.vpalagin.project.model.enums;

public enum State {
    DRAFT(0), NEW(1), APPROVED(2), DECLINED(3), IN_PROGRESS(4), DONE(5), CANCELED(6);

    private Integer number;

    State(int number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }
}