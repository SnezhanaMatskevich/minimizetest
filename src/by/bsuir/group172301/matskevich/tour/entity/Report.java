/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.group172301.matskevich.tour.entity;

/**
 *
 * @author Sniazhana_Matskevich
 */
public class Report  extends Entity{
    private int col1;
    private int col2;
    private double perc;
    private String report;

    /**
     * @return the col1
     */
    public int getCol1() {
        return col1;
    }

    /**
     * @param col1 the col1 to set
     */
    public void setCol1(int col1) {
        this.col1 = col1;
    }

    /**
     * @return the col2
     */
    public int getCol2() {
        return col2;
    }

    /**
     * @param col2 the col2 to set
     */
    public void setCol2(int col2) {
        this.col2 = col2;
    }

    /**
     * @return the perc
     */
    public double getPerc() {
        return perc;
    }

    /**
     * @param perc the perc to set
     */
    public void setPerc(double perc) {
        this.perc = perc;
    }

    /**
     * @return the report
     */
    public String getReport() {
        return report;
    }

    /**
     * @param report the report to set
     */
    public void setReport(String report) {
        this.report = report;
    }
    
}
