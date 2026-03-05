package dk.kinoxp.kinoxp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FILM")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String language;

    private String agerating;

    private int length;

    public Film() {}

    public Film(String title, String language, String agerating, int length ) {
        this.setTitle(title);
        this.setLanguage(language);
        this.setAgerating(agerating);
        this.setLength(length);
    }

    public Long getId() {
        return id;
    }

    public String getTitle (){
        return title;
    }

    public void setTitle (String title){
        this.title = title;

    }

    public String getLanguage (){
        return language;

    }

    public void setLanguage (String language){
        this.language = language;
    }

    public String getAgerating (){
        return agerating;
    }

    public void setAgerating (String agerating){
        this.agerating = agerating;

    }

    public int getLength (){
        return length;

    }

    public void setLength (int length){
        this.length = length;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

