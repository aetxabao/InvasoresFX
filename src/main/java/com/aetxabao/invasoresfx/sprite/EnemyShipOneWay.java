package com.aetxabao.invasoresfx.sprite;

import com.aetxabao.invasoresfx.util.Rect;
import javafx.scene.image.Image;

public class EnemyShipOneWay extends EnemyShip {

    public EnemyShipOneWay(Rect gameRect, Image img, int N) {
        super(gameRect, img, N);
    }

    @Override
    public void update() {
        //Desplazamiento hacia la derecha
        if (xSpeed>0){
            if (x > gameRect.right - width - xSpeed) {
                x = gameRect.left;
                y = y + height;
            }
        }
        //Desplazamiento hacia la izquierda
        if (xSpeed<0){
            if (x < gameRect.left - width + xSpeed ) {
                x = gameRect.right;
                y = y + height;
            }
        }
        x = x + xSpeed;
        updateFrame();
    }

}
