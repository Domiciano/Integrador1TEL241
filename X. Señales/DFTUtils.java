package com.example.dftplot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DFTUtils {

    public static double findMax(double[] arr) {
        double max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    public static double findMin(double[] arr) {
        double min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        return min;
    }

    public static void writeCSV(String fileName, String columnDelim, String rowDelim, char decimalPoint, double[]... arrays) throws IOException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(decimalPoint);
        DecimalFormat numberFormat = new DecimalFormat("0.######", symbols);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            int numRows = arrays[0].length;
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < arrays.length; col++) {
                    writer.write(numberFormat.format(arrays[col][row]));
                    if (col < arrays.length - 1) {
                        writer.write(columnDelim);
                    }
                }
                writer.write(rowDelim);
            }
        }
    }

    public static double[] dftSpectrum(double[] signal) {
        int N = signal.length;
        double[] spectrum = new double[N / 2];
        for (int k = 0; k < N / 2; k++) {
            double realSum = 0.0;
            double imagSum = 0.0;
            for (int n = 0; n < N; n++) {
                double angle = 2 * Math.PI * k * n / N;
                realSum += signal[n] * Math.cos(angle);
                imagSum -= signal[n] * Math.sin(angle);
            }
            spectrum[k] = Math.sqrt(realSum * realSum + imagSum * imagSum);
        }
        return spectrum;
    }

    public static double[] dftFreq(double[] signal, double samplingFrequency) {
        int N = signal.length;
        double[] frequencies = new double[N / 2];
        for (int k = 0; k < N / 2; k++) {
            frequencies[k] = k * samplingFrequency / N;
        }
        return frequencies;
    }

    public static double[] normalize(double[] signal) {
        // Calcular la media (componente DC) de la señal
        double sum = 0;
        for (double value : signal) {
            sum += value;
        }
        double mean = sum / signal.length;

        // Restar la media de cada muestra para eliminar el componente DC
        for (int i = 0; i < signal.length; i++) {
            signal[i] -= mean;
        }

        // Calcular la amplitud máxima de la señal
        double maxAbs = Math.abs(signal[0]);
        for (int i = 1; i < signal.length; i++) {
            double absValue = Math.abs(signal[i]);
            if (absValue > maxAbs) {
                maxAbs = absValue;
            }
        }

        // Dividir cada muestra por la amplitud máxima para normalizar la señal
        for (int i = 0; i < signal.length; i++) {
            signal[i] /= maxAbs;
        }

        return signal;
    }


}
