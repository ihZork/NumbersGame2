package ru.ihzork.funnynumbers.GameUtils;

import com.badlogic.gdx.Screen;

import ru.ihzork.funnynumbers.NumbersGame;
import ru.ihzork.funnynumbers.Screens.AbstractScreen;

public class ScreenManager {

    private NumbersGame game;

    // Singleton - unique instance
    private static ScreenManager instance;

    private ScreenManager() {
        super();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initialize(NumbersGame game) {
        this.game = game;
    }

    // Use enum to retrieve screen
    public void showScreen(Enums.Screen screenEnum, Object... params) {

        // Get current screen to dispose of
        Screen currentScreen = game.getScreen();

        // Show new screen
        AbstractScreen newScreen = screenEnum.getScreen(params);
        newScreen.buildStage();
        game.setScreen(newScreen);

        // Dispose old screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }

    }

}
