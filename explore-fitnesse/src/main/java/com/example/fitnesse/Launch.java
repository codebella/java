package com.example.fitnesse;

import fitnesseMain.Arguments;
import fitnesseMain.FitNesseMain;

public class Launch extends FitNesseMain {

    public static void main(String[] args) throws Exception {
        Arguments arguments = null;

        try {
            arguments = new Arguments(args);
        } catch (IllegalArgumentException var5) {
            exit(1);
        }

        Integer exitCode = 0;

        try {
            exitCode = (new FitNesseMain()).launchFitNesse(arguments);
        } catch (Exception var4) {
            exitCode = 1;
        }

        if (exitCode != null) {
            exit(exitCode);
        }

    }

}

