package com.aetxabao.invasoresfx.sprite;

import com.aetxabao.invasoresfx.sprite.weaponry.AShot;
import com.aetxabao.invasoresfx.sprite.weaponry.AWeapon;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Los enemigos van a poder disparar si tienen un arma
 */
public abstract class AEnemy extends ASprite implements ICanShoot {

    AWeapon weapon;

    public AEnemy(Image img, int rows, int cols) {
        super(img, rows, cols);
    }

    public void setWeapon(AWeapon weapon) {
        this.weapon = weapon;
    }

    public boolean hasWeapon(){
        return weapon != null;
    }

    public ArrayList<AShot> shoot(){
        ArrayList<AShot> list = new ArrayList<>();
        if (weapon!=null){
            list = weapon.shoot(this);
        }
        return list;
    }

}
