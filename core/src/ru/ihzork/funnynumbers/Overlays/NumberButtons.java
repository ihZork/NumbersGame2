package ru.ihzork.funnynumbers.Overlays;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class NumberButtons {

    int number;
    Sprite sprite;
    BoundingBox area;
    Rectangle rect;
    Vector2 position;

    public NumberButtons(int number, Sprite sprite, Vector2 position) {
        this.number = number;
        this.sprite = sprite;
        this.position = position;
        rect = new Rectangle(this.position.x, this.position.y,(this.sprite.getWidth()),  this.sprite.getHeight());
        area = new BoundingBox(
                new Vector3(this.position.x, this.position.y, 0),
                new Vector3(this.position.x+(this.sprite.getWidth()),  this.position.y+this.sprite.getHeight(), 0));


    }


    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public BoundingBox getArea() {
        return area;
    }

    public void setArea(BoundingBox area) {
        this.area = area;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
