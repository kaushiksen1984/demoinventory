package com.products.model;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class DepartmentsTest {

	@Test
    public void should_Pass_All_Pojo_Tests_DepartmentsTest(){
        final Class<Departments> classUnderTest = Departments.class;
        Assertions.assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER,
        		Method.TO_STRING).areWellImplemented();
    }
}
