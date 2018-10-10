package ru.ihzork.funnynumbers.Overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.GameUtils.Enums;
import ru.ihzork.funnynumbers.GameUtils.MyButton;
import ru.ihzork.funnynumbers.GameUtils.Utils;
import ru.ihzork.funnynumbers.Screens.GameplayScreen;

import static ru.ihzork.funnynumbers.GameUtils.Constants.FONT_UBUNTU;

public class DialogOverlay {

    GameplayScreen screen;

    public final Viewport viewport;

    public Stage stage;
    private Skin skin;
    private final MyButton resumeButton;
    Window window;
    boolean inPlace =false;
    public MoveToAction action,moveToOrigin;
    Actions actions;
    private  Vector2 startPosition,finishPosition;

    public DialogOverlay(final GameplayScreen screen, SpriteBatch batch,String titleString) {
        this.screen = screen;
        this.viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));

        stage = new Stage(viewport, batch);

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = Utils.generateFreeTypeFont(FONT_UBUNTU, 45, Color.WHITE);

        window = new Window("",windowStyle);


        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.toFront();

        MyButton.MyButtonStyle buttonStyle = new MyButton.MyButtonStyle();
        buttonStyle.font = Utils.generateFreeTypeFont(FONT_UBUNTU, 35, Color.WHITE);
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_EASY_ON)));
        resumeButton = new MyButton("RESUME",buttonStyle);

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                window.clearActions();
                moveToOrigin.reset();
                window.setPosition(viewport.getScreenWidth()/2,viewport.getScreenHeight()/2);
                moveToOrigin.setPosition(finishPosition.x,finishPosition.y);
                moveToOrigin.setDuration(0.2f);
                window.addAction(moveToOrigin);



                    screen.setGameState(Enums.GAME_STATE.RETURNTOGAME);

                Gdx.app.log("PAUSE OVERLAY", "RESUME BUTTON CLICKED");
                event.stop();


            }
        });


        MyButton.MyButtonStyle cancelButtonStyle = new MyButton.MyButtonStyle();
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
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;

        Label title = new Label(titleString,style);
        table.add(title);
        table.row().pad(10);

        table.add(resumeButton).padLeft(50).padRight(50).colspan(3);
        table.row().pad(4);
        table.add(cancelButton).padLeft(50).padRight(50).colspan(3);




        window.setMovable(true);
        window.setKeepWithinStage(false);
        window.add(table);

        window.pack();

        finishPosition= new Vector2(viewport.getScreenWidth()+window.getPrefWidth(),viewport.getScreenHeight()/2);
        startPosition = new Vector2(-window.getPrefWidth(),viewport.getScreenHeight()/2);
        window.setPosition(startPosition.x,startPosition.y);

        action = new MoveToAction();

        action.setPosition(viewport.getScreenWidth()/2,viewport.getScreenHeight()/2);
        action.setDuration(0.2f);


        moveToOrigin = new MoveToAction();
        moveToOrigin.setPosition(finishPosition.x,finishPosition.y);
        moveToOrigin.setDuration(0.2f);


        stage.addActor(window);



        Gdx.input.setInputProcessor(stage);
    }

    public Vector2 getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Vector2 startPosition) {
        this.startPosition = startPosition;
    }

    public void update()
    {

        //window.addAction(action);
        //stage.act();

    }

    public Vector2 getFinishPosition() {
        return finishPosition;
    }

    public void setFinishPosition(Vector2 finishPosition) {
        this.finishPosition = finishPosition;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void reset()
    {
        action.reset();
        window.addAction(action);
    }
    public void setPosition(Vector2 position)
    {
        window.setPosition(position.x,position.y);
    }
    public void disableButton() {
        resumeButton.setTouchable(Touchable.disabled);
    }

    public void enableButton() {
        resumeButton.setTouchable(Touchable.enabled);
    }
}
