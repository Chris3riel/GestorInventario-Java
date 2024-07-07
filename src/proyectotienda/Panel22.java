/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectotienda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
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
public class Panel22 extends javax.swing.JFrame {

    /**
     * Creates new form Panel22
     */
    public Panel22() {
        initComponents();
        consultar();
        graficaAlmacen();
        stockMinimo();
        jTable2.setDefaultRenderer(Object.class, renderizacion);
    }
    
    //Render
    RENDER renderizacion = new RENDER();
    //CONEXXION
    Conexion con1 = new Conexion();
    Connection conet;
    DefaultTableModel model;
    DefaultTableModel model2;
    Statement st;
    ResultSet rs;
    int idc;
    
    private void ShowPanel(JPanel p){
    p.setSize(1350,660);
    p.setLocation(0,0);
    
    content.removeAll();
    content.add(p, BorderLayout.CENTER);
    content.revalidate();
    content.repaint();
    }

    void graficaAlmacen(){
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
                "ALMACEN",
                "PRODUCTOS", 
                "", 
                datos,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        
        ChartPanel panel = new ChartPanel(graficoBarra);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension (520, 470));
        
        Grafica.setLayout( new BorderLayout());
        Grafica.add(panel,BorderLayout.NORTH);
        //pack();
        repaint();
    
    }
    //CONSULTA DE TABLA PRODUCTOS
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
                productos[0]= rs.getString("id_producto");
                productos[1]= rs.getString("nombre");
                productos[2]= rs.getString("precio");
                
                productos[3]= rs.getString("stock");
                productos[4]= rs.getString("id_proveedor");
                productos[5]= rs.getString("id_categoria");
                productos[6]= rs.getString("caducidad");
                model.addRow(productos);
            }
            jTable1.setModel(model);
            } catch (Exception e) {
            }
    }
    //STOCK MINIMO
    void stockMinimo(){
        String sql = "SELECT * FROM productos";
        try {
            conet = con1.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            //Objecto con numero de columnas de la tabla:
            Object productos[] = new Object[10];
            model = (DefaultTableModel) jTable2.getModel();
            while (rs.next()) {   
                if (Integer.parseInt(rs.getString("stock"))<=10) {
                productos[0]= rs.getString("id_producto");
                productos[1]= rs.getString("nombre");
                productos[2]= rs.getString("stock");
                productos[3]= rs.getString("id_categoria");
                productos[4]= rs.getString("caducidad");
                model.addRow(productos);
                }
                }
            jTable2.setModel(model);
        } catch (Exception e) {
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        Grafica = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        PanelStock = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 700));

        content.setBackground(new java.awt.Color(0, 204, 204));
        content.setMaximumSize(new java.awt.Dimension(1366, 700));
        content.setMinimumSize(new java.awt.Dimension(1366, 700));
        content.setPreferredSize(new java.awt.Dimension(1366, 700));
        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Grafica.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout GraficaLayout = new javax.swing.GroupLayout(Grafica);
        Grafica.setLayout(GraficaLayout);
        GraficaLayout.setHorizontalGroup(
            GraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        GraficaLayout.setVerticalGroup(
            GraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );

        content.add(Grafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 184, 690, 517));

        jLabel9.setFont(new java.awt.Font("Niagara Engraved", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Picsart_22-05-31_23-33-23-113.png"))); // NOI18N
        jLabel9.setText("¡¡¡ BIENVENIDO ADMINISTRADOR!!!");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        content.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 8, 458, 120));

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setFont(new java.awt.Font("HP Simplified", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_producto", "nombre", "precio", "stock", "id_proveedor", "id_categoria", "caducidad"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTable1);

        content.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 184, 460, 163));

        jLabel10.setFont(new java.awt.Font("HP Simplified", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Tabla de almacen de productos");
        content.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 140, 460, -1));

        jLabel11.setFont(new java.awt.Font("HP Simplified", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Grafico del almacen");
        content.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 132, 731, -1));

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));
        jPanel3.setMinimumSize(new java.awt.Dimension(1366, 768));
        jPanel3.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Vamos a vender");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel2);
        jPanel2.setBounds(10, 130, 180, 30);

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Veamos nuestra ganancia");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel4);
        jPanel4.setBounds(10, 170, 180, 30);

        jLabel3.setFont(new java.awt.Font("HP Simplified", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("¿QUE DESEA HACER?");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(20, 70, 150, 21);

        jPanel8.setBackground(new java.awt.Color(255, 255, 0));

        jLabel4.setFont(new java.awt.Font("HP Simplified", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Regresar");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel8);
        jPanel8.setBounds(10, 0, 180, 30);

        jPanel5.setBackground(new java.awt.Color(0, 255, 0));

        jLabel6.setBackground(new java.awt.Color(0, 255, 255));
        jLabel6.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Ingresar Producto");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel5);
        jPanel5.setBounds(10, 220, 180, 50);

        jPanel6.setBackground(new java.awt.Color(255, 0, 0));

        jLabel7.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Eliminar Producto");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel6);
        jPanel6.setBounds(10, 270, 180, 50);

        jPanel9.setBackground(new java.awt.Color(0, 0, 204));

        jLabel8.setBackground(new java.awt.Color(0, 0, 204));
        jLabel8.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Modificar Almacen");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel9);
        jPanel9.setBounds(10, 320, 180, 50);

        content.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 0, 200, 768));

        jLabel12.setFont(new java.awt.Font("HP Simplified", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("PRODUCTOS EN STOCK MINIMO");
        content.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 354, 460, -1));

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_producto", "nombre", "stock", "id_categoria", "caducidad"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout PanelStockLayout = new javax.swing.GroupLayout(PanelStock);
        PanelStock.setLayout(PanelStockLayout);
        PanelStockLayout.setHorizontalGroup(
            PanelStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );
        PanelStockLayout.setVerticalGroup(
            PanelStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        content.add(PanelStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 391, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 51, 51));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        content.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 164, 690, 14));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 51));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 51));
        content.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 164, 460, 20));
        content.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 378, 460, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 1434, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        MOD m = new MOD();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        Delete e = new Delete();
        e.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        ADD add = new ADD();
        add.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Principal p = new Principal();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        EDR edr = new EDR();
        edr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        //PanelIrAVender pv = new PanelIrAVender();
        //ShowPanel(pv);
        PV panelVentas = new PV();
        panelVentas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        CambiarUsuario change = new CambiarUsuario();
        change.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Panel22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Panel22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Panel22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panel22.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Panel22().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Grafica;
    private javax.swing.JPanel PanelStock;
    private javax.swing.JPanel content;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
