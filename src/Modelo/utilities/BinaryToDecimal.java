package Modelo.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BinaryToDecimal {
    public BinaryToDecimal(){

    }
    public BigDecimal getDecimalValue(String binaryString){
        // Convert binary string to BigDecimal
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal factor = BigDecimal.ONE;

        // Split the binary string into integer and fractional parts
        String[] parts = binaryString.split("\\.");

        // Convert integer part
        for (int i = parts[0].length() - 1; i >= 0; i--) {
            if (parts[0].charAt(i) == '1') {
                result = result.add(factor);
            }
            factor = factor.multiply(BigDecimal.valueOf(2));
        }

        // Convert fractional part
        if (parts.length > 1) {
            factor = BigDecimal.ONE.divide(BigDecimal.valueOf(2));
            for (int i = 0; i < parts[1].length(); i++) {
                if (parts[1].charAt(i) == '1') {
                    result = result.add(factor);
                }
                factor = factor.divide(BigDecimal.valueOf(2));
            }
        }

        return result;
    }
}
