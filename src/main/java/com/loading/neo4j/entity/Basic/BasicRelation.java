package com.loading.neo4j.entity.Basic;

import org.neo4j.ogm.annotation.*;

import java.util.Date;

/**
 * desc:
 * Created on 2017/10/11.
 *
 * @author Lo_ading
 * @version 1.0.0
 * @since 1.0.0
 */
@RelationshipEntity(type = "DEFAULT")
public class BasicRelation implements BasicRelationInterface{

    @GraphId
    private Long id;

    @StartNode
    private BasicNode source;

    @EndNode
    private BasicNode target;

    @Property
    private String relationName = "";

    @Property
    private Long added = new Date().getTime();

    public BasicRelation(){

    }

    public BasicRelation(BasicNode source, BasicNode target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public BasicNode getSource() {
        return source;
    }

    @Override
    public void setSource(BasicNode source) {
        this.source = source;
    }

    @Override
    public BasicNode getTarget() {
        return target;
    }

    @Override
    public void setTarget(BasicNode target) {
        this.target = target;
    }

    @Override
    public Long getAdded() {
        return added;
    }

    @Override
    public void setAdded(Long added) {
        this.added = added;
    }

    @Override
    public String getRelationName() {
        return relationName;
    }

}
