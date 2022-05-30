package com.pap.fishes.papfishes;

import java.util.Collections;
import java.util.Vector;

public class FishList {
    private Vector<Fish> list;
    int index;
    boolean empty;
    public FishList(Vector<Fish> fishlist){
        if (fishlist.isEmpty())
            empty = true;
        else {
            list = fishlist;
            index = 0;
            empty = false;
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public int getIndex() {
        return index;
    }
    public Fish getCurrentFish(){
        return list.get(index);
    }
    public Fish getNextFish(){
        if (index == list.size() - 1){
            index = 0;
        }
        else {
            index++;
        }
        return list.get(index);
    }
    public Fish getPreviousFish(){
        if (index == 0){
            index = list.size() - 1;
        }
        else {
            index--;
        }
        return list.get(index);
    }
    public void shuffle(){
        Collections.shuffle(list);
    }
}
