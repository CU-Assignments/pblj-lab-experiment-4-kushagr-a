import java.util.*;

class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class CardCollectionSystem {
    private Map<String, List<Card>> cardMap;

    public CardCollectionSystem() {
        cardMap = new HashMap<>();
    }

    public void addCard(String suit, String rank) {
        cardMap.putIfAbsent(suit, new ArrayList<>());
        List<Card> cards = cardMap.get(suit);
        
        // Check for duplicate
        for (Card card : cards) {
            if (card.getRank().equals(rank)) {
                System.out.println("Error: Card \"" + rank + " of " + suit + "\" already exists.");
                return;
            }
        }
        
        cards.add(new Card(suit, rank));
        System.out.println("Card added: " + rank + " of " + suit);
    }

    public void findCardsBySuit(String suit) {
        if (cardMap.containsKey(suit) && !cardMap.get(suit).isEmpty()) {
            System.out.println("Cards of " + suit + ":");
            for (Card card : cardMap.get(suit)) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found for " + suit + ".");
        }
    }

    public void displayAllCards() {
        if (cardMap.isEmpty()) {
            System.out.println("No cards found.");
            return;
        }
        
        System.out.println("All Cards:");
        for (List<Card> cards : cardMap.values()) {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }

    public void removeCard(String suit, String rank) {
        if (cardMap.containsKey(suit)) {
            List<Card> cards = cardMap.get(suit);
            for (Iterator<Card> iterator = cards.iterator(); iterator.hasNext();) {
                Card card = iterator.next();
                if (card.getRank().equals(rank)) {
                    iterator.remove();
                    System.out.println("Card removed: " + rank + " of " + suit);
                    return;
                }
            }
        }
        System.out.println("Error: Card \"" + rank + " of " + suit + "\" not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollectionSystem ccs = new CardCollectionSystem();
        int choice;
        
        do {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Find Cards by Suit");
            System.out.println("3. Display All Cards");
            System.out.println("4. Remove Card");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Suit: ");
                    String suit = scanner.nextLine();
                    System.out.print("Enter Rank: ");
                    String rank = scanner.nextLine();
                    ccs.addCard(suit, rank);
                    break;

                case 2:
                    System.out.print("Enter Suit to search: ");
                    String searchSuit = scanner.nextLine();
                    ccs.findCardsBySuit(searchSuit);
                    break;

                case 3:
                    ccs.displayAllCards();
                    break;

                case 4:
                    System.out.print("Enter Suit: ");
                    String removeSuit = scanner.nextLine();
                    System.out.print("Enter Rank: ");
                    String removeRank = scanner.nextLine();
                    ccs.removeCard(removeSuit, removeRank);
                    break;

                case 5:
                    System.out.println("Exiting the system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
        
        scanner.close();
    }
}
