package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.brief.ServiceOutletBrief;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository("serviceOutletBriefRepository")
public interface ServiceOutletBriefRepository extends JpaRepository<ServiceOutletBrief, Integer> {

    ServiceOutletBrief findDistinctByDesignation(String designation);
}
