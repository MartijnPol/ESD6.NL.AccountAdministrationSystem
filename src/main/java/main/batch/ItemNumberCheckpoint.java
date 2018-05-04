package main.batch;

import java.io.Serializable;

/**
 * @author Thom van de Pas on 25-4-2018
 */
/* Checkpoint class for the chunk batch artifacts */
public class ItemNumberCheckpoint implements Serializable {

    private long itemNumber;
    private long numItems;

    public ItemNumberCheckpoint() {
        itemNumber = 0;
    }

    public ItemNumberCheckpoint(int numItems) {
        this();
        this.numItems = numItems;
    }

    public long getItemNumber() {
        return itemNumber;
    }

    public void setNumItems(long numItems) {
        this.numItems = numItems;
    }

    public long getNumItems() {
        return numItems;
    }

    public void nextItem() {
        itemNumber++;
    }

    public void setItemNumber(long item) {
        itemNumber = item;
    }
}