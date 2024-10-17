import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class GameGUI {

    static JFrame gameFrame;
    static JLabel dealerValue;
    static JLabel playerValue;
    static JLabel playerChips;
    static JLabel betLabel2;
    static JPanel mainPanel;
    GridBagConstraints gbc;
    static ArrayList<String> dealerCards = new ArrayList<>();
    static ArrayList<String> playerCards = new ArrayList<>();
    static JPanel dealerCardPanel;
    static JPanel playerCardPanel;
    JFrame nextFrame;
    static JLabel cardLabel;
    static Card cardObj = new Card();

    public void shuffleGUI(){
        
        //main frame
        gameFrame = new JFrame("HighSum Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //gif and photo for shuffle
        ImageIcon shuffle = new ImageIcon("images/ShuffleGIF.gif");
        ImageIcon back = new ImageIcon("images/back.png");

        // Create a scaled instance of the shuffle GIF
        Image scaledShuffleImage = shuffle.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        ImageIcon scaledShuffle = new ImageIcon(scaledShuffleImage);

        //label for "Shuffling..." and align center
        JLabel shuffleLabel = new JLabel(scaledShuffle);
        JLabel shufflingLabel = new JLabel("Shuffling...");
        shufflingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //shuffle panel for the gif
        JPanel shufflePanel = new JPanel();
        shufflePanel.setBackground(new Color(0, 153, 0));
        shufflePanel.setPreferredSize(new Dimension(400, 450));
        shufflePanel.add(shuffleLabel);

        //shuffling panel for text
        JPanel shufflingPanel = new JPanel();
        shufflingPanel.setBackground(new Color(0, 153, 0));
        shufflingPanel.setPreferredSize(new Dimension(400, 50)); 
        shufflingPanel.add(shufflingLabel);

        //bacnkPanel for adding the card photos for shuffling
        JPanel backPanel = new JPanel();
        backPanel.setBackground(new Color(0, 153, 0));
        backPanel.setPreferredSize(new Dimension(900, 225)); 
        for (int i = 0; i < 6; i++) {
            JLabel cardLabel = new JLabel(back);
            backPanel.add(cardLabel);
        }

        //main panel
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(0, 153, 0));
        gbc = new GridBagConstraints();

        //adjust shuffle gif
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.PAGE_START; 
        mainPanel.add(shufflePanel, gbc);

        //adjust shuffling text
        gbc.gridy = 1; 
        gbc.anchor = GridBagConstraints.CENTER; 
        mainPanel.add(shufflingPanel, gbc);

        //adjust back pic
        gbc.gridy = 2; 
        gbc.anchor = GridBagConstraints.LINE_START; 
        gbc.fill = GridBagConstraints.NONE; 
        mainPanel.add(backPanel, gbc);

        //gram frame set visible
        gameFrame.add(mainPanel);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    public void run(){
        //repaint
        mainPanel.removeAll();
        gameFrame.revalidate();
        gameFrame.repaint();

        //deck pic
        ImageIcon deck = new ImageIcon("images/Deck.png");
        Image scaledDeck = deck.getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT);
        ImageIcon scaledDeckImage = new ImageIcon(scaledDeck);

        //bet on table pic
        ImageIcon BOT = new ImageIcon("images/test.png");
        Image scaledBOT = BOT.getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT);
        ImageIcon scaledBOTImage = new ImageIcon(scaledBOT);
                                                                                                                                                                        
        //display dealer cards
        String card = cardObj.drawCard();
        String anotherCard = cardObj.drawCard();
        ImageIcon card1 = new ImageIcon("images/back.png");
        Image card1Pic = card1.getImage().getScaledInstance(130, 170, Image.SCALE_DEFAULT);
        ImageIcon scaledCard1 = new ImageIcon(card1Pic);
        ImageIcon card2 = new ImageIcon("images/"+anotherCard+".png");
        Image card2Pic = card2.getImage().getScaledInstance(130, 170, Image.SCALE_DEFAULT);
        ImageIcon scaledCard2 = new ImageIcon(card2Pic);

        //display player cards
        String playerCard = cardObj.drawCard();
        String anotherPlayerCard = cardObj.drawCard();
        ImageIcon card3 = new ImageIcon("images/"+playerCard+".png");
        Image card3Image = card3.getImage().getScaledInstance(130, 170, Image.SCALE_DEFAULT);
        ImageIcon scaledCard3 = new ImageIcon(card3Image);
        ImageIcon card4 = new ImageIcon("images/"+anotherPlayerCard+".png");
        Image card4Image = card4.getImage().getScaledInstance(130, 170, Image.SCALE_DEFAULT);
        ImageIcon scaledCard4 = new ImageIcon(card4Image);

        //add cards to arrayList 
        dealerCards.add(card);
        dealerCards.add(anotherCard);
        playerCards.add(playerCard);
        playerCards.add(anotherPlayerCard);

        //add all values
        new Card().addDealerValues(dealerCards);
        new Card().addValues(playerCards);

        //labels for deck
        JLabel deckLabel = new JLabel(scaledDeckImage);
        JLabel deckLabel2 = new JLabel("Deck");
        deckLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        //labels for bet
        JLabel betLabel = new JLabel(scaledBOTImage);
        betLabel2 = new JLabel("Chips on Table: " + Bet.chipsOnTable);

        //labels for cardPics
        cardLabel = new JLabel(scaledCard1);
        JLabel cardLabel2 = new JLabel(scaledCard2);
        JLabel cardLabel3 = new JLabel(scaledCard3);
        JLabel cardLabel4 = new JLabel(scaledCard4);

        //label for "Dealer" and "Player"
        JLabel dealerLabel = new JLabel("Dealer");
        dealerLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel playerLabel = new JLabel("Player");

        //label for values and chips 
        dealerValue = new JLabel("Value: " + Card.dealerValues);
        dealerValue.setVisible(false);
        playerValue = new JLabel("Value: " + Card.values);
        playerChips = new JLabel("Balance Chips: " + Bet.chips);

        //panel for deck
        JPanel deckPanel = new JPanel(new BorderLayout());
        deckPanel.setBackground(new Color(0, 153, 0));
        deckPanel.add(deckLabel, BorderLayout.CENTER);
        deckPanel.add(deckLabel2, BorderLayout.PAGE_END);

        //panel for bet
        JPanel BetPanel = new JPanel(new BorderLayout());
        BetPanel.setPreferredSize(new Dimension(120, 120));
        BetPanel.setBackground(new Color(0, 153, 0));
        BetPanel.add(betLabel, BorderLayout.CENTER);
        BetPanel.add(betLabel2, BorderLayout.PAGE_END);

        //panel for dealer's cards
        dealerCardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dealerCardPanel.setBackground(new Color(0, 153, 0));
        dealerCardPanel.add(cardLabel);
        dealerCardPanel.add(cardLabel2);

        //panel for dealer info
        JPanel dealerCenterPanel = new JPanel();
        dealerCenterPanel.setBackground(new Color(0, 153, 0));
        dealerCenterPanel.add(dealerLabel);
        dealerCenterPanel.add(dealerValue);
        dealerLabel.setVerticalTextPosition(SwingConstants.TOP);
        dealerValue.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        //main dealer panel
        JPanel dealerPanel = new JPanel(new BorderLayout());
        dealerPanel.setBackground(new Color(0, 153, 0));
        dealerPanel.setPreferredSize(new Dimension(800, 210));
        dealerPanel.add(dealerCardPanel, BorderLayout.PAGE_START);
        dealerPanel.add(dealerCenterPanel, BorderLayout.CENTER);

        //panel for player's cards
        playerCardPanel= new JPanel();
        playerCardPanel.setBackground(new Color(0, 153, 0));
        playerCardPanel.setPreferredSize(new Dimension(800, 210));
        playerCardPanel.add(cardLabel3);
        playerCardPanel.add(cardLabel4);

        //adjust deckPanel
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(deckPanel, gbc);

        //adjust bet panel
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(BetPanel, gbc);

        //adjust dealer's panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(dealerPanel,gbc);

        //adjust player's cards panel
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        mainPanel.add(playerCardPanel,gbc);

        //adjust "player" panel
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.SOUTH;
        mainPanel.add(playerLabel,gbc);

        //adjust player value label
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.SOUTH;
        mainPanel.add(playerValue,gbc);

        //adjust player chips label
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.SOUTH;
        mainPanel.add(playerChips,gbc);

        //compare cards
        new Card().compareValue(anotherCard, anotherPlayerCard);
        
        //add to main frame
        gameFrame.add(mainPanel);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    public static void addCard(){
        
        String card = cardObj.drawCard();
        ImageIcon cardpic = new ImageIcon("images/"+card+".png");
        Image cardImage = cardpic.getImage().getScaledInstance(130, 170, Image.SCALE_DEFAULT);
        ImageIcon cardIcon = new ImageIcon(cardImage);
        JLabel cardJLabel = new JLabel(cardIcon);
        dealerCardPanel.add(cardJLabel);
        dealerCards.add(card);
        new Card().addDealerValues(dealerCards);

        String card2 = cardObj.drawCard();
        ImageIcon cardpic2 = new ImageIcon("images/"+card2+".png");
        Image cardImage2 = cardpic2.getImage().getScaledInstance(130, 170, Image.SCALE_DEFAULT);
        ImageIcon cardIcon2 = new ImageIcon(cardImage2);
        JLabel cardJLabel2 = new JLabel(cardIcon2);
        playerCardPanel.add(cardJLabel2);
        playerCards.add(card2);
        new Card().addValues(playerCards);

        new Card().compareValue(card, card2);
    }

    public static void updateChips(int chips){
        playerChips.setText("Balance chips: "+ Bet.chips);
    }

    public static void updateChipsOnTable(int betAmount){
        betLabel2.setText("Chips on Table: " + Bet.chipsOnTable);
    }

    public static void updateValue(){
        playerValue.setText("Value: " + Card.values);
        dealerValue.setText("Value: " + Card.dealerValues);
    }

    public void nextGame() {
        nextFrame = new JFrame("HighSum Game");
        JPanel nextPanel = new JPanel(new GridBagLayout());
        nextPanel.setPreferredSize(new Dimension(200, 70));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components
    
        String result;
        if (Card.values > Card.dealerValues) {
            result = "You Win!";
        } else if (Card.values == Card.dealerValues) {
            result = "It's a tie!";
        } else {
            result = "You Lose!";
        }
    
        JLabel nexLabel = new JLabel(result + "Next Game?");
        nexLabel.setPreferredSize(new Dimension(150, 30)); // Adjust label size
    
        JButton play = new JButton("Play Now!");
        play.addActionListener(new ButtonListener());
    
        JButton quit = new JButton("Quit");
        quit.addActionListener(new ButtonListener2());
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        nextPanel.add(nexLabel, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; 
        gbc.anchor = GridBagConstraints.WEST;
        nextPanel.add(play, gbc);
    
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        nextPanel.add(quit, gbc);
    
        nextFrame.add(nextPanel);
        nextFrame.pack();
        nextFrame.setVisible(true);
    }
    

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            nextFrame.dispose();
            dealerCards.clear();
            playerCards.clear();
            Bet.chips = 100;
            Bet.chipsOnTable = 0;
            gameFrame.dispose();
    
            shuffleGUI();
    
            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    run();
                }
            });
            timer.setRepeats(false); 
            timer.start();
        }
    }
    
    
    private class ButtonListener2 implements ActionListener
    {
        public void actionPerformed(ActionEvent event){
            nextFrame.dispose();
            gameFrame.dispose();
        }
    }

    public void dealerReveal(String card){
        ImageIcon image = new ImageIcon("images/"+card+".png");
        Image scaleImage = image.getImage().getScaledInstance(130, 170, Image.SCALE_DEFAULT);
        ImageIcon scaledImage = new ImageIcon(scaleImage);
        cardLabel.setIcon(scaledImage);
        dealerValue.setVisible(true);
    }

}


