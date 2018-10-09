package ru.ihzork.funnynumbers.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Timer;

import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.GameUtils.Countdown;
import ru.ihzork.funnynumbers.GameUtils.Enums;
import ru.ihzork.funnynumbers.GameUtils.Enums.Difficulty;
import ru.ihzork.funnynumbers.GameUtils.ScreenManager;
import ru.ihzork.funnynumbers.Level;
import ru.ihzork.funnynumbers.Overlays.CalculateHUD;
import ru.ihzork.funnynumbers.Overlays.InfoHUD;
import ru.ihzork.funnynumbers.Overlays.PauseOverlay;
import ru.ihzork.funnynumbers.Overlays.PlayerHUD;

import ru.ihzork.funnynumbers.TouchControls;

import static com.badlogic.gdx.utils.Timer.*;
import static ru.ihzork.funnynumbers.GameUtils.Enums.*;


public class GameplayScreen  extends AbstractScreen {

    public static final String TAG = GameplayScreen.class.getName();

    Preferences firstPref = Gdx.app.getPreferences(Constants.PREF_FIRST_TIME);
    Level level;
    SpriteBatch batch;
    private Difficulty difficulty;
    private GAME_STATE gameState;
    private InfoHUD infoHUD;
    private PlayerHUD playerHUD;
    public CalculateHUD calculateHUD;
    PauseOverlay pauseOverlay;

    private ShapeRenderer shapeRenderer;
    private boolean isFirstTime;
    public boolean isPaused;
    public float startLine,deadLine;
    float drawInfosTime;
    float drawSelectorTime;
    private TouchControls controls;
    //Countdown countdown;

    public GameplayScreen(Difficulty difficulty) {
        super();
        this.difficulty = difficulty;
        level = new Level(this, difficulty);


        infoHUD = new InfoHUD(this);
        playerHUD = new PlayerHUD(this);


        deadLine = playerHUD.getDeadLine();
        startLine = level.getViewportHeight()-Constants.NUMBERS_SEGMENT_DEFAULT_SIZE*2.5f;//infoHUD.getHudHeight();

        calculateHUD = new CalculateHUD(this,deadLine);

        //this.countdown = new Countdown(infoHUD.getTimeLabel());
        batch = new SpriteBatch();
        pauseOverlay = new PauseOverlay(this,batch);
        controls = new TouchControls(level,playerHUD);
        isPaused = false;

        isFirstTime = firstPref.getBoolean(Constants.KEY_FIRST_TIME, true);
        Gdx.app.log("GAME", "FIRST PREF: " + isFirstTime);

        if (isFirstTime) {
            gameState = Enums.GAME_STATE.FIRST;
        } else {
            gameState = Enums.GAME_STATE.RUN;
        }
        gameState = GAME_STATE.RUN;
    }

    protected GameplayScreen() {
        super();
    }

    @Override
    public void buildStage() {
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(
                Constants.GAMEPLAY_BACKGROUND_COLOR.r,
                Constants.GAMEPLAY_BACKGROUND_COLOR.g,
                Constants.GAMEPLAY_BACKGROUND_COLOR.b,
                Constants.GAMEPLAY_BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //batch.setProjectionMatrix(numbersHUD.stage.getCamera().combined);



        switch (gameState) {
            case FIRST:
                break;
            case RUN:
                //countdown.update();

                drawInfosTime += delta;

                if (drawInfosTime > 1) {
                    level.decrementCurrentTime();
                    drawInfosTime=0;

                }
                if(playerHUD.isShowSelector())
                {
                    drawSelectorTime += delta;
                }
                else
                {
                    drawSelectorTime=0;
                }
                if (drawSelectorTime > 2) {

                    drawSelectorTime=0;
                    playerHUD.setShowSelector(false);
                }
                level.update(delta);
                level.render(batch);


                playerHUD.render(batch);
                calculateHUD.render(batch);
                infoHUD.updateScore(level.getCurrentScore());
                infoHUD.updateErrors(level.getCurrentError());
                infoHUD.updateTime(level.getCurrentTime());
                infoHUD.stage.draw();

               // playerHUD.stage.draw();
                shapeRenderer.setProjectionMatrix(infoHUD.stage.getCamera().combined);
                shapeRenderer.begin(ShapeType.Line);
                shapeRenderer.setColor(new Color(0, 1, 0, 1f));
                shapeRenderer.line(0, infoHUD.viewport.getWorldHeight()-startLine, getWidth(), infoHUD.viewport.getWorldHeight()-startLine);
                shapeRenderer.end();

                break;
            case PAUSE:
                //isPaused=true;
                //countdown.cancel();
                infoHUD.stage.draw();
                playerHUD.render(batch);
                level.render(batch);

                // Blend transparent rectangle for fade out effect
                Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
                Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                //shapeRenderer.setProjectionMatrix(infoHUD.stage.getCamera().combined);
                /*shapeRenderer.begin(ShapeType.Line);
                shapeRenderer.setColor(new Color(0, 1, 0, 1f));
                shapeRenderer.line(0, infoHUD.viewport.getWorldHeight()-startLine, getWidth(), infoHUD.viewport.getWorldHeight()-startLine);
                shapeRenderer.end();*/

                shapeRenderer.setProjectionMatrix(playerHUD.viewport.getCamera().combined);


                shapeRenderer.begin(ShapeType.Filled);
                shapeRenderer.setColor(new Color(Constants.BACKGROUND_COLOR.r,Constants.BACKGROUND_COLOR.g,Constants.BACKGROUND_COLOR.b,0.8f));
                shapeRenderer.rect(0, 0, getWidth(), getHeight());


                shapeRenderer.setColor(new Color(Constants.BACKGROUND_COLOR));
                shapeRenderer.rect(0, playerHUD.getDeadLine()+5, getWidth(), playerHUD.getDeadLine()+infoHUD.viewport.getWorldHeight());
                shapeRenderer.end();
                Gdx.gl.glDisable(GL20.GL_BLEND);

                pauseOverlay.enableButton();
                pauseOverlay.stage.draw();

                //pauseKeyPressed();
                break;
            case STOP:
                //countdown.cancel();
                level.resetGame();
                ScreenManager.getInstance().showScreen(Enums.Screen.RESTART_SCREEN, difficulty);

                break;
            case CANCEL:
                //countdown.cancel();
                level.resetGame();
                ScreenManager.getInstance().showScreen(Screen.START_SCREEN);

                break;
        }
    }

    @Override
    public void show() {
        super.show();
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(infoHUD.getStage());
        multiplexer.addProcessor(pauseOverlay.stage);
        multiplexer.addProcessor(controls);

        Gdx.input.setInputProcessor(multiplexer);
        /*
        countdown.start(level.getCurrentTime(), new Runnable() {
            @Override
            public void run() {



                schedule(new Task() {
                    @Override
                    public void run() {

                    }
                },1);
            }

        });*/

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        level.viewport.update(width, height, true);
        infoHUD.viewport.update(width, height, true);
        playerHUD.viewport.update(width, height, true);
        pauseOverlay.viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    public void setGameState(GAME_STATE gameState) {
        this.gameState = gameState;
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    public float getStartLine() {
        return startLine;
    }

    public float getDeadLine() {
        return deadLine;
    }
}
