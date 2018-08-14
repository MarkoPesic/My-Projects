package com.shoes.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "shoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shoe.findAll", query = "SELECT s FROM Shoe s")
    , @NamedQuery(name = "Shoe.findById", query = "SELECT s FROM Shoe s WHERE s.id = :id")
    , @NamedQuery(name = "Shoe.findByPrice", query = "SELECT s FROM Shoe s WHERE s.price = :price")
    , @NamedQuery(name = "Shoe.findByCategory", query = "SELECT s FROM Shoe s WHERE s.category = :category")
    , @NamedQuery(name = "Shoe.findBySize", query = "SELECT s FROM Shoe s WHERE s.size = :size"),
      @NamedQuery(name = "Shoe.findByPhoto", query = "SELECT s FROM Shoe s WHERE s.photo = :photo")
    , @NamedQuery(name = "Shoe.findByName", query = "SELECT s FROM Shoe s WHERE s.name = :name")})
public class Shoe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "category")
    private int category;
    @Column(name = "size")
    private Integer size;
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    @Column(name = "photo")
    private String photo;
    
    @Transient
    public int quantity = 0;
    public int getQuantity(){
        return this.quantity;
    }

    public Shoe() {
    }

    public Shoe(Integer id) {
        this.id = id;
    }

    public Shoe(Integer id, int price, int category) {
        this.id = id;
        this.price = price;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shoe)) {
            return false;
        }
        Shoe other = (Shoe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shoes.model.Shoe[ id=" + id + " ]";
    }

}
