package org.cucumbertaf.utils.ctafassert;

import io.cucumber.java.Scenario;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CTAFAssert {

    private final Scenario logger;

    public CTAFAssert(Scenario logger) {
        this.logger = logger;
    }

    public void log(String message) {
        logger.log(message);
    }

    public void assert_equals(String act, String exp, String failure_message, String success_message) {
        Assert.assertEquals(act, exp, failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_not_equals(String act, String exp, String failure_message, String success_message) {
        Assert.assertNotEquals(act, exp, failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_contains(String act, String exp, String failure_message, String success_message) {
        Assert.assertTrue(act.contains(exp), failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_contains(List<String> act, String exp, String failure_message, String success_message) {
        Assert.assertTrue(act.contains(exp), failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_not_contains(String act, String exp, String failure_message, String success_message) {
        Assert.assertFalse(act.contains(exp), failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_not_contains(List<String> act, String exp, String failure_message, String success_message) {
        Assert.assertFalse(act.contains(exp), failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_starts_with(String act, String exp, String failure_message, String success_message) {
        Assert.assertTrue(act.startsWith(exp), failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_not_starts_with(String act, String exp, String failure_message, String success_message) {
        Assert.assertFalse(act.startsWith(exp), failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_equals(int act, int exp, String failure_message, String success_message) {
        Assert.assertEquals(act, exp, failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_not_equals(int act, int exp, String failure_message, String success_message) {
        Assert.assertNotEquals(act, exp, failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_equals(int[] act, int[] exp, String failure_message, String success_message) {
        Assert.assertEquals(act, exp, failure_message);
        logger.log("actual = " + Arrays.toString(act) + " expected = " + Arrays.toString(exp) + " : " + success_message);
    }

    public void assert_not_equals(int[] act, int[] exp, String failure_message, String success_message) {
        Assert.assertNotEquals(act, exp, failure_message);
        logger.log("actual = " + Arrays.toString(act) + " expected = " + Arrays.toString(exp) + " : " + success_message);
    }

    public void assert_contains(Integer[] act, Integer exp, String failure_message, String success_message) {
        Assert.assertTrue(Arrays.asList(act).contains(exp), failure_message);
        logger.log("actual = " + Arrays.toString(act) + " expected = " + exp + " : " + success_message);
    }

    public void assert_not_contains(Integer[] act, Integer exp, String failure_message, String success_message) {
        Assert.assertFalse(Arrays.asList(act).contains(exp), failure_message);
        logger.log("actual = " + Arrays.toString(act) + " expected = " + exp + " : " + success_message);
    }

    public void assert_equals(List<String> act, List<String> exp, String failure_message, String success_message) {
        Assert.assertEquals(act, exp, failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_not_equals(List<String> act, List<String> exp, String failure_message, String success_message) {
        Assert.assertNotEquals(act, exp, failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_equals_integer(List<Integer> act, List<Integer> exp, String failure_message, String success_message) {
        Assert.assertEquals(act, exp, failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_not_equals_integer(List<Integer> act, List<Integer> exp, String failure_message, String success_message) {
        Assert.assertNotEquals(act, exp, failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_equals(Map<String, String> act, Map<String, String> exp, String failure_message, String success_message) {
        Assert.assertEquals(act, exp, failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

    public void assert_not_equals(Map<String, String> act, Map<String, String> exp, String failure_message, String success_message) {
        Assert.assertNotEquals(act, exp, failure_message);
        logger.log("actual = " + act + " expected = " + exp + " : " + success_message);
    }

}
