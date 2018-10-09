package ru.ihzork.funnynumbers.GameUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {

    public static final float WORLD_SIZE = 800;
    public static final Color BACKGROUND_COLOR = Color.SLATE;
    public static final Color GAMEPLAY_BACKGROUND_COLOR = Color.valueOf("00A3B2");
    public static final String TEXTURE_ATLAS = "textures/funny.atlas";
    public static final String UI_SKIN = "uiskin.json";
    public static final String FONT_UBUNTU = "font/Ubuntu-Bold.ttf";
    public static final String PREF_HIGH_SCORE = "High Score Preference";
    public static final String PREF_NEW_HIGH_SCORE = "New High Score Preference";

    public static final float NUMBERS_SEGMENT_DEFAULT_SIZE = 100;
    public static final Vector2 NUMBERS_DEFAULT_START_POINT = new Vector2(WORLD_SIZE / 2-NUMBERS_SEGMENT_DEFAULT_SIZE, WORLD_SIZE+NUMBERS_SEGMENT_DEFAULT_SIZE);

    public static final String PREF_FIRST_TIME = "First Time";

    public static final String KEY_NOVICE_HIGH_SCORE = "noviceHighScore";
    public static final String KEY_NEW_NOVICE_HIGH_SCORE = "newNoviceHighScore";

    public static final String KEY_EASY_HIGH_SCORE = "easyHighScore";
    public static final String KEY_NEW_EASY_HIGH_SCORE = "newEasyHighScore";

    public static final String KEY_MEDIUM_HIGH_SCORE = "mediumHighScore";
    public static final String KEY_NEW_MEDIUM_HIGH_SCORE = "newMediumHighScore";

    public static final String KEY_HARD_HIGH_SCORE = "hardHighScore";
    public static final String KEY_NEW_HARD_HIGH_SCORE = "newHardHighScore";


    public static final String KEY_EXPERT_HIGH_SCORE = "expertHighScore";
    public static final String KEY_NEW_EXPERT_HIGH_SCORE = "newExpertHighScore";



    public static final String KEY_FIRST_TIME = "firstTime";

    public static final int NOVICE_NUMBERS = 2;
    public static final int EASY_NUMBERS = 3;
    public static final int MEDIUM_NUMBERS = 4;
    public static final int HARD_NUMBERS = 5;
    public static final int EXPERT_NUMBERS = 6;


    public static final float HUD_VIEWPORT_SIZE = 480;
    public static final float HUD_MARGIN = 10;
    public static final String HUD_SCORE_LABEL = "Score: ";
    public static final String HUD_ERROR_LABEL = "Errors: ";
    public static final String BUTTON_PAUSE = "btn_pause";



    // START SCREEN
    public static final String MENU_BUTTON = "button";
    public static final String BUTTON_PLAY = "btn_play";
    public static final String TITLE_GAME = "emojiconda_title";
    public static final String IMG_BACKGROUND = "bg_start";
    public static final String SUBTITLE_LABEL = "A GAME   \n" + " bla bla bla ";

    public static final String BUTTON_NOVICE_ON = "_noviceOn";
    public static final String BUTTON_NOVICE_OFF = "_noviceOff";
    public static final String BUTTON_EASY_ON = "_easyOn";
    public static final String BUTTON_EASY_OFF = "_easyOff";
    public static final String BUTTON_MEDIUM_ON = "_mediumOn";
    public static final String BUTTON_MEDIUM_OFF = "_mediumOff";
    public static final String BUTTON_HARD_ON = "_hardOn";
    public static final String BUTTON_HARD_OFF = "_hardOff";
    public static final String BUTTON_EXPERT_ON = "_expertOn";
    public static final String BUTTON_EXPERT_OFF = "_expertOff";


    // GAME_SCREEN OVER SCREEN
    public static final String HIGH_SCORE_LABEL = "High Score: ";
    public static final String NEW_HIGH_SCORE_LABEL = "NEW \n High Score: ";
    public static final String BUTTON_HOME = "btn_home";
    public static final String BUTTON_RESUME = "btn_resume";
    public static final String TITLE_GAME_OVER = "txt_gameover";

    // INSTRUCTION OVERLAY
    public static final String BUTTON_GOT_IT = "btn_got_it";
    public static final String IMG_SWIPE = "swipe";
    public static final String TXT_SWIPE = "swipe-to-move";



}
