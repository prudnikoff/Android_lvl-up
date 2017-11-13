package com.example.kolot.fingerfight.battleField;

/**
 * Created by kolot on 31.10.2017.
 */

public class BattleFieldPresenter {

    private BattleFieldView view;

    public void player_1_click(){
        view.player_1_click();
    }

    public void bindView(BattleFieldView battleFieldView){
        this.view = battleFieldView;
    }

    public void player_2_click(){
        view.player_2_click();
    }

    public void chooseWinner(){
        view.chooseWinner();
    }

    public void setTimer(){
        view.setTimer();
    }

}
