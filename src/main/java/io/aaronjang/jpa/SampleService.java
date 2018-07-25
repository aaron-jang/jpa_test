package io.aaronjang.jpa;

import io.aaronjang.jpa.repository.Sample;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

public interface SampleService {
    @Transactional
    void deleteAll();

    @Transactional
    void saveAll(Collection<Sample> samples);

    @Transactional
    void save(Sample sample);

    @Transactional
    Optional<Sample> getById(String id);
}
