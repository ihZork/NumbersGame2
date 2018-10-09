package ru.ihzork.funnynumbers.GameUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import static com.badlogic.gdx.utils.TimeUtils.*;

public class Utils {




    public static float secondsSince(long timeNanos) {
        return MathUtils.nanoToSec * (nanoTime() - timeNanos);
    }

    // Generate FreeTypeFont
    public static BitmapFont generateFreeTypeFont(String fontFile, int fontSize, Color fontColor) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontFile));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = fontSize;
        parameter.color = fontColor;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

}
