package lca.percentageofwithdralsanddenials;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import lca.keyvalues.LongPair;
import lca.keyvalues.TextPair;
import lca.parsers.LCAparsers;


		public class PercentageOfWithdralsAndDenialsMapper extends Mapper<LongWritable, Text, TextPair, LongPair> {
		private static LCAparsers parser = new LCAparsers();
		
		private static TextPair mapOutputKey = new TextPair();
		private static LongPair mapOutputValue = new LongPair();
		public static long var = new Long(0);
		public long number = new Long(0);
		public void map(LongWritable lineOffset, Text record, Context context) throws IOException, InterruptedException {
			
			parser.parse(record.toString());
			
			mapOutputKey.setFirst(new Text(parser.getLcaCaseEmployerName()));
			mapOutputKey.setSecond(new Text(parser.getCaseSubmitYear()));
			mapOutputKey.setThird(new Text(parser.getStatus()));
		
			mapOutputValue.setFirst(new LongWritable(1));
		
			if(mapOutputKey.getThird().equals( new Text("CERTIFIED-WITHDRAWN")) || mapOutputKey.getThird().equals( new Text("WITHDRAWN")) ||
				mapOutputKey.getThird().equals( new Text("DENIED")) || mapOutputKey.getThird().equals( new Text("CERTIFIED-DENIED"))) {
					mapOutputValue.setSecond(new LongWritable(1));
			}
			else
			{
				mapOutputValue.setSecond(new LongWritable(0));
			}
			context.write(mapOutputKey, mapOutputValue);	
		}

}
