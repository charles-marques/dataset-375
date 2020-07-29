package be.ac.ucl.tele.yasmim.service;

import be.uclouvain.yasmim.business.LocationBsn;
import be.uclouvain.yasmim.business.ResourceBsn;
import be.uclouvain.yasmim.entity.DigitalLocation;
import be.uclouvain.yasmim.entity.Image;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/images")
@ManagedBean
public class ImageResource {

    @EJB
    private ResourceBsn resourceBsn;

    @EJB
    private LocationBsn locationBsn;
    
    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Image getImage(@PathParam("id") String id) {
        Image img = resourceBsn.findImage(id);
        return img;
    }

    @GET
    @Path("{id}/file")
    @Produces("image/jpeg,image/png")
    public Response getImageFile(@PathParam("id") String id, @MatrixParam("width") int width, @MatrixParam("height") int height) throws IOException {
        Image image = resourceBsn.findImage(id);
        DigitalLocation location = (DigitalLocation)locationBsn.findResourceLocation(image);
        File file = new File(location.getAbsolutePath());
        BufferedImage imageFile = ImageIO.read(file);

        if(width > 0 && height == 0) {
            int newHeight = (width * image.getHeight()) / image.getWidth();
            return Response.ok(createResizedCopy(imageFile, width, newHeight)).build();
        }
        else
            return Response.ok(file).build();
    }

    private BufferedImage createResizedCopy(java.awt.Image originalImage, int scaledWidth, int scaledHeight) {
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledBI.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}