package ru.ihzork.funnynumbers.Overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import ru.ihzork.funnynumbers.Entity.NumbersGroup;
import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.GameUtils.Enums;
import ru.ihzork.funnynumbers.GameUtils.OverlapTester;
import ru.ihzork.funnynumbers.Screens.GameplayScreen;

public class PlayerHUD {

    GameplayScreen screen;
    private static final String TAG = PlayerHUD.class.getSimpleName();
    public final Viewport viewport;
    private float deadLine;
    private ShapeRenderer shapeRenderer;
    ArrayList<Sprite> numb = new ArrayList<Sprite>(9);
    ArrayList<NumberButtons> numbButtons = new ArrayList<NumberButtons>();
    Sprite selector;
    Vector2 selectPosition = new Vector2();
    boolean showSelector;
    Image pauseImage;
    boolean enable =true;


    public PlayerHUD(final GameplayScreen screen) {
        this.screen = screen;
        this.viewport =  screen.getViewport();
        Skin skin = new Skin();//Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));

        shapeRenderer = new ShapeRenderer();

        for(int i=1;i<10;i++) {
            numb.add(new Sprite(new TextureRegion(skin.getRegion("_0" + i))));
        }

        float left =  viewport.getWorldWidth()/2-(2.5f*Constants.NUMBERS_SEGMENT_DEFAULT_SIZE*1.2f)-20;
        Gdx.app.log(TAG, ": (" +  viewport.getScreenWidth() + ", " + left + ")");
        //Gdx.input.setInputProcessor(stage);
        for(int i=0;i<5;i++) {
            numbButtons.add(new NumberButtons(i+1, numb.get(i), new Vector2(left+(i*Constants.NUMBERS_SEGMENT_DEFAULT_SIZE*1.2f)+10, Constants.NUMBERS_SEGMENT_DEFAULT_SIZE+Constants.HUD_MARGIN)));
        }
        int j =0;
        left =  viewport.getWorldWidth()/2-2*Constants.NUMBERS_SEGMENT_DEFAULT_SIZE*1.2f-16;
        for(int i=5;i<numb.size();i++) {

            numbButtons.add(new NumberButtons(i+1, numb.get(i), new Vector2(left+(j*Constants.NUMBERS_SEGMENT_DEFAULT_SIZE*1.2f)+10, Constants.HUD_MARGIN)));
            j++;
        }
        selector = new Sprite(new TextureRegion(skin.getRegion("_select")));
        deadLine = Constants.NUMBERS_SEGMENT_DEFAULT_SIZE*2+Constants.HUD_MARGIN*2;
        pauseImage = new Image(new TextureRegion(skin.getRegion("_equel")));

        pauseImage.setRotation(90);
        pauseImage.setSize(Constants.NUMBERS_SEGMENT_DEFAULT_SIZE/2,Constants.NUMBERS_SEGMENT_DEFAULT_SIZE/2);
        pauseImage.setPosition(viewport.getWorldWidth()-Constants.NUMBERS_SEGMENT_DEFAULT_SIZE/2,viewport.getWorldHeight()-Constants.NUMBERS_SEGMENT_DEFAULT_SIZE/2);
        pauseImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SNAKE HUD", "PAUSE BUTTON CLICKED");
                screen.setGameState(Enums.GAME_STATE.PAUSE);
                event.stop();
            }
        });
    }

    public void render(SpriteBatch batch) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        for(int i=0;i<numbButtons.size();i++) {

            batch.draw(numbButtons.get(i).getSprite(),numbButtons.get(i).getPosition().x
                    ,numbButtons.get(i).getPosition().y,Constants.NUMBERS_SEGMENT_DEFAULT_SIZE,Constants.NUMBERS_SEGMENT_DEFAULT_SIZE);
        }
        if(showSelector)
        {

            batch.draw(selector,selectPosition.x,selectPosition.y,Constants.NUMBERS_SEGMENT_DEFAULT_SIZE,Constants.NUMBERS_SEGMENT_DEFAULT_SIZE);
        }
        //pauseImage.draw(batch,1);
        batch.end();
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(new Color(1, 0, 0, 1f));
        shapeRenderer.line(0, deadLine, viewport.getWorldWidth(), deadLine);
        /*for(int i=0;i<numbButtons.size();i++) {
            shapeRenderer.setColor(new Color(0, 1, 0, 1f));
            shapeRenderer.rect(numbButtons.get(i).rect.x, numbButtons.get(i).rect.y, numbButtons.get(i).rect.width, numbButtons.get(i).rect.height);

        }*/

        shapeRenderer.end();
    }

    public float getDeadLine() {
        return deadLine;
    }

    public int getId(Vector2 touchPoint)
    {
        for(int i=0;i<numbButtons.size();i++) {
            if (OverlapTester.pointInRectangle(numbButtons.get(i).getRect(), touchPoint.x, touchPoint.y)) {
                selectPosition.set(numbButtons.get(i).getPosition());
                showSelector = true;
                return numbButtons.get(i).getNumber();
            }
        }
        showSelector = false;
        return -1;
    }

    public boolean isShowSelector() {
        return showSelector;
    }

    public void setShowSelector(boolean showSelector) {
        this.showSelector = showSelector;
    }

    public ArrayList<NumberButtons> getNumbButtons() {
        return numbButtons;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
