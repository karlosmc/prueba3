
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import navaclientesplaca.clsConexion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public final class Busqueda extends javax.swing.JFrame {

    /**
     * Creates new form Busqueda
     */
    ResultSet rs;
    
    
    
    
    public Busqueda() throws SQLException, ClassNotFoundException, ParseException {
        initComponents();
        this.setLocationRelativeTo(null);
        
        TableColumnModel columnModel=tblCliente.getColumnModel();
        
        columnModel.getColumn(0).setPreferredWidth(15);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(20);
        columnModel.getColumn(3).setPreferredWidth(100);
        tblCliente.updateUI();
        init2();
        //LoadGrilla(rs);
                
    }
    
    String cliente;
    
    public void cerrar(ActionEvent e){
                dispose();
    }
            
    
       public void init2() {  
   
        tblCliente.addKeyListener(new KeyAdapter() {  
            @Override
            public void keyPressed(final KeyEvent e) {  
                int key = e.getKeyCode();  
                if (key == KeyEvent.VK_ENTER) {
                        
                        int row=tblCliente.getSelectedRow();
                        
                         cliente=tblCliente.getValueAt(row,1).toString();
                        dispose();
                   
                }  
            }  
        });  
          
//        setSize(150, 70);  
//        setLocationRelativeTo(null);  
//        setVisible(true);  
    }  
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        jScrollPane1.setViewportView(tblCliente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        public void Clientes(String cliente,String ruc) throws ClassNotFoundException, SQLException, ParseException{
            String sql="select codcli,nomcli,ruccli,dircli from mst01cli where nomcli like '%"+cliente+"%' and ruccli like '%"+ruc+"%'";
            Connection cn=clsConexion.Cadena();
            Statement st=cn.createStatement();
            rs=st.executeQuery(sql);
            //reiniciarJTable(tblCliente);
            LoadGrilla();
            
        }
        
          public static void reiniciarJTable(javax.swing.JTable Tabla)
          {
            DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
            while(modelo.getRowCount()>0)modelo.removeRow(0);
          }
          
 private void LoadGrilla() throws SQLException, ClassNotFoundException, ParseException{
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
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Busqueda().setVisible(true);
                } catch (SQLException | ClassNotFoundException | ParseException ex) {
                    Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblCliente;
    // End of variables declaration//GEN-END:variables
}
