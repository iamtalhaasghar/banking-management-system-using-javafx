/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.nio.file.Paths;
import javafx.geometry.Insets;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Talha Asghar
 */
public interface Graphics {
    
    // Fonts
    
    Font TEXTFIELD_FONT = new Font("Ubuntu",25);
    Font HEADING_FONT = new Font("Ubuntu",30);
    
    
    
    // Backgrounds
    
    Background TRANSPARENT_BACKGROUND = new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY));
    Background TITLE_BAR_BACKGROUND = new Background(new BackgroundFill(Color.web("2d364c"),CornerRadii.EMPTY,Insets.EMPTY));
    Background BLACK_BACKGROUND = new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,Insets.EMPTY));
    Background GREEN_BACKGROUND = new Background(new BackgroundFill(Color.GREEN,CornerRadii.EMPTY,Insets.EMPTY));
    Background CLOSE_BUTTON_BACKGROUND = new Background(new BackgroundFill(Color.web("f63c55"),CornerRadii.EMPTY,Insets.EMPTY));
    Background MINIMIZE_BUTTON_BACKGROUND = new Background(new BackgroundFill(Color.web("262e41"),CornerRadii.EMPTY,Insets.EMPTY));
    
    // Borders
    
    
    Border TEXTFIELD_BORDER = new Border(new BorderStroke(Color.TRANSPARENT,
            Color.TRANSPARENT, Color.MEDIUMTURQUOISE,Color.TRANSPARENT,BorderStrokeStyle.NONE,
            BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
            CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY));

    Border TOP_LINE = new Border(new BorderStroke(Color.DARKGREY,
        Color.TRANSPARENT, Color.TRANSPARENT,Color.TRANSPARENT,BorderStrokeStyle.SOLID,
        BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
        CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY)); 

   
    Border BOTTOM_LINE = new Border(new BorderStroke(Color.TRANSPARENT,
            Color.TRANSPARENT, Color.DARKGREY,Color.TRANSPARENT,BorderStrokeStyle.NONE,
            BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
            CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY)); 
    
    Border RIGHT_LINE = new Border(new BorderStroke(Color.TRANSPARENT,
            Color.DARKGREY, Color.TRANSPARENT,Color.TRANSPARENT,BorderStrokeStyle.NONE,
            BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
            CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY));
    
    Border LEFT_LINE = new Border(new BorderStroke(Color.TRANSPARENT,
            Color.TRANSPARENT, Color.TRANSPARENT,Color.DARKGREY,BorderStrokeStyle.NONE,
            BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY));     
   
    Border LABEL_BORDER = new Border(new BorderStroke(Color.TRANSPARENT,
            Color.TRANSPARENT, Color.MEDIUMTURQUOISE,Color.TRANSPARENT,BorderStrokeStyle.NONE,
            BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
            CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY));   
 
    Border STAGE_BOUNDARY = new Border(new BorderStroke(Color.web("2d364c"),
            Color.web("2d364c"), Color.web("2d364c"),Color.web("2d364c"),BorderStrokeStyle.SOLID,
            BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY, new BorderWidths(3), Insets.EMPTY));

    Background DARK_BACKGROUND = new Background(new BackgroundImage(
        new Image(Paths.get("images/login_back.png").toUri().toString()),BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
   
    // effects
    
    InnerShadow INNER_SHADOW_RED = new InnerShadow(10, Color.RED);
   
}
