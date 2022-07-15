package com.fptedu.soc.tarottemp;

public class Card {
    private String card;
    private String category;
    private String career;
    private String love;
    private String finance;
    private String image;
    private String image2;

    public Card(String card, String category, String career, String love, String finance, String image, String image2) {
        super();
        this.card = card;
        this.category = category;
        this.career = career;
        this.love = love;
        this.finance = finance;
        this.image = image;
        this.image2 = image2;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getFinance() {
        return finance;
    }

    public void setFinance(String finance) {
        this.finance = finance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

}
