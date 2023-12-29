package com.wcptw.practice;

public class ArgsValidator {
    public static ActiveStatus validate(String[] args){
        ActiveStatus resultStatus = ActiveStatus.WRONG;
        for (String arg : args) {
            if (checkArgs(arg, ActiveStatus.HELP)){
                return ActiveStatus.HELP;
            }
            else if (checkArgs(arg, ActiveStatus.EXECUTE)){
                resultStatus = ActiveStatus.EXECUTE;
            }
        }
        return resultStatus;
    }

    private static boolean checkArgs(String arg, ActiveStatus activeStatus) {
        ArgsReceived[] argsReceiveds = activeStatus.getArgsReceiveds();
        for (ArgsReceived argsReceived : argsReceiveds) {
            String[] received = argsReceived.getReceived();
            for (String s : received) {
                if (arg.toLowerCase().startsWith(s.toLowerCase()))
                    return true;
            }
        }
        return false;
    }
}
