package lca.percentageofwithdralsanddenials;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

import lca.keyvalues.LongPair;
import lca.keyvalues.TextPair;


public class PercentageOfWithdralsAndDenialsReducer extends Reducer<TextPair, LongPair, TextPair, LongPair> {

	private static Long totalNumberOfCases = new Long(0);
	private static Long noOfCasesDenied = new Long(0);
	private static Float casesDenied = new Float(0);
	private static LongPair result = new LongPair();
	private static Float percentageOfCasesDenied = new Float(0);
	
	public void reduce(TextPair inputKey, Iterable<LongPair> values, Context context) throws IOException, InterruptedException {
	
		totalNumberOfCases = new Long(0);
		noOfCasesDenied = new Long(0);
		casesDenied = new Float(0);
		percentageOfCasesDenied = new Float(0);
		
		for(LongPair value : values)
		{
		
				totalNumberOfCases += value.getFirst().get();
				noOfCasesDenied += value.getSecond().get();
		}
		casesDenied = noOfCasesDenied.floatValue();
		percentageOfCasesDenied = casesDenied.floatValue()/totalNumberOfCases.floatValue();
		percentageOfCasesDenied = percentageOfCasesDenied * 100;
		result.setFirst(new LongWritable(totalNumberOfCases));
		result.setThird(new FloatWritable(percentageOfCasesDenied));		
	
		context.write(inputKey, result);
	
	}
	
}
