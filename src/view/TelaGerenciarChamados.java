/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.*;
import java.util.HashMap;
import javax.swing.JOptionPane;
import model.ModuloConexao;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class TelaGerenciarChamados extends javax.swing.JInternalFrame {

    Connection  conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaGerenciarChamados() {
        initComponents();
        conexao = ModuloConexao.connector();
    }
    
    private void setar_campos(){
        int setar = tbChamado.getSelectedRow();
        txtID.setText(tbChamado.getModel().getValueAt(setar, 0).toString());
        setar_outros();
    }
    
    private void setar_outros(){
        String sql = "select * from tb_chamados where id_chamado = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtID.getText());
            rs = pst.executeQuery();
            
            if(rs.next()){
                txtSituacao.setText(rs.getString(7));
                txtObservacao.setText(rs.getString(8));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar(){
        String sql = "select id_chamado as Código, data_abertura as Abertura, data_saida as Saída, nome_paciente as Paciente, qth as QTH, ndo as Natureza, tarm as TARM, mr as MR, cond as COND, enf as ENF, tecenf as TEC, dr as MÉDICO, transporte as Transporte from tb_Chamados where nome_paciente like ? or tarm like ? or mr like ? or cond like ? or enf like ? or qth like ? or dr like ? or situacao like ? or transporte like ? ORDER BY id_chamado DESC";
        try {
            conexao = ModuloConexao.connector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            pst.setString(2, txtPesquisar.getText() + "%");
            pst.setString(3, txtPesquisar.getText() + "%");
            pst.setString(4, txtPesquisar.getText() + "%");
            pst.setString(5, txtPesquisar.getText() + "%");
            pst.setString(6, txtPesquisar.getText() + "%");
            pst.setString(7, txtPesquisar.getText() + "%");
            pst.setString(8, txtPesquisar.getText() + "%");
            pst.setString(9, txtPesquisar.getText() + "%");
            
            rs = pst.executeQuery();
            tbChamado.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        setar_outros();
    }
    
    private void imprimir() {
    
     try {
         
     HashMap filtro = new HashMap();
     filtro.put("id_chamado", Integer.parseInt(txtID.getText()));
     JasperReport report = JasperCompileManager.compileReport("src/relatorios/chamado.jrxml");
     JasperPrint print = JasperFillManager.fillReport(report, filtro, conexao);
     JasperViewer viewer = new JasperViewer(print, false);
     viewer.setVisible(true);
     viewer.setTitle("Registro de Ocorrências");
     
     } catch (Exception e) {
     JOptionPane.showMessageDialog(null, e);
     }
     }
    
    private void salvar(){
        String sql = "update tb_chamados set situacao =?, observacao =? where id_chamado =?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtSituacao.getText());
            pst.setString(2, txtObservacao.getText());
            pst.setString(3, txtID.getText());
            
            
            if(txtSituacao.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "O campo Situação é obrigatório!");
            } else {
                int adicionado = pst.executeUpdate();
                if(adicionado > 0){
                    JOptionPane.showMessageDialog(null, "Informações atualizadas!");
                    
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);    
    }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbChamado = new javax.swing.JTable();
        txtSituacao = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        btBuscar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gerenciar Chamado");
        setMaximumSize(new java.awt.Dimension(837, 586));
        setMinimumSize(new java.awt.Dimension(837, 586));
        setPreferredSize(new java.awt.Dimension(837, 586));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-selecionado-20.png"))); // NOI18N
        jButton3.setText("Salvar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 484, 170, 45));

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtID.setPreferredSize(new java.awt.Dimension(300, 22));
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        getContentPane().add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 370, 68, 34));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Código:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 378, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Gerenciar Chamado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 917, -1));

        tbChamado = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbChamado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Abertura", "Saida", "Paciente", "QTH", "Natureza", "TARM", "MR", "COND", "ENF", "TEC ENF", "Medico", "Transporte"
            }
        ));
        tbChamado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChamadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbChamado);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 138, 868, 213));

        txtSituacao.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtSituacao.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtSituacao.setPreferredSize(new java.awt.Dimension(300, 22));
        txtSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSituacaoActionPerformed(evt);
            }
        });
        getContentPane().add(txtSituacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 369, 170, 34));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Situação:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 378, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Observações:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 378, -1, -1));

        txtObservacao.setColumns(20);
        txtObservacao.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtObservacao.setRows(5);
        txtObservacao.setMargin(new java.awt.Insets(2, 8, 2, 2));
        jScrollPane2.setViewportView(txtObservacao);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 369, 360, 161));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pdf-2-30.png"))); // NOI18N
        jButton5.setText("Gerar PDF / Imprimir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 483, 220, 47));

        txtPesquisar.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtPesquisar.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtPesquisar.setPreferredSize(new java.awt.Dimension(300, 22));
        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 86, 197, 34));

        btBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pesquisar-20.png"))); // NOI18N
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 86, 41, 34));

        setBounds(0, 0, 933, 597);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        salvar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSituacaoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        imprimir();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        pesquisar();
    }//GEN-LAST:event_btBuscarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        pesquisar();
    }//GEN-LAST:event_formInternalFrameOpened

    private void tbChamadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChamadoMouseClicked
        setar_campos();
    }//GEN-LAST:event_tbChamadoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbChamado;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtSituacao;
    // End of variables declaration//GEN-END:variables
}
