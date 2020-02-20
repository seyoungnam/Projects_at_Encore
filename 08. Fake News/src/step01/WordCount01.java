/* HDFS�� �����͸� Ȱ�� ������ ���α׷��� ���� ���
 * 1. ���� ���� : hdfs�� ����� ������ ���� ��
 * 2. �⺻ ����
 * 		1. ���� �޴� API ���
 * 		2. �ʰ� ���ེ�� ������� ���� ����
 * 3. �⺻ ����
 * 		1. map : �����ڰ� �ڵ�� ������ �����ڿ� �°� �ܼ� ������ ����
 * 		2. reduce : ���ص� �ܾ��� �󵵼� ����(����)
 * 4. �ϵ� ���� �������� ����� �ʿ��� ��ü
 * 		1. map ��� : TokenizerMapper
 * 		2. reduce ��� : IntSumReducer
 * 		3. ������ �ٿ��� ���� �� ���� : args[0], ������ ������ ���� : args[1]
 * 			: ����� �������� ��ġ�� ���� ������ ������ main�� argument ���
 * 			: ���ǻ��� - �������� hdfs�� �������ؾ߸� ��
 * 		4. ���� �ʼ� - main()
 * 
 * 5. ���� ����� ���� ���
 * 	- �������� _SUCCESS
 * 
 * 6. hdfs ��� ��ɾ�
 * 	1. ���丮 Ȯ�� ��ɾ� : hdfs dfs -ls /
 * 	2. ���丮 ���� ��ɾ� : hdfs dfs -mkdir /�������丮��
 * 	3. ���丮 ���� ��ɾ� : hdfs dfs -rm -r /���丮��
 * 	4. ���丮 ���� ��ġ ���� ���� : hdfs dfs -mv /����������ġ�����ϸ� /�̵���Ű�����ϴ���ġ
 * 	5. ������ hdfs�� ����(����)�ϴ� ��ɾ� : hdfs dfs -put �����ϰ����ϴ� ���� /������ġ
 * 	6. ������ ȹ��(��������) ��ɾ� : hdfs dfs -get /�����������ϴ�������ġ�����ϸ� �����ҷ�����ġ
 * 	7. ������ ���� Ȯ�� ��ɾ� : hdfs dfs -cat /�������ϴ�������ġ�����ϸ�
 * 	8. 
 */


package step01;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount01 {

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1); //���ؽ� ���ص� ������ ���� ���� ���� �����Ű�� ��ġ ������ Ÿ�԰� ��ġ��
		private Text word = new Text();

		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			//���� ������ ���еǾ� ���۵� value�� ���ڿ��� ������ �������� ����
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) { //�� ���ο� ��� �ܾ �ִ����� ���� �Ҹ�Ȯ, ���� �ݺ����� while
				word.set(itr.nextToken()); //���е� �ܾ hadoop���� ó�� ������ Text Ÿ������ ��ȯ
				context.write(word, one); //(�ܾ�, 1)
			}
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
		job.setJarByClass(WordCount01.class);
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