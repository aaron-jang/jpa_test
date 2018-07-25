package io.aaronjang.jpa;

import com.google.common.collect.ImmutableList;
import io.aaronjang.jpa.repository.Sample;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceTest {

    @Autowired
    private SampleService sampleServiceJpaImpl;

    @Autowired
    private SampleService sampleServiceEMImpl;

    private static final Sample SAMPLE1 = Sample.create("1", "1111");
    private static final Sample SAMPLE1_1 = Sample.create("1", "3333");
    private static final Sample SAMPLE2 = Sample.create("2", "2222");

    @Test
    public void testSaveJPA() {
        testSave(this.sampleServiceJpaImpl);
    }

    @Test
    public void testSaveEM() {
        testSave(this.sampleServiceEMImpl);
    }

    private void testSave(SampleService sampleService) {
        // Given
        sampleService.deleteAll();
        sampleService.save(SAMPLE1);
        log.info("######## After Given #########");

        // When
        final Optional<Sample> byId = sampleService.getById(SAMPLE1.getId());

        // Then
        assertThat(byId.isPresent(), is(true));
        assertThat(byId.get(), is(SAMPLE1));
    }

    @Test
    public void testSaveDuplicateKeysJPA() {
        testSaveDuplicateKeys(this.sampleServiceJpaImpl);
    }

    @Test
    public void testSaveDuplicateKeysEM() {
        testSaveDuplicateKeys(this.sampleServiceEMImpl);
    }

    private void testSaveDuplicateKeys(SampleService sampleService) {
        // Given
        sampleService.deleteAll();
        sampleService.save(SAMPLE1);
        log.info("######## After Given #########");

        // When
        sampleService.saveAll(ImmutableList.of(SAMPLE1_1, SAMPLE2));

        // Then
    }
}