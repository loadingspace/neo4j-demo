package com.loading.neo4j.service;

import com.loading.neo4j.dao.BasicNodeDao;
import com.loading.neo4j.dao.BasicRelationDao;
import com.loading.neo4j.entity.Basic.BasicNode;
import com.loading.neo4j.entity.Basic.BasicRelation;
import com.loading.neo4j.entity.Company;
import com.loading.neo4j.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * desc:
 * Created on 2017/10/10.
 *
 * @author Lo_ading
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class GraphService {

    @Autowired
    private BasicNodeDao<BasicNode> basicNodeDao;

    @Autowired
    private BasicRelationDao relationDao;

    public <T extends BasicNode> T saveNode(T node){
        basicNodeDao.save(node);
        return node;
    }

    public <T extends BasicRelation> T saveRelation(T relation){
        relationDao.save(relation);
        return relation;
    }

    public void delete(long id){
        basicNodeDao.delete(id);
    }

}
