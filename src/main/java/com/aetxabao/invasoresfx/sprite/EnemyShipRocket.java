package com.aetxabao.invasoresfx.sprite;

import com.aetxabao.invasoresfx.util.Rect;
import javafx.scene.image.Image;

public class EnemyShipRocket extends EnemyShip {

    public EnemyShipRocket(Rect gameRect, Image img, int N) {
        super(gameRect, img, N);
    }

    @Override
    public void update() {
        if (x > gameRect.right - width - xSpeed || x + xSpeed < gameRect.left) {
            xSpeed = -xSpeed;
        }
        if (y > gameRect.bottom - height) {
            y = gameRect.top + height;
        }
        x = x + xSpeed;
        y = y + ySpeed;
        updateFrame();
    }

}
