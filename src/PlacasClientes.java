
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import navaclientesplaca.clsConexion;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jvnet.substance.SubstanceLookAndFeel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public final class PlacasClientes extends javax.swing.JFrame {

    /**
     * Creates new form PlacasClientes
     */
    public PlacasClientes() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        Calendar c2= new GregorianCalendar();
        jdtpinicio.setCalendar(c2);
        jdtpfinal.setCalendar(c2);
        
        TableColumnModel columnModel=tblPlacas.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(30);
        columnModel.getColumn(2).setPreferredWidth(220);
        columnModel.getColumn(3).setPreferredWidth(70);
        columnModel.getColumn(4).setPreferredWidth(30);
        columnModel.getColumn(5).setPreferredWidth(20);
        columnModel.getColumn(6).setPreferredWidth(70);
        columnModel.getColumn(7).setPreferredWidth(10);
        columnModel.getColumn(8).setPreferredWidth(40);
        columnModel.getColumn(9).setPreferredWidth(30);
        columnModel.getColumn(10).setPreferredWidth(20);
        columnModel.getColumn(11).setPreferredWidth(80);
        
        this.buttonGroup1.add(rbtcente1);
        this.buttonGroup1.add(rbtcente2);
        this.buttonGroup1.add(rbtall);
        
        init();
        init3();
        init2();
    }

    private void DarFormatoTblClientes(){
         TableColumnModel columnModel2=tblCliente.getColumnModel();
        
        columnModel2.getColumn(0).setPreferredWidth(15);
        columnModel2.getColumn(1).setPreferredWidth(150);
        columnModel2.getColumn(2).setPreferredWidth(20);
        columnModel2.getColumn(3).setPreferredWidth(100);
        tblCliente.updateUI();
    }
            
    public void init() {  
   
        txtCliente.addKeyListener(new KeyAdapter() {  
            @Override
            public void keyPressed(final KeyEvent e) {  
                int key = e.getKeyCode();  
                if (key == KeyEvent.VK_ENTER) {  
                    
                    try {
                        DarFormatoTblClientes();
                        reiniciarJTable(tblCliente);
                        DarFormatoTblClientes();
                        LoadGrilla2(Clientes(txtCliente.getText(),""));
                        BusquedaCliente.setVisible(true);
                        BusquedaCliente.pack();
                        BusquedaCliente.setLocationRelativeTo(null);
                    } catch (ClassNotFoundException | SQLException | ParseException ex) {
                        Logger.getLogger(PlacasClientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }  
            }  
        });  
          
//        setSize(150, 70);  
//        setLocationRelativeTo(null);  
//        setVisible(true);  
    }  
    
     public void init3() {  
   
        txtRuc.addKeyListener(new KeyAdapter() {  
            @Override
            public void keyPressed(final KeyEvent e) {  
                int key = e.getKeyCode();  
                if (key == KeyEvent.VK_ENTER) {  
                    
                    try {
                        DarFormatoTblClientes();
                        reiniciarJTable(tblCliente);
                        DarFormatoTblClientes();
                        LoadGrilla2(Clientes("", txtRuc.getText()));
                        BusquedaCliente.setVisible(true);
                     BusquedaCliente.pack();
                     BusquedaCliente.setLocationRelativeTo(null);
                    } catch (ClassNotFoundException | SQLException | ParseException ex) {
                        Logger.getLogger(PlacasClientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }  
            }  
        });
          
//        setSize(150, 70);  
//        setLocationRelativeTo(null);  
//        setVisible(true);  
    }  
    
    
    
   public void init2() {  
   
        tblCliente.addKeyListener(new KeyAdapter() {  
            @Override
            public void keyPressed(final KeyEvent e) {  
                int key = e.getKeyCode();  
                if (key == KeyEvent.VK_ENTER) {
                        String clientetbl;
                        int row=tblCliente.getSelectedRow();
                        
                         clientetbl=tblCliente.getValueAt(row,1).toString().trim();
                         txtCliente.setText(clientetbl);
                         txtRuc.setText(tblCliente.getValueAt(row,2).toString().trim());
                         BusquedaCliente.dispose();
                  
                }  
            }  
        });  
   }
    
    public ResultSet Clientes(String cliente,String ruc) throws ClassNotFoundException, SQLException, ParseException{
            String sql="select codcli,nomcli,ruccli,dircli from mst01cli where nomcli like '%"+cliente+"%' and ruccli like '%"+ruc+"%'";
            Connection cn=clsConexion.Cadena();
            Statement st=cn.createStatement();
            ResultSet rs;
            rs=st.executeQuery(sql);
            //reiniciarJTable(tblCliente);
            return rs;
            
        }
    
    private void LoadGrilla2(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException{
//        SimpleDateFormat formato;
//        formato = new SimpleDateFormat("yyyy-MM-dd");
        //reiniciarJTable(tblCliente);
        DefaultTableModel modelo= (DefaultTableModel)tblCliente.getModel();
//        Date f1;
//        Date f2;
        String fecha1,fecha2;
        while(rs.next())
        {
                    
         Object[] fila=new Object[5];
         fila[0]=rs.getString("codcli");
         fila[1]=rs.getString("nomcli");
         fila[2]=rs.getString("ruccli");
         fila[3]=rs.getString("dircli");
        
          modelo.addRow(fila);       
        }
          

        tblCliente.updateUI();
    }
   
   
    
     
    private void EnviarExcel(ResultSet rs) throws IOException{
        String rutaArchivo = System.getProperty("user.home")+"/ejemploExcelJava.xls";
        /*Se crea el objeto de tipo File con la ruta del archivo*/
        File archivoXLS = new File(rutaArchivo);
        /*Si el archivo existe se elimina*/
        if(archivoXLS.exists()) archivoXLS.delete();
        /*Se crea el archivo*/
        archivoXLS.createNewFile();
        
        /*Se crea el libro de excel usando el objeto de tipo Workbook*/
        Workbook libro = new HSSFWorkbook();
        CreationHelper createhelper=libro.getCreationHelper();
        CellStyle cellStyle=libro.createCellStyle();
        cellStyle.setDataFormat(createhelper.createDataFormat().getFormat("dd/mm/yyyy"));
        CellStyle cellStyle2=libro.createCellStyle();
        
        
        
        /*Se inicializa el flujo de datos con el archivo xls*/
        FileOutputStream archivo = new FileOutputStream(archivoXLS);
        
        /*Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente*/
        Sheet hoja = libro.createSheet("ClientesPlacas");
        
        Font fuente =libro.createFont();
        fuente.setFontHeightInPoints((short)13);
        fuente.setFontName("Arial");
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        Font fuente2 =libro.createFont();
        fuente.setFontHeightInPoints((short)13);
        fuente.setFontName("Arial");
        //fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        Row Enc=hoja.createRow(2);
        Cell celda=Enc.createCell(0);
        
        celda.setCellValue("PLACA DE VEHICULOS POR CLIENTES");
        
        
        
        
        
        
        
    }
    
     
        public ResultSet ClientesRes(String cliente,String ruc) throws ClassNotFoundException, SQLException, ParseException{
            String sql="select codcli,nomcli,ruccli,dircli from mst01cli where nomcli like '%"+cliente+"%' and ruccli like '%"+ruc+"%'";
            Connection cn=clsConexion.Cadena();
            Statement st=cn.createStatement();
            ResultSet rs;
            rs=st.executeQuery(sql);
            //reiniciarJTable(tblCliente);
            return rs;
            
        }
    
    
    private ResultSet DevolverRes(String cliente,String ruc,String fec1,String fec2,String opc,String opc2,String pla) throws ClassNotFoundException, SQLException{
        String sql="select fecha,codcli,nomcli,ruccli,case when Codcdv='01' then 'CONT' ELSE 'CRED' END codcdv,case when cdocu='01' then 'FT'\n" +
                        " when cdocu='03' then 'BV' when cdocu='12' then 'TK' else 'VR' end cdocu,ndocu,mone,totn,tcam,codalm,plaveh from "+opc+"\n"+ 
                        " where fecha between '"+fec1+"' and '"+fec2+"' and nomcli like '%"+cliente+"%' and ruccli like '%"+ruc+"%' "+ opc2 +" "+ pla;
        Connection cn=clsConexion.Cadena();
        
        Statement st=cn.createStatement();
         ResultSet rs=st.executeQuery(sql);
         return rs;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        BusquedaCliente = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jdtpfinal = new com.toedter.calendar.JDateChooser();
        jdtpinicio = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        btnConsulta = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        rbtcente2 = new javax.swing.JRadioButton();
        rbtcente1 = new javax.swing.JRadioButton();
        rbtall = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPlacas = new javax.swing.JTable();
        btnExcel = new javax.swing.JButton();
        txtRuc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        chkPlaca = new javax.swing.JCheckBox();

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codi.", "Cliente", "RUC", "Direccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblCliente);

        javax.swing.GroupLayout BusquedaClienteLayout = new javax.swing.GroupLayout(BusquedaCliente.getContentPane());
        BusquedaCliente.getContentPane().setLayout(BusquedaClienteLayout);
        BusquedaClienteLayout.setHorizontalGroup(
            BusquedaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
        );
        BusquedaClienteLayout.setVerticalGroup(
            BusquedaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BusquedaClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jdtpfinal.setToolTipText("Fecha Final");
        jdtpfinal.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jdtpinicio.setToolTipText("Fecha de Inicio");
        jdtpinicio.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel1.setText("CLIENTES");
        jLabel1.setToolTipText("");

        txtCliente.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClienteKeyPressed(evt);
            }
        });

        btnConsulta.setText("Buscar");
        btnConsulta.setToolTipText("Consulta de comprobantes");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        rbtcente2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rbtcente2.setText("Centenario II");

        rbtcente1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rbtcente1.setText("Centenario I");

        rbtall.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rbtall.setSelected(true);
        rbtall.setText("Todos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtcente1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(rbtcente2)
                .addGap(53, 53, 53)
                .addComponent(rbtall)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtcente2)
                    .addComponent(rbtcente1)
                    .addComponent(rbtall))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblPlacas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Cod", "Cliente", "R. U. C.", "Condi.", "T.Doc.", "N. Doc.", "Mon", "Total", "T.C.", "Alm.", "Placa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlacas.setToolTipText("");
        jScrollPane1.setViewportView(tblPlacas);
        tblPlacas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnExcel.setText("Excel");

        txtRuc.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel2.setText("R. U. C.");
        jLabel2.setToolTipText("");

        jLabel3.setText("Inicio");
        jLabel3.setToolTipText("");

        jLabel4.setText("Final");
        jLabel4.setToolTipText("");

        chkPlaca.setText("Incluir sin placa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCliente)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtRuc))
                                                    .addGap(33, 33, 33)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jdtpinicio, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel3)
                                                            .addComponent(jLabel4))
                                                        .addComponent(jdtpfinal, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(chkPlaca))
                                            .addGap(6, 6, 6)))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 388, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jdtpinicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdtpfinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPlaca)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String ConvertDate(JDateChooser jd) {
    SimpleDateFormat formato;
    formato = new SimpleDateFormat("yyyyMMdd");
    if (jd.getDate()!=null){
    return formato.format(jd.getDate());
    }else{
    return null;
}
}
    
     public static void reiniciarJTable(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
 
//        TableColumnModel modCol = Tabla.getColumnModel();
//        while(modCol.getColumnCount()>0)modCol.removeColumn(modCol.getColumn(0));
    }
    
     ResultSet rs1;
    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        reiniciarJTable(tblPlacas);
        
        try {
         String pl="";
         if (!chkPlaca.isSelected()){
             pl="and plaveh<>''";
         }
            
        if (rbtcente1.isSelected()){
             rs1=DevolverRes(txtCliente.getText(), txtRuc.getText(),ConvertDate(jdtpinicio), ConvertDate(jdtpfinal), "[servidor].bdnava01.dbo.mst01fac m", "and codalm='01'",pl);
        }
        else if (rbtcente2.isSelected()){
             rs1=DevolverRes(txtCliente.getText(), txtRuc.getText(),ConvertDate(jdtpinicio), ConvertDate(jdtpfinal), "[server2].bdnava01.dbo.mst01fac m", "and codalm='02'",pl);
        }
        else
        {
             rs1=DevolverRes(txtCliente.getText(), txtRuc.getText(),ConvertDate(jdtpinicio), ConvertDate(jdtpfinal), "[servidor].bdnava01.dbo.mst01fac m", "",pl);
        }
            //ResultSetMetaData rsm=rs1.getMetaData();
            LoadGrilla(rs1);
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(PlacasClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultaActionPerformed

    Busqueda ventana2;
    private void txtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyPressed
//            if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
//                try {
//                    ventana2.setVisible(true);
//                    ventana2.Clientes(txtCliente.getText(),txtRuc.getText());
//                    
//                } catch (ClassNotFoundException | SQLException ex) {
//                    Logger.getLogger(PlacasClientes.class.getName()).log(Level.SEVERE, null, ex);
//                }
//               
//                
//              }
    }//GEN-LAST:event_txtClienteKeyPressed
     
private void LoadGrilla(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException{
//        SimpleDateFormat formato;
//        formato = new SimpleDateFormat("yyyy-MM-dd");
        DefaultTableModel modelo= (DefaultTableModel)tblPlacas.getModel();
//        Date f1;
//        Date f2;
        String fecha1,fecha2;
//        f1=(Date) jdtInicio.getDate();
//        f2=(Date) jdtFin.getDate();
        fecha1=ConvertDate(jdtpinicio);
        fecha2=ConvertDate(jdtpfinal);
        
        while(rs.next())
        {
                    
         Object[] fila=new Object[12];
         fila[0]=rs.getDate("fecha");
         fila[1]=rs.getString("codcli");
         fila[2]=rs.getString("nomcli");
         fila[3]=rs.getString("ruccli");
         fila[4]=rs.getString("codcdv");
         fila[5]=rs.getString("cdocu");
         fila[6]=rs.getString("ndocu");
         fila[7]=rs.getString("mone");
         fila[8]=rs.getDouble("totn");
         fila[9]=rs.getDouble("tcam");
         fila[10]=rs.getString("codalm");
         fila[11]=rs.getString("plaveh");
         modelo.addRow(fila);       
        }
          tblPlacas.updateUI();
    }
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlacasClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                 PlacasClientes.setDefaultLookAndFeelDecorated(true);
                 SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.CremeCoffeeSkin");
                 new PlacasClientes().setVisible(true);
                
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BusquedaCliente;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkPlaca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdtpfinal;
    private com.toedter.calendar.JDateChooser jdtpinicio;
    private javax.swing.JRadioButton rbtall;
    private javax.swing.JRadioButton rbtcente1;
    private javax.swing.JRadioButton rbtcente2;
    public javax.swing.JTable tblCliente;
    private javax.swing.JTable tblPlacas;
    public javax.swing.JTextField txtCliente;
    public javax.swing.JTextField txtRuc;
    // End of variables declaration//GEN-END:variables
}
