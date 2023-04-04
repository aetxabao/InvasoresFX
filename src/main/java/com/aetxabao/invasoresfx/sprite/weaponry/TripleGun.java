package com.aetxabao.invasoresfx.sprite.weaponry;

import com.aetxabao.invasoresfx.sprite.ASprite;

import java.util.ArrayList;

import static com.aetxabao.invasoresfx.game.AppConsts.BALL_SPRITE_IMAGE;

public class TripleGun extends Gun {

    @Override
    public ArrayList<AShot> shoot(ASprite sprite) {
        AShot shot;
        ArrayList<AShot> list = new ArrayList<>();
        // central
        shot = new Cannonball(BALL_SPRITE_IMAGE);
        shot.setPos(sprite.getRect().centerX(), sprite.getRect().bottom);
        list.add(shot);
        // left
        shot = new Cannonball(BALL_SPRITE_IMAGE);
        shot.setPos(sprite.getRect().centerX(), sprite.getRect().bottom);
        shot.setXSpeed(-1*shot.getYSpeed());
        list.add(shot);
        // right
        shot = new Cannonball(BALL_SPRITE_IMAGE);
        shot.setPos(sprite.getRect().centerX(), sprite.getRect().bottom);
        shot.setXSpeed(shot.getYSpeed());
        list.add(shot);
        return list;
    }

}
