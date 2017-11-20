package com.loading.neo4j.entity;

import com.loading.neo4j.entity.Basic.BasicRelation;
import com.loading.neo4j.entity.Basic.BasicRelationInterface;
import com.loading.neo4j.entity.Basic.InvestNode;
import org.neo4j.ogm.annotation.*;

/**
 * desc: 投资关系
 * Created on 2017/10/10.
 *
 * @author Lo_ading
 * @version 1.0.0
 * @since 1.0.0
 */
@RelationshipEntity(type = "INVEST")
public class InvestRelation extends BasicRelation implements BasicRelationInterface{

    @Property
    private String relationName = "投资";

    public InvestRelation(){

    }

    public <T extends InvestNode> InvestRelation(T source, Company target) {
        super(source, target);
    }

    @Override
    public String getRelationName() {
        return relationName;
    }

}
