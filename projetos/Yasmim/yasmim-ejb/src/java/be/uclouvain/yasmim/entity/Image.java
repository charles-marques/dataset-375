package be.uclouvain.yasmim.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@DiscriminatorValue("image")
@XmlRootElement(name="image")
@XmlType(name="ImageType", propOrder={"name","contributors","authorships","width","height","pending"})
@XmlAccessorType(XmlAccessType.NONE)
public class Image extends Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement
    private Integer width;
    @XmlElement
    private Integer height;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}