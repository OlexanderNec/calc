package calculator.datatypes.complex;

import calculator.AbstractValue;
import calculator.AbstractValueParser;
import calculator.ParseValueException;

public class ComplexValueParser implements AbstractValueParser {

    public AbstractValue parse(String value) throws ParseValueException {
        try {
            double re = 0;
            double im = 0;

            if (!(value.contains("i"))) re = Double.parseDouble(value);
            else {
                String[] components = value.split("[+-]");
                int m = components.length;
                if (components[m-1].equals("i")) components[m-1] = "1" + components[m-1];
                switch (m) {
                    case 1 -> im = Double.parseDouble(components[0].substring(0, components[0].length() - 1));
                    case 2 -> {
                        if (components[0].equals(""))
                            im = -Double.parseDouble(components[1].substring(0, components[1].length() - 1));
                        else {
                            re = Double.parseDouble(components[0]);
                            im = Double.parseDouble(components[1].substring(0, components[1].length() - 1)) *
                                    (value.contains("-") ? (-1) : 1);
                        }
                    }
                    case 3 -> {
                        re = -Double.parseDouble(components[1]);
                        im = Double.parseDouble(components[2].substring(0, components[2].length() - 1)) *
                                (value.contains("+") ? 1 : (-1));
                    }
                }
            }

            return new ComplexValue(re, im);

        } catch (NumberFormatException exception) {
            throw new ParseValueException();
        }
    }

    public String getDatatypeName() { return "комплексные числа"; }

}
