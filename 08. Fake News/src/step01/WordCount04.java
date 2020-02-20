package step01;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount04 {

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		private Text word = new Text();
		private static final List<String> FAKE = Arrays.asList(new String[]{"�͸�","������","���ɼ�","����","��������","����","�����","�����̴�","�ؼ��ȴ�","���̴�"});

		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			
				String[] unitSet = value.toString().split("\",\"");
				if(unitSet.length < 15 ) {
					return;
				}
					word.set(unitSet[2].replace("\"", ""));
					
					String[] words = unitSet[14].replace("\"", "").split(",");
					int freq = 0;
					for ( String w : words ) {
						if(FAKE.contains(w)) {
							freq++;
						}
					}
					context.write(word, new IntWritable(freq));
		}	
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		//
		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get(); //(key, 1) (key, 1)
			}
			result.set(sum);
			context.write(key, result);	
		}
	}

	public static void main(String[] args) throws Exception {
		//���� ���� ���� ��ü
		Configuration conf = new Configuration();
		
		//���� ó�� ��� : job
		//job�� ���� ������ ������ �̸� �ο��ϴ� ����
		Job job = Job.getInstance(conf, "word count");
		
		// job�� ��� : map + reduce
		job.setJarByClass(WordCount04.class);
		job.setMapperClass(TokenizerMapper.class);
		//job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		
		//�����ϰ��� �ϴ� ������ ����
		//IntWritable : ī���� ������ ���� ��ġ�� ǥ���ϴ� Ÿ��
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//������ ���� ����
		FileInputFormat.addInputPath(job, new Path(args[0]));
		//map & reduce ���� ����� ����ϴ� ������
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//�ڹ� ���� ȯ�� ���� �����ϴ� API
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}