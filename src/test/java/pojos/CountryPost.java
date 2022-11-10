package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryPost {
/*
    {
        "id": 24113,
        "name": "ANGOLA",
        "states": null
    }
    id'yi sistem atadığı için ve states'ler hep null geldiği için biz post işlemini sadece name ile yapacağız.
*/

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryPost() {
    }

    public CountryPost(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CountryPost{" +
                "name='" + name + '\'' +
                '}';
    }
}