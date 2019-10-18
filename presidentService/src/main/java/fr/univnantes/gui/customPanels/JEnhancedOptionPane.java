package fr.univnantes.gui.customPanels;

import javax.swing.*;
import java.awt.*;

public class JEnhancedOptionPane extends JOptionPane {
    public static String showInputDialog(final Object message, final Object[] options, final Object[] choices, final Object defaultOption,
                                         final String titleOfWindow)
            throws HeadlessException {
        final JOptionPane pane = new JOptionPane(message,
                                                    QUESTION_MESSAGE,
                                                 OK_CANCEL_OPTION,
                                            null,
                                                 options,
                                            null);

        pane.setWantsInput(true);
        pane.setSelectionValues(choices);
        pane.setInitialSelectionValue(defaultOption);
        pane.setComponentOrientation((getRootFrame()).getComponentOrientation());
        pane.setMessageType(QUESTION_MESSAGE);
        pane.selectInitialValue();
        final JDialog dialog = pane.createDialog(null, titleOfWindow);
        dialog.setVisible(true);
        dialog.dispose();
        final Object value = pane.getInputValue();
        return (value == UNINITIALIZED_VALUE) ? null : (String) value;
    }
}