package ru.ihzork.funnynumbers.Overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.GameUtils.Enums;
import ru.ihzork.funnynumbers.GameUtils.MyButton;
import ru.ihzork.funnynumbers.GameUtils.MyButton.MyButtonStyle;
import ru.ihzork.funnynumbers.GameUtils.Utils;
import ru.ihzork.funnynumbers.Screens.GameplayScreen;

import static ru.ihzork.funnynumbers.GameUtils.Constants.FONT_UBUNTU;

public class PauseOverlay {

    GameplayScreen screen;

    public final Viewport viewport;

    public Stage stage;
    private Skin skin;
    private final MyButton resumeButton;

    public PauseOverlay(final GameplayScreen screen, SpriteBatch batch) {
        this.screen = screen;
        this.viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));

        stage = new Stage(viewport, batch);
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.toFront();

        MyButtonStyle buttonStyle = new MyButtonStyle();
        buttonStyle.font = Utils.generateFreeTypeFont(FONT_UBUNTU, 35, Color.WHITE);
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_EASY_ON)));
        resumeButton = new MyButton("RESUME",buttonStyle);

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setGameState(Enums.GAME_STATE.RUN);
                screen.isPaused=false;
                Gdx.app.log("PAUSE OVERLAY", "RESUME BUTTON CLICKED");
                event.stop();
            }
        });


        MyButtonStyle cancelButtonStyle = new MyButtonStyle();
        cancelButtonStyle.font = Utils.generateFreeTypeFont(FONT_UBUNTU, 35, Color.WHITE);
        cancelButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_EXPERT_ON)));

        MyButton cancelButton = new MyButton("HOME",cancelButtonStyle);

        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setGameState(Enums.GAME_STATE.CANCEL);
                //screen.isPaused=false;
                Gdx.app.log("PAUSE OVERLAY", "RESUME BUTTON CLICKED");
                event.stop();
            }
        });


        BitmapFont font = Utils.generateFreeTypeFont(FONT_UBUNTU, 55, Color.WHITE);
        LabelStyle style = new LabelStyle();
        style.font = font;

        Label title = new Label("Pause",style);
        table.add(title);
        table.row().pad(10);

        table.add(resumeButton).padLeft(50).padRight(50).colspan(3);
        table.row().pad(4);
        table.add(cancelButton).padLeft(50).padRight(50).colspan(3);


        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    public void disableButton() {
        resumeButton.setTouchable(Touchable.disabled);
    }

    public void enableButton() {
        resumeButton.setTouchable(Touchable.enabled);
    }
}
