package com.supplynext.company_api;


import com.supplynext.company_api.utilities.CommonUtility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommonUtilityTest {

    // First I want to check methods generate id or not
    @Test
    public void isIdGenerated(){
        String id = CommonUtility.generateIdForEntity("EMP");
        assertNotNull(id);
    }


    @Test
    public void isShouldStartWithEntityName(){
        String id = CommonUtility.generateIdForEntity("EMP"); // EMP
        boolean resp = id.startsWith("EMP");
        assertTrue(resp);
    }


    @Test
    public void forEveryCallShouldGenerateDifferentId(){
        String orderId1 = CommonUtility.generateIdForEntity("ORDER");
        String orderId2 = CommonUtility.generateIdForEntity("ORDER");
        assertNotEquals(orderId2, orderId1);
    }


    // Test scenarios

    @Test
    public void shouldGeneratePasswordWith15Length(){
        String pass = CommonUtility.generateRandomPassword(15);
        assertNotNull(pass);
        assertEquals(15, pass.length());
    }

    @Test
    public void generateDifferentPasswordForDifferentCalls(){
        String pass1 = CommonUtility.generateRandomPassword(15);
        String pass2 = CommonUtility.generateRandomPassword(15);
        assertNotEquals(pass2, pass1);
    }

}
