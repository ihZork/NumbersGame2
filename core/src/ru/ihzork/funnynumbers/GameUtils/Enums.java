package ru.ihzork.funnynumbers.GameUtils;

import ru.ihzork.funnynumbers.GameUtils.Constants;
import ru.ihzork.funnynumbers.Screens.AbstractScreen;
import ru.ihzork.funnynumbers.Screens.GameplayScreen;
import ru.ihzork.funnynumbers.Screens.RestartScreen;
import ru.ihzork.funnynumbers.Screens.StartScreen;


public class Enums {

    public enum GAME_STATE {
        RUN,
        PAUSE,
        FIRST,
        STOP,
        CANCEL
    }
    public enum Difficulty {
        NOVICE(0, Constants.NOVICE_NUMBERS,0.5f,5,300,100,"noviceHighScore","newNoviceHighScore"),
        EASY(1,Constants.EASY_NUMBERS, 0.8f,5,300,100,"easyHighScore", "newEasyHighScore"),
        MEDIUM(2,Constants.MEDIUM_NUMBERS,1.2f,3,300,80,"mediumHighScore","newMediumHighScore"),
        HARD(3,Constants.HARD_NUMBERS,1.4f,1,300,50,"hardHighScore","newHardHighScore"),
        EXPERT(4,Constants.EXPERT_NUMBERS,1.4f,1,300,25,"expertHighScore","newExpertHighScore")
        ;
        int id;
        int go[];
        float dropSpeed;
        int dificultyErrors;
        int timesUp;
        int superScore;
        int numbers;
        String keyHighScore,keyNewHighScore;

        Difficulty( int id, int numbers, float dropSpeed, int dificultyErrors, int timesUp, int superScore,String keyHighScore,String keyNewHighScore) {
            this.id = id;
            this.numbers = numbers;
            this.go =new int[numbers];
            this.dropSpeed = dropSpeed;
            this.dificultyErrors = dificultyErrors;
            this.timesUp = timesUp;
            this.superScore = superScore;
            this.keyHighScore = keyHighScore;
            this.keyNewHighScore = keyNewHighScore;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int[] getGo() {
            return go;
        }

        public void setGo(int[] go) {
            this.go = go;
        }

        public float getDropSpeed() {
            return dropSpeed;
        }

        public void setDropSpeed(float dropSpeed) {
            this.dropSpeed = dropSpeed;
        }

        public int getDificultyErrors() {
            return dificultyErrors;
        }

        public void setDificultyErrors(int dificultyErrors) {
            this.dificultyErrors = dificultyErrors;
        }

        public int getTimesUp() {
            return timesUp;
        }

        public void setTimesUp(int timesUp) {
            this.timesUp = timesUp;
        }

        public int getSuperScore() {
            return superScore;
        }

        public void setSuperScore(int superScore) {
            this.superScore = superScore;
        }

        public int getNumbers() {
            return numbers;
        }

        public void setNumbers(int numbers) {
            this.numbers = numbers;
        }

        public String getKeyHighScore() {
            return keyHighScore;
        }

        public void setKeyHighScore(String keyHighScore) {
            this.keyHighScore = keyHighScore;
        }

        public String getKeyNewHighScore() {
            return keyNewHighScore;
        }

        public void setKeyNewHighScore(String keyNewHighScore) {
            this.keyNewHighScore = keyNewHighScore;
        }
    }
    public enum Screen {

        START_SCREEN {
            public AbstractScreen getScreen(Object... params) {
                return new StartScreen();
            }
        },

        RESTART_SCREEN {
            public AbstractScreen getScreen(Object... params) {
                return new RestartScreen((Difficulty) params[0]);
            }
        },


        GAME_SCREEN {
            public AbstractScreen getScreen(Object... params) {
                return new GameplayScreen((Difficulty) params[0]);
            }
        };

        public abstract AbstractScreen getScreen(Object... params);

    }

}
