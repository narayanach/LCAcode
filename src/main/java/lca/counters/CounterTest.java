package lca.counters;



import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Cluster;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CounterTest extends Configured implements Tool {

	public int run(String[] arg0) throws Exception {
		
		String jobID       = arg0[0];
		String groupName   = arg0[1];
		String counterName = arg0[2];
		
		Cluster cluster = new Cluster(getConf());
		Job job = cluster.getJob(JobID.forName(jobID));
		
		Counters counters = job.getCounters();
		System.out.println(counters.findCounter(groupName,counterName).getValue());
		
		return 0;
	}

	
	public static void main(String[] args) throws Exception {
		
		System.exit(ToolRunner.run(new CounterTest (), args));
	}
}

