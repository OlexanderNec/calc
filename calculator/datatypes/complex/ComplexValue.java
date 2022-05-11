package calculator.datatypes.complex;

import calculator.AbstractValue;
import calculator.DivisionByZeroException;

public class ComplexValue extends AbstractValue {

    private final double re;
    private final double im;

    public ComplexValue(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public AbstractValue add(AbstractValue operand) {
        return new ComplexValue(re + ((ComplexValue) operand).re, im + ((ComplexValue) operand).im);
    }

    public AbstractValue sub(AbstractValue operand) {
        return new ComplexValue(re - ((ComplexValue) operand).re, im - ((ComplexValue) operand).im);
    }

    public AbstractValue mul(AbstractValue operand) {
        return new ComplexValue(re * ((ComplexValue) operand).re - im * ((ComplexValue) operand).im,
                re * ((ComplexValue) operand).im + im * ((ComplexValue) operand).re);
    }

    public AbstractValue div(AbstractValue operand)
            throws DivisionByZeroException {
        double absoluteValue = ((ComplexValue) operand).re * ((ComplexValue) operand).re +
                ((ComplexValue) operand).im * ((ComplexValue) operand).im;
        if (absoluteValue == 0.0)
            throw new DivisionByZeroException();
        return new ComplexValue((this.re * ((ComplexValue) operand).re +
                this.im * ((ComplexValue) operand).im) / absoluteValue, (this.im * ((ComplexValue) operand).re -
                this.re * ((ComplexValue) operand).im) / absoluteValue);
    }

    public String toString() {
        String imv = (Math.abs(Math.abs(im) - 1) < 1e-8 ? "" : String.valueOf(Math.abs(im)));
        if (im == 0) return String.valueOf(re);
        else if (re == 0) return (im > 0 ? "" : "-") + imv + "i";
        else return re + (im > 0 ? "+" : "-") + imv + "i";
    }

}
