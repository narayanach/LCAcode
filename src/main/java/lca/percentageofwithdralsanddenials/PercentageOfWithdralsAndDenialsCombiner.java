package lca.percentageofwithdralsanddenials;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;
import lca.keyvalues.LongPair;
import lca.keyvalues.TextPair;


public class PercentageOfWithdralsAndDenialsCombiner extends Reducer<TextPair, LongPair, TextPair, LongPair> {

	private static Long totalNumberOfCases = new Long(0);
	private static Long noOfCasesDenied    = new Long(0);
	private static LongPair result         = new LongPair();
	
	
	public void reduce(TextPair inputKey, Iterable<LongPair> values, Context context) throws IOException, InterruptedException {
	
		totalNumberOfCases = new Long(0);
		noOfCasesDenied    = new Long(0);
		for(LongPair value : values)
		{
		
				totalNumberOfCases += value.getFirst().get();
				noOfCasesDenied += value.getSecond().get();

		}

		result.setFirst(new LongWritable(totalNumberOfCases));

		result.setSecond(new LongWritable(noOfCasesDenied));				
		context.write(inputKey, result);
	
		}
}
