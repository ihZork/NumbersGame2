package ru.ihzork.funnynumbers.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.GameUtils.Enums;
import ru.ihzork.funnynumbers.GameUtils.MyButton;
import ru.ihzork.funnynumbers.GameUtils.MyButton.MyButtonStyle;
import ru.ihzork.funnynumbers.GameUtils.ScreenManager;
import ru.ihzork.funnynumbers.GameUtils.Utils;

import static ru.ihzork.funnynumbers.GameUtils.Constants.BUTTON_EXPERT_OFF;
import static ru.ihzork.funnynumbers.GameUtils.Constants.BUTTON_EXPERT_ON;
import static ru.ihzork.funnynumbers.GameUtils.Constants.BUTTON_NOVICE_OFF;
import static ru.ihzork.funnynumbers.GameUtils.Constants.BUTTON_NOVICE_ON;


public class RestartScreen extends AbstractScreen implements Screen {

    Preferences scorePref = Gdx.app.getPreferences(Constants.PREF_HIGH_SCORE);
    Preferences newHighScore = Gdx.app.getPreferences(Constants.PREF_NEW_HIGH_SCORE);

    private Skin skin;
    private BitmapFont font;
    private int highScore;
    private boolean isNewHighScore;
    private Enums.Difficulty difficulty;
    private MyButton homeButton,playButton;
    private MyButtonStyle homeButtonStyle, playButtonStyle;
    public RestartScreen(Enums.Difficulty difficulty) {
        super();
        this.difficulty = difficulty;

        highScore = scorePref.getInteger(difficulty.getKeyHighScore());
        isNewHighScore = newHighScore.getBoolean(difficulty.getKeyNewHighScore(), false);

        skin = new Skin();
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));


        homeButtonStyle = new MyButtonStyle();
        playButtonStyle = new MyButtonStyle();




    }

    protected RestartScreen() {
        super();
    }

    @Override
    public void buildStage() {
        Table table = new Table(skin);
        table.setFillParent(true);
        table.center();

        font = Utils.generateFreeTypeFont(Constants.FONT_UBUNTU, 40, Color.WHITE);





        LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);
        Label newHighScoreLabel = new Label("NEW", labelStyle);
        newHighScoreLabel.setFontScale(3);
        Label highScoreLabelText = new Label(Constants.HIGH_SCORE_LABEL, labelStyle);
        highScoreLabelText.setFontScale(2);

        Label highScoreLabelNum = new Label(highScore + "", labelStyle);
        highScoreLabelNum.setFontScale(2);



        homeButtonStyle.font = font;
        homeButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_NOVICE_OFF)));
        homeButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_NOVICE_ON)));
        homeButton = new MyButton("HOME",homeButtonStyle);
        homeButton.align(Align.left);


        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Enums.Screen.START_SCREEN);
                event.stop();
            }
        });

        playButtonStyle.font = font;
        playButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_EXPERT_OFF)));
        playButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_EXPERT_ON)));
        playButton = new MyButton("RESTART",playButtonStyle);
        playButton.align(Align.left);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Enums.Screen.GAME_SCREEN, difficulty);
                event.stop();
            }
        });
        if (isNewHighScore) {
            table.row();
            table.add(newHighScoreLabel).pad(10).padTop(25);
        }

        table.row();
        table.add(highScoreLabelText).pad(10).padTop(25);

        table.row();
        table.add(highScoreLabelNum).pad(10).padBottom(25);

        table.row();
        table.add(homeButton).padTop(100).expand();

        table.row();
        table.add(playButton).padBottom(100).expand();

        addActor(table);

    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
        skin.dispose();
    }
}
