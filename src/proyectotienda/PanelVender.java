/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectotienda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author chris
 */
public class PanelVender extends javax.swing.JPanel {

    /**
     * Creates new form PanelVender
     */
    public PanelVender() {
        initComponents();
         
       // JOptionPane.showMessageDialog(null,"NOTA: Para agregar productos al carrito\n selecionar solo el producto que desea y digitar la cantidad a comprar");
        //datos_tabla(); //iniciamos el metodo para mostrar los datos en la tabla
       // limpiar();
        consultar();
       // datos_tabla(); //iniciamos el metodo para mostrar los datos en la tabla
    }
 
//VENDER 
    String stock;
    double precio = 0;
void venta(){
Conexion con1 = new Conexion();
Connection conet;
DefaultTableModel modelo;
Statement stm;
ResultSet rsss;
int idc;
        try {
        /**if((jTextField1.getText().equals(""))||(jTextField2.getText().equals(""))){
            JOptionPane.showMessageDialog(null,"No ingrese campos vacios");
        }*/if(((Integer.parseInt(stock)-Integer.parseInt(jTextField2.getText())))<=10){
            JOptionPane.showMessageDialog(null, "EL STOCK ESTA POR TERMINAR FAVOR DE IR A SURTIR MAS");
        }if(((Integer.parseInt(stock)-Integer.parseInt(jTextField2.getText())))<=0){
            JOptionPane.showMessageDialog(null, "EL STOCK ESTA VACIO");
        }
        else{
         String sql = "UPDATE productos SET stock = '"+(Integer.parseInt(stock)-Integer.parseInt(jTextField2.getText()))+"' WHERE nombre ='"+jTextField1.getText()+"'";
        conet = con1.getConnection();
        stm = conet.createStatement();
        stm.executeUpdate(sql);
        
        LocalDate today = LocalDate.now();
         String sqll = "INSERT INTO ventasrealizadas(producto, cantidadvendida, precio, fecha) VALUES ('"+jTextField1.getText()+"','"+jTextField2.getText()+"','"+(precio*Double.parseDouble(jTextField2.getText()))+"','"+String.valueOf(today)+"')";
        conet = con1.getConnection();
        stm = conet.createStatement();
        stm.executeUpdate(sqll);
        
        JOptionPane.showMessageDialog(null, "AH HECHO UNA VENTA");
        registro();
        limpiar();
        }
        consultar();
        } catch (Exception e) {
        }
        }
    

Conexion con1  = new Conexion();
Connection conet;
DefaultTableModel model;
Statement st;
ResultSet rsss;
int idc;
double cantidadDeProducto;

Object productos[] = new Object[10];
//consultar
   void consultar(){
    String sql = "SELECT * FROM productos";
        try {
            conet = con1.getConnection();
            st = conet.createStatement();
            rsss = st.executeQuery(sql);
            
     //Objecto con numero de columnas de la tabla:
            
            model = (DefaultTableModel) jTable1.getModel();
            while (rsss.next()) {                
                productos[0]= rsss.getString("id_producto");
                productos[1]= rsss.getString("nombre");
                productos[2]= rsss.getString("precio");
                
                productos[3]= rsss.getString("stock");
                productos[4]= rsss.getString("id_proveedor");
                productos[5]= rsss.getString("id_categoria");
                productos[6]= rsss.getString("caducidad");
                model.addRow(productos);
            }
        jTable1.setModel(model);
            
        } catch (Exception e) {
        }
    }
    //LIMPIAr
    void limpiar(){
    for (int i = 0; i < jTable1.getRowCount(); i++) {
        //productos = null;
        //System.gc();
        model.removeRow(i);
        jTable1.removeAll();
        //model.removeTableModelListener(jTable1);
    }
    }
    
    //AÑADIENDO A LA LISTA
    DefaultListModel <String> l = new DefaultListModel<>(); double totalAPagar = 0;
    void Añadirlista(){
        l.addElement("Producto: "+jTextField1.getText()+"  Cantidad: "+(Double.parseDouble(jTextField2.getText())));
      totalAPagar += precio*Double.parseDouble(jTextField2.getText());
      
      jList1.setModel(l);
    }
    //ELIMINAR DE LA LISTA
    void EliminarDeLaLista(){
        int seleccion = jList1.getSelectedIndex();
        l.remove(seleccion);
        jList1.remove(seleccion);  
    }
    //BAseRegistro
    void registro(){
        Conexion c  = new Conexion();
        Connection conete;
        DefaultTableModel model;
        Statement sta;
        ResultSet rss;
        try {
            LocalDate today = LocalDate.now();
            String sqlin = "INSERT INTO ventasrealizadas(producto, cantidadvendida, precio, fecha) VALUES ('"+jTextField1.getText()+"','"+jTextField2.getText()+"','"+(precio*Double.parseDouble(jTextField2.getText()))+"','hoy')";
            conete = c.getConnection();
            sta = conete.createStatement();
            rss = sta.executeQuery(sqlin);
        } catch (Exception e) {
        }
    }
    //BusquedaDelProducto
    DefaultTableModel modelb;
    Connection conn;
    Statement satement;
    
