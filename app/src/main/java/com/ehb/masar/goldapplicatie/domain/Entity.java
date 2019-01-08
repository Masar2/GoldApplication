package com.ehb.masar.goldapplicatie.domain;



/**
 * Provides an id field for use with entities
 */
abstract class Entity {

    private long id;

    Entity()
    {
        this.id = -1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
