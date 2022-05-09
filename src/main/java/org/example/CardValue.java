package org.example;

/**
 * Номинал карты
 * @author Галина Верхова
 */
enum CardValue {
    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8),
    NINE('9', 9),
    TEN('T', 10),
    JACK('J', 11),
    QUEEN('Q', 12),
    KING('K', 13),
    ACE('A', 14);

    // Сокращенное обозначение карты
    private Character sign;

    // Стоимость карты
    private int value;

    CardValue(Character sign, int value) {
        this.sign = sign;
        this.value = value;
    }

    public Character getSign() {
        return sign;
    }

    public int getValue() {
        return value;
    }
}
