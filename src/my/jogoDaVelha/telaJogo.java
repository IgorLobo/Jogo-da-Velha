package my.jogoDaVelha;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_NUMPAD1;
import static java.awt.event.KeyEvent.VK_NUMPAD2;
import static java.awt.event.KeyEvent.VK_NUMPAD3;
import static java.awt.event.KeyEvent.VK_NUMPAD4;
import static java.awt.event.KeyEvent.VK_NUMPAD5;
import static java.awt.event.KeyEvent.VK_NUMPAD6;
import static java.awt.event.KeyEvent.VK_NUMPAD7;
import static java.awt.event.KeyEvent.VK_NUMPAD8;
import static java.awt.event.KeyEvent.VK_NUMPAD9;
import static java.awt.event.KeyEvent.VK_SPACE;
import javax.swing.JOptionPane;
import static my.jogoDaVelha.Variaveis.rodadas;

/**
 * @author Hygor
 * @author Igor
 */
public class telaJogo extends javax.swing.JFrame {

    int button = 0;
    int R = 1;
    int jogadas = 0;
    String J = Variaveis.simboloJ1;
    boolean jogadorAtivo1 = true;
    boolean jogadorAtivo2 = false;
    int pontos;
    int vitorias;
    int e;
    boolean ganhador = false;

    //************VARIAVEIS PARA CONTROLE DAS MATRIZES**************************
    static int Mresultado[][];
    int aux;
    int[][] MatrizR = new int[2][rodadas];
    int[][] Minversa = new int[2][rodadas];
    int[][] Mtransposta = new int[rodadas][2];
    int[][] Madicao = new int[2][rodadas];
    int[][] Msubtracao = new int[2][rodadas];
    int[][] Mmultiplicacao = new int[2][2];
    static String B1;
    static String O1;
    static String H1;
    //**************************************************************************

