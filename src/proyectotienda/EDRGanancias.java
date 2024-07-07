/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectotienda;

import com.mysql.cj.jdbc.Driver;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JPanel;
/**
 *
 * @author chris
 */
public class EDRGanancias extends javax.swing.JPanel {

    /**
     * Creates new form EDRGanancias
     */
    public EDRGanancias() {
        initComponents();
        InversionRealizada();
        showLineChart(); 
        callEDR();
    }
    
    /*public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      barDataset.setValue( "IPhone 5s" , new Double( 20 ) );  
      barDataset.setValue( "SamSung Grand" , new Double( 20 ) );   
      barDataset.setValue( "MotoG" , new Double( 40 ) );    
      barDataset.setValue( "Nokia Lumia" , new Double( 10 ) );  
      
      //create chart
       JFreeChart piechart = ChartFactory.createPieChart("mobile sales",barDataset, false,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
        piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
        piePlot.setSectionPaint("MotoG", new Color(255,102,153));
        piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelBarChart.removeAll();
        panelBarChart.add(barChartPanel, BorderLayout.CENTER);
        panelBarChart.validate();
    }*/

    /*=============================================================================*/
    
    public void showLineChart(){   
        //create dataset for the graph
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      
        dataset.setValue(2000, "Monto", "Ene");
        dataset.setValue(1500, "Monto", "Feb");
        dataset.setValue(180, "Monto", "Mar");
        dataset.setValue(1000, "Monto", "Abr");
        dataset.setValue(800, "Monto", "May");
        dataset.setValue(250, "Monto", "Jun.");
        dataset.setValue(500, "Monto", "Jul.");
        dataset.setValue(2500, "Monto", "Agos.");
        dataset.setValue(1000, "Monto", "Sept.");
        dataset.setValue(900, "Monto", "Oct");
        dataset.setValue(2000, "Monto", "Nov");
        dataset.setValue(5000, "Monto", "Dic");
        
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("INGRESOS","MENSUALMENTE","MONTO EN MILES", 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        
        //create plot object
         CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
       // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);
        
        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204,0,51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        
         //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        panelLineChart.removeAll();
        panelLineChart.add(lineChartPanel, BorderLayout.CENTER);
        panelLineChart.validate();
        
       
    }

    /*========================================================================================*/
    /*
    public void showHistogram(){
        
         double[] values = { 95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
                            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
                            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
                            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
                            77, 44, 58, 91, 10, 67, 57, 19, 88, 84                                
                          };
 
 
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 20);
        
         JFreeChart chart = ChartFactory.createHistogram("JFreeChart Histogram","Data", "Frequency", dataset,PlotOrientation.VERTICAL, false,true,false);
            XYPlot plot= chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);

        
        
        ChartPanel barpChartPanel2 = new ChartPanel(chart);
        jPanel3.removeAll();
        jPanel3.add(barpChartPanel2, BorderLayout.CENTER);
        jPanel3.validate();
    }

    /*========================================================================================*/
    /*
    public void showBarChart(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(200, "Amount", "january");
        dataset.setValue(150, "Amount", "february");
        dataset.setValue(18, "Amount", "march");
        dataset.setValue(100, "Amount", "april");
        dataset.setValue(80, "Amount", "may");
        dataset.setValue(250, "Amount", "june");
        
        JFreeChart chart = ChartFactory.createBarChart("contribution","monthly","amount", 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        ChartPanel barpChartPanel = new ChartPanel(chart);
        jPanel1.removeAll();
        jPanel1.add(barpChartPanel, BorderLayout.CENTER);
        jPanel1.validate();
        
        
    }*/
    Conexion con1  = new Conexion();
Connection conet;
DefaultTableModel modelo;
Statement stm;
ResultSet rs;
int idc;
   
    void consultar(){
    String sql = "Select*from ventasrealizadas";
        try {
        conet = con1.getConnection();
        stm = conet.createStatement();
        rs = stm.executeQuery(sql);
        Object[] ob = new Object[8];
        modelo = (DefaultTableModel) jTable2.getModel();
        while (rs.next()) {                
                ob[0]= rs.getString("id_producto");
                ob[1]= rs.getString("nombre");
                ob[2]= rs.getString("precio");
                
                ob[3]= rs.getString("stock");
                ob[4]= rs.getString("id_proveedor");
                ob[5]= rs.getString("id_categoria");
                ob[6]= rs.getString("caducidad");
        
        modelo.addRow(ob);
        }
        jTable2.setModel(modelo);
        } catch (Exception e) {
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    void InversionRealizada(){
    try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/basetienda","root","");
    System.out.println("CONNECTED..."+con1);
    Statement stm = con1.createStatement();
    
    DefaultTableModel m = (DefaultTableModel) jTable2.getModel();
    Object ob[] = new Object[10];
    
    String sql = "Select*from ventasrealizadas";
    ResultSet rs = stm.executeQuery(sql);
    
    while (rs.next()) {            
                ob[0]= rs.getString("category");
                ob[1]= rs.getString("producto");
                ob[2]= rs.getString("cantidadvendida");
                ob[3]= rs.getString("precio");
                ob[4]= rs.getString("TotalVenta");
                //ob[5]= rs.getString("fecha");
    m.addRow(ob);
    }
    jTable2.setModel(m);
    } catch (Exception e) {
    }
    }
    
    void limpiar(){
    DefaultTableModel mod = (DefaultTableModel) jTable2.getModel();
    mod.getDataVector().removeAllElements();
    }
    
    void callEDR(){
    EdResultados pedr = new EdResultados();
        ShowPanel(pedr);
    }
    private void ShowPanel(JPanel p){
    p.setSize(1350,660);
    p.setLocation(0,0);
    
    PanelEDR.removeAll();
    PanelEDR.add(p, BorderLayout.CENTER);
    PanelEDR.revalidate();
    PanelEDR.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelLineChart = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new rojeru_san.complementos.RSTableMetro();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        PanelEDR = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setMinimumSize(new java.awt.Dimension(1070, 648));
        setPreferredSize(new java.awt.Dimension(1070, 648));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1060, 630));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("\"GANANCIAS\"");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, -1));

        panelLineChart.setLayout(new java.awt.BorderLayout());
        jPanel1.add(panelLineChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(-8, 35, 580, 300));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoria", "Producto", "Cantidad Vendida", "Precio", "Fecha"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 560, 250));

        jLabel2.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Su inversion relizada");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        jTextField1.setText("2,500 pesos semanales");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 320, -1));

        PanelEDR.setBackground(new java.awt.Color(153, 255, 255));
        PanelEDR.setLayout(new java.awt.BorderLayout());
        jPanel1.add(PanelEDR, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 480, 620));

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
    private javax.swing.JPanel PanelEDR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.RSTableMetro jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panelLineChart;
    // End of variables declaration//GEN-END:variables
}
