package ru.ihzork.funnynumbers.GameUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getSimpleName();
    public static final Assets instance = new Assets();

    public ButtonAssets buttonAssets;


    private AssetManager assetManager;


    public Assets() {
    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        //snakeAssets = new SnakeAssets(atlas);
        //foodAssets = new FoodAssets(atlas);
        buttonAssets = new ButtonAssets(atlas);
    }


    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Could not load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }


    public class ButtonAssets {

        public final AtlasRegion menu_button;
        public final AtlasRegion button_novice_on;
        public final AtlasRegion button_novice_off;
        public final AtlasRegion button_easy_on;
        public final AtlasRegion button_easy_off;
        public final AtlasRegion button_medium_on;
        public final AtlasRegion button_medium_off;
        public final AtlasRegion button_hard_on;
        public final AtlasRegion button_hard_off;
        public final AtlasRegion button_expert_on;
        public final AtlasRegion button_expert_off;


        public ButtonAssets(TextureAtlas atlas) {
            menu_button = atlas.findRegion(Constants.MENU_BUTTON);
            button_novice_on = atlas.findRegion(Constants.BUTTON_NOVICE_ON);
            button_novice_off = atlas.findRegion(Constants.BUTTON_NOVICE_OFF);
            button_easy_on = atlas.findRegion(Constants.BUTTON_EASY_ON);
            button_easy_off = atlas.findRegion(Constants.BUTTON_EASY_OFF);
            button_medium_on = atlas.findRegion(Constants.BUTTON_MEDIUM_ON);
            button_medium_off = atlas.findRegion(Constants.BUTTON_MEDIUM_OFF);
            button_hard_on = atlas.findRegion(Constants.BUTTON_HARD_ON);
            button_hard_off = atlas.findRegion(Constants.BUTTON_HARD_OFF);
            button_expert_on = atlas.findRegion(Constants.BUTTON_EXPERT_ON);
            button_expert_off = atlas.findRegion(Constants.BUTTON_EXPERT_OFF);
        }

    }
}
