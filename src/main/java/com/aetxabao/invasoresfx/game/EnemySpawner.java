package com.aetxabao.invasoresfx.game;

import com.aetxabao.invasoresfx.game.enums.EEnemyShot;
import com.aetxabao.invasoresfx.game.enums.EEnemyType;
import com.aetxabao.invasoresfx.sprite.*;
import com.aetxabao.invasoresfx.sprite.weaponry.Gun;
import com.aetxabao.invasoresfx.util.Rect;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import static com.aetxabao.invasoresfx.game.AppConsts.*;
import static com.aetxabao.invasoresfx.game.enums.EEnemyShot.*;
import static com.aetxabao.invasoresfx.game.enums.EEnemyType.*;

public class EnemySpawner {

    //region attributes
    public static int n = 8;
    public static int m = 16;
    public static int vx = 5;
    public static int vy = 3;
    public static int ticks = 100;
    //endregion

    /**
     * Transforma una coordenada en una posición
     * @param i coordenada de 0 a n eje horizontal
     * @return posicion x
     */
    private static int getX(Rect gameRect, int i){
        return gameRect.left + i*gameRect.width()/ n;
    }

    /**
     * Transforma una coordenada en una posición
     * @param j coordenada de 0 a m eje vertical
     * @return posicion y
     */
    private static int getY(Rect gameRect, int j){
        return gameRect.top + j*gameRect.width()/ m;
    }

    public static List<AEnemy> createEnemies(Rect gameRect, int level) {
        List<AEnemy> enemies = new ArrayList<>();
        level = level % LEVELS;

        switch (level){
            case 1:
                enemies = crearEnemigosNivelBarricada(gameRect);
                break;
            case 2:
                enemies = crearEnemigosNivelDonut(gameRect);
                break;
            case 3:
                enemies = crearEnemigosNivelPaquito(gameRect);
                break;
            case 4:
                enemies = crearEnemigosNivelLambada(gameRect);
                break;
            case 5:
                enemies = crearEnemigosNivelPulpo(gameRect);
                break;
            case 6:
                enemies = crearEnemigosNivelRock(gameRect);
                break;
            case 7:
                enemies = crearEnemigosNivelBarricadaConMuro(gameRect);
                break;
            case 8:
                enemies = crearEnemigosNivelRockConMuroDescendente(gameRect);
                break;
            case 9:
                enemies = crearEnemigosNivelPulpoConTorres(gameRect);
                break;
            default:
                enemies = crearMonstruo(gameRect);
                break;
        }
        return enemies;
    }

    /**
     * Crea un enemigo en una coordenada (i,j) con una velocidad (vx,vy)
     * @param i coordenada horizontal
     * @param j coordenada vertical
     * @param vx velocidad eje x
     * @param vy velocidad eje y
     * @return una instancia del enemigo
     */
    public static EnemyShip createEnemyShip(EEnemyType type, Image enemyImage, Rect gameRect, int i, int j, int vx, int vy, EEnemyShot shot) {
        EnemyShip e;
        switch (type){
            case E_ONEWAY:
                e = new EnemyShipOneWay(gameRect, enemyImage, TICKSxFRAME);
                break;
            case E_DIAGONAL:
                e = new EnemyShipDiagonal(gameRect, enemyImage, TICKSxFRAME);
                break;
            case E_SINU:
                e = new EnemyShipSinu(gameRect, enemyImage, TICKSxFRAME);
                break;
            case E_ROCKET:
                e = new EnemyShipRocket(gameRect, enemyImage, TICKSxFRAME);
                break;
            case E_NORMAL:
            default:
                e = new EnemyShip(gameRect, enemyImage, TICKSxFRAME);
                break;
        }
        if (shot == E_SHOT_GUN){
            e.setWeapon(new Gun());
        }
        e.setPos(getX(gameRect, i), getY(gameRect, j));
        e.setXSpeed(vx);
        e.setYSpeed(vy);
        return e;
    }

