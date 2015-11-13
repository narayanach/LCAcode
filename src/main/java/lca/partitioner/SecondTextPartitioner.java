package lca.partitioner;


import org.apache.hadoop.mapreduce.Partitioner;

import lca.keyvalues.LongPair;
import lca.keyvalues.TextPair;


	public class SecondTextPartitioner extends Partitioner<TextPair, LongPair>{

		@Override
		public int getPartition(TextPair key, LongPair value, int numPartitions) {
		
			int partitionValue = 0;

			partitionValue = new Integer(key.getSecond().toString()).intValue() % numPartitions; 
			return partitionValue;
	
		}

	}