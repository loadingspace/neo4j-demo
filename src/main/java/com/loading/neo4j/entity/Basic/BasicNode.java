package com.loading.neo4j.entity.Basic;

import com.loading.neo4j.entity.InvestRelation;
import org.neo4j.ogm.annotation.*;

import java.util.Date;
import java.util.Set;

/**
 * desc:
 * Created on 2017/10/11.
 *
 * @author Lo_ading
 * @version 1.0.0
 * @since 1.0.0
 */
@NodeEntity
public class BasicNode implements BasicNodeInterface {

    @GraphId
    private Long id;

    @Property
    private String nodeName;

    @Property
    private Long added = new Date().getTime();

    @Relationship(direction = Relationship.OUTGOING)
    private Set<BasicRelation> outGoing;

    @Relationship(direction = Relationship.INCOMING)
    private Set<BasicRelation> inComing;

    public BasicNode(){
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public Long getAdded() {
        return added;
    }

    public void setAdded(Long added) {
        this.added = added;
    }

    public Set<BasicRelation> getOutGoing() {
        return outGoing;
    }

    public void setOutGoing(Set<BasicRelation> outGoing) {
        this.outGoing = outGoing;
    }

    public Set<BasicRelation> getInComing() {
        return inComing;
    }

    public void setInComing(Set<BasicRelation> inComing) {
        this.inComing = inComing;
    }
}
