package com.pap.fishes.papfishes;

import java.util.Collections;
import java.util.Vector;

public class FishList {
    private Vector<Fish> list;
    private int index;
    private boolean empty;
    private FishList sourceList;

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
    public boolean isAnyRepeat() {
        for (Fish f: list){
            if(f.isRepeat())
                return true;
        }
        return false;
    }
    public boolean hasSourceList(){
        if (sourceList == null)
            return false;
        return true;
    }

    public void setSourceList(FishList sourceList) {
        this.sourceList = sourceList;
    }
    public void setAllRepeat(boolean if_repeat){
        for (Fish f : list){
            f.setRepeat(if_repeat);
        }
    }

    public int getIndex() {
        return index;
    }
    public FishList getSourceList() { return sourceList; }

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

    public FishList getRepeatList(){
        Vector<Fish> newList = new Vector<>();
        for (Fish f : list) {
            if (f.isRepeat())
                newList.add(f);
        }
        FishList newFishList = new FishList(newList);
        newFishList.setSourceList(this);
        return  newFishList;
    }
}