    void buscar(String texto){
        try {
        String titulos [] = {"id_producto","nombre","precio","stock","id_proveedor","id_categoria","caducidad"}; 
        String filtrar = ""+texto+"_%";
        String SQL = "Select * from productos where id_producto like "+'"'+filtrar+'"';
            System.out.println(SQL);
        modelb = new DefaultTableModel(null,titulos);
        satement = conn.prepareStatement(SQL);
        ResultSet re = satement.executeQuery(SQL);
        String fila [] = new String[10];
            while (re.next()) {                
                fila[0] = re.getString("id_producto");
                fila[1] = re.getString("nombre");
                fila[2] = re.getString("precio");
                fila[3] = re.getString("stock");
                fila[4] = re.getString("id_proveedor");
                fila[5] = re.getString("id_categoria");
                fila[6] = re.getString("caducidad");
                modelb.addRow(fila);
            }
            jTable2.setModel(modelb);
            re.close();
            satement.close();
        } catch (Exception e) {
        }
    }
//PARTE DE LA BUSQUEDA POR COINCIDENCIA
    String[] Titulos = {"ID", "NOMBRE", "PRECIO", "STOCK", "ID_PROVEEDOR","ID_CATEGORIA","CADUCIDAD"}; //Arreglo de los titulos para la tabla
    DefaultTableModel dtm_datos = new DefaultTableModel(); //creamos  un modelo para la taba de datos
    TableRowSorter<TableModel> trs; //Hacemos el table row sorter para poder ordenar la tabla al presionar los encabezados de la misma
    ResultSet rs;  //el result set es el resultado de la consulta que mandamos por sql
    String[][] M_datos;  //iniciamos una matriz donde pasaremos los datos de sql
    Conectar cc = new Conectar();   //iniciamos un objeto que se encargara de la conexion de datos
    Connection cn = cc.conectar();
    
    
    
