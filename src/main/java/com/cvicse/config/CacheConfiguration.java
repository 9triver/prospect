package com.cvicse.config;

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
            createCache(cm, com.cvicse.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.cvicse.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.cvicse.domain.User.class.getName());
            createCache(cm, com.cvicse.domain.Authority.class.getName());
            createCache(cm, com.cvicse.domain.User.class.getName() + ".authorities");
            createCache(cm, com.cvicse.domain.Officers.class.getName());
            createCache(cm, com.cvicse.domain.Ledger.class.getName());
            createCache(cm, com.cvicse.domain.Department.class.getName());
            createCache(cm, com.cvicse.domain.Role.class.getName());
            createCache(cm, com.cvicse.domain.Permission.class.getName());
            createCache(cm, com.cvicse.domain.Project.class.getName());
            createCache(cm, com.cvicse.domain.Planstrategy.class.getName());
            createCache(cm, com.cvicse.domain.Comprehensivecontrol.class.getName());
            createCache(cm, com.cvicse.domain.Document.class.getName());
            createCache(cm, com.cvicse.domain.Comprehensiveledger.class.getName());
            createCache(cm, com.cvicse.domain.Cycleplan.class.getName());
            createCache(cm, com.cvicse.domain.Annualplan.class.getName());
            createCache(cm, com.cvicse.domain.Monthplan.class.getName());
            createCache(cm, com.cvicse.domain.Planreturns.class.getName());
            createCache(cm, com.cvicse.domain.Planmonitor.class.getName());
            createCache(cm, com.cvicse.domain.Planexecute.class.getName());
            createCache(cm, com.cvicse.domain.Projectcharge.class.getName());
            createCache(cm, com.cvicse.domain.Pbsmanage.class.getName());
            createCache(cm, com.cvicse.domain.Pbssubmanage.class.getName());
            createCache(cm, com.cvicse.domain.Wbsmanage.class.getName());
            createCache(cm, com.cvicse.domain.Wbssubmanage.class.getName());
            createCache(cm, com.cvicse.domain.Pbsbaseline.class.getName());
            createCache(cm, com.cvicse.domain.Wbsbaseline.class.getName());
            createCache(cm, com.cvicse.domain.Progressmanagement.class.getName());
            createCache(cm, com.cvicse.domain.Qualitymanagement.class.getName());
            createCache(cm, com.cvicse.domain.Qualityobjectives.class.getName());
            createCache(cm, com.cvicse.domain.Qualityreturns.class.getName());
            createCache(cm, com.cvicse.domain.UnQualityAudit.class.getName());
            createCache(cm, com.cvicse.domain.Fundsmanagement.class.getName());
            createCache(cm, com.cvicse.domain.Totalbudget.class.getName());
            createCache(cm, com.cvicse.domain.Unitbudget.class.getName());
            createCache(cm, com.cvicse.domain.Fundsavailability.class.getName());
            createCache(cm, com.cvicse.domain.Contractualfunds.class.getName());
            createCache(cm, com.cvicse.domain.OutsourcingmPurchasePlan.class.getName());
            createCache(cm, com.cvicse.domain.OutsourcingmPurchaseExecute.class.getName());
            createCache(cm, com.cvicse.domain.Resourcemanagement.class.getName());
            createCache(cm, com.cvicse.domain.Humanresources.class.getName());
            createCache(cm, com.cvicse.domain.TechnicalCondition.class.getName());
            createCache(cm, com.cvicse.domain.Riskmanagement.class.getName());
            createCache(cm, com.cvicse.domain.Riskreport.class.getName());
            createCache(cm, com.cvicse.domain.Secrecymanagement.class.getName());
            createCache(cm, com.cvicse.domain.ProjectSecrecy.class.getName());
            createCache(cm, com.cvicse.domain.AnnualSecurityPlan.class.getName());
            createCache(cm, com.cvicse.domain.Safetycheck.class.getName());
            createCache(cm, com.cvicse.domain.EvaluationCriteria.class.getName());
            createCache(cm, com.cvicse.domain.ManagementCapacityEvaluation.class.getName());
            createCache(cm, com.cvicse.domain.ApprovalAgent.class.getName());
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
