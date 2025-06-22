package FinancialForecasting;

import java.util.Arrays;

public class FinancialForecastingEngine {
    private FinancialData data;

    public FinancialForecastingEngine(FinancialData data) {
        this.data = data;
    }

    public double[] simpleMovingAverageForcast(int periods, int windowSize) {
        double[] revenue = data.getRevenue();
        double[] forecast = new double[periods];

        for (int i = 0; i < periods; i++) {
            double sum = 0;
            int count = 0;

            for (int j = Math.max(0, data.getCurrentMonth() - windowSize + i);
                 j < data.getCurrentMonth() + i && j < revenue.length; j++) {
                sum += revenue[j];
                count++;
            }

            forecast[i] = count > 0 ? sum / count : 0;
        }

        return forecast;
    }

    public double[] linearGrowthForecast(int periods) {
        double[] revenue = data.getRevenue();
        double[] forecast = new double[periods];

        if (data.getCurrentMonth() < 2) {
            Arrays.fill(forecast, revenue[0]);
            return forecast;
        }

        double totalGrowth = 0;
        int growthPeriods = 0;

        for (int i = 1; i < data.getCurrentMonth(); i++) {
            if (revenue[i - 1] != 0) {
                totalGrowth += (revenue[i] - revenue[i - 1]) / revenue[i - 1];
                growthPeriods++;
            }
        }

        double avgGrowthRate = growthPeriods > 0 ? totalGrowth / growthPeriods : 0;
        double lastRevenue = revenue[data.getCurrentMonth() - 1];

        for (int i = 0; i < periods; i++) {
            forecast[i] = lastRevenue * (1 + avgGrowthRate * (i + 1));
        }

        return forecast;
    }

    public double[] exponentialSmoothingForecast(int periods, double alpha) {
        double[] revenue = data.getRevenue();
        double[] forecast = new double[periods];

        if (data.getCurrentMonth() == 0) return forecast;

        double smoothedValue = revenue[0];

        for (int i = 1; i < data.getCurrentMonth(); i++) {
            smoothedValue = alpha * revenue[i] + (1 - alpha) * smoothedValue;
        }

        Arrays.fill(forecast, smoothedValue);

        return forecast;
    }

    public double[] seasonalForecast(int periods) {
        double[] revenue = data.getRevenue();
        double[] forecast = new double[periods];

        if (data.getCurrentMonth() < 4) {
            return simpleMovingAverageForcast(periods, Math.min(3, data.getCurrentMonth()));
        }

        double[] quarterlyAvg = new double[4];
        int[] quarterlyCount = new int[4];

        for (int i = 0; i < data.getCurrentMonth(); i++) {
            int quarter = i % 4;
            quarterlyAvg[quarter] += revenue[i];
            quarterlyCount[quarter]++;
        }

        for (int i = 0; i < 4; i++) {
            if (quarterlyCount[i] > 0) {
                quarterlyAvg[i] /= quarterlyCount[i];
            }
        }

        for (int i = 0; i < periods; i++) {
            int quarter = (data.getCurrentMonth() + i) % 4;
            forecast[i] = quarterlyAvg[quarter];
        }

        return forecast;
    }

    public double calculateMAPE(double[] actual, double[] predicted) {
        if (actual.length != predicted.length) return -1;

        double sum = 0;
        int validPredictions = 0;

        for (int i = 0; i < actual.length; i++) {
            if (actual[i] != 0) {
                sum += Math.abs((actual[i] - predicted[i]) / actual[i]);
                validPredictions++;
            }
        }

        return validPredictions > 0 ? (sum / validPredictions) * 100 : 0;
    }

    public void displayHistoricalData() {
        System.out.println("Historical Revenue Data:");
        double[] revenue = data.getRevenue();
        String[] months = data.getMonths();

        for (int i = 0; i < data.getCurrentMonth(); i++) {
            System.out.printf("%s: $%.2f\n", months[i], revenue[i]);
        }
        System.out.println();
    }
}
