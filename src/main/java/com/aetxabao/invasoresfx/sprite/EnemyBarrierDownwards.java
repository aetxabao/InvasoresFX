package com.aetxabao.invasoresfx.sprite;

import javafx.scene.image.Image;

public class EnemyBarrierDownwards extends EnemyBarrier {

    public EnemyBarrierDownwards(Image img, int impactMax, int ySpeed) {
        super(img, impactMax);
        this.ySpeed = ySpeed;
    }

    @Override
    public void update() {
        y = y + ySpeed;
        updateFrame();
    }

}
