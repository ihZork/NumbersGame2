package ru.ihzork.funnynumbers.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;


import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.GameUtils.Enums;
import ru.ihzork.funnynumbers.GameUtils.MyButton;
import ru.ihzork.funnynumbers.GameUtils.ScreenManager;
import ru.ihzork.funnynumbers.GameUtils.Utils;

import static com.badlogic.gdx.Gdx.*;
import static com.badlogic.gdx.scenes.scene2d.ui.Label.*;
import static ru.ihzork.funnynumbers.GameUtils.Constants.*;
import static ru.ihzork.funnynumbers.GameUtils.Enums.*;
import static ru.ihzork.funnynumbers.GameUtils.MyButton.*;

public class StartScreen extends AbstractScreen {

    Preferences scorePref = app.getPreferences(PREF_HIGH_SCORE);

    private Skin skin;
    private Difficulty difficulty;
    private Label difficultyLabel;
    private Label highScoreLabel;
    private int highScore;
    private MyButton novice,easy,medium,hard,expert,start;
    private MyButtonStyle noviceButtonStyle,easyButtonStyle,mediumButtonStyle,hardButtonStyle,expertButtonStyle, startButtonStyle;
    private BitmapFont font;


    public StartScreen() {
        super();

        skin = new Skin();//Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(TEXTURE_ATLAS));

        difficulty = Difficulty.NOVICE;

        noviceButtonStyle = new MyButtonStyle();
        easyButtonStyle = new MyButtonStyle();
        mediumButtonStyle = new MyButtonStyle();
        hardButtonStyle = new MyButtonStyle();
        expertButtonStyle = new MyButtonStyle();
        startButtonStyle = new MyButtonStyle();


        highScore = 0;// scorePref.getInteger(Constants.KEY_HIGH_SCORE, 0);

    }

