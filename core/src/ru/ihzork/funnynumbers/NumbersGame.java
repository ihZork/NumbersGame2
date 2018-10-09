package ru.ihzork.funnynumbers;

import com.badlogic.gdx.Game;

import ru.ihzork.funnynumbers.GameUtils.Enums;
import ru.ihzork.funnynumbers.GameUtils.ScreenManager;


public class NumbersGame extends Game {

	@Override
	public void create () {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(Enums.Screen.START_SCREEN);
	}
}
