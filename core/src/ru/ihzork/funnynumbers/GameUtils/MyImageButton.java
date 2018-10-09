package ru.ihzork.funnynumbers.GameUtils;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class MyImageButton extends Image {

    int id;
    public MyImageButton(int id,Drawable imageUp) {
        super(imageUp);
        this.id = id;
    }

}
