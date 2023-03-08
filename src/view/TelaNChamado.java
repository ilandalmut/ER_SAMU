/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.ModuloConexao;
import javax.swing.JTextField;

/**
 *
 * @author Ivanete
 */
public class TelaNChamado extends javax.swing.JInternalFrame {

    Connection  conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaNChamado() {
        initComponents();
        conexao = ModuloConexao.connector();
    }
    
    private void adicionar(){
        String sql = "insert into tb_chamados(data_abertura,data_saida,nome_paciente,qth,ndo,tarm,mr,cond,enf,dr) values(?,?,?,?,?,?,?,?,?,?)";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtAbertura.getText());
            pst.setString(2, txtSaida.getText());
            pst.setString(3, txtPaciente.getText());
            pst.setString(4, txtQTH.getText());
            pst.setString(5, txtNDO.getText());
            pst.setString(6, cbTarm.getSelectedItem().toString());
            pst.setString(7, cbMR.getSelectedItem().toString());
            pst.setString(8, cbCond.getSelectedItem().toString());
            pst.setString(9, cbEnf.getSelectedItem().toString());
            pst.setString(10, cbDR.getSelectedItem().toString());
            
            if(txtQTH.getText().isEmpty() || txtSaida.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Os campos QTH e Registro de Saida, são obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if(adicionado > 0){
                    JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
                    txtAbertura.setText(null);
                    txtSaida.setText(null);
                    txtPaciente.setText(null);
                    txtQTH.setText(null);
                    txtNDO.setText(null);
                    cbTarm.setSelectedItem(null);
                    cbMR.setSelectedItem(null);
                    cbCond.setSelectedItem(null);
                    cbEnf.setSelectedItem(null);
                    cbDR.setSelectedItem(null);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);    
    }
}
    
    private void popularTARM() {
        List<String> strList = new ArrayList<String>();
        
        String sql = "select * from tb_profissionais where cargo_prof = 'TARM' order by nome_prof";
        
        try{
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                
                strList.add(rs.getString(2));
       
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(strList.toArray());
        cbTarm.setModel(defaultComboBox);
    }
    
    private void popularMR() {
        List<String> strList = new ArrayList<String>();
        
        String sql = "select * from tb_profissionais where cargo_prof = 'MR' order by nome_prof";
        
        try{
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                
                strList.add(rs.getString(2));
       
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(strList.toArray());
        cbMR.setModel(defaultComboBox);
    }
    
    private void popularCond() {
        List<String> strList = new ArrayList<String>();
        
        String sql = "select * from tb_profissionais where cargo_prof = 'Condutor' order by nome_prof";
        
        try{
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                
                strList.add(rs.getString(2));
       
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(strList.toArray());
        cbCond.setModel(defaultComboBox);
    }
    
    private void popularEnftec() {
        List<String> strList = new ArrayList<String>();
        
        String sql = "select * from tb_profissionais where cargo_prof = 'Enf' or cargo_prof = 'Téc. Enf' order by nome_prof";
        
        try{
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                
                strList.add(rs.getString(2));
       
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(strList.toArray());
        cbEnf.setModel(defaultComboBox);
    }
    
    private void popularDR() {
        List<String> strList = new ArrayList<String>();
        
        String sql = "select * from tb_profissionais where cargo_prof = 'Médico' order by nome_prof";
        
        try{
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                
                strList.add(rs.getString(2));
       
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(strList.toArray());
        cbDR.setModel(defaultComboBox);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPaciente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbTarm = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbMR = new javax.swing.JComboBox<>();
        cbCond = new javax.swing.JComboBox<>();
        cbEnf = new javax.swing.JComboBox<>();
        cbDR = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQTH = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        txtAbertura = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btSaida = new javax.swing.JButton();
        txtSaida = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtNDO = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Novo Chamado");
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPaciente.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtPaciente.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtPaciente.setPreferredSize(new java.awt.Dimension(300, 22));
        txtPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPacienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 111, 495, 34));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("TARM:*");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 391, -1, -1));

        jLabel11.setText("Campo Obrigatório*");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 91, -1, -1));

        cbTarm.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        getContentPane().add(cbTarm, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 414, 133, 34));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Paciente:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 88, -1, -1));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-ajuda-30.png"))); // NOI18N
        jButton4.setText("Ajuda");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 470, 185, 59));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Novo Chamado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 875, -1));

        cbMR.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        getContentPane().add(cbMR, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 414, 133, 34));

        cbCond.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        getContentPane().add(cbCond, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 414, 133, 34));

        cbEnf.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        cbEnf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEnfActionPerformed(evt);
            }
        });
        getContentPane().add(cbEnf, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 414, 133, 34));

        cbDR.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        getContentPane().add(cbDR, new org.netbeans.lib.awtextra.AbsoluteConstraints(658, 414, 133, 34));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("MR:*");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 391, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("COND:*");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 391, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("ENF/TEC:*");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 391, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("MÉDICO:*");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(658, 391, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("QTH:*");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 151, -1, -1));

        txtQTH.setColumns(20);
        txtQTH.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtQTH.setRows(5);
        txtQTH.setMargin(new java.awt.Insets(2, 8, 2, 2));
        jScrollPane1.setViewportView(txtQTH);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 174, 495, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAbertura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtAbertura.setText("25/11/2022   12:30:00");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("QTR - Chamado aberto em:");

        btSaida.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSaida.setForeground(new java.awt.Color(204, 0, 51));
        btSaida.setText("Registrar Saída");
        btSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaidaActionPerformed(evt);
            }
        });

        txtSaida.setEditable(false);
        txtSaida.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSaida.setMargin(new java.awt.Insets(2, 6, 2, 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAbertura)
                            .addComponent(jLabel5))
                        .addGap(34, 48, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btSaida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSaida))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAbertura)
                .addGap(18, 18, 18)
                .addComponent(btSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 111, -1, 194));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-selecionado-20.png"))); // NOI18N
        jButton5.setText("Registrar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 471, 185, 59));

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-excluir-20.png"))); // NOI18N
        jButton6.setText("Cancelar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, 185, 59));

        txtNDO.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtNDO.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtNDO.setPreferredSize(new java.awt.Dimension(300, 22));
        getContentPane().add(txtNDO, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 339, 495, 34));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Natureza da ocorrência:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 316, -1, -1));

        setBounds(0, 0, 837, 586);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPacienteActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        TelaAjuda ajuda = new TelaAjuda();
        ajuda.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbEnfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEnfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEnfActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        adicionar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
               
        Date dataHoraAtual = new Date();
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        txtAbertura.setText( formatador.format(data) + " - " + hora);
        popularTARM();
        popularMR();
        popularCond();
        popularEnftec();
        popularDR();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaidaActionPerformed
        Date data2 = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
               
        Date dataHoraAtual2 = new Date();
        String hora2 = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual2);
        txtSaida.setText( formatador.format(data2) + " - " + hora2);
    }//GEN-LAST:event_btSaidaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSaida;
    private javax.swing.JComboBox<String> cbCond;
    private javax.swing.JComboBox<String> cbDR;
    private javax.swing.JComboBox<String> cbEnf;
    private javax.swing.JComboBox<String> cbMR;
    private javax.swing.JComboBox<String> cbTarm;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtAbertura;
    private javax.swing.JTextField txtNDO;
    private javax.swing.JTextField txtPaciente;
    private javax.swing.JTextArea txtQTH;
    private javax.swing.JTextField txtSaida;
    // End of variables declaration//GEN-END:variables
}