    @Override
    public void buildStage() {

        Table table = new Table();
        table.setFillParent(true);
        table.center();


        font = Utils.generateFreeTypeFont(FONT_UBUNTU, 35, Color.WHITE);
        //noviceButtonStyle.imageUp = new
        noviceButtonStyle.font = font;
        noviceButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_NOVICE_OFF)));
        noviceButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_NOVICE_ON)));


        easyButtonStyle.font = font;
        easyButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_EASY_OFF)));
        easyButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_EASY_ON)));

        mediumButtonStyle.font = font;
        mediumButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_MEDIUM_OFF)));
        mediumButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_MEDIUM_ON)));

        hardButtonStyle.font = font;
        hardButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_HARD_OFF)));
        hardButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_HARD_ON)));

        expertButtonStyle.font = font;
        expertButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_EXPERT_OFF)));
        expertButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(BUTTON_EXPERT_ON)));


        novice = new MyButton("Novice",noviceButtonStyle);
        novice.align(Align.left);
        easy = new MyButton("Easy",easyButtonStyle);
        easy.align(Align.left);
        medium= new MyButton("Medium",mediumButtonStyle);
        medium.align(Align.left);
        hard= new MyButton("Hard",hardButtonStyle);
        hard.align(Align.left);
        expert= new MyButton("Expert",expertButtonStyle);
        expert.align(Align.left);

        start= new MyButton("Start",expertButtonStyle);
        start.align(Align.left);


        if (!novice.isChecked()) {
            novice.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!novice.isDisabled()) {
                        difficulty = Difficulty.NOVICE;
                        difficultyLabel.setText(getDifficulty().toString());
                        highScoreLabel.setText(HIGH_SCORE_LABEL +String.valueOf(scorePref.getInteger(getDifficulty().getKeyHighScore(), 0)));
                        novice.isChecked();

                        easy.setChecked(false);
                        medium.setChecked(false);
                        hard.setChecked(false);
                        expert.setChecked(false);
                        //btn_medium.setChecked(false);
                        //btn_hard.setChecked(false);


                        easy.setDisabled(false);
                        medium.setDisabled(false);
                        hard.setDisabled(false);
                        expert.setDisabled(false);
                        //btn_medium.setDisabled(false);
                        //btn_hard.setDisabled(false);
                    }
                    novice.setDisabled(true);
                    event.stop();
                }
            });
        }


        if (!easy.isChecked()) {
            easy.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!easy.isDisabled()) {
                        difficulty = Difficulty.EASY;
                        difficultyLabel.setText(getDifficulty().toString());
                        highScoreLabel.setText(HIGH_SCORE_LABEL +String.valueOf(scorePref.getInteger(getDifficulty().getKeyHighScore(), 0)));
                        easy.isChecked();


                        novice.setChecked(false);
                        medium.setChecked(false);
                        hard.setChecked(false);
                        expert.setChecked(false);

                        novice.setDisabled(false);
                        medium.setDisabled(false);
                        hard.setDisabled(false);
                        expert.setDisabled(false);
                    }
                    easy.setDisabled(true);
                    event.stop();
                }
            });
        }

        if (!medium.isChecked()) {
            medium.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!medium.isDisabled()) {
                        difficulty = Difficulty.MEDIUM;
                        difficultyLabel.setText(getDifficulty().toString());
                        highScoreLabel.setText(HIGH_SCORE_LABEL +String.valueOf(scorePref.getInteger(getDifficulty().getKeyHighScore(), 0)));
                        medium.isChecked();

                        easy.setChecked(false);
                        novice.setChecked(false);
                        hard.setChecked(false);
                        expert.setChecked(false);

                        easy.setDisabled(false);
                        novice.setDisabled(false);
                        hard.setDisabled(false);
                        expert.setDisabled(false);
                    }
                    medium.setDisabled(true);
                    event.stop();
                }
            });
        }

        if (!hard.isChecked()) {
            hard.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!hard.isDisabled()) {
                        difficulty = Difficulty.HARD;
                        difficultyLabel.setText(getDifficulty().toString());
                        highScoreLabel.setText(HIGH_SCORE_LABEL +String.valueOf(scorePref.getInteger(getDifficulty().getKeyHighScore(), 0)));
                        hard.isChecked();

                        easy.setChecked(false);
                        novice.setChecked(false);
                        medium.setChecked(false);
                        expert.setChecked(false);

                        easy.setDisabled(false);
                        novice.setDisabled(false);
                        medium.setDisabled(false);
                        expert.setDisabled(false);
                    }
                    hard.setDisabled(true);
                    event.stop();
                }
            });
        }

        if (!expert.isChecked()) {
            expert.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!expert.isDisabled()) {
                        difficulty = Difficulty.EXPERT;
                        difficultyLabel.setText(getDifficulty().toString());
                        highScoreLabel.setText(HIGH_SCORE_LABEL +String.valueOf(scorePref.getInteger(getDifficulty().getKeyHighScore(), 0)));
                        expert.isChecked();

                        easy.setChecked(false);
                        novice.setChecked(false);
                        medium.setChecked(false);
                        hard.setChecked(false);

                        easy.setDisabled(false);
                        novice.setDisabled(false);
                        medium.setDisabled(false);
                        hard.setDisabled(false);
                    }
                    expert.setDisabled(true);
                    event.stop();
                }
            });
        }

        final ScaleToAction scaleScore = Actions.action(ScaleToAction.class);
        scaleScore.setScale(1.5f);
        scaleScore.setDuration(5.0f);

        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Screen.GAME_SCREEN, difficulty);
                start.addAction(scaleScore);
                event.stop();
            }
        });

        BitmapFont font = Utils.generateFreeTypeFont(FONT_UBUNTU, 50, Color.WHITE);
        LabelStyle difficultyLabelStyle = new LabelStyle(font, Color.YELLOW);
        difficultyLabel = new Label(getDifficulty().toString(), difficultyLabelStyle);

        LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);
        Label subtitleLabelText = new Label(SUBTITLE_LABEL, labelStyle);


        BitmapFont highScoreFont = Utils.generateFreeTypeFont(FONT_UBUNTU, 30, Color.WHITE);
        LabelStyle highScoreLabelStyle = new LabelStyle(highScoreFont, Color.WHITE);
        highScoreLabel = new Label(HIGH_SCORE_LABEL + highScore, highScoreLabelStyle);

        subtitleLabelText.setFontScale(2);
        subtitleLabelText.setAlignment(Align.center);
        table.add(subtitleLabelText).padLeft(50).padRight(50).padBottom(100).colspan(3).center();
        table.row().pad(4);
        table.add(novice).padLeft(50).padRight(50).colspan(3);
        table.row().pad(4);
        table.add(easy).padLeft(50).padRight(50).colspan(3);
        table.row().pad(4);
        table.add(medium).padLeft(50).padRight(50).colspan(3);
        table.row().pad(4);
        table.add(hard).padLeft(50).padRight(50).colspan(3);
        table.row().pad(4);
        table.add(expert).padLeft(50).padRight(50).colspan(3);
        table.row();

        table.add(difficultyLabel).padTop(50).padLeft(50).padRight(50).padBottom(100).colspan(3).center();

        table.row();
        table.add(highScoreLabel).padTop(50).padBottom(70).colspan(3);
        table.row();
        table.add(start).colspan(3).expand().center();
        //table.debug();


        addActor(table);
    }
    @Override
    public void dispose() {
        super.dispose();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
