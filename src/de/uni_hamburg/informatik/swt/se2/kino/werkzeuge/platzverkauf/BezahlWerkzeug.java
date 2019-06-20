package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.platzverkauf;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Geldbetrag;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kasse.KassenWerkzeug;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

class BezahlWerkzeug {
    private PlatzVerkaufsWerkzeug _platzVerkaufsWerkzeug;

    private BezahlWerkzeugUI _ui;
    private Geldbetrag _geldbetrag;

    BezahlWerkzeug(PlatzVerkaufsWerkzeug platzVerkaufsWerkzeug) {
        _platzVerkaufsWerkzeug = platzVerkaufsWerkzeug;
    }

    void erzeugeFenster(int preis) {
        if (_ui != null) {
            return;
        }

        _geldbetrag = new Geldbetrag(preis);
        _ui = new BezahlWerkzeugUI(_geldbetrag.toString());
        KassenWerkzeug.setEnabled(false);

        registriereUIAktionen();
        aktualisiereUI();
    }

    private void registriereUIAktionen() {
        _ui.getOkButton().addActionListener(actionEvent -> okButtonGedrueckt());
        _ui.getAbbrechenButton().addActionListener(actionEvent -> abbrechenButtonGedrueckt());

        _ui.getHauptFenster().addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                schliesseFenster();
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });
        _ui.getGezahlterBetragField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                aktualisiereUI();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                aktualisiereUI();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                aktualisiereUI();
            }
        });
    }

    private void aktualisiereUI() {
        if (istBezahlenMoeglich()) {
            _ui.getRestBetragLabel().setText(new Geldbetrag(getGeldbetrag().getEuroCent() - _geldbetrag.getEuroCent()) + " €");
            _ui.getRestPanel().setVisible(true);
        } else {
            _ui.getRestPanel().setVisible(false);
        }

        _ui.getOkButton().setEnabled(istBezahlenMoeglich());
    }

    private void okButtonGedrueckt() {
        _platzVerkaufsWerkzeug.verkaufePlaetze();
        schliesseFenster();
    }

    private void abbrechenButtonGedrueckt() {
        if (_ui == null) {
            return;
        }

        schliesseFenster();
    }

    private void schliesseFenster() {
        _ui.schliesse();
        _ui = null;
        KassenWerkzeug.setEnabled(true);
    }

    private Geldbetrag getGeldbetrag() {
        String input = _ui.getGezahlterBetragField().getText();
        int parsedEuro;

        if (!input.contains(",")) {
            try {
                parsedEuro = Integer.parseInt(input) * 100;
            } catch (Exception ex) {
                return new Geldbetrag(0, 0);
            }
            return new Geldbetrag(parsedEuro);
        }

        String[] withoutComma = input.split(",");
        try {
            if (withoutComma[1].length() > 2) {
                return new Geldbetrag(0, 0);
            }
        } catch (Exception ex) {
        }

        int parsedCent;

        try {
            parsedEuro = Integer.parseInt(withoutComma[0]);
            parsedCent = withoutComma[1].length() == 1 ? Integer.parseInt(withoutComma[1]) * 10 : Integer.parseInt(withoutComma[1]);
        } catch (Exception ex) {
            return new Geldbetrag(0, 0);
        }
        return new Geldbetrag(parsedEuro, parsedCent);
    }

    private boolean istBezahlenMoeglich() {
        return getGeldbetrag().getEuroCent() >= _geldbetrag.getEuroCent();
    }
}
