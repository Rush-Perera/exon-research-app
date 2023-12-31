package mapping;
// Generated Jul 18, 2023 10:03:05 AM by Hibernate Tools 4.3.1

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




/**
 * Research generated by hbm2java
 */
public class Research  implements java.io.Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     
     private Area area;
     private String topic;
     private String researchAbstract;
     private String docPath;
     private String imgPath;
     private String keywords;

    public Research() {
    }

	
    public Research(int id, Area area) {
        this.id = id;
        this.area = area;
    }
    public Research(int id, Area area, String topic, String researchAbstract, String docPath, String imgPath, String keywords) {
       this.id = id;
       this.area = area;
       this.topic = topic;
       this.researchAbstract = researchAbstract;
       this.docPath = docPath;
       this.imgPath = imgPath;
       this.keywords = keywords;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Area getArea() {
        return this.area;
    }
    
    public void setArea(Area area) {
        this.area = area;
    }
    public String getTopic() {
        return this.topic;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getResearchAbstract() {
        return this.researchAbstract;
    }
    
    public void setResearchAbstract(String researchAbstract) {
        this.researchAbstract = researchAbstract;
    }
    public String getDocPath() {
        return this.docPath;
    }
    
    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }
    public String getImgPath() {
        return this.imgPath;
    }
    
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public String getKeywords() {
        return this.keywords;
    }
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }




}


