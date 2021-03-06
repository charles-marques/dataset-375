import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import apoSkunkman.ApoSkunkmanConstants;
import apoSkunkman.entity.ApoSkunkmanEntity;
import apoSkunkman.level.ApoSkunkmanLevel;

/*
 * Copyright (C) 2012 Kilian Gaertner
 * 
 * Dieser Quelltext ist Open Source und kann von jedem verwendet werden, der 
 * folgende Bedingung einh�lt:
 * Jeder, der den Quelltext, ob in Teilen oder komplett,nutzt, muss dem Inhabenden
 * des Copyrights eine Pizza spendieren, sollte derjenige dem Inhabenden des Copyrights
 * begegnen.
 */

public class TrollBombEntity extends ApoSkunkmanEntity {

    private static BufferedImage bImage;

    private final ApoSkunkmanLevel level;
    private static int playerId;

    private long layBombTimer = 1250L;

    public TrollBombEntity(float x, float y, ApoSkunkmanLevel level) {
        super(bImage, x * ApoSkunkmanConstants.TILE_SIZE, y * ApoSkunkmanConstants.TILE_SIZE, ApoSkunkmanConstants.TILE_SIZE, ApoSkunkmanConstants.TILE_SIZE, 4, 250L, 1, false);
        this.level = level;
    }

    @Override
    public void think(int time) {
        super.think(time);

        if ((layBombTimer -= time) <= 0) {
            this.setBVisible(false);
            level.layBomb((int) getX() / ApoSkunkmanConstants.TILE_SIZE, (int) getY() / ApoSkunkmanConstants.TILE_SIZE, playerId);
        }
    }
    public void render(Graphics2D g, int x, int y) {
        if (isBVisible() && getIBackground() != null) {
            g.drawImage(this.getImages()[0][getFrame()], (int) (this.getX() + x), (int) (this.getY() + y), null);
        }
    }

    public static void init(BufferedImage preBombEffect, int player) {
        bImage = preBombEffect;
        playerId = player;
    }
}
