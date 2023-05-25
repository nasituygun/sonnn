package com.fadcr.agent.service;

import com.fadcr.agent.entity.Version;
import com.fadcr.agent.entity.method.Method;
import com.fadcr.agent.repository.VersionRepository;

import reactor.core.publisher.Flux;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class VersionService {

    private final VersionRepository versionRepository;

    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public Mono<Void> saveVersion(Version version) {
        return versionRepository.save(version).then();
    }

    public void saveVersionBlock(Version version){
        versionRepository.save(version).block();
    }


    public void saveVersions(List<Version> versions) {

        versionRepository.saveAll(versions).blockFirst();
    }

    public Version findByVersionName(String versionName) {

        Version version= versionRepository.findByName(versionName).block();

        return version;
    }


    public boolean isExitsByName(String name) {

        return versionRepository.existsByName(name).block();
    }

    public List<Version> getAll() {
        return versionRepository.findAll().collectList().block();
    }
}
