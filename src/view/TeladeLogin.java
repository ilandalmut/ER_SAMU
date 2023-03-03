/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import model.ModuloConexao;
import javax.swing.JTextField;


public class TeladeLogin extends javax.swing.JFrame {

    Connection  conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TeladeLogin() {
        initComponents();
        conexao = ModuloConexao.connector();
       
        
    }

    private void status(){
        try{
            if(conexao != null){
                lblStatus.setText("");
            } else {
                lblStatus.setForeground(Color.RED);
                lblStatus.setText("Banco de Dados desconectado");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
        
    }
}
    public void logar(){
    String sql = "select * from tb_usuarios where login_usuario = ? and senha_usuario = ?";
            try{
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtLogin.getText());
                pst.setString(2, txtSenha.getText());
                
                rs = pst.executeQuery();
                
                if(rs.next()){
                    String perfil_usuario = rs.getString(5);
                    if(perfil_usuario.equals ("Admin")){
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.setVisible(true);
                        TelaPrincipal.menuCad.setEnabled(true);
                        TelaPrincipal.menuRel.setEnabled(true);
                        TelaPrincipal.btRel.setEnabled(true);
                        TelaPrincipal.txtUser.setText(rs.getString(2));
                        
                        this.dispose();
                    }
                    if(perfil_usuario.equals ("User")){
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.setVisible(true);
                        TelaPrincipal.txtUser.setText(rs.getString(2));
                        
                        this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário/Senha inválidos");
                    
                }
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatus = new javax.swing.JLabel();
        btlogar = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtExibirSenha = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ER SAMU-Login");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        lblStatus.setText("Status");

        btlogar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btEntrar.png"))); // NOI18N
        btlogar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btlogar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btlogarMouseClicked(evt);
            }
        });

        txtLogin.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtLogin.setMargin(new java.awt.Insets(2, 10, 2, 2));
        txtLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLoginFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLoginFocusLost(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/samu-192-logo.png"))); // NOI18N

        txtSenha.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtSenha.setMargin(new java.awt.Insets(2, 10, 2, 2));
        txtSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSenhaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSenhaFocusLost(evt);
            }
        });
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Seja bem vindo!");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Login");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Senha");

        txtExibirSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtExibirSenha.setText("Mostrar senha");
        txtExibirSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExibirSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtLogin, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btlogar, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtExibirSenha))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtExibirSenha))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2)))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btlogar)
                    .addComponent(lblStatus))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btlogarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btlogarMouseClicked
        logar();
    }//GEN-LAST:event_btlogarMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        status();
        if(txtSenha.getText().equals("")){
            txtSenha.setEchoChar((char) 0);
	    txtSenha.setForeground(Color.lightGray);	
            txtSenha.setText("Digite sua senha");
                
	}
    }//GEN-LAST:event_formWindowActivated

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            logar();
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtExibirSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExibirSenhaActionPerformed
        if (txtExibirSenha.isSelected()) {
            txtSenha.setEchoChar((char) 0);
        } else {
        txtSenha.setEchoChar('●');
        }
    }//GEN-LAST:event_txtExibirSenhaActionPerformed

    private void txtLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLoginFocusGained
        if(txtLogin.getText().equals("Digite seu usuário")){
		txtLogin.setText("");
                txtLogin.setForeground(Color.BLACK);
	}
    }//GEN-LAST:event_txtLoginFocusGained

    private void txtLoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLoginFocusLost
        if(txtLogin.getText().equals("")){
	    txtLogin.setForeground(Color.lightGray);	
            txtLogin.setText("Digite seu usuário");
                
	}
    }//GEN-LAST:event_txtLoginFocusLost

    private void txtSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusGained
        if(txtSenha.getText().equals("Digite sua senha")){
                txtSenha.setEchoChar('●');
		txtSenha.setText("");
                txtSenha.setForeground(Color.BLACK);
	}
    }//GEN-LAST:event_txtSenhaFocusGained

    private void txtSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusLost
        if(txtSenha.getText().equals("")){
            txtSenha.setEchoChar((char) 0);
	    txtSenha.setForeground(Color.lightGray);	
            txtSenha.setText("Digite sua senha");
                
	}
    }//GEN-LAST:event_txtSenhaFocusLost

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TeladeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeladeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeladeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeladeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeladeLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btlogar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JCheckBox txtExibirSenha;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