    public telaJogo() {
        initComponents();
        IniciarCampos();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("launcher.png")));
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                if (!telaJogo.this.isVisible()) {
                    return;
                }
                try {
                    KeyEvent evt = (KeyEvent) event;
                    if (evt.getID() == KeyEvent.KEY_PRESSED) {
                        switch (evt.getKeyCode()) {
                            case VK_NUMPAD1:
                                M1.doClick();
                                break;
                            case VK_NUMPAD2:
                                M2.doClick();
                                break;
                            case VK_NUMPAD3:
                                M3.doClick();
                                break;
                            case VK_NUMPAD4:
                                M4.doClick();
                                break;
                            case VK_NUMPAD5:
                                M5.doClick();
                                break;
                            case VK_NUMPAD6:
                                M6.doClick();
                                break;
                            case VK_NUMPAD7:
                                M7.doClick();
                                break;
                            case VK_NUMPAD8:
                                M8.doClick();
                                break;
                            case VK_NUMPAD9:
                                M9.doClick();
                                break;
                            case VK_SPACE:
                                evt.consume();
                                break;
                        }

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(telaJogo.this, e);
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }

    public void IniciarCampos() {
        Mresultado = new int[2][Variaveis.rodadas];

        if (Variaveis.nJ1.equals("")) {
            JL_J1.setText("Jogador 1:");
        } else {
            JL_J1.setText(Variaveis.nJ1 + ":");
        }
        if (Variaveis.nJ2.equals("")) {
            JL_J2.setText("Jogador 2:");
        } else {
            JL_J2.setText(Variaveis.nJ2 + ":");
        }
        JL_totalRodadas.setText(Integer.toString(Variaveis.rodadas));
        btn_Relatorios.setEnabled(false);
        Atualizar();
        JL_vez.setText(Variaveis.nJ1);
        btn_NovoJogo.setToolTipText("Cria um novo jogo! :)");
        btn_Relatorios.setToolTipText("Abre os relatórios pós-jogo :)");
        Dica.setToolTipText("<html><b>Dica:</b> Você pode jogar pelo teclado numérico também!<html>");

    }

    public void JogadorAtivo() {//******PASSA A VEZ DOS JOGADORES****************
        if (jogadorAtivo1 == true) {//passa pro j2
            jogadorAtivo1 = false;
            jogadorAtivo2 = true;
            J = Variaveis.simboloJ2;
            JL_vez.setText(Variaveis.nJ2);
            JL_vezSimbolo.setText(Variaveis.simboloJ2);

        } else {//passa pro j1
            jogadorAtivo1 = true;
            jogadorAtivo2 = false;
            J = Variaveis.simboloJ1;
            JL_vez.setText(Variaveis.nJ1);
            JL_vezSimbolo.setText(Variaveis.simboloJ1);
        }
        TestarGanhador(Variaveis.simboloJ1);
        TestarGanhador(Variaveis.simboloJ2);
        if (jogadas == 9 && ganhador == false) {
            M1.setForeground(Color.red);
            M2.setForeground(Color.red);
            M3.setForeground(Color.red);
            M4.setForeground(Color.red);
            M5.setForeground(Color.red);
            M6.setForeground(Color.red);
            M7.setForeground(Color.red);
            M8.setForeground(Color.red);
            M9.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, "Deu velha :(");
            MostrarGanhador("EMPATE");
            Limpar();
        }

    }

    public void TestarGanhador(String jogador) {//******Teste ganhador***********
        //*******Teste linhas**************

        if (M1.getText().equals(jogador) && M2.getText().equals(jogador) && M3.getText().equals(jogador)) {
            M1.setForeground(Color.green);
            M2.setForeground(Color.green);
            M3.setForeground(Color.green);
            MostrarGanhador(jogador);
            Limpar();
        } else if (M4.getText().equals(jogador) && M5.getText().equals(jogador) && M6.getText().equals(jogador)) {
            M4.setForeground(Color.green);
            M5.setForeground(Color.green);
            M6.setForeground(Color.green);
            MostrarGanhador(jogador);
            Limpar();
        } else if (M7.getText().equals(jogador) && M8.getText().equals(jogador) && M9.getText().equals(jogador)) {
            M7.setForeground(Color.green);
            M8.setForeground(Color.green);
            M9.setForeground(Color.green);
            MostrarGanhador(jogador);
            Limpar();
        } else if (M7.getText().equals(jogador) && M4.getText().equals(jogador) && M1.getText().equals(jogador)) {
            M7.setForeground(Color.green);
            M4.setForeground(Color.green);
            M1.setForeground(Color.green);
            MostrarGanhador(jogador);
            Limpar();
        } else if (M8.getText().equals(jogador) && M5.getText().equals(jogador) && M2.getText().equals(jogador)) {
            M8.setForeground(Color.green);
            M5.setForeground(Color.green);
            M2.setForeground(Color.green);
            MostrarGanhador(jogador);
            Limpar();
        } else if (M9.getText().equals(jogador) && M6.getText().equals(jogador) && M3.getText().equals(jogador)) {
            M9.setForeground(Color.green);
            M6.setForeground(Color.green);
            M3.setForeground(Color.green);
            MostrarGanhador(jogador);
            Limpar();
        } else if (M7.getText().equals(jogador) && M5.getText().equals(jogador) && M3.getText().equals(jogador)) {
            M7.setForeground(Color.green);
            M5.setForeground(Color.green);
            M3.setForeground(Color.green);
            MostrarGanhador(jogador);
            Limpar();
        } else if (M1.getText().equals(jogador) && M5.getText().equals(jogador) && M9.getText().equals(jogador)) {
            M1.setForeground(Color.green);
            M5.setForeground(Color.green);
            M9.setForeground(Color.green);
            MostrarGanhador(jogador);
            Limpar();

        }

    }

    public void MostrarGanhador(String JogadorVencedor) {//**********************
        if (JogadorVencedor.equals(Variaveis.simboloJ1)) {
            J = Variaveis.simboloJ1;
            ganhador = true;
            JL_pontosJ1.setText(Integer.toString(Integer.parseInt(JL_pontosJ1.getText()) + 5));
            JL_vitoriasJ1.setText(Integer.toString(Integer.parseInt(JL_vitoriasJ1.getText()) + 1));
            jogadorAtivo1 = true;
            jogadorAtivo2 = false;
            if (R == 1) {
                Mresultado[0][R - 1] += 5;
                Mresultado[1][R - 1] += 0;
            } else {
                Mresultado[0][R - 1] = Mresultado[0][R - 2] + 5;
                Mresultado[1][R - 1] = Mresultado[1][R - 2] + 0;
            }
            JOptionPane.showMessageDialog(null, "Parabéns, " + Variaveis.nJ1 + " ganhou a rodada!!");
        }
        if (JogadorVencedor.equals(Variaveis.simboloJ2)) {

            J = Variaveis.simboloJ2;
            ganhador = true;
            JL_pontosJ2.setText(Integer.toString(Integer.parseInt(JL_pontosJ2.getText()) + 5));
            JL_vitoriasJ2.setText(Integer.toString(Integer.parseInt(JL_vitoriasJ2.getText()) + 1));
            jogadorAtivo1 = false;
            jogadorAtivo2 = true;

            if (R == 1) {
                Mresultado[0][R - 1] += 0;
                Mresultado[1][R - 1] += 5;
            } else {
                Mresultado[1][R - 1] = Mresultado[1][R - 2] + 5;
                Mresultado[0][R - 1] = Mresultado[0][R - 2] + 0;
            }
            JOptionPane.showMessageDialog(null, "Parabéns, " + Variaveis.nJ2 + " ganhou a rodada!!");
        }
        if (JogadorVencedor.equals("EMPATE")) {
            JL_pontosJ1.setText(Integer.toString(Integer.parseInt(JL_pontosJ1.getText()) + 2));
            JL_pontosJ2.setText(Integer.toString(Integer.parseInt(JL_pontosJ2.getText()) + 2));
            JL_Empates.setText(Integer.toString(Integer.parseInt(JL_Empates.getText()) + 1));
            if (R == 1) {
                Mresultado[0][R - 1] += 2;
                Mresultado[1][R - 1] += 2;
            } else {
                Mresultado[0][R - 1] = Mresultado[0][R - 2] + 2;
                Mresultado[1][R - 1] = Mresultado[1][R - 2] + 2;
            }
        }
    }

    public void Limpar() {

        jogadas = 0;
        if (R < Variaveis.rodadas) {
            M1.setText("");
            M2.setText("");
            M3.setText("");
            M4.setText("");
            M5.setText("");
            M6.setText("");
            M7.setText("");
            M8.setText("");
            M9.setText("");

            R++;
            ganhador = false;
        } else {
            M1.setEnabled(false);
            M2.setEnabled(false);
            M3.setEnabled(false);
            M4.setEnabled(false);
            M5.setEnabled(false);
            M6.setEnabled(false);
            M7.setEnabled(false);
            M8.setEnabled(false);
            M9.setEnabled(false);
            btn_Relatorios.setEnabled(true);
            if (Integer.parseInt(JL_pontosJ1.getText()) > Integer.parseInt(JL_pontosJ2.getText()) ) {
                JOptionPane.showMessageDialog(null, "Parabéns " + Variaveis.nJ1 +",  você ganhou!!");
            }else if (Integer.parseInt(JL_pontosJ2.getText()) > Integer.parseInt(JL_pontosJ1.getText()) ) {
                JOptionPane.showMessageDialog(null, "Parabéns " + Variaveis.nJ2 +",  você ganhou!!");
            }else if (Integer.parseInt(JL_pontosJ1.getText()) == Integer.parseInt(JL_pontosJ2.getText()) ) {
                JOptionPane.showMessageDialog(null, "Empate!!");
            }
        }
        M1.setForeground(Color.black);
        M2.setForeground(Color.black);
        M3.setForeground(Color.black);
        M4.setForeground(Color.black);
        M5.setForeground(Color.black);
        M6.setForeground(Color.black);
        M7.setForeground(Color.black);
        M8.setForeground(Color.black);
        M9.setForeground(Color.black);
    }

    public void Atualizar() {
        JL_jogadas.setText(Integer.toString(jogadas));
        JL_Rodada.setText(Integer.toString(R));
        if (jogadorAtivo1 == true) {
            JL_vez.setText(Variaveis.nJ1);
            JL_vezSimbolo.setText(Variaveis.simboloJ1);
        }else if (jogadorAtivo2 == true) {
            JL_vez.setText(Variaveis.nJ2);
            JL_vezSimbolo.setText(Variaveis.simboloJ2);
        }
    }

    public void Inverter() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < rodadas; j++) {
                Minversa[i][j] = MatrizR[i][j];
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int z = 0; z < Variaveis.rodadas / 2; z++) {
                aux = Minversa[i][z];
                Minversa[i][z] = Minversa[i][Variaveis.rodadas - 1];
                Minversa[i][Variaveis.rodadas - 1] = aux;

            }
        }
        for (int i = 0; i < rodadas; i++) {
            Variaveis.matB1 += String.format("<td><center> %s </center></td>", Minversa[0][i]);
            Variaveis.matB2 += String.format("<td><center> %s </center></td>", Minversa[1][i]);
        }

    }

    public void Transposta() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < rodadas; j++) {
                Mtransposta[j][i] = MatrizR[i][j];
            }
        }
        for (int i = 0; i < rodadas; i++) {
            Variaveis.matC1 += String.format("%s<br>", Mtransposta[i][0]);
            Variaveis.matC2 += String.format("%s<br>", Mtransposta[i][1]);

        }

    }

    public void Adicao() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < rodadas; j++) {
                Madicao[i][j] = MatrizR[i][j] + Minversa[i][j];
            }
        }
        for (int i = 0; i < rodadas; i++) {
            Variaveis.matD1 += String.format("<td><center> %s </center></td>", Madicao[0][i]);
            Variaveis.matD2 += String.format("<td><center> %s </center></td>", Madicao[1][i]);
        }

    }

    public void Subtracao() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < rodadas; j++) {
                Msubtracao[i][j] = MatrizR[i][j] - Minversa[i][j];
            }
        }
        for (int i = 0; i < rodadas; i++) {
            Variaveis.matE1 += String.format("<td><center> %s </center></td>", Msubtracao[0][i]);
            Variaveis.matE2 += String.format("<td><center> %s </center></td>", Msubtracao[1][i]);
        }

    }

    public void Multiplicacao() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < rodadas; k++) {
                    Mmultiplicacao[i][j] += (MatrizR[i][k] * Mtransposta[k][j]);

                }
            }
        }

        for (int i = 0; i < 2; i++) {
            Variaveis.matF1 += String.format("<td><center> %s </center></td>", Mmultiplicacao[0][i]);
            Variaveis.matF2 += String.format("<td><center> %s </center></td>", Mmultiplicacao[1][i]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_jogo = new javax.swing.JPanel();
        M7 = new javax.swing.JButton();
        M8 = new javax.swing.JButton();
        M9 = new javax.swing.JButton();
        M4 = new javax.swing.JButton();
        M5 = new javax.swing.JButton();
        M6 = new javax.swing.JButton();
        M1 = new javax.swing.JButton();
        M2 = new javax.swing.JButton();
        M3 = new javax.swing.JButton();
        jp_jogadores = new javax.swing.JPanel();
        JL_J1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JL_vitoriasJ1 = new javax.swing.JLabel();
        JL_pontosJ1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JL_pontosJ2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JL_vitoriasJ2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        JL_J2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_Voltar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        JL_jogadas = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JL_Rodada = new javax.swing.JLabel();
        btn_NovoJogo = new javax.swing.JButton();
        btn_Relatorios = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        JL_totalRodadas = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JL_Empates = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        JL_vez = new javax.swing.JLabel();
        Dica = new javax.swing.JLabel();
        JL_vezSimbolo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Jogo da Velha");
        setResizable(false);

        jp_jogo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jp_jogo.setMaximumSize(new java.awt.Dimension(350, 350));
        jp_jogo.setMinimumSize(new java.awt.Dimension(350, 350));
        jp_jogo.setPreferredSize(new java.awt.Dimension(351, 351));

        M7.setBackground(new java.awt.Color(255, 255, 255));
        M7.setFont(new java.awt.Font("Chiller", 1, 60)); // NOI18N
        M7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M7ActionPerformed(evt);
            }
        });

        M8.setBackground(new java.awt.Color(255, 255, 255));
        M8.setFont(new java.awt.Font("Chiller", 1, 60)); // NOI18N
        M8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M8ActionPerformed(evt);
            }
        });

        M9.setBackground(new java.awt.Color(255, 255, 255));
        M9.setFont(new java.awt.Font("Chiller", 1, 60)); // NOI18N
        M9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M9ActionPerformed(evt);
            }
        });

        M4.setBackground(new java.awt.Color(255, 255, 255));
        M4.setFont(new java.awt.Font("Chiller", 1, 60)); // NOI18N
        M4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M4ActionPerformed(evt);
            }
        });

        M5.setBackground(new java.awt.Color(255, 255, 255));
        M5.setFont(new java.awt.Font("Chiller", 1, 60)); // NOI18N
        M5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M5ActionPerformed(evt);
            }
        });

        M6.setBackground(new java.awt.Color(255, 255, 255));
        M6.setFont(new java.awt.Font("Chiller", 1, 60)); // NOI18N
        M6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M6ActionPerformed(evt);
            }
        });

        M1.setBackground(new java.awt.Color(255, 255, 255));
        M1.setFont(new java.awt.Font("Chiller", 1, 60)); // NOI18N
        M1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M1ActionPerformed(evt);
            }
        });

        M2.setBackground(new java.awt.Color(255, 255, 255));
        M2.setFont(new java.awt.Font("Chiller", 1, 60)); // NOI18N
        M2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M2ActionPerformed(evt);
            }
        });

        M3.setBackground(new java.awt.Color(255, 255, 255));
        M3.setFont(new java.awt.Font("Chiller", 1, 60)); // NOI18N
        M3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M3ActionPerformed(evt);
            }
        });

        jp_jogadores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_J1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JL_J1.setText("Jogador 1:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/jogoDaVelha/Imagens/Coin.png"))); // NOI18N
        jLabel1.setText("Pontos:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/jogoDaVelha/Imagens/crown (1).png"))); // NOI18N
        jLabel2.setText("Vitórias:");

        JL_vitoriasJ1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JL_vitoriasJ1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JL_vitoriasJ1.setText("0");

        JL_pontosJ1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JL_pontosJ1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JL_pontosJ1.setText("0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/jogoDaVelha/Imagens/Coin.png"))); // NOI18N
        jLabel3.setText("Pontos:");

        JL_pontosJ2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JL_pontosJ2.setText("0");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/jogoDaVelha/Imagens/crown (1).png"))); // NOI18N
        jLabel6.setText("Vitórias:");

        JL_vitoriasJ2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JL_vitoriasJ2.setText("0");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        JL_J2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JL_J2.setText("Jogador 2:");

        javax.swing.GroupLayout jp_jogadoresLayout = new javax.swing.GroupLayout(jp_jogadores);
        jp_jogadores.setLayout(jp_jogadoresLayout);
        jp_jogadoresLayout.setHorizontalGroup(
            jp_jogadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_jogadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_jogadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_jogadoresLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JL_pontosJ1))
                    .addGroup(jp_jogadoresLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JL_vitoriasJ1))
                    .addComponent(JL_J1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_jogadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_jogadoresLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JL_pontosJ2))
                    .addGroup(jp_jogadoresLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JL_vitoriasJ2))
                    .addComponent(JL_J2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_jogadoresLayout.setVerticalGroup(
            jp_jogadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_jogadoresLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jp_jogadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_jogadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jp_jogadoresLayout.createSequentialGroup()
                            .addGroup(jp_jogadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(JL_pontosJ1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jp_jogadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(JL_vitoriasJ1)))
                        .addGroup(jp_jogadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jp_jogadoresLayout.createSequentialGroup()
                                .addComponent(JL_J2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jp_jogadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(JL_pontosJ2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jp_jogadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(JL_vitoriasJ2)))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(JL_J1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jp_jogoLayout = new javax.swing.GroupLayout(jp_jogo);
        jp_jogo.setLayout(jp_jogoLayout);
        jp_jogoLayout.setHorizontalGroup(
            jp_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_jogoLayout.createSequentialGroup()
                .addGroup(jp_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_jogoLayout.createSequentialGroup()
                        .addGroup(jp_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_jogoLayout.createSequentialGroup()
                                .addComponent(M4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_jogoLayout.createSequentialGroup()
                                .addComponent(M1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_jogoLayout.createSequentialGroup()
                                .addComponent(M7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jp_jogadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jp_jogoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {M1, M2, M3, M4, M5, M6, M7, M8, M9});

        jp_jogoLayout.setVerticalGroup(
            jp_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_jogoLayout.createSequentialGroup()
                .addGroup(jp_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(M7, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(M8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(M9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(M4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(M5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(M6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_jogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(M1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(M2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(M3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_jogadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jp_jogoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {M1, M2, M3, M4, M5, M6, M7, M8, M9});

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_Voltar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Voltar.setText("Voltar");
        btn_Voltar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VoltarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Jogadas:");

        JL_jogadas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JL_jogadas.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Rodada:");

        JL_Rodada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JL_Rodada.setText("0");

        btn_NovoJogo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_NovoJogo.setText("Novo jogo");
        btn_NovoJogo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_NovoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NovoJogoActionPerformed(evt);
            }
        });

        btn_Relatorios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Relatorios.setText("Relatórios");
        btn_Relatorios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RelatoriosActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("/");

        JL_totalRodadas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JL_totalRodadas.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Empates:");

        JL_Empates.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        JL_Empates.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Quem joga agora:");

        JL_vez.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        Dica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/jogoDaVelha/Imagens/Yoda.png"))); // NOI18N
        Dica.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        JL_vezSimbolo.setFont(new java.awt.Font("Chiller", 1, 36)); // NOI18N
        JL_vezSimbolo.setText("X");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Relatorios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(btn_NovoJogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Voltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JL_Empates)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(JL_Rodada)
                                            .addComponent(JL_jogadas))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JL_totalRodadas)))
                                .addGap(77, 77, 77))
                            .addComponent(Dica, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JL_vezSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(JL_vez, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Dica, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(JL_vezSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JL_vez, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(JL_jogadas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(JL_Rodada)
                    .addComponent(jLabel11)
                    .addComponent(JL_totalRodadas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JL_Empates))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_NovoJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Relatorios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Voltar)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_NovoJogo, btn_Relatorios, btn_Voltar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp_jogo, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_jogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void M7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M7ActionPerformed
        if (M7.getText().equals("") && ganhador == false) {
            jogadas++;
            M7.setText(J);
            JogadorAtivo();
            Atualizar();
        }
    }//GEN-LAST:event_M7ActionPerformed

    private void M4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M4ActionPerformed
        if (M4.getText().equals("") && ganhador == false) {
            jogadas++;
            M4.setText(J);
            JogadorAtivo();
            Atualizar();
        }
    }//GEN-LAST:event_M4ActionPerformed

    private void M1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M1ActionPerformed
        if (M1.getText().equals("") && ganhador == false) {
            jogadas++;
            M1.setText(J);
            JogadorAtivo();
            Atualizar();
        }
    }//GEN-LAST:event_M1ActionPerformed

    private void btn_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VoltarActionPerformed
        telaConfiguracao jpConfiguracao = new telaConfiguracao();
        jpConfiguracao.setVisible(true);
        Variaveis.LimparMatriz();
        this.dispose();
    }//GEN-LAST:event_btn_VoltarActionPerformed

    private void M8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M8ActionPerformed
        if (M8.getText().equals("") && ganhador == false) {
            jogadas++;
            M8.setText(J);
            JogadorAtivo();
            Atualizar();
        }
    }//GEN-LAST:event_M8ActionPerformed

    private void M9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M9ActionPerformed
        if (M9.getText().equals("") && ganhador == false) {
            jogadas++;
            M9.setText(J);
            JogadorAtivo();
            Atualizar();
        }
    }//GEN-LAST:event_M9ActionPerformed

    private void M5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M5ActionPerformed
        if (M5.getText().equals("") && ganhador == false) {
            jogadas++;
            M5.setText(J);
            JogadorAtivo();
            Atualizar();
        }
    }//GEN-LAST:event_M5ActionPerformed

    private void M6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M6ActionPerformed
        if (M6.getText().equals("") && ganhador == false) {
            jogadas++;
            M6.setText(J);
            JogadorAtivo();
            Atualizar();
        }
    }//GEN-LAST:event_M6ActionPerformed

    private void M2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M2ActionPerformed
        if (M2.getText().equals("") && ganhador == false) {
            jogadas++;
            M2.setText(J);
            JogadorAtivo();
            Atualizar();
        }
    }//GEN-LAST:event_M2ActionPerformed

    private void M3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M3ActionPerformed
        if (M3.getText().equals("") && ganhador == false) {
            jogadas++;
            M3.setText(J);
            JogadorAtivo();
            Atualizar();
        }
    }//GEN-LAST:event_M3ActionPerformed

    private void btn_RelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RelatoriosActionPerformed
        if (button == 0) {
            for (int i = 0; i < 2; i++) {//COPIA E PREPARA MAT A
                for (int j = 0; j < (Variaveis.rodadas); j++) {
                    MatrizR[i][j] = Mresultado[i][j];
                    Variaveis.Msoma += Mresultado[i][j];
                }
            }
            for (int i = 0; i < Variaveis.rodadas; i++) {
                Variaveis.matA1 += String.format("<td><center> %s </center></td>", MatrizR[0][i]);
                Variaveis.matA2 += String.format("<td><center> %s </center></td>", MatrizR[1][i]);
            }

            //*********************CHAMAR METODOS PARA GERAR MATRIZ*****************
            Inverter();
            Transposta();
            Adicao();
            Subtracao();
            Variaveis.convertaBin(Variaveis.Msoma);
            Variaveis.convertaOct(Variaveis.Msoma);
            Variaveis.convertaHex(Variaveis.Msoma);
            Multiplicacao();
        }
        button++;
        telaRelatorios jpRelatorios = new telaRelatorios();
        jpRelatorios.setVisible(true);
    }//GEN-LAST:event_btn_RelatoriosActionPerformed

    private void btn_NovoJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NovoJogoActionPerformed
        button = 0;
        telaConfiguracao jpConfiguracao = new telaConfiguracao();
        jpConfiguracao.setVisible(true);
        this.dispose();
        Variaveis.LimparMatriz();
    }//GEN-LAST:event_btn_NovoJogoActionPerformed

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
            java.util.logging.Logger.getLogger(telaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaJogo().setVisible(true);
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Dica;
    private javax.swing.JLabel JL_Empates;
    private javax.swing.JLabel JL_J1;
    private javax.swing.JLabel JL_J2;
    private javax.swing.JLabel JL_Rodada;
    private javax.swing.JLabel JL_jogadas;
    private javax.swing.JLabel JL_pontosJ1;
    private javax.swing.JLabel JL_pontosJ2;
    private javax.swing.JLabel JL_totalRodadas;
    private javax.swing.JLabel JL_vez;
    private javax.swing.JLabel JL_vezSimbolo;
    private javax.swing.JLabel JL_vitoriasJ1;
    private javax.swing.JLabel JL_vitoriasJ2;
    public javax.swing.JButton M1;
    public javax.swing.JButton M2;
    public javax.swing.JButton M3;
    public javax.swing.JButton M4;
    public javax.swing.JButton M5;
    public javax.swing.JButton M6;
    public javax.swing.JButton M7;
    public javax.swing.JButton M8;
    public javax.swing.JButton M9;
    private javax.swing.JButton btn_NovoJogo;
    private javax.swing.JButton btn_Relatorios;
    private javax.swing.JButton btn_Voltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jp_jogadores;
    private javax.swing.JPanel jp_jogo;
    // End of variables declaration//GEN-END:variables
}
