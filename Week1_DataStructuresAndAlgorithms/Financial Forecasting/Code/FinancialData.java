package FinancialForecasting;


public class FinancialData {
    private double[] monthlyRevenue;
    private String[] months;
    private int currentMonth;

    public FinancialData(int capacity) {
        this.monthlyRevenue = new double[capacity];
        this.months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        this.currentMonth = 0;
    }

    public void addMonthlyRevenue(double revenue) {
        if (currentMonth < monthlyRevenue.length) {
            monthlyRevenue[currentMonth] = revenue;
            currentMonth++;
        }
    }

    public double[] getRevenue() { return monthlyRevenue; }
    public String[] getMonths() { return months; }
    public int getCurrentMonth() { return currentMonth; }
}

