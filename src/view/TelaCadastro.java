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

public class TelaCadastro extends javax.swing.JInternalFrame {

    Connection  conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaCadastro() {
        initComponents();
        conexao = ModuloConexao.connector();
    }
    
    private void setar_campos(){
        int setar = tblProf.getSelectedRow();
        txtId.setText(tblProf.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblProf.getModel().getValueAt(setar, 1).toString());
        txtEmail.setText(tblProf.getModel().getValueAt(setar, 2).toString());
        //txtSenha.setText(tblUser.getModel().getValueAt(setar, 3).toString());
        btSalvar.setEnabled(false);
        setar_outros();
        btEditar.setEnabled(true);
        btExcluir.setEnabled(true);
        btNovo.setEnabled(true);
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
        btSalvar.setEnabled(true);
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
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
        btSalvar = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
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

        txtRua.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtRua.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtRua.setPreferredSize(new java.awt.Dimension(300, 22));
        txtRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRuaActionPerformed(evt);
            }
        });

        txtNome.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtNome.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtNome.setPreferredSize(new java.awt.Dimension(300, 22));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Cargo:*");

        txtCidade.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtCidade.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtCidade.setPreferredSize(new java.awt.Dimension(300, 22));
        txtCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeActionPerformed(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtEmail.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtEmail.setPreferredSize(new java.awt.Dimension(300, 22));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Rua:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Cidade:");

        txtCargo.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TARM", "MR", "Condutor", "Enf", "Téc. Enf", "Médico" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Nome:*");

        txtCep.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtCep.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtCep.setPreferredSize(new java.awt.Dimension(300, 22));
        txtCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCepActionPerformed(evt);
            }
        });

        txtNumero.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtNumero.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtNumero.setPreferredSize(new java.awt.Dimension(300, 22));
        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("N°:");

        txtTelefone.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtTelefone.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtTelefone.setPreferredSize(new java.awt.Dimension(300, 22));
        txtTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("E-mail:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Cep:");

        txtBairro.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtBairro.setMargin(new java.awt.Insets(2, 8, 2, 2));
        txtBairro.setPreferredSize(new java.awt.Dimension(300, 22));
        txtBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Novo Profissional");

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Telefone:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Bairro:");

        jLabel11.setText("Campo Obrigatório*");

        btSalvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-selecionado-20.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btNovo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-soma3-20.png"))); // NOI18N
        btNovo.setText("Novo");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-editar-20.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btExcluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-excluir-20.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-pesquisar-20.png"))); // NOI18N
        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

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

        txtId.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel9)
                        .addGap(37, 37, 37)
                        .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(14, 14, 14)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2))))
                                    .addGap(20, 20, 20)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addComponent(jLabel5)
                                    .addGap(29, 29, 29)
                                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addComponent(jLabel6)
                                    .addGap(29, 29, 29)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel7)
                                    .addGap(29, 29, 29)
                                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel8)
                                    .addGap(29, 29, 29)
                                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel1)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel2)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel5))
                            .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel8))
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        setBounds(0, 0, 837, 586);
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

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        adicionar();
        pesquisar();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        novo();
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
    }//GEN-LAST:event_btNovoActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        editar();
        pesquisar();
    }//GEN-LAST:event_btEditarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluir();
        pesquisar();
    }//GEN-LAST:event_btExcluirActionPerformed

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
        btNovo.setEnabled(false);
        btEditar.setEnabled(false);
        btExcluir.setEnabled(false);
        pesquisar();
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSalvar;
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
