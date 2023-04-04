package com.aetxabao.invasoresfx.sprite;

import com.aetxabao.invasoresfx.game.EnemySpawner;
import com.aetxabao.invasoresfx.util.Rect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import static com.aetxabao.invasoresfx.game.AppConsts.ENEMYMONSTER_ALFA;

public class EnemyMonster extends EnemyTower implements ICanSpawn {

    int ticksToSpawn;
    int ticksCounter;
    Rect gameRect;

    public EnemyMonster(Image img, int impactMax, int ticksToSpawn, Rect gameRect) {
        super(img, impactMax);
        this.ticksToSpawn = ticksToSpawn;
        this.gameRect = gameRect;
        ticksCounter = ticksToSpawn;
    }

    @Override
    public List<AEnemy> spawn() {
        ticksCounter++;
        if (ticksCounter >= ticksToSpawn){
            ticksCounter = 0;
            return EnemySpawner.crearEnemigosNivelRock(gameRect);
        }
        return new ArrayList<AEnemy>();
    }

    @Override
    public Rect getRect(){
        return new Rect((int) (x + 0.2*width*ENEMYMONSTER_ALFA), (int) (y + 0.2*height*ENEMYMONSTER_ALFA),
                        (int) (x + 0.8*width*ENEMYMONSTER_ALFA), (int) (y + 0.8*height*ENEMYMONSTER_ALFA));
    }

    @Override
    public void draw(GraphicsContext gc) {
        int srcX = 0;
        int srcY = currentFrame * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, (int)(x + width*ENEMYMONSTER_ALFA), (int)(y +  height*ENEMYMONSTER_ALFA));
        gc.drawImage(img, src.left, src.top, src.width(), src.height(),
                          dst.left, dst.top, dst.width(), dst.height());
    }

}
