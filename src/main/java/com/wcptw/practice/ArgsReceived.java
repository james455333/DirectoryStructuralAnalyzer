package com.wcptw.practice;

import lombok.Getter;

@Getter
public enum ArgsReceived {
    HELP("get program info", "-h", "--help"),
    PATH("the path you want to analyze", "-p", "--path"),
    ;

    private final String desp;
    private final String[] received;

    ArgsReceived(String desp, String... received) {
        this.desp = desp;
        this.received = received;
    }
}
