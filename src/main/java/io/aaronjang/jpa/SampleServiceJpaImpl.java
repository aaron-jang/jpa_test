package io.aaronjang.jpa;

import io.aaronjang.jpa.repository.Sample;
import io.aaronjang.jpa.repository.SampleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class SampleServiceJpaImpl implements SampleService {

    private final SampleRepository sampleRepository;

    @Autowired
    public SampleServiceJpaImpl(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }
    @Override
    @Transactional
    public void deleteAll() {
        sampleRepository.deleteAll();
    }

    @Override
    @Transactional
    public void saveAll(Collection<Sample> samples) {
        sampleRepository.saveAll(samples);
    }

    @Override
    @Transactional
    public void save(Sample sample) {
        sampleRepository.save(sample);
    }

    @Override
    @Transactional
    public Optional<Sample> getById(String id) {
        final Optional<Sample> byId = sampleRepository.findById(id);
        return byId;
    }
}
