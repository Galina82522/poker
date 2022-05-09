package org.example;

import java.util.List;

/**
 * Результаты произведенных вычислений в руке для последующего ранжирования рук
 * @author Галина Верхова
 */
class HandCalculation {
    // Вычисленная комбинация руки
    private HandCombination handCombination;

    // Отсортированный список по убыванию, содержащий номиналы карт в руке с их количеством.
    // Сортировка производится по количеству карт одного достоинства, если количество совпадает,
    // то сортировка производится по номиналу карты.
    private List<CardCount> sortedCardCounts;

    public HandCombination getHandCombination() {
        return handCombination;
    }

    public void setHandCombination(HandCombination handCombination) {
        this.handCombination = handCombination;
    }

    public List<CardCount> getSortedCardCounts() {
        return sortedCardCounts;
    }

    public void setSortedCardCounts(List<CardCount> sortedCardCounts) {
        this.sortedCardCounts = sortedCardCounts;
    }
}