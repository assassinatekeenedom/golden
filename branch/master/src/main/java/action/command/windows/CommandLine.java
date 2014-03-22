package action.command.windows;

import java.io.IOException;

public enum CommandLine {

    CREATE("@echo off\nsetlocal"),
    NAMED("%"),
    TO("|"),
    HAS("="),
    FLIP("=="),
    IF("if"),
    WRITE(">"),
    APPEND(">>"),
    ECHO("echo"),
    SET("set"),
    CALL("call"),
    START("start"),
    PATH("%cd%"),
    READ("type"),
    FIND("findstr"),
    KILL("TaskKill.exe"),
    SPACE(" "),
    EMPTY("\"\""),
    GOTO("goto"),
    BLOCK(":"),
    CLOSE("endlocal");

    private final String command;

    CommandLine(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public Process start() throws IOException {
        return new ProcessBuilder().start();
    }
}
