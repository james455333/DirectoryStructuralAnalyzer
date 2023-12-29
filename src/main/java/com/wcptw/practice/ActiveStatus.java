package com.wcptw.practice;

import lombok.Getter;

@Getter
public enum ActiveStatus {
    HELP(ArgsReceived.HELP),
    EXECUTE(ArgsReceived.PATH),
    WRONG
    ;


    private final ArgsReceived[] argsReceiveds;

    ActiveStatus(ArgsReceived... argsReceiveds){
        this.argsReceiveds = argsReceiveds;
    }
}
