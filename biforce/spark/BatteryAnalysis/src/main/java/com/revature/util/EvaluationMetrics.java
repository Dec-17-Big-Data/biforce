package com.revature.util;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;

public class EvaluationMetrics {

	// 2nd column should be % chance to fail (0.0 = 0% to 1.0 = 100%)
	// 3rd column should be either 0 for fail or 1 for pass

	// Return MAE (Mean Absolute Error)
	// EX1: if chance to fail is 0.70 but battery passed with 1, absolute error is
	// 0.70.
	// EX2: if chance to fail is 0.70 but battery failed with 0, absolute error is
	// 0.30.
	public static double testMAE(JavaRDD<Row> results) {
		return results.mapToDouble(row -> Math.abs(new Double(row.getInt(2)) - 1.0d + row.getDouble(1))).mean();
	}

	// Return RMSE (Root Mean Squared Error)
	
	public static double testRMSE(JavaRDD<Row> results) {
		return Math.sqrt(results
				.mapToDouble(row -> Math.pow((new Double(row.getInt(2)) - 1.0d + row.getDouble(1)), 2.0d)).mean());
	}

}