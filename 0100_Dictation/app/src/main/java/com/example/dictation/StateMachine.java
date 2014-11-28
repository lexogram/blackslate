package com.example.dictation;

/**
 * Created by james on 2014-11-26.
 */
public class StateMachine {

    private StateArray stateArray;

    public StateMachine(StateArray stateArray) {
        this.stateArray = stateArray;
        stateArray.setNextState();
    }
}
