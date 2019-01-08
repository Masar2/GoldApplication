package com.ehb.masar.goldapplicatie.domain;

public class Rating extends Entity {


    private Float rating ;
    private  Product product ;

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
