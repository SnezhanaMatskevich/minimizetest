/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.bsuir.group172301.matskevich.tour.util.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artur Buzov
 */
public class MatrixList {
    private List<Integer> id = new ArrayList<>();
    private List<Integer> row = new ArrayList<>();
    private List<Integer> col = new ArrayList<>();

    public void add(Integer id, Integer row, Integer col) {
        this.id.add(id);
        this.row.add(row);
        this.col.add(col);
    }
    
    public Integer getId(int number) {
        return id.get(number);
    }

    public Integer getRow(int number) {
        return row.get(number);
    }
    
    public Integer getCol(int number) {
        return col.get(number);
    }

    public int getSize() {
        return id.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            sb.append(getId(i));
            sb.append(" Row = ");
            sb.append(getRow(i));
            sb.append(" Col = ");
            sb.append(getCol(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
