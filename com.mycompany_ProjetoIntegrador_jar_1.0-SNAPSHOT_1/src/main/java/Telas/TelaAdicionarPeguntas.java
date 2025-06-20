/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Telas;

import BD.PerguntasDAO;
import Model.Pergunta;
import Model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author conta
 */
public class TelaAdicionarPeguntas extends javax.swing.JFrame {
    private Usuario usuario;
    /**
     * Creates new form AreaAdm
     */
    public TelaAdicionarPeguntas(Usuario usuario) {
        initComponents();
        setaButton.setContentAreaFilled(false);
    setaButton.setBorderPainted(false);
    setaButton.setFocusPainted(false);
    setaButton.setOpaque(false);
    this.setLocationRelativeTo(null);
    this.setResizable(  false); 
    this.usuario = usuario;
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
        setaButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        adicionarPerguntasButton = new javax.swing.JButton();
        txtAddPergunta = new javax.swing.JTextField();
        txtAddRespA = new javax.swing.JTextField();
        txtAddRespB = new javax.swing.JTextField();
        txtAddRespC = new javax.swing.JTextField();
        txtAddRespD = new javax.swing.JTextField();
        txtAddRespE = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dificuldadeComboBox = new javax.swing.JComboBox<>();
        materiasComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255, 220));

        setaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/seta1-removebg-preview.png"))); // NOI18N
        setaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setaButtonActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Logo_Poliedro_Horiz_EDUCACAO_300x70.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(setaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1166, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(setaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1550, 80));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255, 220));

        adicionarPerguntasButton.setBackground(new java.awt.Color(42, 122, 195));
        adicionarPerguntasButton.setFont(new java.awt.Font("Javanese Text", 0, 18)); // NOI18N
        adicionarPerguntasButton.setForeground(new java.awt.Color(255, 255, 255));
        adicionarPerguntasButton.setText("Adicionar");
        adicionarPerguntasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarPerguntasButtonActionPerformed(evt);
            }
        });

        txtAddPergunta.setFont(new java.awt.Font("Javanese Text", 0, 18)); // NOI18N
        txtAddPergunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddPerguntaActionPerformed(evt);
            }
        });

        txtAddRespA.setFont(new java.awt.Font("Javanese Text", 0, 14)); // NOI18N

        txtAddRespB.setFont(new java.awt.Font("Javanese Text", 0, 14)); // NOI18N

        txtAddRespC.setFont(new java.awt.Font("Javanese Text", 0, 14)); // NOI18N

        txtAddRespD.setFont(new java.awt.Font("Javanese Text", 0, 14)); // NOI18N

        txtAddRespE.setFont(new java.awt.Font("Javanese Text", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Javanese Text", 0, 18)); // NOI18N
        jLabel2.setText("Pergunta:");

        jLabel4.setFont(new java.awt.Font("Javanese Text", 0, 18)); // NOI18N
        jLabel4.setText("Alternativas:");

        dificuldadeComboBox.setFont(new java.awt.Font("Javanese Text", 0, 14)); // NOI18N
        dificuldadeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fácil", "Médio", "Difícil", " " }));

        materiasComboBox.setFont(new java.awt.Font("Javanese Text", 0, 14)); // NOI18N
        materiasComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Biologia", "Filosofia", "Física", "Geografia", "História", "Matemática", "Português", "Química", "Sociologia" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(dificuldadeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                .addComponent(materiasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtAddRespA)
                                .addComponent(txtAddRespB)
                                .addComponent(txtAddRespC)
                                .addComponent(txtAddRespD)
                                .addComponent(txtAddRespE, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                                .addComponent(txtAddPergunta, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(adicionarPerguntasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtAddRespA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtAddRespB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtAddRespC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtAddRespD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtAddRespE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dificuldadeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(materiasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(adicionarPerguntasButton)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 730, 740));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/img corredor - png.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1560, 1030));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setaButtonActionPerformed
        // TODO add your handling code here:
        TelaGerenciarPerguntas GerenciarPerguntasTela = new TelaGerenciarPerguntas(usuario);
        GerenciarPerguntasTela.setVisible(true);
        dispose();

    }//GEN-LAST:event_setaButtonActionPerformed

    private void adicionarPerguntasButtonActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        String textoPergunta = txtAddPergunta.getText();

        List<String> alternativas = new ArrayList<>();
        alternativas.add(txtAddRespA.getText()); // correta
        alternativas.add(txtAddRespB.getText());
        alternativas.add(txtAddRespC.getText());
        alternativas.add(txtAddRespD.getText());
        alternativas.add(txtAddRespE.getText());

        
        if (textoPergunta.trim().isEmpty() || alternativas.stream().anyMatch(a -> a.trim().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de adicionar.");
            return;
        }

        
        String disciplinaSelecionada = materiasComboBox.getSelectedItem().toString();
        int idDisciplina = switch (disciplinaSelecionada) {
            case "Matemática" -> 1;
            case "Física" -> 2;
            case "Química" -> 3;
            case "Biologia" -> 4;
            case "Português" -> 5;
            case "História" -> 6;
            case "Geografia" -> 7;
            case "Filosofia" -> 8;
            case "Sociologia" -> 9;
            default -> throw new IllegalArgumentException("Disciplina inválida");
        };

        
        String dificuldadeSelecionada = dificuldadeComboBox.getSelectedItem().toString();
        int idDificuldade = switch (dificuldadeSelecionada) {
            case "Fácil" -> 1;
            case "Médio" -> 2;
            case "Difícil" -> 3;
            default -> throw new IllegalArgumentException("Dificuldade inválida");
        };

        PerguntasDAO dao = new PerguntasDAO();
        boolean sucesso = dao.adicionarPerguntaEAlternativas(textoPergunta, alternativas, idDisciplina, idDificuldade);

        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Pergunta adicionada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar pergunta, tente novamente.");
        }

        
        txtAddPergunta.setText("");
        txtAddRespA.setText("");
        txtAddRespB.setText("");
        txtAddRespC.setText("");
        txtAddRespD.setText("");
        txtAddRespE.setText("");
        materiasComboBox.setSelectedIndex(0);
        dificuldadeComboBox.setSelectedIndex(0);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
    }
}

                                                        

    private void txtAddPerguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddPerguntaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddPerguntaActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(TelaAdicionarPeguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TelaAdicionarPeguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TelaAdicionarPeguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TelaAdicionarPeguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TelaAdicionarPeguntas().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarPerguntasButton;
    private javax.swing.JComboBox<String> dificuldadeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> materiasComboBox;
    private javax.swing.JButton setaButton;
    private javax.swing.JTextField txtAddPergunta;
    private javax.swing.JTextField txtAddRespA;
    private javax.swing.JTextField txtAddRespB;
    private javax.swing.JTextField txtAddRespC;
    private javax.swing.JTextField txtAddRespD;
    private javax.swing.JTextField txtAddRespE;
    // End of variables declaration//GEN-END:variables
}
