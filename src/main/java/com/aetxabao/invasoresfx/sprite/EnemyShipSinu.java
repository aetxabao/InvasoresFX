package com.aetxabao.invasoresfx.sprite;


import com.aetxabao.invasoresfx.util.Rect;
import javafx.scene.image.Image;

/**
 * Los enemigos se desplazan de forma sinusoidal.
 */
public class EnemyShipSinu extends EnemyShip {

    int yRef;

    public EnemyShipSinu(Rect gameRect, Image img, int N) {
        super(gameRect, img, N);
    }

    @Override
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
        yRef = y;
    }

    @Override
    public void update() {
        if (x > gameRect.right - width - xSpeed || x + xSpeed < gameRect.left) {
            xSpeed = -xSpeed;
            yRef = yRef + height;
        }
        x = x + xSpeed;
        y = (int) (yRef + height*Math.sin(2*(double)x/(double)(width)));
        updateFrame();
    }

}
