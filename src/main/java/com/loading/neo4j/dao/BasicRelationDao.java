package com.loading.neo4j.dao;

import com.loading.neo4j.entity.Basic.BasicRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * desc:
 * Created on 2017/10/10.
 *
 * @author Lo_ading
 * @version 1.0.0
 * @since 1.0.0
 */
public interface BasicRelationDao extends Neo4jRepository<BasicRelation, Long>{
}
