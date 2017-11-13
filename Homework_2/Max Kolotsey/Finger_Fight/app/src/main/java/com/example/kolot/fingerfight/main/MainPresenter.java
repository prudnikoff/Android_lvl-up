package com.example.kolot.fingerfight.main;

/**
 * Created by kolot on 27.10.2017.
 */

public class MainPresenter {

    private MainView view;

    public void bindView(MainView mainView){
        this.view = mainView;
    }

    public void startAnim (){
        view.loadAnim();
    }

    public void sendIntent (){
        view.startGame();
    }

    public void quit(){
        view.quitGame();
    }

    public void openSettings(){
        view.openSettings();
    }

}
