package ru.ihzork.funnynumbers.GameUtils;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class InfoLabel extends Table {
    Label labelName;


    public InfoLabel(Image image, String parametrName, Label.LabelStyle style)
    {

       // defaults().space(3);

        labelName=new Label(parametrName, style);
        labelName.setAlignment(Align.left);
        add(image).pad(4);
        add(labelName);



    }


    public Label getLabelName() {
        return labelName;
    }

    public void setLabelName(Label labelName) {
        this.labelName = labelName;
    }



    public void update(String val)
    {
        labelName.setText(val);

    }

}