    /**
     * Crea un enemigo en una coordenada (i,j) con una velocidad (vx,vy)
     * @param i coordenada horizontal
     * @param j coordenada vertical
     * @return una instancia del enemigo
     */
    public static EnemyBarrier createEnemyBarrier(EEnemyType type, Image enemyImage, int impactsMax, Rect gameRect, int i, int j, int vy) {
        EnemyBarrier e;
        switch (type){
            case E_TOWER:
                e = new EnemyTower(enemyImage, impactsMax);
                break;
            case E_BARRIERDOWN:
                e = new EnemyBarrierDownwards(enemyImage, impactsMax, vy);
                break;
            case E_BARRIER:
            default:
                e = new EnemyBarrier(enemyImage, impactsMax);
                break;
        }
        e.setPos(getX(gameRect, i), getY(gameRect, j));
        return e;
    }

    public static List<AEnemy> crearEnemigosNivelBarricada(Rect gameRect) {
        List<AEnemy> enemies = new ArrayList<>();
        enemies.add(createEnemyShip(E_ONEWAY, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 0, 0, vx, 0, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_ONEWAY, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 7, 1, -vx, 0, E_SHOT_GUN));
        List<EnemyShip> el1 = new ArrayList<>();
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_1, gameRect, 2, 2, 0, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_1, gameRect, 3, 2, 0, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_1, gameRect, 4, 2, 0, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_1, gameRect, 5, 2, 0, 0, E_SHOT_NOTHING));
        EnemyShipGroup eg1 = new EnemyShipGroup(gameRect, el1);
        eg1.setXSpeed(vx);
        enemies.add(eg1);
        List<EnemyShip> el2 = new ArrayList<>();
        el2.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_3, gameRect, 2, 4, 0, 0, E_SHOT_NOTHING));
        el2.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_3, gameRect, 3, 4, 0, 0, E_SHOT_NOTHING));
        el2.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_3, gameRect, 4, 4, 0, 0, E_SHOT_NOTHING));
        el2.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_3, gameRect, 5, 4, 0, 0, E_SHOT_NOTHING));
        EnemyShipGroup eg2 = new EnemyShipGroup(gameRect, el2);
        eg2.setXSpeed(-vx);
        enemies.add(eg2);
        return enemies;
    }

    public static List<AEnemy> crearEnemigosNivelDonut(Rect gameRect) {
        List<AEnemy> enemies = new ArrayList<>();
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 0, 0, vx, 0, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 7, 1, -vx, 0, E_SHOT_GUN));
        List<EnemyShip> el1 = new ArrayList<>();
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 2, 3, 0, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 3, 2, 0, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 4, 2, 0, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 5, 3, 0, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 2, 4, 0, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 3, 5, 0, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 4, 5, 0, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 5, 4, 0, 0, E_SHOT_NOTHING));
        EnemyShipGroup eg1 = new EnemyShipGroup(gameRect, el1);
        eg1.setXSpeed(vx);
        enemies.add(eg1);
        return enemies;
    }

    public static List<AEnemy> crearEnemigosNivelPaquito(Rect gameRect) {
        List<AEnemy> enemies = new ArrayList<>();
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 0, 0, vx, 0, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 7, 1, -vx, 0, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 3, 2, vx, 0, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 4, 3, -vx, 0, E_SHOT_GUN));
        List<EnemyShip> el1 = new ArrayList<>();
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 2, 4, vx, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 3, 4, vx, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 4, 4, vx, 0, E_SHOT_NOTHING));
        el1.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 5, 4, vx, 0, E_SHOT_NOTHING));
        EnemyShipGroup eg1 = new EnemyShipGroup(gameRect, el1);
        eg1.setXSpeed(vx);
        enemies.add(eg1);
        return enemies;
    }

    public static List<AEnemy> crearEnemigosNivelLambada(Rect gameRect) {
        List<AEnemy> enemies = new ArrayList<>();
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 0, 0, vx, 0, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 7, 1, -vx, 0, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_SINU, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 0, 3, vx, vy, E_SHOT_NOTHING));
        enemies.add(createEnemyShip(E_SINU, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 7, 6, -vx, vy, E_SHOT_NOTHING));
        enemies.add(createEnemyShip(E_SINU, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 3, 3, -vx, vy, E_SHOT_NOTHING));
        enemies.add(createEnemyShip(E_SINU, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 4, 6, vx, vy, E_SHOT_NOTHING));
        return enemies;
    }

    public static List<AEnemy> crearEnemigosNivelPulpo(Rect gameRect) {
        List<AEnemy> enemies = new ArrayList<>();
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 0, 0, vx, 0, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 7, 1, -vx, 0, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_DIAGONAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 0, 0, vx, vy, E_SHOT_NOTHING));
        enemies.add(createEnemyShip(E_DIAGONAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 7, 0, -vx, vy, E_SHOT_NOTHING));
        enemies.add(createEnemyShip(E_DIAGONAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 3, 0, -vx, vy, E_SHOT_NOTHING));
        enemies.add(createEnemyShip(E_DIAGONAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 4, 0, vx, vy, E_SHOT_NOTHING));
        return enemies;
    }

    public static List<AEnemy> crearEnemigosNivelRock(Rect gameRect) {
        List<AEnemy> enemies = new ArrayList<>();
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 0, 0, vx, 0, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_NORMAL, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 7, 1, -vx, 0, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_ROCKET, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 0, 3, vx, 2*vy, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_ROCKET, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 7, 6, -vx, 2*vy, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_ROCKET, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 3, 3, -vx, 2*vy, E_SHOT_GUN));
        enemies.add(createEnemyShip(E_ROCKET, ENEMYSHIP_SPRITE_IMAGE_2, gameRect, 4, 6, vx, 2*vy, E_SHOT_GUN));
        return enemies;
    }

    public static List<AEnemy> crearEnemigosNivelBarricadaConMuro(Rect gameRect) {
        List<AEnemy> enemies = crearEnemigosNivelBarricada(gameRect);
        enemies.add(createEnemyBarrier(E_BARRIER, ENEMYBARRIER4_SPRITE_IMAGE, 12, gameRect, 0, 14, 0));
        enemies.add(createEnemyBarrier(E_BARRIER, ENEMYBARRIER4_SPRITE_IMAGE, 12, gameRect, 2, 14, 0));
        enemies.add(createEnemyBarrier(E_BARRIER, ENEMYBARRIER4_SPRITE_IMAGE, 12, gameRect, 4, 14, 0));
        enemies.add(createEnemyBarrier(E_BARRIER, ENEMYBARRIER4_SPRITE_IMAGE, 12, gameRect, 6, 14, 0));
        return enemies;
    }

    public static List<AEnemy> crearEnemigosNivelRockConMuroDescendente(Rect gameRect) {
        List<AEnemy> enemies = crearEnemigosNivelRock(gameRect);
        enemies.add(createEnemyBarrier(E_BARRIERDOWN, ENEMYBARRIER4_SPRITE_IMAGE, 9, gameRect, 0, 0, vy));
        enemies.add(createEnemyBarrier(E_BARRIERDOWN, ENEMYBARRIER4_SPRITE_IMAGE, 9, gameRect, 2, 0, 2*vy));
        enemies.add(createEnemyBarrier(E_BARRIERDOWN, ENEMYBARRIER4_SPRITE_IMAGE, 9, gameRect, 4, 0, vy));
        enemies.add(createEnemyBarrier(E_BARRIERDOWN, ENEMYBARRIER4_SPRITE_IMAGE, 9, gameRect, 6, 0, 2*vy));
        return enemies;
    }

    public static List<AEnemy> crearEnemigosNivelPulpoConTorres(Rect gameRect) {
        List<AEnemy> enemies = crearEnemigosNivelPulpo(gameRect);
        enemies.add(createEnemyBarrier(E_TOWER, ENEMYTOWER3_SPRITE_IMAGE, 15, gameRect, 1, 7, 0));
        enemies.add(createEnemyBarrier(E_TOWER, ENEMYTOWER3_SPRITE_IMAGE, 15, gameRect, 6, 7, 0));
        return enemies;
    }

    public static List<AEnemy> crearMonstruo(Rect gameRect) {
        List<AEnemy> enemies = new ArrayList<>();
        AEnemy monster = new EnemyMonster(ENEMYTOWER3_SPRITE_IMAGE, 28, ticks, gameRect);
        monster.setPos(getX(gameRect, 3), getY(gameRect, 3));
        enemies.add(monster);
        enemies.add(createEnemyBarrier(E_TOWER, ENEMYTOWER3_SPRITE_IMAGE, 15, gameRect, 1, 7, 0));
        enemies.add(createEnemyBarrier(E_TOWER, ENEMYTOWER3_SPRITE_IMAGE, 15, gameRect, 6, 7, 0));
        return enemies;
    }

}
