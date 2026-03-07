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

    private String ageRating;

    private Integer length;

    public Film() {}

    public Film(String title, String language, String ageRating, Integer length ) {
        this.setTitle(title);
        this.setLanguage(language);
        this.setAgeRating(ageRating);
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

    public String getAgeRating(){
        return ageRating;
    }

    public void setAgeRating(String ageRating){
        this.ageRating = ageRating;

    }

    public Integer getLength (){
        return length;

    }

    public void setLength (Integer length){
        this.length = length;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

