package ru.ihzork.funnynumbers.Overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.GameUtils.Enums;
import ru.ihzork.funnynumbers.GameUtils.InfoLabel;
import ru.ihzork.funnynumbers.GameUtils.Utils;
import ru.ihzork.funnynumbers.Screens.GameplayScreen;

public class InfoHUD {

    GameplayScreen screen;

    public final Viewport viewport;
    final BitmapFont font;

    public Stage stage;
    private InfoLabel scoreLabel;
    private InfoLabel errorLabel;
    private InfoLabel timeLabel;

    //private Button pauseButton;
    private Skin skin;
    private int score,error,time;
    private float hudHeight,buttonsHeight;
    private ImageButton pause;
    //ArrayList<Image> numb = new ArrayList<Image>(9);


    public InfoHUD(final GameplayScreen screen) {
        this.screen = screen;
        this.viewport = new ExtendViewport(Constants.HUD_VIEWPORT_SIZE, Constants.HUD_VIEWPORT_SIZE);
        font = Utils.generateFreeTypeFont(Constants.FONT_UBUNTU, 24, Color.WHITE);
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));
        Image imageError = new Image(new TextureRegion(skin.getRegion("_errorIcon")));
        Image imageCorrect = new Image(new TextureRegion(skin.getRegion("_correctIcon")));
        Image imageTime = new Image(new TextureRegion(skin.getRegion("_timeIcon")));
        Image imagePause = new Image(new TextureRegion(skin.getRegion("_equel")));


        //imagePause.setOrigin(imagePause.getImageWidth()/2,imagePause.getImageHeight()/2);
        //imagePause.setRotation(90);
        //imagePause.setSize(Constants.NUMBERS_SEGMENT_DEFAULT_SIZE/4,Constants.NUMBERS_SEGMENT_DEFAULT_SIZE/4);

        /*for(int i=1;i<10;i++) {
            Image image = new Image(new TextureRegion(skin.getRegion("_0" + i)));
            image.setSize(Constants.NUMBERS_SEGMENT_DEFAULT_SIZE/2,Constants.NUMBERS_SEGMENT_DEFAULT_SIZE/2);
            numb.add(image);
        }*/
        score = 0;
        error = 0;
        stage = new Stage(viewport);
        Table table = new Table();
        table.top();
        table.defaults().expandX().space(2);
        table.setFillParent(true);
        table.setTransform(true);
        table.toFront();


        LabelStyle labelStyle = new LabelStyle(font, Color.GREEN);

        scoreLabel = new InfoLabel(imageCorrect,""+ score, labelStyle);

        labelStyle = new LabelStyle(font, Color.YELLOW);

        timeLabel = new InfoLabel(imageTime,"", labelStyle);
        labelStyle = new LabelStyle(font, Color.RED);
        errorLabel = new InfoLabel(imageError,""+ score, labelStyle);


        imagePause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SNAKE HUD", event+"PAUSE BUTTON CLICKED");
                screen.setGameState(Enums.GAME_STATE.PAUSE);
                screen.isPaused=true;
                event.stop();
            }
        });



        //table.add(scoreLabel).padLeft(Constants.HUD_MARGIN).padTop(Constants.HUD_MARGIN);
        table.add(imagePause).width(Constants.NUMBERS_SEGMENT_DEFAULT_SIZE/2).height(Constants.NUMBERS_SEGMENT_DEFAULT_SIZE/2);
        //table.add(scoreLabel).padLeft(Constants.HUD_MARGIN);
        table.add(scoreLabel).padTop(Constants.HUD_MARGIN);
        //table.add(imageTime);
        table.add(timeLabel).padTop(Constants.HUD_MARGIN);

        //table.add(imageError);
        table.add(errorLabel).padTop(Constants.HUD_MARGIN);


        //table.add(pauseButton).maxHeight(40).maxWidth(100).expandX().padTop(Constants.SNAKE_HUD_MARGIN);

        //table.debug();
        //table.pack();
        //level.getViewportHeight()-Constants.NUMBERS_SEGMENT_DEFAULT_SIZE-startLine
        hudHeight = table.getPrefHeight();//+Constants.NUMBERS_SEGMENT_DEFAULT_SIZE;
        stage.addActor(table);
        /*
        Table buttonsTable = new Table();
        buttonsTable.bottom();
        buttonsTable.defaults().expandX();
        buttonsTable.setFillParent(true);
        buttonsTable.setTransform(true);
        buttonsTable.toFront();
        for(int i=0;i<5;i++) {
            buttonsTable.add(numb.get(i));
            //numbButtons.add(new NumberButtons(i+1, numb.get(i), new Vector2(buttonleft+(numb.get(i).getWidth()*i), numb.get(i).getHeight())));
        }
        buttonsTable.row();
        int j =0;
        for(int i=5;i<numb.size();i++) {
            buttonsTable.add(numb.get(i));
            //numbButtons.add(new NumberButtons(i+1, numb.get(i), new Vector2(buttonleft+((numb.get(i).getWidth()/2)+numb.get(i).getWidth()*j), (numb.get(i).getHeight()-100*aspectRatio))));
            j++;
        }


        stage.addActor(buttonsTable);
        buttonsHeight = buttonsTable.getPrefHeight();*/



        //Gdx.input.setInputProcessor(stage);
    }

    public Stage getStage() {
        return stage;
    }

    public float getHudHeight() {
        return hudHeight;
    }

    public void updateScore(int _score) {
        this.score = _score;
        scoreLabel.update(""+ score);
    }
    public void updateErrors(int _error) {
        this.error = _error;
        errorLabel.update(""+ error);
    }
    public void updateTime(int _time)
    {
        this.time = _time;
        timeLabel.update(""+ time);
    }


}
