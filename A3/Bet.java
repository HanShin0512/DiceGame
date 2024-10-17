import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Bet {

    public static int chipsOnTable;
    JFrame betFrame;
    JFrame dealerBetFrame;
    JPanel panel;
    private JTextField tf;
    public static int chips = 100;

    public void bet(){
        betFrame = new JFrame("HighSum Game");
        betFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Player Call. State bet: ");
        tf = new JTextField(10);
        JButton button = new JButton("OK");
        button.addActionListener(new ButtonListener());

        JPanel panel1 = new JPanel();
        panel1.add(label);
        panel1.add(tf);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(panel1,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTH;
        panel.add(button,gbc);

        betFrame.add(panel);
        betFrame.pack();
        betFrame.setVisible(true);
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event){
            try{
                int playerBet = Integer.parseInt(tf.getText());
                if(Bet.chips<=0 || GameGUI.dealerCards.size()==5){
                    if(playerBet>Bet.chips){
                        JOptionPane.showMessageDialog(null,"Insuffient Chips.","Error",JOptionPane.ERROR_MESSAGE);
                    } else {
                        Bet.chips-=playerBet;
                        GameGUI.updateChips(Bet.chips);
                        Bet.chipsOnTable += (2*playerBet);
                        GameGUI.updateChipsOnTable(playerBet);
                        betFrame.dispose();
                        new GameGUI().dealerReveal(GameGUI.dealerCards.get(0));
                        new GameGUI().nextGame();
                    }
                } else {
                    if(playerBet>Bet.chips){
                        JOptionPane.showMessageDialog(null,"Insuffient Chips.","Error",JOptionPane.ERROR_MESSAGE);
                    } else if(playerBet>0 && playerBet<Bet.chips){
                        Bet.chips-=playerBet;
                        GameGUI.updateChips(Bet.chips);
                        Bet.chipsOnTable += (2*playerBet);
                        GameGUI.updateChipsOnTable(playerBet);
                        betFrame.dispose();
                        GameGUI.addCard();
                        new Card().addDealerValues(GameGUI.dealerCards);
                        new Card().addValues(GameGUI.playerCards);
                        GameGUI.updateValue();
                    } else if(playerBet == Bet.chips) {
                        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to bet all?", "Warning!", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
                        if(option==JOptionPane.OK_OPTION){
                            Bet.chips-=playerBet;
                            GameGUI.updateChips(Bet.chips);
                            Bet.chipsOnTable += (2*playerBet);
                            GameGUI.updateChipsOnTable(playerBet);
                            betFrame.dispose();
                            new GameGUI().dealerReveal(GameGUI.dealerCards.get(0));
                            new GameGUI().nextGame();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Please enter only correct amount", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Enter valid input!", "Error", JOptionPane.ERROR_MESSAGE);
            } 
        }
    }

    public void dealerBet(){
        dealerBetFrame = new JFrame("HighSum Game");
        dealerBetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(200, 70));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Dealer call, place 10 chips.");
        label.setVerticalTextPosition(SwingConstants.CENTER);

        JButton follow = new JButton("Follow");
        follow.addActionListener(new ButtonListener1());

        JButton quit = new JButton("Quit");
        quit.addActionListener(new ButtonListener2());

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(follow,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(quit,gbc);

        dealerBetFrame.add(panel);
        dealerBetFrame.pack();
        dealerBetFrame.setVisible(true);
    }

    private class ButtonListener1 implements ActionListener
    {
        public void actionPerformed(ActionEvent event){

            if(Bet.chips<10 || GameGUI.dealerCards.size()==5){
                if(Bet.chips<10){
                    Bet.chipsOnTable += 2 * Bet.chips;
                    Bet.chips = 0;
                    GameGUI.updateChips(0);
                    GameGUI.updateChipsOnTable(Bet.chipsOnTable);
                } else {
                    Bet.chips -= 10;
                    Bet.chipsOnTable += 20;
                    GameGUI.updateChips(chips);
                    GameGUI.updateChipsOnTable(chipsOnTable);
                }
                dealerBetFrame.dispose();
                new GameGUI().dealerReveal(GameGUI.dealerCards.get(0));
                new GameGUI().nextGame();
                
            } else {
                Bet.chips -= 10;
                Bet.chipsOnTable += 20;
                GameGUI.updateChips(chips);
                GameGUI.updateChipsOnTable(chipsOnTable);
                dealerBetFrame.dispose();
                GameGUI.addCard();
                new Card().addDealerValues(GameGUI.dealerCards);
                new Card().addValues(GameGUI.playerCards);
                GameGUI.updateValue();
            }
        }
    }

    private class ButtonListener2 implements ActionListener
    {
        public void actionPerformed(ActionEvent event){
            dealerBetFrame.dispose();
            new GameGUI().dealerReveal(GameGUI.dealerCards.get(0));
            new GameGUI().nextGame();
        }
    }

}

