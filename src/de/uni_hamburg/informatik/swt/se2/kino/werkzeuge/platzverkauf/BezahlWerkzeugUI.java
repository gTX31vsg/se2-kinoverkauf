package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class BezahlWerkzeugUI {
    private final String _geldbetrag;

    private JFrame _hauptFenster;
    private JPanel _restPanel;
    private JTextField _gezahltBetragField;
    private JLabel _restBetragLabel;
    private JButton _okButton;
    private JButton _abbrechenButton;

    BezahlWerkzeugUI(String geldbetrag) {
        _geldbetrag = geldbetrag;

        _hauptFenster = erstelleFenster();
    }

    private JFrame erstelleFenster() {
        JFrame frame = new JFrame();

        frame.setTitle("Bar bezahlen");
        frame.setBounds(300, 400, 300, 400);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(new Insets(75, 150, 75, 150)));

        JPanel zuZahlenPanel = new JPanel(new GridLayout());
        JLabel zuZahlenLabel = new JLabel("Zu zahlen:");
        JLabel zuZahlenBetrag = new JLabel(_geldbetrag + " €");
        zuZahlenPanel.add(zuZahlenLabel);
        zuZahlenPanel.add(zuZahlenBetrag);
        contentPanel.add(zuZahlenPanel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        _restPanel = new JPanel(new GridLayout());
        JLabel restLabel = new JLabel("Rest:");
        _restBetragLabel = new JLabel();
        _restPanel.add(restLabel);
        _restPanel.add(_restBetragLabel);
        contentPanel.add(_restPanel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel gezahltPanel = new JPanel();
        gezahltPanel.setLayout(new GridBagLayout());
        JLabel gezahltLabel = new JLabel("Gezahlt:  ");
        _gezahltBetragField = new JTextField(10);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gezahltPanel.add(gezahltLabel);
        gridBagConstraints.gridx = 1;
        gezahltPanel.add(_gezahltBetragField);
        contentPanel.add(gezahltPanel);

        frame.add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        _okButton = new JButton("Ok");
        _abbrechenButton = new JButton("Abbrechen");
        buttonPanel.add(_okButton);
        buttonPanel.add(_abbrechenButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        return frame;
    }

    void schliesse() {
        _hauptFenster.dispose();
    }

    JFrame getHauptFenster() {
        return _hauptFenster;
    }

    JPanel getRestPanel() {
        return _restPanel;
    }

    JButton getOkButton() {
        return _okButton;
    }

    JButton getAbbrechenButton() {
        return _abbrechenButton;
    }

    JLabel getRestBetragLabel() {
        return _restBetragLabel;
    }

    JTextField getGezahlterBetragField() {
        return _gezahltBetragField;
    }
}
