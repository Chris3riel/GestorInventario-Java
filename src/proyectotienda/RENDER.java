/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectotienda;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author chris
 */
public class RENDER extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        switch(String.valueOf(table.getValueAt(row, column).toString())){
        case "10":
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        break;
        case "9":
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        break;
        case "8":
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        break;
        case "7":
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        break;
        case "6":
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        break;
        case "5":
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        break;
        case "4":
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        break;
        case "3":
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        break;
        case "2":
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        break;
        case "1":
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        break;
        case "0":
            setBackground(Color.RED);
            setForeground(Color.WHITE);
        break;
        default:
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }
     
}
