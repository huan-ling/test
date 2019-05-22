package com.blizzard.manage.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-21 15:52
 */
public class DeckCard implements Serializable {

    private static final long serialVersionUID = 2497604872118546977L;

    private int id;
    private int deckId;
    private int cardId;
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "DeckCard{" +
                "id=" + id +
                ", deckId=" + deckId +
                ", cardId=" + cardId +
                ", num=" + num +
                '}';
    }
}
