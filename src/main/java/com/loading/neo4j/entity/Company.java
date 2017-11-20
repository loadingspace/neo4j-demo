package com.loading.neo4j.entity;

import com.loading.neo4j.entity.Basic.BasicNode;
import com.loading.neo4j.entity.Basic.BasicNodeInterface;
import com.loading.neo4j.entity.Basic.InvestNode;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.*;

/**
 * desc: https://docs.spring.io/spring-data/neo4j/docs/current/reference/html/
 * Created on 2017/10/10.
 *
 * @author Lo_ading
 * @version 1.0.0
 * @since 1.0.0
 */
@NodeEntity
public class Company extends InvestNode implements BasicNodeInterface{

    private String code;

    @Relationship(type = "INVEST", direction = Relationship.INCOMING)
    private Set<InvestRelation> partner;

    @Relationship(type = "INVEST", direction = Relationship.OUTGOING)
    private Set<InvestRelation> investment;

    public Company(){

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<InvestRelation> getPartner() {
        return partner;
    }

    public void setPartner(Set<InvestRelation> partner) {
        this.partner = partner;
    }

    public Set<InvestRelation> getInvestment() {
        return investment;
    }

    public void setInvestment(Set<InvestRelation> investment) {
        this.investment = investment;
    }
}
