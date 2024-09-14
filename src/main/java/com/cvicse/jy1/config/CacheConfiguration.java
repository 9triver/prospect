package com.cvicse.jy1.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(
                Object.class,
                Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries())
            )
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build()
        );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.cvicse.jy1.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.cvicse.jy1.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.cvicse.jy1.domain.User.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Authority.class.getName());
            createCache(cm, com.cvicse.jy1.domain.User.class.getName() + ".authorities");
            createCache(cm, com.cvicse.jy1.domain.Officers.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Officers.class.getName() + ".departments");
            createCache(cm, com.cvicse.jy1.domain.Department.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Department.class.getName() + ".officers");
            createCache(cm, com.cvicse.jy1.domain.Project.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Project.class.getName() + ".projectpbs");
            createCache(cm, com.cvicse.jy1.domain.Project.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.Projectpbs.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Projectpbs.class.getName() + ".projects");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".projects");
            createCache(cm, com.cvicse.jy1.domain.Officers.class.getName() + ".roles");
            createCache(cm, com.cvicse.jy1.domain.Projectpbs.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".projectpbs");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".progressPlans");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".fundsEstimations");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".contractCostBudgets");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".costControlSystems");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".qualityObjectives");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".outsourcingContractuals");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".outsourcingPurchasePlans");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".technicals");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".technicalConditions");
            createCache(cm, com.cvicse.jy1.domain.Projectwbs.class.getName() + ".projectRisks");
            createCache(cm, com.cvicse.jy1.domain.Role.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Role.class.getName() + ".permissions");
            createCache(cm, com.cvicse.jy1.domain.Role.class.getName() + ".officers");
            createCache(cm, com.cvicse.jy1.domain.Permission.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Permission.class.getName() + ".roles");
            createCache(cm, com.cvicse.jy1.domain.Document.class.getName());
            createCache(cm, com.cvicse.jy1.domain.ProgressPlan.class.getName());
            createCache(cm, com.cvicse.jy1.domain.ProgressPlan.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.ProgressPlan.class.getName() + ".projectRisks");
            createCache(cm, com.cvicse.jy1.domain.PlanReturns.class.getName());
            createCache(cm, com.cvicse.jy1.domain.FundsEstimation.class.getName());
            createCache(cm, com.cvicse.jy1.domain.FundsEstimation.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.Contract.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Contract.class.getName() + ".costControlSystems");
            createCache(cm, com.cvicse.jy1.domain.ContractCostBudget.class.getName());
            createCache(cm, com.cvicse.jy1.domain.ContractCostBudget.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.CostControlSystem.class.getName());
            createCache(cm, com.cvicse.jy1.domain.CostControlSystem.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.CostControlSystem.class.getName() + ".contracts");
            createCache(cm, com.cvicse.jy1.domain.QualityObjectives.class.getName());
            createCache(cm, com.cvicse.jy1.domain.QualityObjectives.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.QualityObjectives.class.getName() + ".qualityReturns");
            createCache(cm, com.cvicse.jy1.domain.QualityReturns.class.getName());
            createCache(cm, com.cvicse.jy1.domain.QualityReturns.class.getName() + ".qualityObjectives");
            createCache(cm, com.cvicse.jy1.domain.UnQualityAudit.class.getName());
            createCache(cm, com.cvicse.jy1.domain.OutsourcingContractual.class.getName());
            createCache(cm, com.cvicse.jy1.domain.OutsourcingContractual.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.OutsourcingPurchasePlan.class.getName());
            createCache(cm, com.cvicse.jy1.domain.OutsourcingPurchasePlan.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.OutsourcingPurchaseExecute.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Projectremit.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Technical.class.getName());
            createCache(cm, com.cvicse.jy1.domain.Technical.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.TechnicalCondition.class.getName());
            createCache(cm, com.cvicse.jy1.domain.TechnicalCondition.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.ProjectRisk.class.getName());
            createCache(cm, com.cvicse.jy1.domain.ProjectRisk.class.getName() + ".projectwbs");
            createCache(cm, com.cvicse.jy1.domain.ProjectRisk.class.getName() + ".progressPlans");
            createCache(cm, com.cvicse.jy1.domain.RiskReport.class.getName());
            createCache(cm, com.cvicse.jy1.domain.LeaveApplicationInfo.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
