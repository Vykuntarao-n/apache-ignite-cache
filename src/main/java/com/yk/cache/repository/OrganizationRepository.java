package com.yk.cache.repository;

import com.yk.cache.entity.Organization;
import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;

@RepositoryConfig(cacheName = "ORG_CACHE")
public interface OrganizationRepository extends IgniteRepository<Organization,Long> {
}
