package ru.ihzork.funnynumbers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.ihzork.funnynumbers.Entity.NumbersGroup;
import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.GameUtils.Enums;
import ru.ihzork.funnynumbers.GameUtils.Generator;
import ru.ihzork.funnynumbers.Screens.GameplayScreen;


public class Level {

    public static final String TAG = Level.class.getName();

    Preferences scorePref = Gdx.app.getPreferences(Constants.PREF_HIGH_SCORE);
    Preferences newHighScore = Gdx.app.getPreferences(Constants.PREF_NEW_HIGH_SCORE);

    GameplayScreen screen;

    public Viewport viewport;

    public boolean isGameOver;
    public int currentBadResult = 0;
    public int currentScore = 0;
    public int currentError;
    public int currentTime;
    public int highScore;// = scorePref.getInteger(Constants.KEY_HIGH_SCORE, 0);

    public Enums.Difficulty difficulty;
    public NumbersGroup group;
    Generator generator;
    boolean okHandAnimate = false,failHandAnimate = false;


    public Level(GameplayScreen screen, Enums.Difficulty _difficulty) {
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        this.screen = screen;
        this.difficulty = _difficulty;
        highScore =  scorePref.getInteger(difficulty.getKeyHighScore());
        currentError = difficulty.getDificultyErrors();
        generator = new Generator(difficulty);
        currentTime = difficulty.getTimesUp();

        initGameDefault();
    }

    public void update(float delta) {
        if (!isGameOver) {
            //generator.update()
            group.update(delta);
            checkGameOver();
            //food.snakeCollisionWithSnack();
        }
    }

    public void render(SpriteBatch batch) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        group.render(batch);
        //food.render(batch);
        batch.end();
    }

    public void checkGameOver() {

        if (currentError == 0 || currentTime==0) {
            isGameOver = true;
            screen.setGameState(Enums.GAME_STATE.STOP);
        }
        if (group.getPosition().y < screen.getDeadLine()) {
            //isGameOver = true;

                decrementCurrentError(1);
                group.reset();
                generateNewInts();

        }



    }

    public void initGameDefault() {
        isGameOver = false;

        group = new NumbersGroup(this,screen.getStartLine());

        generateNewInts();



    }

    public int getCurrentTime() {
        return currentTime;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getCurrentError() {
        return currentError;
    }

    public void setCurrentError(int currentError) {
        this.currentError = currentError;
    }

    public void decrementCurrentTime()
    {
        currentTime--;
    }
    public void decrementCurrentError(int pointErrorValue)
    {
        currentError -=pointErrorValue;
        if (currentError <1) {
            isGameOver = true;
            screen.setGameState(Enums.GAME_STATE.STOP);
        }
    }
    public void incrementCurrentScore(int pointValue) {
        currentScore += pointValue;

    }
    public void resetGame() {
        if (currentScore > highScore) {
            highScore = currentScore;
            scorePref.putInteger(difficulty.getKeyHighScore(), highScore);
            scorePref.flush();
            newHighScore.putBoolean(difficulty.getKeyNewHighScore(), true);
            newHighScore.flush();
        } else {
            newHighScore.putBoolean(difficulty.getKeyNewHighScore(), false);
            newHighScore.flush();
        }
        resetCurrentScore();
    }

    public void generateNewInts()
    {

        if(!isGameOver) {
            group.reset();
            currentBadResult = generator.randInt(1, 9);
            group.setInts(generator.update(currentBadResult));
        }
    }

    public String calculateLog()
    {
        String out = "";
        int sizeInts =difficulty.getNumbers();
        int z=0;
        for(int in =0;in<sizeInts-1;in++)
        {
            int n= group.getInts()[in];
            out+= n+ " + ";
            z+=n;
        }
        int n= group.getInts()[sizeInts-1];
        z+=n;
        if(z<10)
        {
            out += n + " = " + currentBadResult;
        }else if(z==10)
        {

            out += n + " = " + z + " = 1 + 0 = "+ currentBadResult;
        }
        else {
            int z0= z;
            int c = z0%10;
            int c1 = (z0-(c))/10;
            out += n + " = " + z + " = " + c1+" + " +c+" = "+ currentBadResult;
            Gdx.app.log(TAG,out);
        }
        return out;
    }


    public void resetCurrentScore() {
        currentScore = 0;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public float getViewportWidth() {
        return screen.getViewport().getWorldWidth();
    }

    public float getViewportHeight() {
        return screen.getViewport().getWorldHeight();
    }

    public Enums.Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Enums.Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isOkHandAnimate() {
        return okHandAnimate;
    }

    public void setOkHandAnimate(boolean okHandAnimate) {
        this.okHandAnimate = okHandAnimate;
    }

    public boolean isFailHandAnimate() {
        return failHandAnimate;
    }

    public void setFailHandAnimate(boolean failHandAnimate) {
        this.failHandAnimate = failHandAnimate;
    }


}
