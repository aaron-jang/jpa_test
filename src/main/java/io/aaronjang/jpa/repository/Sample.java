package io.aaronjang.jpa.repository;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Sample {

    @Id
    private String id;
    private String value;

    public static Sample create(String id, String value) {
        final Sample sample = new Sample();
        sample.id = id;
        sample.value = value;
        return sample;
    }
}
