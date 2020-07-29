package be.ac.ucl.tele.yasmim.controller;

import be.uclouvain.yasmim.business.ApplicationPropertyBsn;
import be.uclouvain.yasmim.business.LocationBsn;
import be.uclouvain.yasmim.business.ResourceBsn;
import be.uclouvain.yasmim.entity.ApplicationProperty;
import be.uclouvain.yasmim.entity.DigitalLocation;
import be.uclouvain.yasmim.entity.Image;
import be.uclouvain.yasmim.entity.Properties;
import be.uclouvain.yasmim.entity.Resource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ResourceUploadServlet extends HttpServlet {

    @EJB
    private ApplicationPropertyBsn applicationPropertyBsn;

    @EJB
    private ResourceBsn resourceBsn;

    @EJB
    private LocationBsn locationBsn;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(ServletFileUpload.isMultipartContent(request)) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(4096);
                ApplicationProperty property = applicationPropertyBsn.findApplicationProperty(Properties.REPOSITORY_PATH);
                File repository = new File(property.getPropertyValue());
                factory.setRepository(repository);

                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = upload.parseRequest(request);

                Iterator iParam = items.iterator();
                String resourceId = null;
                String resourceType = null;
                Resource resource = null;
                DigitalLocation location = null;
                FileItem item;
                while (iParam.hasNext()) {
                    item = (FileItem) iParam.next();

                    if (item.isFormField()) {
                        if(item.getFieldName().equals("resourceType")) {
                            resourceType = item.getString();
                        }
                        else if(item.getFieldName().equals("resourceId")) {
                            resourceId = item.getString();
                            resource = resourceBsn.findResource(resourceId);
                            location = new DigitalLocation(resource, resourceType);
                        }
                    }
                    else {
                        if(resource != null && location != null) {
                            location.setFileName(resource.getId());
                            location.setFileExtension(item.getName().substring(item.getName().lastIndexOf(".") + 1).toLowerCase());
                            location.setFileContentType(item.getContentType());
                            location.setFileSize(item.getSize());
                            location.setAbsolutePath(property.getPropertyValue() +"/"+ location.getRelativePath() +"/"+ location.getFileName() +"."+ location.getFileExtension());
                            File uploadedFile = new File(location.getAbsolutePath());
                            item.write(uploadedFile);

                            BufferedImage img = ImageIO.read(uploadedFile);
                            Image image = (Image) resource;
                            image.setWidth(img.getWidth());
                            image.setHeight(img.getHeight());
                            image.setPending(false);
                            location.setResource(image);
                            locationBsn.save(location);
                        }
                    }
                }
            }
        } catch(FileUploadException fue) {
            fue.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/yasmim/media/image_annotation.xhtml");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Resource Upload Servlet";
    }
}