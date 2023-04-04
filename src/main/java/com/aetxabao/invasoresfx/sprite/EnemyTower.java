package com.aetxabao.invasoresfx.sprite;

import com.aetxabao.invasoresfx.sprite.weaponry.TripleGun;
import com.aetxabao.invasoresfx.util.Rect;
import javafx.scene.image.Image;

import static com.aetxabao.invasoresfx.game.AppConsts.ENEMYTOWER_COLS;
import static com.aetxabao.invasoresfx.game.AppConsts.ENEMYTOWER_ROWS;

public class EnemyTower extends EnemyBarrier {

    public EnemyTower(Image img, int impactMax) {
        super(img, impactMax);
        cols = ENEMYTOWER_COLS;
        rows = ENEMYTOWER_ROWS;
        if (img!=null) {
            this.width = (int) (img.getWidth() / cols);
            this.height = (int) (img.getHeight() / rows);
        }
        setWeapon(new TripleGun());
    }

    @Override
    public Rect getRect(){
        return new Rect((int) (x + 0.2*width), (int) (y + 0.2*height), (int) (x + 0.8*width), (int) (y + 0.8*height));
    }

}
