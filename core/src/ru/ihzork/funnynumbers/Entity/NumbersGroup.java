package ru.ihzork.funnynumbers.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;


import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.Level;

public class NumbersGroup {


    private static final String TAG = NumbersGroup.class.getSimpleName();

    private Skin skin;
    private float viewportWidth, viewportHeight;
    public Vector2 position,startPosition;
    public boolean alive;
    ArrayList<Sprite> numb = new ArrayList<Sprite>(10);
    int Ints[];
    float left;
    int size = 0;

    boolean isMoving;
    Level level;
    float dropSpeed,deadLine,aspect;
    float centerX;

    public NumbersGroup(Level level,float startLine) {

        this.level = level;
        this.dropSpeed = this.level.getDifficulty().getDropSpeed();
        skin = new Skin();//Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));

        Ints = new int[this.level.getDifficulty().getNumbers()];
        size = this.level.getDifficulty().getNumbers();
        for(int i=0;i<10;i++) {
            numb.add(new Sprite(new TextureRegion(skin.getRegion("_0" + i))));
        }


        viewportWidth = level.getViewportWidth();
        viewportHeight = level.getViewportHeight();

        left = viewportWidth/2;
        //startPosition.set();

        startPosition= new Vector2(left-(level.getDifficulty().getNumbers()*Constants.NUMBERS_SEGMENT_DEFAULT_SIZE)/2-level.getDifficulty().getNumbers()*4,viewportHeight-Constants.NUMBERS_SEGMENT_DEFAULT_SIZE*2);
        position = new Vector2();
        centerX = (startPosition.x+level.getDifficulty().getNumbers()*Constants.NUMBERS_SEGMENT_DEFAULT_SIZE+4*level.getDifficulty().getNumbers())/2;
        reset();

    }
    public void render(Batch batch)
    {
        //int size = Ints.length;
        for(int i=0;i<size;i++) {
            batch.draw(numb.get(Ints[i]),position.x+i*Constants.NUMBERS_SEGMENT_DEFAULT_SIZE+4,position.y,Constants.NUMBERS_SEGMENT_DEFAULT_SIZE,Constants.NUMBERS_SEGMENT_DEFAULT_SIZE);

        }
    }
    public void update(float delta) {

        move();
    }
    public void setInts(int[] ints)
    {
        Ints = ints;
        alive = true;
    }
    public int[] getInts() {
        return Ints;
    }

    public void move()
    {
        if(isMoving)
        {
            position.y-=dropSpeed;
        }
    }
    public void reset() {

        position.set(startPosition);
        alive = true;
        isMoving = true;

    }
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }
}
