/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javafx.scene.control.CheckBox;
import javax.swing.JOptionPane;
import model.ModuloConexao;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ivanete
 */
public class TelaRelatorio extends javax.swing.JInternalFrame {

    Connection  conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    int checkFiltro = 0;
    
    public TelaRelatorio() {
        initComponents();
        conexao = ModuloConexao.connector();
    }

    
    
    private void imprimirGeral() {
    
     try {
         
     HashMap filtro = new HashMap();
     filtro.put("data_abertura", (txtabertura.getText()));
     JasperReport report = JasperCompileManager.compileReport("src/relatorios/relatoriogeral.jrxml");
     JasperPrint print = JasperFillManager.fillReport(report, filtro, conexao);
     JasperViewer viewer = new JasperViewer(print, false);
     viewer.setVisible(true);
     viewer.setTitle("Registro de Ocorrências");
     
     } catch (Exception e) {
     JOptionPane.showMessageDialog(null, e);
     }
     }
    
    private void imprimirPorQth() {
    
     try {
         
     HashMap filtro = new HashMap();
     filtro.put("qth", (txtabertura.getText()));
     JasperReport report = JasperCompileManager.compileReport("src/relatorios/relatorioqth.jrxml");
     JasperPrint print = JasperFillManager.fillReport(report, filtro, conexao);
     JasperViewer viewer = new JasperViewer(print, false);
     viewer.setVisible(true);
     viewer.setTitle("Registro de Ocorrências");
     
     } catch (Exception e) {
     JOptionPane.showMessageDialog(null, e);
     }
     }
    
    private void imprimirPorSituacao() {
    
     try {
         
     HashMap filtro = new HashMap();
     filtro.put("situacao", (txtabertura.getText()));
     JasperReport report = JasperCompileManager.compileReport("src/relatorios/relatoriosituacao.jrxml");
     JasperPrint print = JasperFillManager.fillReport(report, filtro, conexao);
     JasperViewer viewer = new JasperViewer(print, false);
     viewer.setVisible(true);
     viewer.setTitle("Registro de Ocorrências");
     
     } catch (Exception e) {
     JOptionPane.showMessageDialog(null, e);
     }
     }
    
