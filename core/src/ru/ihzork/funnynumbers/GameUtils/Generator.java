package ru.ihzork.funnynumbers.GameUtils;

import com.badlogic.gdx.Gdx;

import java.util.Random;


public class Generator {


    public static final String TAG = Generator.class.getName();


    static int ints[][][] ={{{0,1},{9,1},{8,2},{7,3},{6,4},{5,5}},
                           {{0,2},{9,2},{8,3},{7,4},{6,5},{1,1}},
                           {{0,3},{9,3},{8,4},{7,5},{6,6},{1,2}},
                           {{0,4},{9,4},{8,5},{7,6},{3,1},{2,2}},
                           {{0,5},{9,5},{8,6},{7,7},{4,1},{3,2}},
                           {{0,6},{9,6},{8,7},{5,1},{4,2},{3,3}},
                           {{0,7},{9,7},{8,8},{4,3},{5,2},{6,1}},
                           {{0,8},{9,8},{7,1},{6,2},{5,3},{4,4}},
                           {{0,9},{9,9},{8,1},{7,2},{6,3},{5,4}}};

    private Enums.Difficulty difficulty;


    public Generator(Enums.Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public int[] decay(int inInt)
    {

        int[] outInt = new int[2];
        int getInt1 = randInt(0, 5);
        outInt[0] = ints[inInt][getInt1][0];
        outInt[1] = ints[inInt][getInt1][1];

        return outInt;
    }
    public int[] update(int inInt)
    {


        int nInt = 0;
        int getInt1 =   0;
        int getInt2 =   0;
        int[] outInt =  null;
        int decayInt[] = new int[2];
        int decayInt1[] = new int[2];
        int decayInt2[] = new int[2];
        int decayInt3[] = new int[2];
        int decayInt4[] = new int[2];


        outInt = new int[difficulty.numbers];


        switch (difficulty.getId()) {
            case 0:

                outInt = decay(inInt-1);
                break;

            case 1:


                decayInt =decay(inInt-1);

                getInt1 = decayInt[1];

                if(getInt1==0)
                {
                    decayInt1[0]=0;
                    decayInt1[1]=0;
                }else {

                    decayInt1 = decay(getInt1 - 1);
                }




                outInt[0]=decayInt[0];
                outInt[1]=decayInt1[0];
                outInt[2]=decayInt1[1];
                Gdx.app.log(TAG," " + decayInt[0] + " " + decayInt[1] +" => "+   decayInt[0] + " " + decayInt1[0] + " " + decayInt1[1] + " babResult =  " + getBadResult(outInt));
                break;


            case 2:


                decayInt =decay(inInt-1);
                getInt1 = decayInt[0];
                getInt2 = decayInt[1];


                if(getInt1==0) {

                    decayInt1[0]=0;
                    decayInt1[1]=0;
                }
                else
                {
                    decayInt1 = decay(getInt1-1);
                }
                if(getInt2==0)
                {
                    decayInt2[0]=0;
                    decayInt2[1]=0;
                }
                else {


                    decayInt2 = decay(getInt2 - 1);
                }


                outInt[0]=decayInt1[0];
                outInt[1]=decayInt1[1];
                outInt[2]=decayInt2[0];
                outInt[3]=decayInt2[1];
                Gdx.app.log(TAG," " + decayInt[0] + " " + decayInt[1] +" => "+   decayInt1[0] + " " + decayInt1[1] + " " + decayInt2[0]+ " " + decayInt2[1] + " babResult =  " + getBadResult(outInt));

                break;
            case 3:


                decayInt =decay(inInt-1);
                getInt1 = decayInt[0];
                getInt2 = decayInt[1];

                if(getInt1==0) {

                    decayInt1[0]=0;
                    decayInt1[1]=0;
                }
                else
                {
                    decayInt1 = decay(getInt1-1);
                }
                if(getInt2==0)
                {
                    decayInt2[0]=0;
                    decayInt2[1]=0;
                }
                else {

                    decayInt2 = decay(getInt2 - 1);
                }

                //decayInt1 = decay(decayInt[0]-1);
                //decayInt2 = decay(decayInt[1]-1);


                if(decayInt2[1]==0) {
                    decayInt3[0] = 0;
                    decayInt3[1] = 0;
                }
                else {
                    decayInt3 = decay(decayInt2[1] - 1);
                }

                outInt[0]=decayInt1[0];
                outInt[1]=decayInt1[1];
                outInt[2]=decayInt2[0];
                outInt[3]=decayInt3[0];
                outInt[4]=decayInt3[1];
                Gdx.app.log(TAG," " + decayInt[0] + " " + decayInt[1] +" => " + decayInt1[0] + " " + decayInt1[1] + " " + decayInt2[0] + " " + decayInt3[0]+ " " + decayInt3[1]+  " babResult =  " + getBadResult(outInt));

                break;
            case 4:

                decayInt =decay(inInt-1);
                getInt1 = decayInt[0];
                getInt2 = decayInt[1];


                if(getInt1==0) {

                    decayInt1[0]=0;
                    decayInt1[1]=0;
                }
                else
                {
                    decayInt1 = decay(getInt1-1);
                }
                if(getInt2==0)
                {
                    decayInt2[0]=0;
                    decayInt2[1]=0;
                }
                else {


                    decayInt2 = decay(getInt2 - 1);
                }
                if(decayInt1[0]==0) {

                    decayInt3[0]=0;
                    decayInt3[1]=0;
                }
                else
                {
                    decayInt3 = decay(decayInt1[0]-1);
                }
                if(decayInt1[1]==0) {

                    decayInt4[0]=0;
                    decayInt4[1]=0;
                }
                else
                {
                    decayInt4 = decay(decayInt1[1]-1);
                }


                outInt[0]=decayInt4[0];
                outInt[1]=decayInt4[1];
                outInt[2]=decayInt2[0];
                outInt[3]=decayInt2[1];
                outInt[4]=decayInt3[0];
                outInt[5]=decayInt3[1];
                Gdx.app.log(TAG," " + decayInt[0] + " " + decayInt[1] +" => " + outInt[0] + " " + outInt[1] + " " + outInt[2] + " " + outInt[3]+ " " + outInt[4]+ " " + outInt[5]+  " babResult =  " + getBadResult(outInt));

                break;


        }
        return outInt;
    }
    public int getBadResult(int[] inInt)
    {

        int badResult =0;
        int res=0;
        for(int i=0;i<inInt.length;i++)
        {
            res+=inInt[i];
        }
        if(res>9) {
            if(res==10) {

                int x1 = res % 10;
                badResult = x1 ;
            }
            else if(res>10)
            {
                int x2 = res % 100 / 10;
                int x1 = res % 10;
                badResult = x1 + x2;
            }

        }
        else
        {
            badResult = res;
        }


        return badResult;
    }


    public static int randInt(int min, int max) {


        Random random = new Random();
        int randomNum = random.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
