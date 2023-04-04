package com.aetxabao.invasoresfx.sprite;

import com.aetxabao.invasoresfx.util.Rect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static com.aetxabao.invasoresfx.game.AppConsts.*;

public class EnemyBarrier extends AEnemy implements IHaveShield {

    int impactMax;
    int impacts;

    public EnemyBarrier(Image img, int impactMax) {
        super(img, ENEMYBARRIER_ROWS, ENEMYBARRIER_COLS);
        this.impactMax = impactMax;
        this.impacts = 0;
    }

    @Override
    public void update() {
        updateFrame();
    }

    public void updateFrame(){
        int impactsPerFrame = impactMax/rows;
        currentFrame = impacts / impactsPerFrame;
        if (currentFrame>=rows) currentFrame = rows - 1;
    }

    @Override
    public boolean impact() {
        impacts++;
        updateFrame();
        return impacts==impactMax;
    }

    @Override
    public Rect getRect(){
        int sliceWidth = width / rows;
        int numSlices = rows - currentFrame;
        int ax = (width - sliceWidth*numSlices) / 2;
        return new Rect(x+ax, y, (int) (x + ax + numSlices * sliceWidth), y+height);
    }

    @Override
    public void draw(GraphicsContext gc) {
        int srcX = 0;
        int srcY = currentFrame * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, (int)(x + width), (int)(y +  height));
        gc.drawImage(img, src.left, src.top, src.width(), src.height(),
                dst.left, dst.top, dst.width(), dst.height());
    }

}
