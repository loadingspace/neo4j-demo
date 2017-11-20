package com.loading.neo4j.entity;

import com.loading.neo4j.entity.Basic.BasicNodeInterface;
import com.loading.neo4j.entity.Basic.InvestNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;


/**
 * desc:
 * Created on 2017/10/10.
 *
 * @author Lo_ading
 * @version 1.0.0
 * @since 1.0.0
 */
@NodeEntity
public class Person extends InvestNode implements BasicNodeInterface{

    private int age;

    @Relationship(type = "INVEST", direction = Relationship.OUTGOING)
    private Set<InvestRelation> investment;

    public Person(){

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<InvestRelation> getInvestment() {
        return investment;
    }

    public void setInvestment(Set<InvestRelation> investment) {
        this.investment = investment;
    }
}
