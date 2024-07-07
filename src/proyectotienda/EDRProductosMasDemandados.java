/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectotienda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author chris
 */
public class EDRProductosMasDemandados extends javax.swing.JPanel {

    /**
     * Creates new form EDRProductosMasDemandados
     */
    public EDRProductosMasDemandados() {
        initComponents();
        consultar();
        graficaProductosMayorDemandados();
    }
    //VARIABLES DE LA CONEXXION
    Conexion con1 = new Conexion();
    Connection conet;
    DefaultTableModel model;
    DefaultTableModel model2;
    Statement st;
    ResultSet rs;
    int idc;
    
    //CONSULTA DE LOS PRODUCTOS
    void consultar(){
    String sql = "SELECT * FROM productos";
        try {
            conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            
            //Objecto con numero de columnas de la tabla:
            Object productos[] = new Object[10];
            model = (DefaultTableModel) jTable1.getModel();
            while (rs.next()) {  
                //40 stock maximo del almacen
                if (Integer.parseInt(rs.getString("stock"))<=20) {
                productos[0]= rs.getString("id_producto");
                productos[1]= rs.getString("nombre");
                productos[2]= rs.getString("precio");
                
                productos[3]= rs.getString("stock");
                productos[4]= rs.getString("id_proveedor");
                productos[5]= rs.getString("id_categoria");
                productos[6]= rs.getString("caducidad");
                model.addRow(productos);
                } 
            }
            jTable1.setModel(model);
            
        } catch (Exception e) {
        }
        
        
    }
    
    //GRAFICA
    void graficaProductosMayorDemandados(){
     // Grafica de barras
        
        int Bdas = 0;
        int Abrs = 0;
        int Atmn = 0;
        int Hrpn = 0;
        int Pdes = 0;
        int Btns = 0;
        int Bdash = 0;
        int Hgpl = 0;
        int Psla = 0;
        int Cfdc = 0;
        int Hlds = 0;
        int Lcts = 0;
        if (jTable1.getRowCount()>0) {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Bdas")) {
                    Bdas+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Abrs")) {
                    Abrs+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Atmn")) {
                    Atmn+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Hrpn")) {
                    Hrpn+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Pdes")) {
                    Pdes+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Btns")) {
                    Btns+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Bdash")) {
                    Bdash+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Hgpl")) {
                    Hgpl+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Psla")) {
                    Psla+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Cfdc")) {
                    Cfdc+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Hlds")) {
                    Hlds+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
                if (String.valueOf(jTable1.getValueAt(i, 5)).equals("Lcts")) {
                    Lcts+=Integer.parseInt((String) jTable1.getValueAt(i, 3));
                }
        }}
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        datos.setValue(Bdas, " "+"Bebidas", "PRODUCTO");
        datos.setValue(Abrs, " "+"Abarrotes", "PRODUCTO");
        datos.setValue(Atmn, " "+"Auto Medicacion", "PRODUCTO");
        datos.setValue(Hrpn, " "+"Harinas y pan", "PRODUCTO"); 
        datos.setValue(Pdes, " "+"Productos enlatados", "PRODUCTO");
        datos.setValue(Btns, " "+"Botanas","PRODUCTO");
        datos.setValue(Bdash, " "+"Bebidas Aloholicas", "PRODUCTO");
        datos.setValue(Hgpl, " "+"Higiene Personal", "PRODUCTO");
        datos.setValue(Psla, " "+"Productos de Limpieza", "PRODUCTO");
        datos.setValue(Cfdc, " "+"Cafeteria y dulceria", "PRODUCTO");
        datos.setValue(Hlds, " "+"Helados", "PRODUCTO");
        datos.setValue(Lcts, " "+"Lacteos", "PRODUCTO");
        
        JFreeChart graficoBarra = ChartFactory.createBarChart3D(
                "SUMA POR CATEGORIA DE LOS PRODUCTOS MAYOR DEMANDADOS",
                "TOTAL DE UNIDADES POR CATEGORIAS", 
                "", 
                datos,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        
        ChartPanel panel = new ChartPanel(graficoBarra);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension (574, 568));
        
        Grafica.setLayout( new BorderLayout());
        Grafica.add(panel,BorderLayout.NORTH);
        //pack();
        repaint();
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Grafica = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1070, 648));
        setPreferredSize(new java.awt.Dimension(1070, 648));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("HP Simplified", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Productos con mas demanda");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, -1));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("GRAFICA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 47, -1, -1));

        javax.swing.GroupLayout GraficaLayout = new javax.swing.GroupLayout(Grafica);
        Grafica.setLayout(GraficaLayout);
        GraficaLayout.setHorizontalGroup(
            GraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
        );
        GraficaLayout.setVerticalGroup(
            GraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );

        jPanel1.add(Grafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 68, -1, -1));

        jLabel3.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tabla");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(624, 38, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id_producto", "nombre", "precio", "stock", "id_proveedor", "id_categoria", "cadicidad"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, -1, 570));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Grafica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
