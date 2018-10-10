package ru.ihzork.funnynumbers.Overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.ihzork.funnynumbers.Entity.Particle;
import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.GameUtils.Utils;
import ru.ihzork.funnynumbers.Screens.GameplayScreen;

public class CalculateHUD {

    private static final String TAG = CalculateHUD.class.getSimpleName();
    GameplayScreen screen;

    public final Viewport viewport;
    final BitmapFont font;
    private Skin skin;
    public Stage stage;

    private boolean visible = false;
    private Vector2 startPosition,tmpPosition;
    private float alfa = 1,delta;
    //LabelStyle labelStyle;
    int maxLabels = 20;
    Array<Particle> particles = new Array<Particle>(false,maxLabels);


    public CalculateHUD(final GameplayScreen screen,float deadLine) {
        this.screen = screen;
        this.viewport =  screen.getViewport();
        font = Utils.generateFreeTypeFont(Constants.FONT_UBUNTU, 40, Color.WHITE);
        startPosition =  new Vector2(40,deadLine+100);

        //labelStyle = new LabelStyle(font, Color.GREEN);


    }
    private Pool<Particle> freeParticles = new Pool<Particle>(maxLabels,maxLabels) {
        @Override
        protected Particle newObject() {
            return new Particle(startPosition,"----",new LabelStyle(font, Color.GREEN));
        }
    };
    public void render(SpriteBatch batch) {
        viewport.apply();
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();


        for (int i = particles.size - 1; i >= 0; i--) {
            Particle particle = particles.get(i);
            if (particle.life > 0) {
                updateParticle(particle);
                if(particle.life >= 1)
                    particle.draw(batch,1);
                else
                {
                    particle.draw(batch,particle.life);
                }
                //Gdx.app.log(TAG, particle.getLabelText()+" "+particle.getLife()+" "+ particle.getX() + " " +particle.getY());
            } else {
                particles.removeIndex(i);
                freeParticles.free(particle);
            }


        }
        batch.end();
    }

    public void addParticle(String text,Color color) {
        if(particles.size>maxLabels) return;
        //if(Gdx.graphics.getFramesPerSecond()<25 && !(this instanceof ExplosionParticleEmitter)) return;
        Particle particle = freeParticles.obtain();
        particle.setup(startPosition,5f,text,color);

        //Gdx.app.log(TAG, particle.getLabelText()+" "+particle.getLife()+" "+ startPosition);
        particles.add(particle);
    }
    private void updateParticle(Particle particle) {
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());

        if (particle.life > 0) {
            particle.life -= delta;
            particle.setPosition(startPosition.x, startPosition.y-particle.life*20);

        }
    }

}
