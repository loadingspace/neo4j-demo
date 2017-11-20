package com.loading.neo4j.entity.Basic;


/**
 * desc:
 * Created on 2017/10/10.
 *
 * @author Lo_ading
 * @version 1.0.0
 * @since 1.0.0
 */
public interface BasicNodeInterface {

    Long getId();

    void setId(Long id);

    String getNodeName();

    void setNodeName(String nodeName);

    Long getAdded();

    void setAdded(Long added);

}