     private void datos_tabla() {
        int contador = 0;  //creamos un contador para saber el numero de datos que obtendremos de la tabla datos de sql
        try { //para las consultas sql siempre vamos a ocupar un try catch por su ocurre un error
            Statement st_cont = cn.createStatement(); //el statement nos ayuda a procesar una sentencia sql 
            ResultSet rs_cont = st_cont.executeQuery("SELECT COUNT(*) FROM productos"); // asignamos los datos obtenidos de la consulta al result set
             if (rs_cont.next()) {
                contador = rs_cont.getInt(1);
            }
//lo anterior fue solo para conocer el numero de datos que manejariamos esto mediante logra gracias con count de sql y con el  * le decimos que nos cuenta todas las filas de la tabla

            
            Statement st = cn.createStatement(); //ahora vamos a  hacer lo mismo solo que esta vez no obtendremos el numero de filas en la tabla
            rs = st.executeQuery("SELECT id_producto,nombre,precio,stock,id_proveedor,id_categoria,caducidad FROM productos"); //aora obtendremos los datos de la tabla para mostrarlos en el jtable
            
            int cont = 0; //el contador nos ayudara para movernos en las filas de la matriz mientras que los numeros fijos (0,1,2,3) nos moveran por las 4 columnas que seran el id, nombre, etc
            M_datos = new String[contador][7]; //definimos el tamaño de la matriz 
            while (rs.next()) { //el while nos ayudara a recorrer los datos obtenidos en la consulta anterior y asignarlos a la matriz  
               // M_datos[cont][0] = rs.getString("id");    //agregamos los datos a la table
                M_datos[cont][0] = rs.getString("id_producto");
                M_datos[cont][1] = rs.getString("nombre");
                M_datos[cont][2] = rs.getString("precio");
                M_datos[cont][3] = rs.getString("stock");
                M_datos[cont][4] = rs.getString("id_proveedor");
                M_datos[cont][5] = rs.getString("id_categoria");
                M_datos[cont][6] = rs.getString("caducidad");
                cont = cont + 1; //avanzamos una posicion del contador para que pase a la siguiente fila
            }

        } catch (SQLException ex) {
            Logger.getLogger(PanelVender.class.getName()).log(Level.SEVERE, null, ex); //si llegara a ocurrir un error ya se  una mala consulta o mala conexion aqui nos lo mostraria
        }

        dtm_datos = new DefaultTableModel(M_datos, Titulos) { //ahora agregaremos la matriz y los titulos al modelo de tabla
            public boolean isCellEditable(int row, int column) {//este metodo es muy util si no quieren que editen su tabla, 
                return false;  //si quieren modificar los campos al dar clic entonces borren este metodo
            }
        };
        jTable2.setModel(dtm_datos); //ahora el modelo que ya tiene tanto los datos como los titulos lo agregamos a la tabla
        trs = new TableRowSorter<>(dtm_datos); //iniciamos el table row sorter para ordenar los datos (esto es si gustan)
        jTable2.setRowSorter(trs); //y lo agregamos al jtable
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(1366, 700));
        setMinimumSize(new java.awt.Dimension(1366, 700));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1366, 700));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("HP Simplified", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("\"VENTANA DE VENTAS\"");

        jTable1.setBackground(new java.awt.Color(102, 255, 255));
        jTable1.setFont(new java.awt.Font("HP Simplified", 1, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_producto", "nombre", "precio", "stock", "id_proveedor", "id_categoria", "caducidad"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tabla del almacen:");

        jLabel3.setFont(new java.awt.Font("HP Simplified", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("\"AGREGAR AL CARRITO\"");

        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre del producto:");

        jLabel5.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cantidad:");

        jTextField2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Lista de lo que se encuentra en carrito:");

        jList1.setBackground(new java.awt.Color(255, 255, 0));
        jScrollPane2.setViewportView(jList1);

        jButton1.setText("VENDER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TOTAL A PAGAR:");

        jTextField3.setText("jTextField3");

        jLabel8.setText("Buscqueda:");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", ""
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(126, 126, 126)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jButton1))
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304)
                .addComponent(jLabel2))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(490, 490, 490)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(15, 15, 15)
                        .addComponent(jLabel6)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTextField3.setText(String.valueOf(precio*Double.parseDouble(jTextField2.getText())));
        //jTextField3.setText(String.valueOf(totalAPagar));
        Añadirlista();
        venta();
        registro();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
       jTextField1.setText(""); jTextField2.setText("");
       int selecc = jTable1.rowAtPoint(evt.getPoint());
       jTextField1.setText(String.valueOf(jTable1.getValueAt(selecc, 1)));
       
       int stocker = jTable1.rowAtPoint(evt.getPoint());
       stock = String.valueOf(jTable1.getValueAt(stocker,3));
       
       int cash = jTable1.rowAtPoint(evt.getPoint());
        precio += Double.parseDouble(jTable1.getValueAt(cash, 2).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
      int valor = 0;
        int cont = 0;
        String aux = "" + jTextField1.getText();//aqui obtenemos cada letra que ingresemos en el textfield en tiempo real
            try {
                Statement st_cont = cn.createStatement(); //hacemos lo mismo que con el metodo mostrar, buscamos el numero de filas dela tabla
                rs = st_cont.executeQuery("SELECT COUNT(*) FROM productos WHERE nombre LIKE'" + jTextField1.getText() + "%'");//solo que esta ves usamos like
                if (rs.next()) {// like nos ayudara a buscar nombres que tengan similitudes con lo que estamos escribiendo en el texfield
                    valor = rs.getInt(1); //una vez que obtenimos el numero de filas continuamos a sacar  el valor que buscamos
                }
                
                    M_datos = new String[valor][7];
                    rs = st_cont.executeQuery("SELECT * FROM productos WHERE nombre LIKE'" + jTextField1.getText() + "%'"); //aqui es donde buscaremos a a la persona en especifico o las personas
                    while (rs.next()) {
                         M_datos[cont][0] = rs.getString("id_producto");
                M_datos[cont][1] = rs.getString("nombre");
                M_datos[cont][2] = rs.getString("precio");
                M_datos[cont][3] = rs.getString("stock");
                M_datos[cont][4] = rs.getString("id_proveedor");
                M_datos[cont][5] = rs.getString("id_categoria");
                M_datos[cont][6] = rs.getString("caducidad");
                        cont = cont + 1;
                    }
                    dtm_datos = new DefaultTableModel(M_datos, Titulos) {
                        public boolean isCellEditable(int row, int column) {//este metodo es muy util si no quieren que editen su tabla, 
                return false;  //si quieren modificar los campos al dar clic entonces borren este metodo
            }
                    };
                    jTable2.setModel(dtm_datos);
                    trs = new TableRowSorter<>(dtm_datos);
                    jTable2.setRowSorter(trs);
              
            } catch (Exception e) {
            }
    
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
         jTextField1.setText(""); jTextField2.setText("");
       int selecc = jTable2.rowAtPoint(evt.getPoint());
       jTextField1.setText(String.valueOf(jTable2.getValueAt(selecc, 1)));
       
       int stocker = jTable2.rowAtPoint(evt.getPoint());
       stock = String.valueOf(jTable2.getValueAt(stocker,3));
       
       int cash = jTable2.rowAtPoint(evt.getPoint());
        precio += Double.parseDouble(jTable2.getValueAt(cash, 2).toString());
    }//GEN-LAST:event_jTable2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
