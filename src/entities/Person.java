package entities;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * @Author maqiuyue
 * @Date 2020/7/7 16:58
 * @description
 */



public class Person {

    private Integer id;
    private String personName;

    public Person(String personName){
        this.personName = personName;
    }

    public Person(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