    private void total_registros(){
            
            String sql = "select count(*) from tb_chamados";
            
            try{
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    String soma = rs.getString("count(*)");
                txtTotalR1.setText(soma);
                }
                
            }catch(Exception e){
               
            }
        }
   
    private void total_registros_ano(){
        txtDataAno.setVisible(false);
        pegarAno();
            String sql = "select count(*) from tb_chamados where data_abertura like ?";
            
            try{
                conexao = ModuloConexao.connector();
                pst = conexao.prepareStatement(sql);
                pst.setString(1,"%" + txtDataAno.getText() + "%");
                rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    String soma = rs.getString("count(*)");
                txtTotalRano.setText(soma);
                }
                
            }catch(Exception e){
               
            }
        }
    
    private void total_registros_mes(){
        txtDataMes.setVisible(false);
        pegarAno();
            String sql = "select count(*) from tb_chamados where data_abertura like ?";
            
            try{
                conexao = ModuloConexao.connector();
                pst = conexao.prepareStatement(sql);
                pst.setString(1,"%" + txtDataMes.getText() + "%");
                rs = pst.executeQuery();
                
                if(rs.next()){
                    
                    String soma = rs.getString("count(*)");
                txtTotalRmes.setText(soma);
                }
                
            }catch(Exception e){
               
            }
        }
    
    private void pegarAno(){
        Date date = new Date();
        SimpleDateFormat getYearFormat = new SimpleDateFormat("yy");
        String currentYear = getYearFormat.format(date);
        txtDataAno.setText(currentYear);
    }
    
    private void pegarmes(){
        Date date = new Date();
        SimpleDateFormat getYearFormat = new SimpleDateFormat("/"+"MM");
        String currentYear = getYearFormat.format(date);
        txtDataMes.setText(currentYear);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtabertura = new javax.swing.JTextField();
        BTRGERAL = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTotalRano = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtTotalR1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtTotalRmes = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDataMes = new javax.swing.JTextField();
        txtDataAno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbData = new javax.swing.JCheckBox();
        cbQth = new javax.swing.JCheckBox();
        cbSit = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Relatório");
        setMaximumSize(new java.awt.Dimension(837, 586));
        setMinimumSize(new java.awt.Dimension(837, 586));
        setPreferredSize(new java.awt.Dimension(837, 586));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        txtabertura.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtabertura.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtabertura.setPreferredSize(new java.awt.Dimension(300, 22));
        txtabertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaberturaActionPerformed(evt);
            }
        });

        BTRGERAL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BTRGERAL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pdf-2-30.png"))); // NOI18N
        BTRGERAL.setText("Gerar Relatório PDF");
        BTRGERAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTRGERALActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Filtrar por:");

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Relatório");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addContainerGap(705, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel2.setText("(Para trazer todos os registros por ano, digite por exemplo: 23, para o ano de 2023)");

        jLabel4.setText("(Para trazer todos os registros por mês, digite por exemplo: 02/23, para o mês 02 de 2023)");

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        txtTotalRano.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtTotalRano.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalRano.setText("00");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Registros deste ano");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(txtTotalRano)
                .addGap(80, 80, 80))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(29, 29, 29)
                .addComponent(txtTotalRano)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jLabel5.setText("(Para trazer todos os registros por dia específico, digite por exemplo: 01/02/23, para o dia 01/02/2023)");

        jPanel3.setBackground(new java.awt.Color(0, 102, 0));

        txtTotalR1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtTotalR1.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalR1.setText("00");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total de Registros");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(txtTotalR1)
                .addGap(80, 80, 80))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(29, 29, 29)
                .addComponent(txtTotalR1)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(102, 0, 0));

        txtTotalRmes.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtTotalRmes.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalRmes.setText("00");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Registros deste mês");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTotalRmes)
                .addGap(80, 80, 80))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(29, 29, 29)
                .addComponent(txtTotalRmes)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        txtDataMes.setText("jTextField1");
        txtDataMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataMesActionPerformed(evt);
            }
        });

        txtDataAno.setText("jTextField1");
        txtDataAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataAnoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Filtro:");

        cbData.setText("Data");
        cbData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbDataMouseClicked(evt);
            }
        });
        cbData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDataActionPerformed(evt);
            }
        });

        cbQth.setText("QTH (Endereço)");
        cbQth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbQthMouseClicked(evt);
            }
        });
        cbQth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbQthActionPerformed(evt);
            }
        });

        cbSit.setText("Situação");
        cbSit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbSitMouseClicked(evt);
            }
        });
        cbSit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSitActionPerformed(evt);
            }
        });

        jLabel10.setText("(Para trazer todos os registros por qth, digite por exemplo: centro, para as ocorrências realizadas no bairro centro)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(txtDataAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191)
                .addComponent(txtDataMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel4))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cbData)
                .addGap(18, 18, 18)
                .addComponent(cbQth)
                .addGap(18, 18, 18)
                .addComponent(cbSit))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(txtabertura, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BTRGERAL, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDataAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbData)
                    .addComponent(cbQth)
                    .addComponent(cbSit))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtabertura, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTRGERAL, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        setBounds(0, 0, 837, 586);
    }// </editor-fold>//GEN-END:initComponents

    private void txtaberturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaberturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaberturaActionPerformed

    private void BTRGERALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTRGERALActionPerformed

    if(checkFiltro==0){
    imprimirGeral();
    } else if(checkFiltro==1){
        imprimirGeral();
    } else if(checkFiltro==2){
        imprimirPorQth();
    } else if(checkFiltro==3){
        imprimirPorSituacao();
    }
    }//GEN-LAST:event_BTRGERALActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        pegarAno();
        pegarmes();
        total_registros();
        total_registros_ano();
        total_registros_mes();
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtDataMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataMesActionPerformed

    private void txtDataAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataAnoActionPerformed

    private void cbQthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbQthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbQthActionPerformed

    private void cbSitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSitActionPerformed

    private void cbDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDataActionPerformed
        
    }//GEN-LAST:event_cbDataActionPerformed

    private void cbDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbDataMouseClicked
        if(cbData.isSelected()) {
            checkFiltro = 1;
            cbQth.setSelected(false);
            cbSit.setSelected(false);
        }
    }//GEN-LAST:event_cbDataMouseClicked

    private void cbQthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbQthMouseClicked
        if(cbQth.isSelected()) {
            checkFiltro = 2;
            cbData.setSelected(false);
            cbSit.setSelected(false);
        }
    }//GEN-LAST:event_cbQthMouseClicked

    private void cbSitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbSitMouseClicked
        if(cbSit.isSelected()) {
            checkFiltro = 3;
            cbQth.setSelected(false);
            cbData.setSelected(false);
        }
    }//GEN-LAST:event_cbSitMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTRGERAL;
    private javax.swing.JCheckBox cbData;
    private javax.swing.JCheckBox cbQth;
    private javax.swing.JCheckBox cbSit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtDataAno;
    private javax.swing.JTextField txtDataMes;
    private javax.swing.JLabel txtTotalR1;
    private javax.swing.JLabel txtTotalRano;
    private javax.swing.JLabel txtTotalRmes;
    private javax.swing.JTextField txtabertura;
    // End of variables declaration//GEN-END:variables
}
