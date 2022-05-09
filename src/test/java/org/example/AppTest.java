package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class AppTest 
{
    @Test
    public void testSortHands() {
        List<Hand> hands1 = new ArrayList<>();

        String cards1 = "3C 3S 4D 5D AS";// две тройки
        String cards2 = "TC KS AD 5H 7S";// старшая карта - A потом K
        String cards3 = "JC 8C 4H 5C AH";// старшая карта - A потом J
        String cards4 = "4C 6C 5S 8D 7H";// последовательность
        String cards5 = "2D 2H 3H QS AC";// две двойки, старшая карта - туз
        String cards6 = "2S TS 6S 9H 2C";// две двойки, старшая карта - десятка

        hands1.add(new Hand(cards1));
        hands1.add(new Hand(cards2));
        hands1.add(new Hand(cards3));
        hands1.add(new Hand(cards4));
        hands1.add(new Hand(cards5));
        hands1.add(new Hand(cards6));

        Collections.sort(hands1);

        Assert.assertEquals(
                List.of(cards3, cards2, cards6, cards5, cards1, cards4),
                hands1.stream().map(Hand::toString).collect(Collectors.toList())
        );
    }

    @Test
    public void testSortHands2() {
        List<Hand> hands1 = new ArrayList<>();
        String cards1 = "6C 7S 8H 9D TS";// последовательность с 6
        String cards2 = "TC JD TH TD JS";// тройка и двойка
        String cards3 = "4C 6H 5S 8D 7H";// последовательность с 4
        String cards4 = "2C 2S 2H 3D 3S";// тройка и двойка

        hands1.add(new Hand(cards1));
        hands1.add(new Hand(cards2));
        hands1.add(new Hand(cards3));
        hands1.add(new Hand(cards4));

        Collections.sort(hands1);

        assertEquals(cards3, hands1.get(0).toString());
        assertEquals(cards1, hands1.get(1).toString());
        assertEquals(cards4, hands1.get(2).toString());
        assertEquals(cards2, hands1.get(3).toString());
    }

    @Test
    public void testAllHandCombinations() {
        String cards = "TC KS AD 5H 7S";
        Hand hand1 = new Hand(cards);
        assertEquals(HandCombination.HIGH_CARD, hand1.getHandCombination());

        cards = "3C 3S 4D 5D AS";
        Hand hand2 = new Hand(cards);
        assertEquals(HandCombination.PAIR, hand2.getHandCombination());

        cards = "TC TS 8D 8C AS";
        Hand hand3 = new Hand(cards);
        assertEquals(HandCombination.TWO_PAIRS, hand3.getHandCombination());

        cards = "TC 8S 8D 8C AS";
        Hand hand4 = new Hand(cards);
        assertEquals(HandCombination.THREE_OF_A_KIND, hand4.getHandCombination());

        cards = "TC JS QD KC AS";
        Hand hand5 = new Hand(cards);
        assertEquals(HandCombination.STRAIGHT, hand5.getHandCombination());

        cards = "TC TS QD TD QS";
        Hand hand6 = new Hand(cards);
        assertEquals(HandCombination.FULL_HOUSE, hand6.getHandCombination());

        cards = "TC TS TH TD QS";
        Hand hand7 = new Hand(cards);
        assertEquals(HandCombination.FOUR_OF_A_KIND, hand7.getHandCombination());
    }
}
