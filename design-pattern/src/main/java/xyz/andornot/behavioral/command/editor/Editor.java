package xyz.andornot.behavioral.command.editor;

import xyz.andornot.behavioral.command.commands.*;

import javax.swing.*;
import java.awt.*;

public class Editor {
    private final CommandHistory history = new CommandHistory();
    public JTextArea textField;
    public String clipboard;

    public void init() {
        var frame = new JFrame("Text editor (type 6 use buttons, Luke!)");
        var content = new JPanel();
        frame.setContentPane(content);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        textField = new JTextArea();
        textField.setLineWrap(true);
        content.add(textField);

        var buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        var ctrlC = new JButton("Ctrl+C");
        var ctrlX = new JButton("Ctrl+X");
        var ctrlV = new JButton("Ctrl+V");
        var ctrlZ = new JButton("Ctrl+Z");

        var editor = this;
        ctrlC.addActionListener(_ -> executeCommand(new CopyCommand(editor)));
        ctrlX.addActionListener(_ -> executeCommand(new CutCommand(editor)));
        ctrlV.addActionListener(_ -> executeCommand(new PasteCommand(editor)));
        ctrlZ.addActionListener(_ -> undo());

        buttons.add(ctrlC);
        buttons.add(ctrlX);
        buttons.add(ctrlV);
        buttons.add(ctrlZ);
        content.add(buttons);

        frame.setSize(450, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void executeCommand(Command command) {
        if (command.execute()) {
            history.push(command);
        }
    }

    private void undo() {
        if (history.isEmpty()) {
            return;
        }

        Command command = history.pop();
        if (command != null) {
            command.undo();
        }
    }
}
