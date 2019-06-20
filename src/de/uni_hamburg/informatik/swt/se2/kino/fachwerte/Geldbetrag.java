package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

public class Geldbetrag {
    private int _euro;
    private int _cent;

    public Geldbetrag(int euro, int cent) {
        _euro = euro;
        _cent = cent;
    }

    public Geldbetrag(int euroCent) {
        _euro = euroCent / 100;
        _cent = euroCent % 100;
    }

    public int getEuro() {
        return _euro;
    }

    public int getCent() {
        return _cent;
    }

    public int getEuroCent() {
        return _euro * 100 + _cent;
    }

    private String getFormatiertenCentAnteil()
    {
        String result = "";
        if (_cent < 10)
        {
            result += "0";
        }
        result += _cent;
        return result;
    }

    @Override
    public String toString() {
        return _euro + "," + getFormatiertenCentAnteil();
    }
}
