package ru.ihzork.funnynumbers.GameUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;


public class MyButton extends Button {

    String text;
    //private final Image image;
    private final Label label;

    private MyButtonStyle style;

    public MyButton (String text, Skin skin) {
        this(text, skin.get(MyButtonStyle.class));
        setSkin(skin);
    }

    public MyButton (String text, Skin skin, String styleName) {
        this(text, skin.get(styleName, MyButtonStyle.class));
        setSkin(skin);
    }

    public MyButton (String text, MyButtonStyle style) {
        super(style);
        this.style = style;


        defaults().space(8);

        //image = new Image();
        //add(image).left();

        label = new Label(text, new LabelStyle(style.font, style.fontColor));
        label.setAlignment(Align.center);



        add(label).padLeft(10);//.center();
        //row();



        setStyle(style);

        setSize(getPrefWidth(), getPrefHeight());

    }

    public void setStyle (ButtonStyle style) {
        if (!(style instanceof MyButtonStyle)) throw new IllegalArgumentException("style must be a ImageTextButtonStyle.");
        super.setStyle(style);
        this.style = (MyButtonStyle)style;

        if (label != null) {
            MyButtonStyle textButtonStyle = (MyButtonStyle)style;
            LabelStyle labelStyle = label.getStyle();
            labelStyle.font = textButtonStyle.font;
            labelStyle.fontColor = textButtonStyle.fontColor;
            label.setStyle(labelStyle);
        }
    }
/*
    private void updateImage () {

        Drawable drawable = null;
        if (isDisabled() && style.imageDisabled != null)
            drawable = style.imageDisabled;
        else if (isPressed() && style.imageDown != null)
            drawable = style.imageDown;
        else if (isChecked() && style.imageChecked != null)
            drawable = (style.imageCheckedOver != null && isOver()) ? style.imageCheckedOver : style.imageChecked;
        else if (isOver() && style.imageOver != null)
            drawable = style.imageOver;
        else if (style.imageUp != null) //
            drawable = style.imageUp;
        image.setDrawable(drawable);
    }
*/
    public void draw (Batch batch, float parentAlpha) {
        //updateImage();
        Color fontColor;
        if (isDisabled() && style.disabledFontColor != null)
            fontColor = style.disabledFontColor;
        else if (isPressed() && style.downFontColor != null)
            fontColor = style.downFontColor;
        else if (isChecked() && style.checkedFontColor != null)
            fontColor = (isOver() && style.checkedOverFontColor != null) ? style.checkedOverFontColor : style.checkedFontColor;
        else if (isOver() && style.overFontColor != null)
            fontColor = style.overFontColor;
        else
            fontColor = style.fontColor;
        if (fontColor != null) label.getStyle().fontColor = fontColor;
        super.draw(batch, parentAlpha);
    }


    static public class MyButtonStyle extends TextButtonStyle {

        /** Optional. */
        public Drawable imageUp, imageDown, imageOver, imageChecked, imageCheckedOver, imageDisabled;
        public BitmapFont font;

        public MyButtonStyle () {
        }
        public MyButtonStyle (Drawable up, Drawable down, Drawable checked, BitmapFont font) {
            super(up, down, checked, font);
        }

        public MyButtonStyle (MyButtonStyle style) {
            super(style);
            if (style.imageUp != null) this.imageUp = style.imageUp;
            if (style.imageDown != null) this.imageDown = style.imageDown;
            if (style.imageOver != null) this.imageOver = style.imageOver;
            if (style.imageChecked != null) this.imageChecked = style.imageChecked;
            if (style.imageCheckedOver != null) this.imageCheckedOver = style.imageCheckedOver;
            if (style.imageDisabled != null) this.imageDisabled = style.imageDisabled;
            if(font!=null) this.font = style.font;
        }

        public MyButtonStyle (TextButtonStyle style) {
            super(style);
        }
    }
}
