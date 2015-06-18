package com.dxns.data;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.dxns.data.api.Api;
import com.dxns.data.model.StatuData;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

        StatuData statuData = Api.getApi().Login();

        System.out.println(statuData.message);
    }
}