package io.aaronjang.jpa;

import io.aaronjang.jpa.repository.Sample;
import io.aaronjang.jpa.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class SampleServiceEMImpl implements SampleService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SampleRepository sampleRepository;

    @Transactional
    @Override
    public void deleteAll() {
        sampleRepository.deleteAll();
    }

    @Transactional
    @Override
    public void saveAll(Collection<Sample> samples) {
        samples.stream().filter(s -> entityManager.contains(s)).forEach(s -> entityManager.persist(s));
        samples.stream().filter(s -> !entityManager.contains(s)).forEach(s -> entityManager.merge(s));
    }

    @Transactional
    @Override
    public void save(Sample sample) {
        entityManager.persist(sample);
    }

    @Override
    public Optional<Sample> getById(String id) {
        return Optional.of(entityManager.find(Sample.class, id));
    }
}
