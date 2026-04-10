package p3.prog;

import p3.prog.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

    private final List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public String buildSentence() {
        StringBuilder sb = new StringBuilder();

        for (Command c : commands) {
            c.apply();
            sb.append(c.getSentencePart()).append(" ");
        }

        return sb.toString().trim() + ".";
    }

}
