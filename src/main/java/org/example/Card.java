package org.example;

import java.util.Objects;

/**
 * Карта
 * @author Галина Верхова
 */
class Card implements Comparable<Card> {
    // Номинал карты
    private CardValue cardValue;

    // Масть
    private CardSuit suit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardValue == card.cardValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardValue);
    }

    @Override
    public int compareTo(Card otherCard) {
        return this.cardValue.getValue() - otherCard.cardValue.getValue();
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public void setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
    }

    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }
}

