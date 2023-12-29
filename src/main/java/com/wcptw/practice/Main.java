package com.wcptw.practice;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    private final static String horizonBar = "—";
    private final static String verticalBar = "|";
    private static String lineSeparator;

    public static void main(String[] args) throws IOException {
        lineSeparator = System.lineSeparator();
        switch (ArgsValidator.validate(args)){
            case WRONG -> System.out.println(worngOutput(null));
            case HELP -> System.out.println(helpOutPut());
            default -> System.out.println(execute(args));
        }

    }

    private static String worngOutput(String output) {
        StringBuilder sb = new StringBuilder("can't execute program correctly");
        if (output != null)
            return sb.append(", ").append(output).toString();
        return sb.append(", use -h for more help").toString();
    }

    private static String execute(String[] args) throws IOException {
        String path = null;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i].toLowerCase();
            String[] receiveds = ArgsReceived.PATH.getReceived();
            for (String received : receiveds) {
                if (arg.startsWith(received) && (i + 1) < args.length) {
                    path = args[i + 1];
                    break;
                }
            }
        }

        if (path == null)
            return worngOutput("can't target path");

        File file = new File(path);
        if (!file.exists())
            return worngOutput("can't find exists : " + path);
        else if (!file.isDirectory())
            return worngOutput("path must be a directory : " + path);
        else if (path.equals("./") || path.equals(".\\"))
            return worngOutput("must be an absolute path");
        else{
            StringBuilder sb = new StringBuilder();
            int layerCount = 0;
            executeForFileWalk(file, sb, layerCount);
            return sb.toString();
        }
    }



    private static void executeForFileWalk(File file, StringBuilder sb, int layerCount){
        if (file.isDirectory()){
            sb.append(fileNameFormat(file.getName(), layerCount));
            File[] files = file.listFiles();
            if (files != null){
                for (File child : files) {
                    if (child.isDirectory()){
                        executeForFileWalk(child, sb, layerCount+1);
                    }else{
                        sb.append(fileNameFormat(child.getName(), layerCount+1));
                    }
                }
            }
        }else{
            sb.append(fileNameFormat(file.getName(), layerCount+1));
        }
    }

    private static String fileNameFormat(String name, int layerCount) {
        StringBuilder sb = new StringBuilder();
        if (layerCount == 0){
            return sb.append(name).append(lineSeparator).toString();
        }
        for (int i = 0; i < layerCount; i++) {
            if (i == layerCount-1)
                sb.append(String.format("%s%s %s", verticalBar, horizonBar, name)).append(lineSeparator);
            else
                sb.append(String.format("%-4s", verticalBar));
        }
        return sb.toString();
    }

    private static String helpOutPut() {
        StringBuilder sb = new StringBuilder();
        sb.append("This program analyzed the structure of the path you gave").append("\r\n")
                .append("===============HELP=================")
                .append(lineSeparator)
        ;

        ArgsReceived[] argsReceiveds = ArgsReceived.values();
        String[] argNames = Arrays.stream(argsReceiveds).map(argsReceived -> Arrays.toString(argsReceived.getReceived())).toArray(String[]::new);
        int maxNameLength = getMaxStringLength(argNames) + 4;
//        System.out.println("maxNamesLength : " + maxNameLength);
        String[] desps = Arrays.stream(argsReceiveds).map(ArgsReceived::getDesp).toArray(String[]::new);
        int maxDespLength = getMaxStringLength(desps);
//        System.out.println("maxDespLength : " + maxDespLength);

        String format = "%-" + maxNameLength + "s %-" + maxDespLength + "s";
//        System.out.println("format : " + format );
        sb.append(String.format(format, "argument", "description")).append(lineSeparator);
        for (ArgsReceived argsReceived : argsReceiveds) {
            sb.append(String.format(format, Arrays.toString(argsReceived.getReceived()), argsReceived.getDesp()))
                    .append(lineSeparator);
        }

        return sb.toString();
    }

    private static int getMaxStringLength(String[] strings) {
        int maxLength = 0;
        for (String str : strings) {
            if (str.length() > maxLength) {
                maxLength = str.length();
            }
        }
        return maxLength;
    }



}