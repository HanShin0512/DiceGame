import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Card {

    public static int values;
    public static int dealerValues;
    private List<String> cards;
    String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    String[] suits = {"Heart", "Diamond", "Club", "Spade"};

    public Card() {
        
        cards = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(suit + rank);
            }
        }
    }

    public String drawCard() {
        Random random = new Random();
        int index = random.nextInt(cards.size());
        return cards.remove(index);
    }

    public String getCardRank(String card) {
        String rank = card.replaceAll("Heart|Diamond|Club|Spade", "").trim();
        return rank;
    }

    public int getCardValue(String card) {
        String rank = getCardRank(card);
        int value = 0;
        
        if (rank.equals("Ace")) {
            value = 1; 
        } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
            value = 10;
        } else {
            value = Integer.parseInt(rank);
        }
        
        return value;
    }

    public void compareValue(String card1, String card2) {
        int value1 = getCardValue(card1);
        int value2 = getCardValue(card2);

        if(Integer.compare(value1, value2)<0){
            new Bet().bet();
        } else if(Integer.compare(value1, value2)>0){
            new Bet().dealerBet();
        } else {
            if(getRank(card1).equals(card2)){
                if(new Card().compareSuit(card1, card2).equals("player wins")){
                    new Bet().bet();
                } else if(new Card().compareSuit(card1, card2).equals("dealer wins")){
                    new Bet().dealerBet();
                }
            } else if(getRank(card1).equals("King")){
                new Bet().dealerBet();
            } else if(getRank(card1).equals("Queen") && !getRank(card2).equals("King")){
                new Bet().dealerBet();
            } else if(getRank(card2).equals("10")){
                new Bet().dealerBet();
            } else {
                new Bet().bet();
            }
            
        }
    }    

    public String getRank(String card) {
        String[] ranks = { "King", "Queen", "Jack", "10"};
    
        for (String rank : ranks) {
            if (card.contains(rank)) {
                return rank;
            }
        }
    
        return card.trim();
    }
    

    public String getSuit(String card) {

        for (String rank : ranks) {
            if (card.endsWith(rank)) {
                return card.substring(0, card.length() - rank.length()).trim();
            }
        }

        return card.trim();
    }
    

    public String compareSuit(String card1, String card2) {
        String rank1 = getSuit(card1);
        String rank2 = getSuit(card2);
    
        if(rank1.equals(rank2)){
            return "tie";
        } else if(rank1.equals("Heart") && !rank2.equals("Spade")){
            return "dealer wins";
        } else if(rank1.equals("Club") && rank2.equals("Diamond")){
            return "dealer wins";
        } else if(rank1.equals("Spade")) {
            return "dealer wins";
        } else {
            return "player wins";
        }
    }    

    public void addValues(ArrayList<String> arrayList){
        Card.values = 0;
        for(String cards : arrayList){
            Card.values += getCardValue(cards);
        }
    }

    public void addDealerValues(ArrayList<String> arrayList){
        Card.dealerValues = 0;
        for(String cards : arrayList){
            Card.dealerValues += getCardValue(cards);
        }
    }

}
