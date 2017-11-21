package com.loading.neo4j.service;

import com.alibaba.fastjson.JSON;
import com.loading.neo4j.dao.BasicNodeDao;
import com.loading.neo4j.dao.CompanyDao;
import com.loading.neo4j.dao.PersonDao;
import com.loading.neo4j.entity.Basic.BasicNode;
import com.loading.neo4j.entity.Company;
import com.loading.neo4j.entity.InvestRelation;
import com.loading.neo4j.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * desc:
 * Created on 2017/10/13.
 *
 * @author Lo_ading
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
//@Rollback(true)
public class GraphServiceTest {

    @Autowired
    private GraphService graphService;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private BasicNodeDao<BasicNode> basicNodeDao;

    @Test
    public void save() throws Exception {

        Person personA = new Person();
        personA.setNodeName("A Person");
        personA.setAge(20);
        graphService.saveNode(personA);
        System.out.println(JSON.toJSONString(personA));

        Person personB = new Person();
        personB.setNodeName("B Person");
        personB.setAge(23);
        graphService.saveNode(personB);
        System.out.println(JSON.toJSONString(personB));

        Person personC = new Person();
        personC.setNodeName("C Person");
        personC.setAge(40);
        graphService.saveNode(personC);
        System.out.println(JSON.toJSONString(personC));

        Company companyA = new Company();
        companyA.setNodeName("A Company");
        companyA.setCode("A code");
        graphService.saveNode(companyA);
        System.out.println(JSON.toJSONString(companyA));

        Company companyB = new Company();
        companyB.setNodeName("B Company");
        companyB.setCode("B code");
        graphService.saveNode(companyB);
        System.out.println(JSON.toJSONString(companyB));

        InvestRelation partnerRelationA = new InvestRelation(personA, companyA);
        graphService.saveRelation(partnerRelationA);
        System.out.println(JSON.toJSONString(partnerRelationA));

        InvestRelation partnerRelationB = new InvestRelation(companyB, companyA);
        graphService.saveRelation(partnerRelationB);
        System.out.println(JSON.toJSONString(partnerRelationB));

        InvestRelation partnerRelationC = new InvestRelation(personB, companyB);
        graphService.saveRelation(partnerRelationC);
        System.out.println(JSON.toJSONString(partnerRelationC));

        InvestRelation partnerRelationD = new InvestRelation(personC, companyA);
        graphService.saveRelation(partnerRelationD);
        System.out.println(JSON.toJSONString(partnerRelationD));

        InvestRelation partnerRelationE = new InvestRelation(personC, companyB);
        graphService.saveRelation(partnerRelationE);
        System.out.println(JSON.toJSONString(partnerRelationE));
    }

    @Test
    public void delete() throws Exception {
        graphService.delete(28L);
    }

    @Test
    public void findCompany() throws Exception {
        Company company = companyDao.findOne(34L,1);
        System.out.println(JSON.toJSONString(company));
    }

    @Test
    public void findPerson() throws Exception {
        Person person = personDao.findOne(32L,2);
        System.out.println(JSON.toJSONString(person));
    }

    @Test
    public void findNode() throws Exception {
        BasicNode node = basicNodeDao.findOne(34L, 1);
        System.out.println(JSON.toJSONString(node));
    }

}