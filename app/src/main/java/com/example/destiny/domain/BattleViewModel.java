package com.example.destiny.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BattleViewModel extends ViewModel {

    // live data so the UI can be updated throughout the execution of battle
    private final MutableLiveData<BattleResult> battleResult = new MutableLiveData<>();

    public LiveData<BattleResult> getBattleResult() {
        return this.battleResult;
    }

    public void updateBattleResult(BattleResult battleResult) {
        this.battleResult.setValue(new BattleResult(
                battleResult.adventurer,
                battleResult.enemy,
                battleResult.actionLogText,
                battleResult.battleState
        ));
    }
}