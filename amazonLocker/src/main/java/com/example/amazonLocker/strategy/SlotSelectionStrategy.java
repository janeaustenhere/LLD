package com.example.amazonLocker.strategy;

import com.example.amazonLocker.models.Slot;

import java.util.List;

public interface SlotSelectionStrategy {

    Slot selectSlot(List<Slot> eligibleSlots);
}
