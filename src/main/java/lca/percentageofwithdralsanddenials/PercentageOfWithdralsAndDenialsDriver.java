package lca.percentageofwithdralsanddenials;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import lca.keyvalues.LongPair;
import lca.keyvalues.TextPair;



public class PercentageOfWithdralsAndDenialsDriver extends Configured implements Tool{

	public int run(String[] arg0) throws Exception {
		Configuration conf = getConf();
		
		Job job = Job.getInstance(conf);
		job.setJarByClass(getClass());
	
		FileSystem fs = FileSystem.get(URI.create(arg0[0]), conf);
		Path path = new Path(arg0[0] + arg0[1]); 
		FileStatus[] status = fs.globStatus(path);
		Path[] paths = FileUtil.stat2Paths(status);
		for (Path p : paths) {
			System.out.println(p.toString());
			FileInputFormat.addInputPath(job, p);   
		}
		
		job.setInputFormatClass(TextInputFormat.class);
		
		job.setMapperClass(PercentageOfWithdralsAndDenialsMapper.class);
		job.setMapOutputKeyClass(TextPair.class);
		job.setMapOutputValueClass(LongPair.class);
		
		job.setCombinerClass(PercentageOfWithdralsAndDenialsCombiner.class);
		job.setReducerClass(PercentageOfWithdralsAndDenialsReducer.class);
		
		job.setNumReduceTasks(2);
		job.setOutputKeyClass(TextPair.class);
		job.setOutputValueClass(LongPair.class);	
		
		FileOutputFormat.setOutputPath(job, new Path(arg0[2]));
		
		return job.waitForCompletion(true) ? 0 : 1;
	}


		public static void main(String[] args) throws Exception{
			System.exit(ToolRunner.run(new PercentageOfWithdralsAndDenialsDriver(), args));
			
		}
}
