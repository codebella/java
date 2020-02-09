package com.example.fitnesse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Factor {
    private double Multiplier;
    private String Symbol;
    private List<String> Tenor = new ArrayList<>();
    private List<String> Duration = new ArrayList<>();
    private List<String> Value = new ArrayList<>();
    private List<String> FValue = new ArrayList<>();
    private List<String> DValue = new ArrayList<>();
    private List<String> tList = new ArrayList<>();
    private List<String> dList = new ArrayList<>();
    private List<String> valueList = new ArrayList<>();
    private List<String> fValueList = new ArrayList<>();
    private List<String> dValueList = new ArrayList<>();

    /**
     * Description: The below is the demonstrate that we can pass arguments to the query     *
     * @param Multiplier
     * @param Symbol
     */
    public Factor(Double Multiplier, String Symbol) {
        System.out.println("Param Multiplier: " + Multiplier + " Clazz: " + Symbol);
        this.Multiplier = Multiplier;
        this.Symbol = Symbol;
    }

    // The below is preparation of the resultset after performing the computation.
    // Hence we have all the logic to compare and put it as part of the data
    public List doTable(List<List<String>> table) {
        System.out.println("Query is getting called");
        System.out.println(table);
        try {
            Tenor = table.get(0);
            Duration = table.get(1);
            Value = table.get(2);
            FValue = table.get(3);
            DValue = table.get(4);
            System.out.println("Duration Size: " + Duration.size());
            for (int i = 1; i < Duration.size(); i++) {
                System.out.println("Duration: continuing");
                Double cVal = Double.valueOf(Value.get(i));
                Double fVal = Double.valueOf(FValue.get(i));
                Double dVal = Double.valueOf(DValue.get(i));
                Double calValue = calculateValue(i);
                Double calFValue = calculateFValue(i);
                // Scale needs to be carefully evaluated, especially if this is being handled from data as outside computation
                Double calDValue = new BigDecimal(calValue + calFValue).setScale(7, RoundingMode.HALF_EVEN).doubleValue();
                final String valText;

                // Both syntax works, whether to append the value or just mark the entry as pass/fail
                if (calValue.compareTo(cVal) == 0) {
                    valText = "pass:" + calValue;
                } else {
                    valText = "fail:" + calValue;
                }
                valueList.add(valText);

                final String valFText;
                if (calFValue.compareTo(fVal) == 0) {
                    valFText = "pass:" + calFValue;
                } else {
                    valFText = "fail" + calFValue;
                }
                fValueList.add(valFText);

                final String valDText;
                if (calDValue.compareTo(dVal) == 0) {
                    // Both syntax works, whether to append the value or just mark the entry as pass/fail
                    valDText = "pass";
                } else {
                    valDText = "fail";
                }
                dValueList.add(valDText);
                String durationValue = Duration.get(i);
                dList.add("pass:" + durationValue);
                String tenorValue = Tenor.get(i);
                tList.add("pass:" + tenorValue);
            }
            dList.add(0, "ignore");
            tList.add(0, "ignore");
            valueList.add(0, "ignore");
            fValueList.add(0, "ignore");
            dValueList.add(0, "ignore");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        List data = asList(tList, dList, valueList, fValueList, dValueList);
        List data2 = asList(asList(Tenor, Duration, Value, FValue, DValue));
        System.out.println("Original: " + data2);
        System.out.println("New: " + data);
        return data;
    }

    private Double calculateValue(int i) {
        Double d = Double.valueOf(Duration.get(i));
        Double value = Math.exp(1.96 * Multiplier * Double.valueOf(d) / 252);
        BigDecimal decimal = BigDecimal.valueOf(value)
                .setScale(7, RoundingMode.HALF_EVEN);
        return decimal.doubleValue();
    }

    private Double calculateFValue(int i) {
        Double value = Math.exp(1.96 * Multiplier * 10 / 252);
        BigDecimal decimal = BigDecimal.valueOf(value)
                .setScale(7, RoundingMode.HALF_EVEN);
        return decimal.doubleValue();
    }

}
