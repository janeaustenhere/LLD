package com.example.amazonLocker.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SlotSize {

    int length;
    int height;
    int width;

    public boolean canFit(Package packageDetails){
        return  true;
    }

}
