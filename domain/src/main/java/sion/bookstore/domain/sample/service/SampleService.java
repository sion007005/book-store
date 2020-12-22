package sion.bookstore.domain.sample.service;

import org.springframework.stereotype.Service;
import sion.bookstore.domain.sample.repository.Sample;
import sion.bookstore.domain.sample.repository.SampleRepository;

import java.util.List;

@Service
public class SampleService {
    private final SampleRepository sampleRepository;

    public SampleService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    public Long insert(Sample sample) {
        sampleRepository.insert(sample);
        return sample.getId();
    }

    public Sample findOne(Long id) {
        return sampleRepository.findOne(id);
    }

    public List<Sample> findAll() {
        return sampleRepository.findAll();
    }
}
