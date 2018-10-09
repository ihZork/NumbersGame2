package ru.ihzork.funnynumbers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ru.ihzork.funnynumbers.Overlays.PlayerHUD;

public class TouchControls extends InputAdapter implements InputProcessor {


    Level level;
    private Vector3 lastTouch = new Vector3();
    private Vector2 touch = new Vector2();
    PlayerHUD hud;


    public TouchControls(Level level,PlayerHUD hud) {
        this.level = level;
        this.hud = hud;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(!level.isGameOver && !level.screen.isPaused) {

            level.getViewport().getCamera().unproject( lastTouch.set(screenX, screenY,0));
            touch.set(lastTouch.x,lastTouch.y);
            boolean update =false;
            boolean error= false;
            int id = hud.getId(touch);
            if (id > -1 && level.currentBadResult == id) {
                if(level.highScore>level.getDifficulty().getSuperScore()) {
                    level.incrementCurrentScore(5);
                    update = true;
                    error = false;
                }
                else
                {
                    update = true;
                    error = true;
                    level.incrementCurrentScore(1);
                }


            } else if (id > -1 && level.currentBadResult != id) {
                update = true;
                level.decrementCurrentError(1);
            }
            if(update) {
                if(error) {
                    level.screen.calculateHUD.addParticle(level.calculateLog(), Color.GREEN);
                }else
                {
                    level.screen.calculateHUD.addParticle(level.calculateLog(), Color.RED);
                }

                level.generateNewInts();

            }
        }
        //Gdx.app.log("MOBILE", "TOUCH DOWN: (" + lastTouch.x + ", " + lastTouch.y + ") " + id);

        //Gdx.app.log("MOBILE", "TOUCH DOWN: (" + screenX + ", " + screenY + ")");
        return true;
    }


}
