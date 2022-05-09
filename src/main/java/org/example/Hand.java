package org.example;

import java.util.*;

/**
 * Рука в покере
 *
 * @author Галина Верхова
 */
class Hand implements Comparable<Hand> {
    private static final int countOfCards = 5;
    private final Card[] cards = new Card[countOfCards];
    private final String strCards;

    public Hand(String cards) {
        strCards = cards;
        String[] splitCards = cards.split(" ");

        for (int i = 0; i < countOfCards; i++) {
            String stringCard = splitCards[i];
            Character first = stringCard.charAt(0);
            Character second = stringCard.charAt(1);

            Card card = new Card();

            boolean isValidSymbol = false;
            for (CardValue cardValue : CardValue.values()) {
                if (cardValue.getSign().equals(first)) {
                    card.setCardValue(cardValue);
                    isValidSymbol = true;
                }
            }

            if (!isValidSymbol)
                throw new RuntimeException("Недействительный символ в  переданной строке!");

            isValidSymbol = false;
            for (CardSuit cardSuit : CardSuit.values()) {
                if (((Character) cardSuit.toString().charAt(0)).equals(second)) {
                    card.setSuit(cardSuit);
                    isValidSymbol = true;
                }
            }
            if (!isValidSymbol)
                throw new RuntimeException("Недействительный символ в переданной строке!");

            this.cards[i] = card;
        }
    }

    @Override
    public String toString() {
        return strCards;
    }

    @Override
    public int compareTo(Hand hand) {
        HandCalculation handCalculation = getHandCalculation();
        HandCalculation otherHandCalculation = hand.getHandCalculation();

        HandCombination combination = handCalculation.getHandCombination();
        HandCombination otherCombination = otherHandCalculation.getHandCombination();

        int diffCombination = combination.getValue() - otherCombination.getValue();

        if (diffCombination != 0) {
            return diffCombination;
        } else {
            List<CardCount> cardCounts = handCalculation.getSortedCardCounts();
            List<CardCount> otherCardCounts = otherHandCalculation.getSortedCardCounts();

            for (int i = 0; i < countOfCards; i++) {
                CardCount cardCount = cardCounts.get(i);
                CardCount otherCardCount = otherCardCounts.get(i);
                int diffCardCount = cardCount.getCount() - otherCardCount.getCount();

                if (diffCardCount != 0) {
                    return diffCardCount;
                } else {
                    int diffValue = cardCount.getCard().getCardValue().getValue() - otherCardCount.getCard().getCardValue().getValue();
                    if (diffValue != 0) {
                        return diffValue;
                    }
                }
            }

            return 0;
        }
    }

    private List<CardCount> getSortedCardCounts() {
        Map<Card, Integer> counts = new HashMap<>();
        for (Card card : cards) {
            if (counts.containsKey(card)) {
                counts.put(card, counts.get(card) + 1);
            } else {
                counts.put(card, 1);
            }
        }

        List<CardCount> items = new ArrayList<>();
        for (Map.Entry<Card, Integer> item : counts.entrySet()) {
            CardCount cardCount = new CardCount(item.getKey(), item.getValue());
            items.add(cardCount);
        }

        Collections.sort(items);
        Collections.reverse(items);

        return items;
    }

    public HandCombination getHandCombination() {
        return getHandCalculation().getHandCombination();
    }

    private int[] getKitCount(List<CardCount> cardCounts) {
        int[] kitCount = new int[countOfCards - 1];

        for (CardCount cardCount : cardCounts) {
            kitCount[countOfCards - 1 - cardCount.getCount()]++;
        }

        return kitCount;

    }

    private HandCalculation getHandCalculation() {
        List<CardCount> cardCounts = getSortedCardCounts();
        HandCalculation handCalculation = new HandCalculation();
        handCalculation.setSortedCardCounts(cardCounts);

        int[] kitCount = getKitCount(cardCounts);

        for (HandCombination handCombination : HandCombination.values()) {
            if ((!handCombination.equals(HandCombination.HIGH_CARD))
                    && (!handCombination.equals(HandCombination.STRAIGHT))
                    && (Arrays.equals(handCombination.getKitCount(), kitCount))) {
                handCalculation.setHandCombination(handCombination);
                return handCalculation;
            }
        }

        Arrays.sort(cards);

        boolean sequence = true;
        for (int i = 0; i < cards.length - 1; i++) {
            if ((cards[i + 1].getCardValue().getValue() - cards[i].getCardValue().getValue()) != 1) {
                sequence = false;
                break;
            }
        }

        if (sequence) {
            handCalculation.setHandCombination(HandCombination.STRAIGHT);
            return handCalculation;
        }

        handCalculation.setHandCombination(HandCombination.HIGH_CARD);

        return handCalculation;
    }
}
