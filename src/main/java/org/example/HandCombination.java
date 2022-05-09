package org.example;

/**
 * Возможные комбинации в покере
 * @author Галина Верхова
 */
enum HandCombination {
    HIGH_CARD(1, new int[] {0, 0, 0, 5}),
    PAIR(2, new int[] {0, 0, 1, 3}),
    TWO_PAIRS(3, new int[] {0, 0, 2, 1}),
    THREE_OF_A_KIND(4, new int[] {0, 1, 0, 2}),
    STRAIGHT(5, new int[] {0, 0, 0, 5}),
    FULL_HOUSE(6, new int[] {0, 1, 1, 0}),
    FOUR_OF_A_KIND(7, new int[] {1, 0, 0, 1});

    // Стоимость комбинации
    private int value;

    // Массив, содержащий количество повторений наборов карт одного достоинства,
    // т.е. сколько содержится в руке наборов по четыре карты, по три карты,
    // по две карты и по одной карте (нужно для вычисления комбинации).
    private int[] kitCount;

    HandCombination(int value, int[] kitCount) {
        this.value = value;
        this.kitCount = kitCount;
    }

    public int[] getKitCount() {
        return kitCount;
    }

    public int getValue() {
        return value;
    }
}