package net.minecraft;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
import net.minecraft.util.Util;

public class TexturedPanel extends JPanel
{
  private static final long serialVersionUID = 1L;
  private Image img;
  private Image bgImage;

  public TexturedPanel()
  {
    setOpaque(true);
    bgImage = Util.loadBg();
  }

  public void update(Graphics g) {
    paint(g);
  }

  public void paintComponent(Graphics g2) 
  {
    int w = getWidth() / 2 + 1;
    int h = getHeight() / 2 + 1;
    if ((img == null) || (img.getWidth(null) != w) || (img.getHeight(null) != h)) {
      img = createImage(w, h);

      Graphics g = img.getGraphics();
      for (int x = 0; x <= w / 32; x++) {
        for (int y = 0; y <= h / 32; y++)
          g.drawImage(bgImage, x * 32, y * 32, null);
      }
      if ((g instanceof Graphics2D)) {
        Graphics2D gg = (Graphics2D)g;
        int gh = 1;
        gg.setPaint(new GradientPaint(new Point2D.Float(0.0F, 0.0F), new Color(553648127, true), new Point2D.Float(0.0F, gh), new Color(0, true)));
        gg.fillRect(0, 0, w, gh);

        gh = h;
        gg.setPaint(new GradientPaint(new Point2D.Float(0.0F, 0.0F), new Color(0, true), new Point2D.Float(0.0F, gh), new Color(1610612736, true)));
        gg.fillRect(0, 0, w, gh);
      }
      g.dispose();
    }
    g2.drawImage(img, 0, 0, w * 2, h * 2, null);
  }
}