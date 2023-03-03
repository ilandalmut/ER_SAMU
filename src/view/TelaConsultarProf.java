/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.*;
import javax.swing.JOptionPane;
import model.ModuloConexao;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class TelaConsultarProf extends javax.swing.JInternalFrame {

    Connection  conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaConsultarProf() {
        initComponents();
        conexao = ModuloConexao.connector();
    }
    
    private void setar_campos(){
        int setar = tblProf.getSelectedRow();
        txtId.setText(tblProf.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblProf.getModel().getValueAt(setar, 1).toString());
        txtEmail.setText(tblProf.getModel().getValueAt(setar, 2).toString());
        //txtSenha.setText(tblUser.getModel().getValueAt(setar, 3).toString());
        setar_outros();        
    }
    
    private void setar_outros(){
        String sql = "select * from tb_profissionais where id_prof = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();
            
            if(rs.next()){
                txtTelefone.setText(rs.getString(3));
                txtEmail.setText(rs.getString(4));
                txtRua.setText(rs.getString(5));
                txtNumero.setText(rs.getString(6));
                txtBairro.setText(rs.getString(7));
                txtCidade.setText(rs.getString(8));
                txtCep.setText(rs.getString(9));
                txtCargo.setSelectedItem(rs.getString(10));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void editar(){
        String sql = "update tb_profissionais set nome_prof = ?, email_prof = ?, telefone_prof = ?, rua_prof = ?, numero_casa_prof = ?, bairro_prof = ?, cidade_prof = ?, cep_prof = ?, cargo_prof = ? where id_prof = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtEmail.getText());
            pst.setString(3, txtTelefone.getText());
            pst.setString(4, txtRua.getText());
            pst.setString(5, txtNumero.getText());
            pst.setString(6, txtBairro.getText());
            pst.setString(7, txtCidade.getText());
            pst.setString(8, txtCep.getText());
            pst.setString(9, txtCargo.getSelectedItem().toString());
            pst.setString(10, txtId.getText());
            
            
            
            if(txtNome.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "O campo nome é obrigatório!");
            } else {
                int adicionado = pst.executeUpdate();
                if(adicionado > 0){
                    JOptionPane.showMessageDialog(null, "Profissional cadastrado!");
                    novo();
                    
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);    
    }
    }
    
    private void novo(){
        txtId.setText(null);
        txtNome.setText(null);
        txtEmail.setText(null);
        txtTelefone.setText(null);
        txtRua.setText(null);
        txtNumero.setText(null);
        txtBairro.setText(null);
        txtCidade.setText(null);
        txtCep.setText(null);
        ((DefaultTableModel)tblProf.getModel()).setRowCount(0);
    }
    
    private void excluir(){
        String sql = "delete from tb_profissionais where id_prof = ?";
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse profissional?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            try{
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtId.getText());
                int excluido = pst.executeUpdate();
                if(excluido > 0){
                    JOptionPane.showMessageDialog(null, "Profissional excluido!");
                    txtNome.setText(null);
                    txtEmail.setText(null);
                    txtTelefone.setText(null);
                    txtRua.setText(null);
                    txtNumero.setText(null);
                    txtBairro.setText(null);
                    txtCidade.setText(null);
                    txtCep.setText(null);
                    txtCargo.setSelectedItem(null);
                }
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void pesquisar(){
        String sql = "select id_prof as ID, nome_prof as Nome, cargo_prof as Cargo from tb_profissionais where nome_prof like ?";
        try {
            conexao = ModuloConexao.connector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText() + "%");
            rs = pst.executeQuery();
            tblProf.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void adicionar(){
        String sql = "insert into tb_profissionais(nome_prof,email_prof,telefone_prof,rua_prof,numero_casa_prof,bairro_prof,cidade_prof,cep_prof,cargo_prof) values(?,?,?,?,?,?,?,?,?)";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtEmail.getText());
            pst.setString(3, txtTelefone.getText());
            pst.setString(4, txtRua.getText());
            pst.setString(5, txtNumero.getText());
            pst.setString(6, txtBairro.getText());
            pst.setString(7, txtCidade.getText());
            pst.setString(8, txtCep.getText());
            pst.setString(9, txtCargo.getSelectedItem().toString());
            
            if(txtNome.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "O campo nome é obrigatório!");
            } else {
                int adicionado = pst.executeUpdate();
                if(adicionado > 0){
                    JOptionPane.showMessageDialog(null, "Profissional cadastrado!");
                    txtNome.setText(null);
                    txtEmail.setText(null);
                    txtTelefone.setText(null);
                    txtRua.setText(null);
                    txtNumero.setText(null);
                    txtBairro.setText(null);
                    txtCidade.setText(null);
                    txtCep.setText(null);
                    txtCargo.setSelectedItem(null);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);    
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtRua = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtCep = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProf = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Profissionais");
        setMaximumSize(new java.awt.Dimension(837, 586));
        setMinimumSize(new java.awt.Dimension(837, 586));
        setPreferredSize(new java.awt.Dimension(837, 586));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
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

        txtRua.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtRua.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtRua.setPreferredSize(new java.awt.Dimension(300, 22));
        txtRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRuaActionPerformed(evt);
            }
        });
        getContentPane().add(txtRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 264, 354, 34));

        txtNome.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtNome.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtNome.setPreferredSize(new java.awt.Dimension(300, 22));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 106, 354, 34));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Cargo:*");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 427, -1, -1));

        txtCidade.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtCidade.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtCidade.setPreferredSize(new java.awt.Dimension(300, 22));
        txtCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeActionPerformed(evt);
            }
        });
        getContentPane().add(txtCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 368, 352, 34));

        txtEmail.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtEmail.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtEmail.setPreferredSize(new java.awt.Dimension(300, 22));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 158, 354, 34));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Rua:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 271, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Cidade:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 375, -1, -1));

        txtCargo.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TARM", "MR", "Condutor", "Enf", "Téc. Enf", "Médico" }));
        getContentPane().add(txtCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 420, 141, 34));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Nome:*");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 113, -1, -1));

        txtCep.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtCep.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtCep.setPreferredSize(new java.awt.Dimension(300, 22));
        txtCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCepActionPerformed(evt);
            }
        });
        getContentPane().add(txtCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 420, 120, 34));

        txtNumero.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtNumero.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtNumero.setPreferredSize(new java.awt.Dimension(300, 22));
        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 316, 59, 34));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("N°:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 323, -1, -1));

        txtTelefone.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtTelefone.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtTelefone.setPreferredSize(new java.awt.Dimension(300, 22));
        txtTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneActionPerformed(evt);
            }
        });
        getContentPane().add(txtTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 210, 172, 34));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("E-mail:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 165, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Cep:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 427, -1, -1));

        txtBairro.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtBairro.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtBairro.setPreferredSize(new java.awt.Dimension(300, 22));
        txtBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroActionPerformed(evt);
            }
        });
        getContentPane().add(txtBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 316, 201, 34));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Profissionais Cadastrados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 822, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Telefone:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Bairro:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 323, -1, -1));

        jLabel11.setText("Campo Obrigatório*");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 86, -1, -1));

        btBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pesquisar-20.png"))); // NOI18N
        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 409, 142, 45));

        tblProf = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblProf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProfMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProf);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 106, 340, 244));

        txtId.setEditable(false);
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 212, 50, 34));

        setBounds(0, 0, 837, 511);
    }// </editor-fold>//GEN-END:initComponents

    private void txtRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRuaActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneActionPerformed

    private void txtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        pesquisar();
    }//GEN-LAST:event_btBuscarActionPerformed

    private void tblProfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProfMouseClicked

        setar_campos();
        
    }//GEN-LAST:event_tblProfMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        txtId.setVisible(false);
        pesquisar();
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProf;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JComboBox<String> txtCargo;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
