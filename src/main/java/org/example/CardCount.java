package org.example;

/**
 * Число карт одного достоинства в руке
 * @author Галина Верхова
 */
class CardCount implements Comparable<CardCount> {
    // Карта
    private Card card;

    // Количество
    private Integer count;

    public CardCount(Card card, Integer count) {
        this.card = card;
        this.count = count;
    }

    public Card getCard() {
        return card;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public int compareTo(CardCount otherCardCount) {
        if (count - otherCardCount.count != 0) {
            return count - otherCardCount.count;
        } else {
            return card.getCardValue().getValue() - otherCardCount.card.getCardValue().getValue();
        }
    }
}
