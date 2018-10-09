package ru.ihzork.funnynumbers.Entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.GameUtils.Utils;

public class Particle extends Label{

    public float life;

   //LabelStyle labelStyle;
    public Particle(Vector2 position,String calculateLabel,LabelStyle labelStyle) {

        super(calculateLabel,labelStyle);
       // this.labelStyle.font = labelStyle.font;
        setPosition(position.x,position.y);

    }

    public void setup(Vector2 position, float life, String calculateLabel,Color color) {
        setPosition(position.x,position.y);
        //this.labelStyle.fontColor = color;
        getStyle().fontColor = color;
        //setStyle(labelStyle);
        setText(calculateLabel);
        this.life = life;
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }
    public String getLabelText()
    {
        return String.valueOf(getText());
    }

}
