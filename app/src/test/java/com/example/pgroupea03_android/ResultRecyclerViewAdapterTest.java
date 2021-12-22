package com.example.pgroupea03_android;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.pgroupea03_android.dtos.result.DtoOutputResult;
import com.example.pgroupea03_android.view.student.ResultRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultRecyclerViewAdapterTest {
    @Test
    public void testGenerateAverage_filledWitchCorrectData_returnExpected() {

        DtoOutputResult result = new DtoOutputResult(1, 1,
                "test", 2, 20, 1, "");
        DtoOutputResult result2 = new DtoOutputResult(1, 2,
                "test", 4, 20, 1, "");

        List<DtoOutputResult> results = new ArrayList<>(Arrays.asList(result, result2));

        ResultRecyclerViewAdapter resultRecyclerViewAdapter = new ResultRecyclerViewAdapter(results);

        double actual = resultRecyclerViewAdapter.generateAverage(results);

        final double expected = 15;

        assertEquals(expected, actual, 0.01);
    }
}